package com.chatapp.abobakrdev.egychat2.CompleteInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.chatapp.abobakrdev.egychat2.AddNewUser.AddNewUser;
import com.chatapp.abobakrdev.egychat2.MainActivity;
import com.chatapp.abobakrdev.egychat2.R;

import java.util.ArrayList;

public class Completeinfo extends AppCompatActivity {
    private AddNewUser addNewUser ;
    private String  name,mail,img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completeinfo);
        addNewUser=new AddNewUser(this);

        name=getIntent().getExtras().getString("name");
        mail=getIntent().getExtras().getString("mail");
        img=getIntent().getExtras().getString("img");
        String[] days = getResources().getStringArray(R.array.day);



    }

    public void next(View v)
    {

        addNewUser.add_user(name,mail,"gender","1/1/1997",
                "phone",img,new ArrayList<String>());

    }
}
