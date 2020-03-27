package com.chatapp.abobakrdev.egychat2.ActiveUser.ui.Live;

import android.util.Log;

import com.chatapp.abobakrdev.egychat2.ActiveUser.ui.Live.model.model;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {


    private MutableLiveData<HashMap<String, model>> mText;
    private com.chatapp.abobakrdev.egychat2.ActiveUser.ui.Live.model.model model;
    private HashMap<String, model> models;

    public LiveData<HashMap<String, model>> HomeViewModel() {
        if (mText == null) {
            mText = new MutableLiveData<>();
            models = new HashMap<>();
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

                if(dataSnapshot.exists()) {
                    Log.e("data", dataSnapshot.getKey().toString());
                    model = dataSnapshot.getValue(model.class);
                    Log.e("name", model.getName());
                    Log.e("mail", model.getMail());
                    Log.e("gender", model.getGender());
                    Log.e("img", model.getImg());
                    Log.e("time", model.getTime_enter());
                    models.put(model.getMail().toString(), model);
                    mText.postValue(models);
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                model model1 = dataSnapshot.getValue(model.class);
                Log.e("removename", model1.getName());
                Log.e("removemail", model1.getMail());
                Log.e("removegender", model1.getGender());
                Log.e("removeimg", model1.getImg());
                Log.e("removetime", model1.getTime_enter());
                models.remove(model1.getMail());
                mText.postValue(models);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.e("datare", dataSnapshot.getKey().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}