package com.chatapp.abobakrdev.egychat2.navigationbottom.ui.Setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chatapp.abobakrdev.egychat2.R;

public class Viewimage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.scene_view_img);

        Glide.with(this).load(getIntent().getExtras().getString("path"))
                .into((ImageView) findViewById(R.id.imageView4));

    }

    @Override
    public void onBackPressed() {
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );

        super.onBackPressed();

    }
}
