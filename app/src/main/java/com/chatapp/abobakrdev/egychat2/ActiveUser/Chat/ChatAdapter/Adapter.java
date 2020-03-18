package com.chatapp.abobakrdev.egychat2.ActiveUser.Chat.ChatAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chatapp.abobakrdev.egychat2.ActiveUser.Chat.model.Message;
import com.chatapp.abobakrdev.egychat2.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends RecyclerView.Adapter<Viewholder>  {


    private ArrayList<Message> messages ;
    private Context context ;
    private SharedPreferences sharedPreferences;


    public Adapter(ArrayList<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;
        sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);

    }

    @Override
    public int getItemViewType(int position) {
        if(messages.get(position).getSend().
                equals(sharedPreferences.getString("Gmail","-1").trim().toString()))
        {
            return 1;
        }
            else
        {
            return 0;
        }
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==1)
        {
            return new Viewholder(LayoutInflater.from(context).inflate(R.layout.my_msgs_layout,parent,false));
        }
        else
        {
            return new Viewholder(LayoutInflater.from(context).inflate(R.layout.recieved_msg,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        if(holder.getItemViewType()==1)
        {
            holder.text_message_body.setText(messages.get(position).getTxt());
            holder.text_message_time.setText(messages.get(position).getTime());

        }
        else
        {
            Glide.with(context).load(messages.get(position).getImg()).into(holder.image_message_profile);
            holder.text_message_body.setText(messages.get(position).getTxt());
            holder.text_message_time.setText(messages.get(position).getTime());
        }

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}

class  Viewholder extends RecyclerView.ViewHolder
{

    public TextView text_message_body,text_message_time;
    public CircleImageView image_message_profile;
    public Viewholder(@NonNull View itemView) {
        super(itemView);
        text_message_body=itemView.findViewById(R.id.text_message_body);
        text_message_time=itemView.findViewById(R.id.text_message_time);
        image_message_profile=itemView.findViewById(R.id.image_message_profile);


    }
}
