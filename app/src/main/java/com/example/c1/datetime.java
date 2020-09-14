package com.example.c1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

public class datetime extends AppCompatActivity {

    TextView date;
    Button dateb,proceed1;

    private int nMonth, nDay, nYear, nHour, nMin;

  String fulldate;



        @Override
        protected void onCreate (Bundle savedInstanceState){


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        ListView list1 = findViewById(R.id.list2);
        proceed1 = findViewById(R.id.proceed);


        final ArrayList<String> timeslot = new ArrayList<>();

        timeslot.add("11:00 AM");
        timeslot.add("12:00 AM");
        timeslot.add("1:00 PM");
        timeslot.add("2:00 PM");
        timeslot.add("3:00 PM");
        timeslot.add("4:00 PM");
        timeslot.add("5:00 PM");
        timeslot.add("6:00 PM");
        timeslot.add("7:00 PM");
        timeslot.add("8:00 PM");
        timeslot.add("9:00 PM");
        timeslot.add("10:00 PM");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, timeslot);

        list1.setAdapter(arrayAdapter);


        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                view.setSelected(true);


                Intent intent = new Intent(datetime.this, phone.class);

                startActivity(intent);

            }
        });


        dateb = (Button) findViewById(R.id.dateb);


        date = (TextView) findViewById(R.id.date);


        dateb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handleDate();

            }
        });


        proceed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }



    public void handleDate() {

        Calendar calendar = Calendar.getInstance();

        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        final int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                 fulldate = "DATE : " + dayOfMonth + "/" + (month + 1) + "/" + year;


                //date.setText(fulldate);


            }
        }, YEAR, MONTH, DATE);


        datePickerDialog.show();

        date.setText(fulldate);
    }


}
