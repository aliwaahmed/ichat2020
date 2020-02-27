package com.chatapp.abobakrdev.egychat2.AddNewUser;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddNewUser {

    private Context context;
    private SharedPreferences.Editor sharedPreferences ;

    public AddNewUser(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences("login",Context.MODE_PRIVATE).edit();
    }


    /**
     * @param name
     * @param mail
     * @param gender
     * @param age
     * @param phone
     * @param img
     * @param userimgs
     */
    public void add_user(String name, String mail, String gender, String age, String phone, String img, ArrayList<String> userimgs) {



        Log.e("aa",Remove_delemeter(mail));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        myRef.child(Remove_delemeter(mail));
        DatabaseReference refmail = myRef.child(Remove_delemeter(mail)).getRef();
        refmail.child("email").setValue(name);
        refmail.child("age").setValue(age);
        refmail.child("gender").setValue(gender);
        refmail.child("phone").setValue(phone);
        refmail.child("name").setValue(name);
        refmail.child("img").setValue(img);

        sharedPreferences.putString("name",name);
        sharedPreferences.putString("Gmail",mail);

        sharedPreferences.putString("mail",Remove_delemeter(mail));
        sharedPreferences.putString("age",age);
        sharedPreferences.putString("gender",gender);
        sharedPreferences.putString("img",img);
        sharedPreferences.putString("phone",phone);
        sharedPreferences.apply();

        DatabaseReference subimg = refmail.child("image").getRef();

//        subimg.setValue("img",userimgs.get(0));
//        subimg.setValue("img1",userimgs.get(1));
//        subimg.setValue("img2",userimgs.get(2));
//        subimg.setValue("img3",userimgs.get(3));
//        subimg.setValue("img4",userimgs.get(4));




    }


    public String Remove_delemeter(String mail)
    {
        StringBuilder stringBuilder =new StringBuilder();
        for(int i=0;i<mail.length();i++) {
            if ((mail.charAt(i) != '.' )&& (mail.charAt(i) != '@') && (mail.charAt(i) != ']') &&( mail.charAt(i) != '[')
                    && (mail.charAt(i) != '#') && (mail.charAt(i) != '$') && (mail.charAt(i) != '%'))
            {
                stringBuilder.append(mail.charAt(i));

            }


        }
        return stringBuilder.toString();
    }

    /**
     *
     * @param mail
     * @param time
     */
    public void add_To_active_user(String mail,String time)
    {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("active_user");

        myRef.child(mail).setValue(time);



    }

    public void remove_active_user(String mail)
    {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("active_user");

        myRef.child(mail).removeValue();
    }
}
