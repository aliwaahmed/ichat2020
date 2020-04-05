package com.chatapp.abobakrdev.egychat2.navigationbottom.ui.Live.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chatapp.abobakrdev.egychat2.navigationbottom.ui.Setting.Viewimage;
import com.chatapp.abobakrdev.egychat2.navigationbottom.ui.Live.model.model;
import com.chatapp.abobakrdev.egychat2.Chat.Chat_Activity;
import com.chatapp.abobakrdev.egychat2.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class OnlineUserAdapter extends RecyclerView.Adapter<viewholder> implements RewardedVideoAdListener {


    private Context context;
    private HashMap<String, model> models;
    private Object[] keys;
    private RewardedVideoAd mRewardedVideoAd;


    public OnlineUserAdapter(Context context, HashMap<String, model> models) {
        this.context = context;
        this.models = models;
        Log.e("sizee", String.valueOf(models.size()));
        keys = models.keySet().toArray();
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        mRewardedVideoAd.loadAd("ca-app-pub-9568503552755438/4943626068",
                new AdRequest.Builder().build());
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(LayoutInflater.from(context).inflate(R.layout.active_user_iteam, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, final int position) {


        holder._name.setText(models.get(keys[position].toString()).getName());
        Glide.with(context).load(models.get(keys[position].toString()).getImg()).into(holder._img);

        if (models.get(keys[position].toString()).getGender().equals("Male")) {

            holder._gender.setVisibility(View.INVISIBLE);
        } else if (models.get(keys[position].toString()).getGender().equals("Female")) {
           // holder._gender.setImageResource(R.drawable.female);
            holder._gender.setVisibility(View.INVISIBLE);
        }

        holder._img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }
                Intent intent = new Intent(context, Chat_Activity.class);
                intent.putExtra("mail", models.get(keys[position].toString()).getMail().toString());
                intent.putExtra("name", models.get(keys[position].toString()).getName().toString());
                intent.putExtra("img", models.get(keys[position].toString()).getImg().toString());
                intent.putExtra("token",models.get(keys[position]).getToken());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public void onRewarded(RewardItem reward) {
        Toast.makeText(context, "onRewarded! currency: " + reward.getType() + "  amount: " +
                reward.getAmount(), Toast.LENGTH_SHORT).show();
        // Reward the user.
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(context, "onRewardedVideoAdLeftApplication",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        mRewardedVideoAd.loadAd("ca-app-pub-9568503552755438/4943626068",
                new AdRequest.Builder().build());
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        mRewardedVideoAd.loadAd("ca-app-pub-9568503552755438/4943626068",
                new AdRequest.Builder().build());
    }

    @Override
    public void onRewardedVideoAdLoaded() {
    }

    @Override
    public void onRewardedVideoAdOpened() {
    }

    @Override
    public void onRewardedVideoStarted() {
    }

    @Override
    public void onRewardedVideoCompleted() {
        mRewardedVideoAd.loadAd("ca-app-pub-9568503552755438/4943626068",
                new AdRequest.Builder().build());
    }
}


class viewholder extends RecyclerView.ViewHolder {

    public TextView _name;
    public ImageView _img;
    public ImageView _gender;

    public viewholder(@NonNull View itemView) {
        super(itemView);
        _name = itemView.findViewById(R.id._name);
        _img = itemView.findViewById(R.id._img);
        _gender = itemView.findViewById(R.id._gender);
    }
}