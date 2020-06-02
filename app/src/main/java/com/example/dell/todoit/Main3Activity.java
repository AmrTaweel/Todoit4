package com.example.dell.todoit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

public class Main3Activity extends AppCompatActivity {
private CalendarView Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Date=findViewById(R.id.Date);
        Date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange( @NonNull CalendarView calendarView, int i, int i1, int i2) {
            String Dates=i2+"/"+(i1+1)+"/"+i;
                Intent butDate=new Intent(Main3Activity.this,Main2Activity.class);
                butDate.putExtra("date",Dates);
                startActivity(butDate);
            }
        });
    }
}
