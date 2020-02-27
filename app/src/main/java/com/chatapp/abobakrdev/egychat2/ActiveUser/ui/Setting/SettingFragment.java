package com.chatapp.abobakrdev.egychat2.ActiveUser.ui.Setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.chatapp.abobakrdev.egychat2.R;

import static android.content.Context.MODE_PRIVATE;

public class SettingFragment extends Fragment {


    private SharedPreferences sharedPreferences;

    private String name;
    private String mail;
    private String age;
    private String gender;
    private String img;
    private String phone,Gmail;
    private ImageView imageView;
    private TextView gander, date, _phone, _mail,_my_name;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.myprofile_layout, container, false);
        gander=root.findViewById(R.id._gender);
        date=root.findViewById(R.id._date);
        _phone =root.findViewById(R.id._phone);
        _mail=root.findViewById(R.id._mail);
        _my_name=root.findViewById(R.id._my_name);




        imageView = root.findViewById(R.id._profile_img);
        sharedPreferences = getActivity().getSharedPreferences("login", MODE_PRIVATE);

        name = sharedPreferences.getString("name", "-1");
        Gmail=sharedPreferences.getString("Gmail", "-1");
        mail = sharedPreferences.getString("mail", "-1");
        age = sharedPreferences.getString("age", "-1");
        gender = sharedPreferences.getString("gender", "-1");
        img = sharedPreferences.getString("img", "-1");
        phone = sharedPreferences.getString("phone", "-1");

        Log.e("age",age);
        _my_name.setText(name);
        date.setText(age);
        gander.setText(gender);
        _phone.setText(phone);
        _mail.setText(Gmail);

        Glide.with(getActivity()).load(img).into(imageView);


        return root;
    }
}