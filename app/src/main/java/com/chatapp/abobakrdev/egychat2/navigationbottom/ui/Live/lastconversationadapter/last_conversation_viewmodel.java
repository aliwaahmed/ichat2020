package com.chatapp.abobakrdev.egychat2.navigationbottom.ui.Live.lastconversationadapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.chatapp.abobakrdev.egychat2.Chat.model.Message;
import com.chatapp.abobakrdev.egychat2.navigationbottom.ui.Live.model.model;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class last_conversation_viewmodel extends ViewModel {

    private MutableLiveData<ArrayList<Message>> mText;
    private Message model;
    private ArrayList<Message> models;
    private Context context ;

    public LiveData<ArrayList<Message>> lastmessages(Context context) {
        if (mText == null) {
            mText = new MutableLiveData<>();
            models = new ArrayList<>();
            this.context=context;
            get_online_User();
            return mText;
        } else {
            return mText;
        }

    }


  private void get_online_User() {
        SharedPreferences sharedPreferences;

        sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
      FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

      DatabaseReference user = firebaseDatabase.getReference("users").child(sharedPreferences.getString("mail","-1"))
              .child("chats").getRef();


      user.addChildEventListener(new ChildEventListener() {
          @Override
          public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
              Log.e("lastconversation", dataSnapshot.getKey().toString());
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
