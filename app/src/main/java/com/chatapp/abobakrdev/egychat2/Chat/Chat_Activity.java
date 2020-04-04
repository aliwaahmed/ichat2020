package com.chatapp.abobakrdev.egychat2.Chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chatapp.abobakrdev.egychat2.Chat.ChatAdapter.Adapter;
import com.chatapp.abobakrdev.egychat2.Chat.mViewmodel.chatViewmodel;
import com.chatapp.abobakrdev.egychat2.Chat.model.Message;
import com.chatapp.abobakrdev.egychat2.AddNewUser.FirebaseOperation;
import com.chatapp.abobakrdev.egychat2.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class Chat_Activity extends AppCompatActivity {


    private TextView name;
    private CircleImageView img;
    private TextView send;
    String delegate = "hh:mm aaa";
    private chatViewmodel chatViewmodel;
    SharedPreferences sharedPreferences;
    private EditText _txtmsg;
    private RecyclerView recyclerView3;
    private Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private View viewactionbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_);


        name = findViewById(R.id.name);
        img = findViewById(R.id.img3);


        name.setText(getIntent().getExtras().getString("name"));
        Glide.with(getApplicationContext()).load(getIntent().getExtras().getString("img")).into(img);

        chatViewmodel = ViewModelProviders.of(this).get(chatViewmodel.class);
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);

        send = findViewById(R.id.button_chatbox_send);
        _txtmsg = findViewById(R.id.edittext_chatbox);
        recyclerView3 = findViewById(R.id.recyclerView3);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView3.setLayoutManager(linearLayoutManager);


        String name1 = sharedPreferences.getString("name", "-1");
        String mail1 = sharedPreferences.getString("mail", "-1");
        String age1 = sharedPreferences.getString("age", "-1");
        String gender1 = sharedPreferences.getString("gender", "-1");
        String img1 = sharedPreferences.getString("img", "-1");

        FirebaseOperation.getInstance(getApplicationContext()).
                add_To_active_user(sharedPreferences.getString("Gmail", "-1"),
                        String.valueOf(DateFormat.format(delegate, Calendar.getInstance().getTime())),
                        name1, img1, gender1);


        Log.e("mailto:", getIntent().getExtras().getString("mail", "-1"));
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!_txtmsg.getText().toString().isEmpty()) {


                    String timeStamp = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));

                    Message message = new Message();
                    message.setTime(String.valueOf(DateFormat.format(delegate, Calendar.getInstance().getTime())));
                    message.setTxt(_txtmsg.getText().toString());
                    message.setImg(sharedPreferences.getString("img", "-1").toString());
                    message.setType("0");
                    message.setName(sharedPreferences.getString("name", "-1"));
                    message.setSend(sharedPreferences.getString("Gmail", "-1"));

                    // message.setTimeStamp(timeStamp);


                    FirebaseOperation.getInstance(getApplicationContext()).
                            SendMessage(FirebaseOperation.getInstance(getApplicationContext()).
                                            Remove_delemeter(getIntent().getExtras().getString("mail", "-1")),
                                    message);

                    _txtmsg.setText("");
                } else {
                    Snackbar.make(v, "Enter Message", Snackbar.LENGTH_LONG)
                            .setBackgroundTint(getResources().getColor(R.color.appColor))
                            .show();
                }

            }
        });


        Intent intent = new Intent("custom-event-name");
        // You can also include some extra data.
        intent.putExtra("message", FirebaseOperation.getInstance(getApplicationContext()).
                Remove_delemeter(getIntent().getExtras().getString("mail", "-1")));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);


        chatViewmodel.HomeViewModel(FirebaseOperation.getInstance(getApplicationContext()).Remove_delemeter(getIntent().getExtras().getString("mail", "-2")), getApplicationContext())
                .observe(this, new Observer<ArrayList<Message>>() {
                    @Override
                    public void onChanged(ArrayList<Message> messages) {

                        adapter = new Adapter(messages, getApplication());
                        recyclerView3.setAdapter(adapter);


                    }
                });

        new CountDownTimer(1000, 100) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent("custom-event-name");
                // You can also include some extra data.
                intent.putExtra("message", FirebaseOperation.getInstance(getApplicationContext()).
                        Remove_delemeter(getIntent().getExtras().getString("mail", "-1")));
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
            }
        }.start();


    }


    @Override
    protected void onPostResume() {
        Intent intent = new Intent("custom-event-name");
        // You can also include some extra data.
        intent.putExtra("message", FirebaseOperation.getInstance(getApplicationContext()).
                Remove_delemeter(getIntent().getExtras().getString("mail", "-1")));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        super.onPostResume();


    }

    @Override
    protected void onStart() {
        Intent intent = new Intent("custom-event-name");
        // You can also include some extra data.
        intent.putExtra("message", FirebaseOperation.getInstance(getApplicationContext()).
                Remove_delemeter(getIntent().getExtras().getString("mail", "-1")));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        super.onStart();


    }


    @Override
    protected void onResume() {
        Intent intent = new Intent("custom-event-name");
        // You can also include some extra data.
        intent.putExtra("message", FirebaseOperation.getInstance(getApplicationContext()).
                Remove_delemeter(getIntent().getExtras().getString("mail", "-1")));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        super.onResume();


    }


    @Override
    protected void onDestroy() {
        Intent intent = new Intent("custom-event-name");
        // You can also include some extra data.
        intent.putExtra("message", "");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Intent intent = new Intent("custom-event-name");
        // You can also include some extra data.
        intent.putExtra("message", "");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        super.onStop();
    }

    @Override
    protected void onPause() {
        Intent intent = new Intent("custom-event-name");
        // You can also include some extra data.
        intent.putExtra("message", "");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent("custom-event-name");
        // You can also include some extra data.
        intent.putExtra("message", "");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        super.onBackPressed();
    }
}
