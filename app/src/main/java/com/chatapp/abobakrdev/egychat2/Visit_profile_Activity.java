package com.chatapp.abobakrdev.egychat2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chatapp.abobakrdev.egychat2.Chat.Chat_Activity;

public class Visit_profile_Activity extends AppCompatActivity {

    LinearLayout info ;
    ImageView sendgif ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_profile_);

        info = findViewById(R.id.info);
        sendgif = findViewById(R.id.send_gif);



        sendgif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(v.getContext()).asGif().load(R.raw.giphy).into(sendgif);


                Thread thread = new Thread(){
                    @Override
                    public void run() {

                        try{

                            sleep(800);
                            Intent i = new Intent(getApplicationContext() , Chat_Activity.class);
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
        });

    }
}
