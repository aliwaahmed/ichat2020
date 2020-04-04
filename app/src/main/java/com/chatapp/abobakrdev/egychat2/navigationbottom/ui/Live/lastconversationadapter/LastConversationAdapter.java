package com.chatapp.abobakrdev.egychat2.navigationbottom.ui.Live.lastconversationadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chatapp.abobakrdev.egychat2.Chat.Chat_Activity;
import com.chatapp.abobakrdev.egychat2.Chat.model.Message;
import com.chatapp.abobakrdev.egychat2.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class LastConversationAdapter extends RecyclerView.Adapter<mViewholder> {


    private ArrayList<Message> list;
    private Context context;

    public LastConversationAdapter(ArrayList<Message> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public mViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new mViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lastconversation, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull mViewholder holder, final int position) {
        holder.name.setText(list.get(position).getName());
        holder.msg.setText(list.get(position).getTxt());
        Glide.with(context).load(list.get(position).getImg()).into(holder.img);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Chat_Activity.class);
                intent.putExtra("mail", list.get(position).getSend());
                intent.putExtra("name", list.get(position).getName());
                intent.putExtra("img", list.get(position).getImg());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}


class mViewholder extends RecyclerView.ViewHolder {
    public TextView name, msg;
    public CircleImageView img;

public LinearLayout linearLayout;
    public mViewholder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id._name);
        msg = itemView.findViewById(R.id.textView8);

        img = itemView.findViewById(R.id._img);
        linearLayout=itemView.findViewById(R.id._lin);

    }

}

