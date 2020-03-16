package com.chatapp.abobakrdev.egychat2.ActiveUser.Chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import de.hdodenhof.circleimageview.CircleImageView;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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


    private TextView name ;
    private CircleImageView  img;
    private ImageView send ;
    private AddNewUser addNewUser ;
    String delegate = "hh:mm aaa";
    private chatViewmodel  chatViewmodel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_);
        chatViewmodel= ViewModelProviders.of(this).get(chatViewmodel.class);
        name=findViewById(R.id.textView6);
        img=findViewById(R.id.circleImageView2);
        send =findViewById(R.id.Send_msg);
        name.setText(getIntent().getExtras().getString("name"));
        Glide.with(getApplicationContext()).load(getIntent().getExtras().getString("img")).into(img);
        addNewUser=new AddNewUser(getApplicationContext());
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timeStamp = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));

                Message message =new Message();
                message.setTime(String.valueOf(DateFormat.format(delegate, Calendar.getInstance().getTime())));
                message.setTxt("dsf");
                message.setType("0");
                message.setSend("0");
                message.setTimeStamp(timeStamp);


                addNewUser.SendMessage(getIntent().getExtras().getString("mail","-2"),
                       message);




            }
        });

        chatViewmodel.HomeViewModel(new  AddNewUser(getApplicationContext()).Remove_delemeter(getIntent().getExtras().getString("mail","-2")),getApplicationContext())
                .observe(this, new Observer<ArrayList<Message>>() {
            @Override
            public void onChanged(ArrayList<Message> messages) {
                Log.e("msg",messages.toString());

            }
        });


    }
}
