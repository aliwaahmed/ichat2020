package com.chatapp.abobakrdev.egychat2.ActiveUser.ui.dashboard;

import android.content.Context;
import android.util.Log;

import com.chatapp.abobakrdev.egychat2.ActiveUser.ui.dashboard.model.Post;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Post>> list;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private Post post;
    private ArrayList<Post> arrayList;


    public LiveData<ArrayList<Post>> getlist() {
        if (list == null) {
            list = new MutableLiveData<>();
            arrayList=new ArrayList<>();
            loaddata();
            return list;
        }
        return list;
    }

    private void loaddata() {

        int Month = Calendar.getInstance().get(Calendar.MONTH);
        int Day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        Log.e("rw", String.valueOf(Month));
        Log.e("rw", String.valueOf(Day));

        DatabaseReference myRef = database.getReference("TimeLine");
        DatabaseReference myRef1=   myRef  .child(String.valueOf(Day) + ":" + String.valueOf(Month));

        // Read from the database


        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {

                // A new comment has been added, add it to the displayed list


                // ...
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {


                // ...
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {


                // ...
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {


                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        myRef1.addChildEventListener(childEventListener);

        myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String,Object> stringObjectMap =   (Map<String,Object>) dataSnapshot.getValue();
                //iterate through each user, ignoring their UID
                Log.e("sss+",stringObjectMap.toString());
                for (Map.Entry<String, Object> entry : stringObjectMap.entrySet()){

                    //Get user map
                    Map singleUser = (Map) entry.getValue();
                     Log.e("color",singleUser.get("color").toString());

                    //Get phone field and append to listLog.e("color",singleUser.get("color").toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}