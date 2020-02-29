package com.chatapp.abobakrdev.egychat2.ActiveUser.ui.home;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {




    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }


    public void get_online_User()
    {
        FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();

        DatabaseReference online_user =firebaseDatabase.getReference("active_user");

        // Read from the database
        online_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated


                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    Log.e("a",dsp.getKey().toString());

                    for(DataSnapshot dataSnapshot1 :dsp.getChildren())
                    {
                        Log.e("key",dataSnapshot1.getKey().toString());
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


    }

}