package com.chatapp.abobakrdev.egychat2.FCM;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.chatapp.abobakrdev.egychat2.AddNewUser.FirebaseOperation;
import com.chatapp.abobakrdev.egychat2.Chat.Chat_Activity;
import com.chatapp.abobakrdev.egychat2.login.MainActivity;
import com.chatapp.abobakrdev.egychat2.navigationbottom.home;
import com.chatapp.abobakrdev.egychat2.R;
import com.google.firebase.inappmessaging.display.FirebaseInAppMessagingDisplay;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private BroadcastReceiver mMessageReceiver;
    SharedPreferences sharedPreferences;


    String mail;
    String name;
    String img;
    String token;

    Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                sendNotification(bitmap, mail, name, img, token);

        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            Log.e("errorimg", e.getMessage().toString());


        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        sharedPreferences = getApplicationContext().getSharedPreferences("currentuser", Context.MODE_PRIVATE);


        Log.e("currentuser", sharedPreferences.getString("currentuser", "-1"));

//        mMessageReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                // Get extra data included in the Intent
//
//                String message = intent.getStringExtra("message");
//                currentuser = message.toString().trim();
//                Log.e("broadcast", currentuser);
//            }
//        };
//        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
//                new IntentFilter("custom-event-name"));
//        Log.e("messageali", remoteMessage.getData().toString());


        getImage(remoteMessage);

    }

    private void sendNotification(Bitmap bitmap, String mail, String name, String img, String token) {


        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.bigPicture(bitmap);

        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(getApplicationContext(), Chat_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("mail", mail);
        intent.putExtra("name", name);
        intent.putExtra("img", img);
        intent.putExtra("token", token);

        PendingIntent pendingIntent = PendingIntent.
                getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "10as1";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_MAX);

            //Configure Notification Channel
            notificationChannel.setDescription("Game Notifications");
            notificationChannel.enableLights(true);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);

            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(Config.title)
                .setAutoCancel(true)
                .setSound(defaultSound)
                .setContentText(Config.content)
                //  .setStyle(style)
                .setLargeIcon(bitmap)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_MAX);


        notificationBuilder .setContentIntent(pendingIntent);

        notificationManager.notify(Integer.MAX_VALUE, notificationBuilder.build());


    }

    JSONObject jsonObject;

    private void getImage(final RemoteMessage remoteMessage) {

        Map<String, String> data = remoteMessage.getData();

        try {
            jsonObject = new JSONObject(data.get("data"));
            Log.e("jsonali", jsonObject.toString());
            Config.gameUrl = jsonObject.getString("img");
            Config.imageUrl = jsonObject.getString("img");
            Config.title = jsonObject.getString("title");
            Config.content = jsonObject.getString("content");
            mail = jsonObject.getString("mail");
            name = jsonObject.getString("title");
            img = jsonObject.getString("img");
            token = jsonObject.getString("token");
            Log.e("currentmail", mail);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (!FirebaseOperation.getInstance(getApplicationContext()).
                Remove_delemeter(sharedPreferences.getString("currentuser", "-1").trim()).equals(FirebaseOperation.getInstance(getApplicationContext()).
                Remove_delemeter(mail).trim())) {
            Log.e("imgali", Config.gameUrl);

            //Create thread to fetch image from notification
            if (remoteMessage.getData() != null) {

                Handler uiHandler = new Handler(Looper.getMainLooper());
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // Get image from data Notification
                        Picasso.get()
                                .load(Config.imageUrl)
                                .into(target);
                    }
                });
            }
        }
    }


}