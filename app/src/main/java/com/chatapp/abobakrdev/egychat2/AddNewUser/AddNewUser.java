package com.chatapp.abobakrdev.egychat2.AddNewUser;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.chatapp.abobakrdev.egychat2.ActiveUser.ui.dashboard.model.Post;
import com.chatapp.abobakrdev.egychat2.ActiveUser.ui.home.model.model;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class AddNewUser {

    private Context context;
    private SharedPreferences.Editor sharedPreferences;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();


    public AddNewUser(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE).edit();
    }


    /**
     * @param name
     * @param mail
     * @param gender
     * @param age
     * @param phone
     * @param img
     */
    public void add_user(String name, String mail, String gender, String age, String phone, String img) {


        Log.e("aa", Remove_delemeter(mail));
        DatabaseReference myRef = database.getReference("users");
        myRef.child(Remove_delemeter(mail));
        DatabaseReference refmail = myRef.child(Remove_delemeter(mail)).getRef();
        refmail.child("email").setValue(mail);
        refmail.child("age").setValue(age);
        refmail.child("gender").setValue(gender);
        refmail.child("phone").setValue(phone);
        refmail.child("name").setValue(name);
        refmail.child("img").setValue(img);
        refmail.child("about").setValue("");

        sharedPreferences.putString("name", name);
        sharedPreferences.putString("Gmail", mail);
        sharedPreferences.putString("mail", Remove_delemeter(mail));
        sharedPreferences.putString("age", age);
        sharedPreferences.putString("gender", gender);
        sharedPreferences.putString("img", img);
        sharedPreferences.putString("phone", phone);
        sharedPreferences.apply();
        sharedPreferences.commit();
        DatabaseReference subimg = refmail.child("images").getRef().child("img0");
        subimg.setValue("");
        DatabaseReference subimg1 = refmail.child("images").getRef().child("img1");
        subimg1.setValue("");


    }


    public void add_img_profile(String path, String mail) {
        DatabaseReference myRef = database.getReference("users");
        DatabaseReference refmail = myRef.child(Remove_delemeter(mail)).getRef();
        refmail.child("img").setValue(path);

    }


    public void add_img(String path,String mail,String img_Zero_One)
    {
        DatabaseReference myRef = database.getReference("users");
        DatabaseReference refmail = myRef.child(Remove_delemeter(mail)).getRef();
        DatabaseReference imges =refmail.child("images").getRef();
        imges.child(img_Zero_One).setValue(path);

    }

    public String Remove_delemeter(String mail) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mail.length(); i++) {
            if ((mail.charAt(i) != '.') && (mail.charAt(i) != '@') && (mail.charAt(i) != ']') && (mail.charAt(i) != '[')
                    && (mail.charAt(i) != '#') && (mail.charAt(i) != '$') && (mail.charAt(i) != '%')) {
                stringBuilder.append(mail.charAt(i));

            }


        }
        return stringBuilder.toString();
    }

    /**
     * @param mail
     * @param time
     */
    public void add_To_active_user(String mail, String time,String name ,String img,String gender) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        model jsonObject =new model() ;

            jsonObject.setMail(mail);
            jsonObject.setName(name);

            jsonObject.setTime_enter(time);

            jsonObject.setGender(gender);

            jsonObject.setImg(img);



        DatabaseReference myRef = database.getReference("active_user");
        myRef.child(Remove_delemeter(mail))
                .setValue(jsonObject);

    }



    /**
     *
     * @param mail
     * @param data
     */
    public void update_about_me(String mail,String data)
    {
        DatabaseReference myRef = database.getReference("users");
        myRef.child(Remove_delemeter(mail));
        DatabaseReference refmail = myRef.child(Remove_delemeter(mail)).getRef();

        refmail.child("about").setValue(data);

    }


    /**
     *
     * @param name
     * @param mail
     * @param txt
     * @param color
     * @param date
     * @param ref
     */
    public void add_post(String name ,String mail ,String  txt,String color,String date,String ref,String img)
    {

        DatabaseReference myRef = database.getReference("TimeLine").child(ref).push();

        Post post =new Post();
        post.setColor(color);
        post.setName(name);
        post.setColor(color);
        post.setImg(img);
        post.setMail(mail);
        post.setText(txt);
        myRef.setValue(post);




    }










}
