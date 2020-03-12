package com.chatapp.abobakrdev.egychat2.ActiveUser.ui.home;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import com.chatapp.abobakrdev.egychat2.ActiveUser.ui.home.model.model;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {


    private MutableLiveData<String> mText;
    private com.chatapp.abobakrdev.egychat2.ActiveUser.ui.home.model.model model;
    List<model> td;

    public LiveData<String> HomeViewModel() {
        if (mText == null) {
            mText = new MutableLiveData<>();
            get_online_User();
            return mText;
        } else {
            return mText;
        }

    }


    public void get_online_User() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference online_user = firebaseDatabase.getReference("active_user");

        online_user.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Log.e("data",dataSnapshot.getKey().toString());
                model = dataSnapshot.getValue(model.class);
                Log.e("name",model.getName());
                Log.e("mail",model.getMail());
                Log.e("gender",model.getGender());
                Log.e("img",model.getImg());
                Log.e("time",model.getTime_enter());

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.e("datare",dataSnapshot.getKey().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}