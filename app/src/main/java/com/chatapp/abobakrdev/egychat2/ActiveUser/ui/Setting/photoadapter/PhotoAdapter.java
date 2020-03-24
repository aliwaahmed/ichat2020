package com.chatapp.abobakrdev.egychat2.ActiveUser.ui.Setting.photoadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chatapp.abobakrdev.egychat2.R;

public class PhotoAdapter  extends RecyclerView.Adapter<mViewholder>{
    @NonNull
    @Override
    public mViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new mViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.photoiteam,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull mViewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 100;
    }
}
class  mViewholder extends RecyclerView.ViewHolder
{

    public mViewholder(@NonNull View itemView) {
        super(itemView);
    }
}