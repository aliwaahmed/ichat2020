package com.chatapp.abobakrdev.egychat2.ActiveUser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.chatapp.abobakrdev.egychat2.AddNewUser.AddNewUser;
import com.chatapp.abobakrdev.egychat2.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class home extends AppCompatActivity {

    private AddNewUser addNewUser ;
    private SharedPreferences sharedPreferences;

    private  String name  ;
    private  String mail  ;
    private  String age   ;
    private  String gender;
    private  String img   ;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

        addNewUser=new AddNewUser(this);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);


         name   = sharedPreferences.getString("name", "-1");
         mail   = sharedPreferences.getString("mail", "-1");
         age    = sharedPreferences.getString("age", "-1");
         gender = sharedPreferences.getString("gender", "-1");
         img    = sharedPreferences.getString("img", "-1");


        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        addNewUser.add_To_active_user(mail,currentTime,name,img,gender);
    }

    @Override
    protected void onPause() {
        super.onPause();
        addNewUser.remove_active_user(mail);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        addNewUser.remove_active_user(mail);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        addNewUser.remove_active_user(mail);

    }

    @Override
    protected void onStop() {
        super.onStop();
        addNewUser.remove_active_user(mail);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
