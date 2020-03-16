package com.chatapp.abobakrdev.egychat2.AddNewUser;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.chatapp.abobakrdev.egychat2.ActiveUser.Chat.model.Message;
import com.chatapp.abobakrdev.egychat2.ActiveUser.ui.dashboard.model.Post;
import com.chatapp.abobakrdev.egychat2.ActiveUser.ui.home.model.model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;

public class AddNewUser {

    private static Context context;
    private static SharedPreferences.Editor sharedPreferences;
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();

    private static SharedPreferences sharedPreferences1;
    private static HashMap<String, Integer> map;

    private static AddNewUser addNewUser;

    public static AddNewUser getInstance(Context context1) {

        if (addNewUser == null) {
            addNewUser = new AddNewUser();
            context = context1;
            map = new HashMap<>();
            sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE).edit();
            map.put("A", 1);
            map.put("B", 2);
            map.put("C", 3);
            map.put("D", 4);
            map.put("E", 5);
            map.put("F", 6);
            map.put("G", 7);
            map.put("H", 8);
            map.put("I", 9);
            map.put("J", 10);
            map.put("K", 11);
            map.put("L", 12);
            map.put("M", 13);

            map.put("N", 14);
            map.put("O", 15);
            map.put("P", 16);
            map.put("Q", 17);
            map.put("R", 18);
            map.put("S", 19);
            map.put("T", 20);
            map.put("U", 21);
            map.put("V", 22);
            map.put("W", 23);
            map.put("X", 14);
            map.put("Y", 25);
            map.put("Z", 26);


            map.put("a", 27);
            map.put("b", 28);
            map.put("c", 29);
            map.put("d", 30);
            map.put("e", 31);
            map.put("f", 32);
            map.put("g", 33);
            map.put("h", 34);
            map.put("i", 35);
            map.put("j", 36);
            map.put("k", 37);
            map.put("l", 38);
            map.put("m", 39);
            map.put("n", 40);
            map.put("o", 41);
            map.put("p", 42);
            map.put("q", 43);
            map.put("r", 44);
            map.put("s", 45);
            map.put("t", 46);
            map.put("u", 47);
            map.put("v", 48);
            map.put("w", 49);
            map.put("x", 50);
            map.put("y", 51);
            map.put("z", 52);


            map.put("0", 53);
            map.put("1", 54);
            map.put("2", 55);
            map.put("3", 56);
            map.put("4", 57);
            map.put("5", 58);
            map.put("6", 59);
            map.put("7", 60);
            map.put("8", 61);
            map.put("9", 62);

        }

        return addNewUser;

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


    public void add_img(String path, String mail, String img_Zero_One) {
        DatabaseReference myRef = database.getReference("users");
        DatabaseReference refmail = myRef.child(Remove_delemeter(mail)).getRef();
        DatabaseReference imges = refmail.child("images").getRef();
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
    public void add_To_active_user(String mail, String time, String name, String img, String gender) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        SharedPreferences sharedPreferences;

        sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        model jsonObject = new model();

        jsonObject.setMail(mail);
        jsonObject.setName(name);

        jsonObject.setTime_enter(time);

        jsonObject.setGender(gender);

        jsonObject.setImg(img);

        jsonObject.setHashkey(sharedPreferences.getString("hash", "-1"));


        DatabaseReference myRef = database.getReference("active_user");
        myRef.child(Remove_delemeter(mail))
                .setValue(jsonObject);

    }


    /**
     * @param mail
     * @param data
     */
    public void update_about_me(String mail, String data) {
        DatabaseReference myRef = database.getReference("users");
        myRef.child(Remove_delemeter(mail));
        DatabaseReference refmail = myRef.child(Remove_delemeter(mail)).getRef();

        refmail.child("about").setValue(data);

    }


    /**
     * @param name
     * @param mail
     * @param txt
     * @param color
     * @param date
     * @param ref
     */
    public void add_post(String name, String mail, String txt, String color, String date, String ref, String img) {

        DatabaseReference myRef = database.getReference("TimeLine").child(ref).push();

        Post post = new Post();
        post.setColor(color);
        post.setName(name);
        post.setColor(color);
        post.setImg(img);
        post.setMail(mail);
        post.setText(txt);
        post.setDate(date);
        myRef.setValue(post);


    }

    /***
     * @param mail
     * @param message
     */
    public void SendMessage(String mail, Message message) {


        DatabaseReference myRef;

        myRef = database.getReference("chats")
                .child(Generate_Child(mail)).push();


        myRef.setValue(message);


    }

    public String Generate_Child(String mail) {
        SharedPreferences sharedPreferences;
        sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);

        StringBuilder key = new StringBuilder();
        StringBuilder key1 = new StringBuilder();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < mail.length(); i++) {

            key.append(map.get(String.valueOf(mail.charAt(i))));
        }


        for (int i = 0; i < sharedPreferences.getString("mail", "-1").length(); i++) {
            key1.append(map.get(String.valueOf(sharedPreferences.getString("mail", "-1").charAt(i))));
        }

        if (Double.parseDouble(key.toString()) > Double.parseDouble(key1.toString())) {
            stringBuilder.append(String.valueOf(key) + ":" + String.valueOf(key1));

        } else {
            stringBuilder.append(String.valueOf(key1) + ":" + String.valueOf(key));


        }
        return stringBuilder.toString();

    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


}
