package com.chatapp.abobakrdev.egychat2.ActiveUser.Chat.ChatAdapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Viewholder>  {



    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class  Viewholder extends RecyclerView.ViewHolder
{

    public Viewholder(@NonNull View itemView) {
        super(itemView);
    }
}
