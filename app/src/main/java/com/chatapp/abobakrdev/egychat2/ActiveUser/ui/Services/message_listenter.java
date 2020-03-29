package com.chatapp.abobakrdev.egychat2.ActiveUser.ui.Services;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.chatapp.abobakrdev.egychat2.ActiveUser.Chat.Chat_Activity;
import com.chatapp.abobakrdev.egychat2.ActiveUser.home;
import com.chatapp.abobakrdev.egychat2.AddNewUser.FirebaseOperation;
import com.chatapp.abobakrdev.egychat2.FCM.Config;
import com.chatapp.abobakrdev.egychat2.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class message_listenter extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.chatapp.abobakrdev.egychat2.ActiveUser.Chat.Services.action.FOO";
    private static final String ACTION_BAZ = "com.chatapp.abobakrdev.egychat2.ActiveUser.Chat.Services.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.chatapp.abobakrdev.egychat2.ActiveUser.Chat.Services.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.chatapp.abobakrdev.egychat2.ActiveUser.Chat.Services.extra.PARAM2";


    public static String current_user_chat ="";
    public message_listenter() {
        super("message_listenter");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, message_listenter.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, message_listenter.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    String img,txt,mail;
    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(final String param1, String param2) {
        // TODO: Handle action Baz
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference user = firebaseDatabase.getReference("users").child(param1).getRef();

        user.child("chats").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull final DataSnapshot dataSnapshot, @Nullable String s) {





                mail=dataSnapshot.getKey().toString();

                Log.e("mailali:to",mail);

                Target target = null;
                for (final DataSnapshot jobSnapshot: dataSnapshot.getChildren()) {
                    String key = jobSnapshot.getKey().toString();
                    Log.e("key89",mail);
                    Log.e("current",current_user_chat);
                    if (key.equals("txt")) {
                        txt = jobSnapshot.getValue(String.class);

                    }

                    if (key.equals("img")) {
                        img = jobSnapshot.getValue(String.class);

                    }

                }
                if(!current_user_chat.equals(FirebaseOperation.getInstance(getApplicationContext()).
                        Remove_delemeter(mail.trim())))
                {

                    target = new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                            sendNotification(bitmap,txt,img,mail);
                        }



                        @Override
                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                            Log.e("errorimg",e.getMessage().toString());

                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    };
                    Picasso.get()
                            .load(img)
                            .into(target);


                    Log.e("imgali",img);



                }


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {





                mail=dataSnapshot.getKey().toString();

                Log.e("mailali:to",mail);

                Target target = null;
                for (final DataSnapshot jobSnapshot: dataSnapshot.getChildren()) {
                    String key = jobSnapshot.getKey().toString();
                   Log.e("key89",mail);
                   Log.e("current",current_user_chat);
                    if (key.equals("txt")) {
                        txt = jobSnapshot.getValue(String.class);

                    }

                    if (key.equals("img")) {
                        img = jobSnapshot.getValue(String.class);

                    }

                }
                if(!current_user_chat.equals(FirebaseOperation.getInstance(getApplicationContext()).
                        Remove_delemeter(mail.trim())))
                {

                    target = new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                            sendNotification(bitmap,txt,img,mail);
                        }



                        @Override
                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                            Log.e("errorimg",e.getMessage().toString());

                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    };
                    Picasso.get()
                            .load(img)
                            .into(target);


                    Log.e("imgali",img);



                }






            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     *
     * @param bitmap
     * @param txt
     * @param img
     * @param mail
     */
    private void sendNotification(Bitmap bitmap,String txt,String img,String mail) {

        Log.e("notificationmail",mail);
        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.bigPicture(bitmap);

        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(getApplicationContext(), Chat_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("mail", mail);
        intent.putExtra("name", mail);
        intent.putExtra("img", img);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "10as1";

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_MAX);

            //Configure Notification Channel
            notificationChannel.setDescription("Game Notifications");
            notificationChannel.enableLights(true);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);

            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat
                .Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(txt)
                .setAutoCancel(true)
                .setStyle(style)
                .setSound(defaultSound)
                .setContentText(Config.content)
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_MAX);


        notificationManager.notify(Integer.MAX_VALUE, notificationBuilder.build());


    }
}
