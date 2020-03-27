package com.chatapp.abobakrdev.egychat2.ActiveUser.ui.TimeLine;

import android.util.Log;

import com.chatapp.abobakrdev.egychat2.ActiveUser.ui.TimeLine.model.Post;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Post>> list;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private com.chatapp.abobakrdev.egychat2.ActiveUser.ui.TimeLine.model.Post post;
    private ArrayList<Post> arrayList;


    public LiveData<ArrayList<Post>> getlist() {
        if (list == null) {
            list = new MutableLiveData<>();
            arrayList = new ArrayList<>();
            loaddata();
            return list;
        }
        return list;
    }
    public LiveData<ArrayList<Post>> getlist1() {

            list = new MutableLiveData<>();
            arrayList = new ArrayList<>();
            loaddata();


        return list;
    }


    private void loaddata() {

        int Month = Calendar.getInstance().get(Calendar.MONTH);
        int Day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        Log.e("rw", String.valueOf(Month));
        Log.e("rw", String.valueOf(Day));

        DatabaseReference myRef = database.getReference("TimeLine");
        DatabaseReference myRef1 = myRef.child(String.valueOf(Day) + ":" + String.valueOf(Month + 1));

        if (myRef1 != null) {



            myRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                        post = childSnapshot.getValue(Post.class);
                        arrayList.add(post);
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