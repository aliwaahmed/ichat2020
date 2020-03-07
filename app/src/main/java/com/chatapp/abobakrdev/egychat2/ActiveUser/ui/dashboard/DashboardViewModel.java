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
import androidx.annotation.Nullable;
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
        DatabaseReference myRef1=   myRef  .child(String.valueOf(Day) + ":" + String.valueOf(Month+1));

        if(myRef1!=null) {


            myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Object> stringObjectMap = (Map<String, Object>) dataSnapshot.getValue();
                //iterate through each user, ignoring their UID
                if(stringObjectMap!=null) {
                    Log.e("sss+", stringObjectMap.toString());
                    for (Map.Entry<String, Object> entry : stringObjectMap.entrySet()) {
                        Map singleUser = (Map) entry.getValue();
                        if(singleUser.size()>5) {
                            post = new Post();
                            post.setColor(singleUser.get("color").toString());
                            post.setText(singleUser.get("txt").toString());
                            post.setMail(singleUser.get("mail").toString());
                            post.setName(singleUser.get("name").toString());
                            post.setImg(singleUser.get("img").toString());
                            post.setDate(singleUser.get("date").toString());
                            arrayList.add(post);
                        }

                    }
                    list.postValue(arrayList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    }
}