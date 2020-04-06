package com.chatapp.abobakrdev.egychat2.navigationbottom.ui.Live;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatapp.abobakrdev.egychat2.Chat.model.Message;
import com.chatapp.abobakrdev.egychat2.FCM.MyFirebaseMessagingService;
import com.chatapp.abobakrdev.egychat2.navigationbottom.ui.Live.Adapter.OnlineUserAdapter;
import com.chatapp.abobakrdev.egychat2.navigationbottom.ui.Live.lastconversationadapter.LastConversationAdapter;
import com.chatapp.abobakrdev.egychat2.navigationbottom.ui.Live.lastconversationadapter.last_conversation_viewmodel;
import com.chatapp.abobakrdev.egychat2.navigationbottom.ui.Live.model.model;
import com.chatapp.abobakrdev.egychat2.R;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private OnlineUserAdapter modelmodelArrayList;

    private RecyclerView recyclerViewlastconversation;
    private LinearLayoutManager linearLayoutManagerlastconversation;
    private last_conversation_viewmodel mLast_conversation_viewmodel;


    LastConversationAdapter lastconversationAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);


        SharedPreferences.Editor sharedPreferenceseditor;

        sharedPreferenceseditor = getContext().
                getSharedPreferences("currentuser", Context.MODE_PRIVATE).edit();

        sharedPreferenceseditor.putString("currentuser","empty");
        sharedPreferenceseditor.apply();
        sharedPreferenceseditor.commit();



        mLast_conversation_viewmodel=ViewModelProviders.of(this).get(last_conversation_viewmodel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerViewlastconversation = root.findViewById(R.id.Rec_last_conversation);
        linearLayoutManagerlastconversation = new LinearLayoutManager(getActivity());
        recyclerViewlastconversation.setLayoutManager(linearLayoutManagerlastconversation);

        mLast_conversation_viewmodel.lastmessages(getContext()).observe(getActivity(), new Observer<ArrayList<Message>>() {
            @Override
            public void onChanged(ArrayList<Message> messages) {
                lastconversationAdapter=new LastConversationAdapter(messages,getContext());
                recyclerViewlastconversation.setAdapter(lastconversationAdapter);

            }
        });

        homeViewModel.HomeViewModel().observe(getActivity(), new Observer<HashMap<String, model>>() {
            @Override
            public void onChanged(HashMap<String, model> stringmodelHashMap) {
                modelmodelArrayList = new OnlineUserAdapter(getContext(), stringmodelHashMap);
                recyclerView.setAdapter(modelmodelArrayList);

            }
        });

        return root;
    }

    @Override
    public void onResume() {
        SharedPreferences.Editor sharedPreferenceseditor;

        sharedPreferenceseditor = getContext().
                getSharedPreferences("currentuser", Context.MODE_PRIVATE).edit();

        sharedPreferenceseditor.putString("currentuser","empty");
        sharedPreferenceseditor.apply();
        sharedPreferenceseditor.commit();


        super.onResume();
    }
}