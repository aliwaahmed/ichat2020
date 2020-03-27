package com.chatapp.abobakrdev.egychat2.slideradapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.chatapp.abobakrdev.egychat2.R;


public class ScreenSlidePageFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.screen_slide_page, container, false);
        Bundle bundle = this.getArguments();

     //   details.setText(String.valueOf(bundle.getInt("test")));
        return rootView;
    }
}