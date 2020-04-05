package com.chatapp.abobakrdev.egychat2.FCM;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import androidx.annotation.Nullable;

public class services extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Log.e("errorimg",e.getMessage().toString());


            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        Handler uiHandler = new Handler(Looper.getMainLooper());
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                // Get image from data Notification
                Picasso.get()
                        .load("https://www.google.com/logos/doodles/2020/stay-home-save-lives-6753651837108752-s.png")
                        .into(target);
            }
        });

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent intent1 = new Intent(this, AlarmReceiver.class);
        sendBroadcast(intent1);

    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Intent intent1 = new Intent(this, AlarmReceiver.class);
        sendBroadcast(intent1);
    }
}
