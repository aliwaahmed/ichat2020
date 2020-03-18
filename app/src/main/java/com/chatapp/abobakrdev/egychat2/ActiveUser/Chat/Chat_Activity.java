package com.chatapp.abobakrdev.egychat2.ActiveUser.Chat;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chatapp.abobakrdev.egychat2.ActiveUser.Chat.ChatAdapter.Adapter;
import com.chatapp.abobakrdev.egychat2.ActiveUser.Chat.mViewmodel.chatViewmodel;
import com.chatapp.abobakrdev.egychat2.ActiveUser.Chat.model.Message;
import com.chatapp.abobakrdev.egychat2.ActiveUser.ui.home.HomeViewModel;
import com.chatapp.abobakrdev.egychat2.AddNewUser.AddNewUser;
import com.chatapp.abobakrdev.egychat2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class Chat_Activity extends AppCompatActivity {


    private TextView name;
    private CircleImageView img;
    private LinearLayout send;
    String delegate = "hh:mm aaa";
    private chatViewmodel chatViewmodel;
    SharedPreferences sharedPreferences;
    private EditText _txtmsg;
    private RecyclerView recyclerView3;
    private Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private View viewactionbar;

    public void LoadActionBar() {

        viewactionbar = getSupportActionBar().getCustomView();
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.chatactionbar);


        name = findViewById(R.id.name);
        img = findViewById(R.id.img);


        name.setText(getIntent().getExtras().getString("name"));
        Glide.with(getApplicationContext()).load(getIntent().getExtras().getString("img")).into(img);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_);

        LoadActionBar();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        chatViewmodel = ViewModelProviders.of(this).get(chatViewmodel.class);
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);

        send = findViewById(R.id.send);
        _txtmsg = findViewById(R.id.msg);
        recyclerView3 = findViewById(R.id.recyclerView3);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView3.setLayoutManager(linearLayoutManager);

        Log.e("mailto:", getIntent().getExtras().getString("mail", "-1"));
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timeStamp = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));

                Message message = new Message();
                message.setTime(String.valueOf(DateFormat.format(delegate, Calendar.getInstance().getTime())));
                message.setTxt(_txtmsg.getText().toString());
                message.setImg(sharedPreferences.getString("img", "-1").toString());
                message.setType("0");
                message.setSend(sharedPreferences.getString("Gmail", "-1"));

                // message.setTimeStamp(timeStamp);


                AddNewUser.getInstance(getApplicationContext()).
                        SendMessage(AddNewUser.getInstance(getApplicationContext()).
                                        Remove_delemeter(getIntent().getExtras().getString("mail", "-1")),
                                message);

                _txtmsg.setText("");


            }
        });

        chatViewmodel.HomeViewModel(AddNewUser.getInstance(getApplicationContext()).Remove_delemeter(getIntent().getExtras().getString("mail", "-2")), getApplicationContext())
                .observe(this, new Observer<ArrayList<Message>>() {
                    @Override
                    public void onChanged(ArrayList<Message> messages) {

                        adapter = new Adapter(messages, getApplication());
                        recyclerView3.setAdapter(adapter);


                    }
                });


    }
}
