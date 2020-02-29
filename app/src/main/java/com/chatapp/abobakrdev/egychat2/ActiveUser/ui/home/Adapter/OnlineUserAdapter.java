package com.chatapp.abobakrdev.egychat2.ActiveUser.ui.home.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OnlineUserAdapter  extends RecyclerView.Adapter<viewholder> {




    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}


class viewholder extends RecyclerView.ViewHolder
{

    public viewholder(@NonNull View itemView) {
        super(itemView);
    }
}