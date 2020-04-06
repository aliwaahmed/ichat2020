package com.chatapp.abobakrdev.egychat2.navigationbottom;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import com.chatapp.abobakrdev.egychat2.FCM.AlarmReceiver;
import com.chatapp.abobakrdev.egychat2.FCM.MyFirebaseMessagingService;
import com.chatapp.abobakrdev.egychat2.FCM.services;
import com.chatapp.abobakrdev.egychat2.navigationbottom.ui.Live.model.model;
import com.chatapp.abobakrdev.egychat2.AddNewUser.FirebaseOperation;
import com.chatapp.abobakrdev.egychat2.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.okhttp.OkHttpClient;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class home extends AppCompatActivity {

    public static final int REQUEST_GOOGLE_PLAY_SERVICES = 1972;
    private SharedPreferences sharedPreferences;

    private String name;
    private String mail;
    private String age;
    private String gender;
    private String img;
    private AdView mAdView;
    private String delegate = "hh:mm aaa";
    private static final int SYSTEM_ALERT_WINDOW_PERMISSION = 2084;
    private static final int RSS_JOB_ID = 1000;
    OkHttpClient mClient;
    JSONArray jsonArray;
    String token;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_home);
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);

//        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(this, AlarmReceiver.class);
//        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
//// setRepeating() lets you specify a precise custom interval--in this case,
//// 20 minutes.
//        Calendar calendar = Calendar.getInstance();
//
//        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                1000 * 30, alarmIntent);
        startRegistrationService();

//        Intent intent1 = new Intent(this, services.class);
//        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
//                Intent.FLAG_ACTIVITY_CLEAR_TOP |
//                Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startService(intent1);

        SharedPreferences.Editor sharedPreferenceseditor;

        sharedPreferenceseditor = getApplicationContext().
                getSharedPreferences("currentuser", Context.MODE_PRIVATE).edit();

        sharedPreferenceseditor.putString("currentuser","empty");
        sharedPreferenceseditor.apply();
        sharedPreferenceseditor.commit();

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);


        name = sharedPreferences.getString("name", "-1");
        mail = sharedPreferences.getString("mail", "-1");
        age = sharedPreferences.getString("age", "-1");
        gender = sharedPreferences.getString("gender", "-1");
        img = sharedPreferences.getString("img", "-1");

        //stopService(new Intent(getApplicationContext(), message_listenter.class));

        //  message_listenter.startActionBaz(getApplicationContext(), mail, "");

        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());


        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(
                new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.e("token1", "getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        token = task.getResult().getToken();

                        String refreshedToken = token;//add your user refresh tokens who are logged in with firebase.

                        jsonArray = new JSONArray();
                        jsonArray.put(refreshedToken);
                        Log.d("token", token);
                    }
                });


        model model = new model();
        model.setName(name);
        model.setToken(sharedPreferences.getString("token", "-1"));
        model.setImg(img);
        model.setMail(sharedPreferences.getString("Gmail", "-1"));
        model.setGender(gender);
        model.setTime_enter(String.valueOf(DateFormat.format(delegate, Calendar.getInstance().getTime())));

        FirebaseOperation.getInstance(getApplicationContext()).
                add_To_active_user(model);




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_GOOGLE_PLAY_SERVICES:
                if (resultCode == Activity.RESULT_OK) {
                    Intent i = new Intent(this, MyFirebaseMessagingService.class);
                    startService(i); // OK, init GCM
                }
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }




    private void startRegistrationService() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int code = api.isGooglePlayServicesAvailable(this);
        if (code == ConnectionResult.SUCCESS) {
            onActivityResult(REQUEST_GOOGLE_PLAY_SERVICES, Activity.RESULT_OK, null);
        } else if (api.isUserResolvableError(code) &&
                api.showErrorDialogFragment(this, code, REQUEST_GOOGLE_PLAY_SERVICES)) {
            // wait for onActivityResult call (see below)
        } else {
            Toast.makeText(this, api.getErrorString(code), Toast.LENGTH_LONG).show();
        }
    }


}

