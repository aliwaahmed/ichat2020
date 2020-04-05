package com.chatapp.abobakrdev.egychat2.FCM;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        Intent intent1 =new Intent(context,services.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
                Intent.FLAG_ACTIVITY_CLEAR_TOP|
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startService(intent1);
        Toast.makeText(context,"broad",Toast.LENGTH_LONG).show();

    }
}
