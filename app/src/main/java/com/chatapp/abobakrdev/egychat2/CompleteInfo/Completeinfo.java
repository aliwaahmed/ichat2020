package com.chatapp.abobakrdev.egychat2.CompleteInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chatapp.abobakrdev.egychat2.ActiveUser.home;
import com.chatapp.abobakrdev.egychat2.AddNewUser.AddNewUser;
import com.chatapp.abobakrdev.egychat2.R;

import java.util.ArrayList;
import java.util.Calendar;

public class Completeinfo extends AppCompatActivity {
    private AddNewUser addNewUser ;
    private String  name,mail,img;
    private RadioGroup radioGroup ;
    private String gender;
    private RadioButton radioButton ;
    private EditText Phone;
    private ImageView _calendar;
    private TextView date;

    private   DatePickerDialog.OnDateSetListener pickerListener;
    private Calendar c;
    int  year;
    int month;
    int day  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completeinfo);
        addNewUser=new AddNewUser(this);
        radioGroup=findViewById(R.id.radioGroup);
        Phone =findViewById(R.id._phone);
        _calendar=findViewById(R.id._calendar);
        date=findViewById(R.id.textView);
        c = Calendar.getInstance();
         year = c.get(Calendar.YEAR);
         month = c.get(Calendar.MONTH);
         day  = c.get(Calendar.DAY_OF_MONTH);

        pickerListener = new DatePickerDialog.OnDateSetListener() {

            // when dialog box is closed, below method will be called.
            @Override
            public void onDateSet(DatePicker view, int selectedYear,
                                  int selectedMonth, int selectedDay) {

                int year  = selectedYear;
                int month = selectedMonth;
                int day   = selectedDay;

                // Show selected date
                date.setText(new StringBuilder().append(month + 1)
                        .append("-").append(day).append("-").append(year)
                        .append(" "));

            }
        };


        _calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                DatePickerDialog dialog =  new DatePickerDialog(Completeinfo.this, pickerListener, year, month,day);


                dialog.show();

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                radioButton=findViewById(checkedId);
                gender=radioButton.getText().toString();
            }
        });


        name=getIntent().getExtras().getString("name");
        mail=getIntent().getExtras().getString("mail");
        img=getIntent().getExtras().getString("img");


        Log.e("img",img);

    }

    public void next(View v)
    {
        if(Phone.getText().toString().isEmpty())
        {
            Phone.setError("Enter Phone Number");
        }
        else
        {
        addNewUser.add_user(name,mail,radioButton.getText().toString(), date.getText().toString(),
                Phone.getText().toString(),img,new ArrayList<String>());


        Intent intent =new Intent(this, home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        this.finish();
        }

    }



}
