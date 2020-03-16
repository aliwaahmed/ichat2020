package com.chatapp.abobakrdev.egychat2.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.chatapp.abobakrdev.egychat2.R;
import com.chatapp.abobakrdev.egychat2.login.MainActivity;

public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);


        Thread thread = new Thread(){
            @Override
            public void run() {

                try{

                    sleep(2000);
                    Intent i = new Intent(getApplicationContext() , MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();

                }

                catch (InterruptedException e){
                    e.printStackTrace();

                }
            }
        };
        thread.start();
    }
}
