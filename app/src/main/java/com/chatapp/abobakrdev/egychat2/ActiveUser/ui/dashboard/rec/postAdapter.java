package com.chatapp.abobakrdev.egychat2.ActiveUser.ui.dashboard.rec;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chatapp.abobakrdev.egychat2.ActiveUser.ui.dashboard.model.Post;
import com.chatapp.abobakrdev.egychat2.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class postAdapter extends RecyclerView.Adapter<Viewholder> {
    private Context context;
    private ArrayList<Post> arrayList;

    public postAdapter(Context context, ArrayList<Post> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(context).inflate(R.layout.post_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder holder, final int position) {

        Glide.with(context).load(arrayList.get(position).getImg()).into(holder.circleImageView);

        holder.name.setText(arrayList.get(position).getName());
        holder.txt.setText(arrayList.get(position).getText());
        holder.time.setText(arrayList.get(position).getDate());
        holder.materialCardView.setBackgroundColor(Color.parseColor(arrayList.get(position).getColor()));

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.like.setColorFilter(ContextCompat.getColor(context,
                        R.color.colorAccent), android.graphics.PorterDuff.Mode.MULTIPLY);

            }
        });


        holder.dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.dislike.setColorFilter(ContextCompat.getColor(context,
                        R.color.colorAccent), android.graphics.PorterDuff.Mode.MULTIPLY);
            }
        });
        holder.Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "$" + arrayList.get(position).getName() + "\n"
                        + arrayList.get(position).getText());
                context.startActivity(Intent.createChooser(sharingIntent, "Share "));


            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}

class Viewholder extends RecyclerView.ViewHolder {

    public CircleImageView circleImageView;
    public TextView name, txt, time;
    public MaterialCardView materialCardView;
    public ImageView like, dislike, Share;

    public Viewholder(@NonNull View itemView) {
        super(itemView);
        circleImageView = itemView.findViewById(R.id.circleImageView);
        name = itemView.findViewById(R.id.textView3);
        txt = itemView.findViewById(R.id.textView4);
        time = itemView.findViewById(R.id.textView2);
        materialCardView = itemView.findViewById(R.id.materialCardView);
        like = itemView.findViewById(R.id.imageView6);
        dislike = itemView.findViewById(R.id.imageView7);
        Share = itemView.findViewById(R.id._POST);


    }
}
