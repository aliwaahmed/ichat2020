package com.chatapp.abobakrdev.egychat2.ActiveUser.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chatapp.abobakrdev.egychat2.ActiveUser.ui.dashboard.rec.postAdapter;
import com.chatapp.abobakrdev.egychat2.ActiveUser.ui.home.Adapter.OnlineUserAdapter;
import com.chatapp.abobakrdev.egychat2.ActiveUser.ui.home.model.model;
import com.chatapp.abobakrdev.egychat2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    OnlineUserAdapter modelmodelArrayList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);


        homeViewModel.HomeViewModel().observe(getActivity(), new Observer<HashMap<String, model>>() {
            @Override
            public void onChanged(HashMap<String, model> stringmodelHashMap) {
                modelmodelArrayList = new OnlineUserAdapter(getContext(), stringmodelHashMap);
                recyclerView.setAdapter(modelmodelArrayList);

            }
        });

        return root;
    }


}