package com.chatapp.abobakrdev.egychat2.ActiveUser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;

import com.chatapp.abobakrdev.egychat2.AddNewUser.AddNewUser;
import com.chatapp.abobakrdev.egychat2.DarkMode.InitApplication;
import com.chatapp.abobakrdev.egychat2.R;
import com.chatapp.abobakrdev.egychat2.foregroundservices.notification;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class home extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private String name;
    private String mail;
    private String age;
    private String gender;
    private String img;
    private AdView mAdView;
    private  String delegate = "hh:mm aaa";
    private static final int SYSTEM_ALERT_WINDOW_PERMISSION = 2084;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_home);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
//            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
//                    Uri.parse("package:" + getPackageName()));
//            startActivityForResult(intent, SYSTEM_ALERT_WINDOW_PERMISSION);
//
//        }
//        startService(new Intent(this, notification.class));




        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setVisibility(View.GONE);
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);


        name = sharedPreferences.getString("name", "-1");
        mail = sharedPreferences.getString("mail", "-1");
        age = sharedPreferences.getString("age", "-1");
        gender = sharedPreferences.getString("gender", "-1");
        img = sharedPreferences.getString("img", "-1");


        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());


        AddNewUser.getInstance(getApplicationContext()).
                add_To_active_user(sharedPreferences.getString("Gmail", "-1"),
                        String.valueOf(DateFormat.format(delegate, Calendar.getInstance().getTime())), name, img, gender);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
       // AddNewUser.getInstance(getApplicationContext()).removectiveuser(sharedPreferences.getString("mail","-1"));
    }

    @Override
    protected void onStop() {
        super.onStop();
      //  AddNewUser.getInstance(getApplicationContext()).removectiveuser(sharedPreferences.getString("mail","-1"));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AddNewUser.getInstance(getApplicationContext()).removectiveuser(sharedPreferences.getString("mail","-1"));

    }

    @Override
    protected void onResume() {
        super.onResume();
        AddNewUser.getInstance(getApplicationContext()).
                add_To_active_user(sharedPreferences.getString("Gmail", "-1"),
                        String.valueOf(DateFormat.format(delegate, Calendar.getInstance().getTime())), name, img, gender);


    }
}
