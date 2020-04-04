package com.chatapp.abobakrdev.egychat2.Chat.mViewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.chatapp.abobakrdev.egychat2.Chat.model.Message;
import com.chatapp.abobakrdev.egychat2.AddNewUser.FirebaseOperation;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class chatViewmodel extends ViewModel {


    private MutableLiveData<ArrayList<Message>> mText;
    private Message model;
    private ArrayList<Message> models;
    SharedPreferences sharedPreferences;
    private Context context1;


    public LiveData<ArrayList<Message>> HomeViewModel(String mail, Context context) {
        if (mText == null) {
            context1 = context;
            sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);

            mText = new MutableLiveData<>();
            models = new ArrayList<>();
            get_online_User(mail);
            return mText;
        } else {
            return mText;
        }

    }


    private void get_online_User(String mail) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference online_user = firebaseDatabase.getReference("chats")
                .child(FirebaseOperation.getInstance(context1).Generate_Child(mail));

        online_user.orderByValue().limitToLast(100).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Log.e("data10", dataSnapshot.getKey().toString());
                model = dataSnapshot.getValue(Message.class);


                models.add(model);
                mText.postValue(models);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {


            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}