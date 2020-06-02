package com.example.dell.todoit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dell.todoit.managers.PreferencesManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.jar.Attributes;

public class Main2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button Back = findViewById(R.id.Back);
        Button Date = findViewById(R.id.Date);
        Button Repeat = findViewById(R.id.Repeat);
        final Button Save = findViewById(R.id.Save);
        final EditText Name = findViewById(R.id.Name);
        final EditText Hours = findViewById(R.id.Hours);
        final EditText Minutes = findViewById(R.id.Minutes);
        final EditText AboutMission = findViewById(R.id.AboutMission);
        final TextView Datess = findViewById(R.id.DateTeset);
        final Spinner AmPm = findViewById(R.id.AMPM);
        final Spinner importance=findViewById(R.id.importance);
        Intent comeDate = getIntent();
        final String Dates=comeDate.getStringExtra("date");
        final ArrayList<NewMission>toMainActivity=new Gson().fromJson(PreferencesManager.getInstance(Main2Activity.this).getString("Names","no"),new TypeToken<ArrayList<NewMission>>(){}.getType());
        Datess.setText(Dates);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.AmPm, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AmPm.setAdapter(adapter);
        final ArrayAdapter<CharSequence> Important = ArrayAdapter.createFromResource(this,
                R.array.impotance, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        importance.setAdapter(Important);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Main2Activity.this,MainActivity.class));//It helps you to return to mission page
            }
        });

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Date=new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(Date);
            }
        });

        Repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name.getText().clear();
                Hours.getText().clear();
                Minutes.getText().clear();
                AboutMission.getText().clear();
                Datess.setText("");
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (Name.getText().toString().isEmpty()) {
                    Name.setError("");
                } else if (Hours.getText().toString().isEmpty()) {
                    Hours.setError("");
                } else if (Minutes.getText().toString().isEmpty()) {
                    Minutes.setError("");
                } else if (AboutMission.getText().toString().isEmpty()) {
                    AboutMission.setError("");
                } else {
                    int h = Integer.parseInt(Hours.getText().toString());//The hours
                    int m = Integer.parseInt(Minutes.getText().toString());//The minutes
                    if (m >= 60) {
                        Minutes.setError("Minutes should be under 60");
                    } else if (h >= 12) {
                        Hours.setError("Hours should be under 12");
                    }else if (Dates==null){
                        Datess.setError("");
                    } else {
                       new Gson().fromJson(PreferencesManager.getInstance(Main2Activity.this).getString("Names","no"),new TypeToken<ArrayList<NewMission>>(){}.getType());
                        toMainActivity . add( new NewMission(Integer.parseInt(PreferencesManager.getInstance(Main2Activity.this).getString("Number","0")) ,"Date" + ":" + Dates, "Name" + ":" + Name.getText().toString(), "Time" + ":" + Hours.getText().toString() + ":" + Minutes.getText().toString() + AmPm.getSelectedItem().toString(), "About Mission" + ":" + AboutMission.getText().toString(), Integer.parseInt(importance.getSelectedItem().toString())));
                        PreferencesManager.getInstance(Main2Activity.this).putString("Name", new Gson().toJson(toMainActivity));
                        startActivity(new Intent(Main2Activity.this, MainActivity.class));


                    }

                }

            }
        });
    }
}
