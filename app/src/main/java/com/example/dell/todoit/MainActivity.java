package com.example.dell.todoit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.dell.todoit.managers.PreferencesManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NameAdapter.DeleteCallback {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<NewMission> rMissions = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton delete = findViewById(R.id.delets);
        delete.setVisibility(View.INVISIBLE);
        if(new Gson().fromJson(PreferencesManager.getInstance(MainActivity.this).getString("Name","no"),new TypeToken<ArrayList<NewMission>>(){}.getType())!=null){
            rMissions = new Gson().fromJson(PreferencesManager.getInstance(MainActivity.this).getString("Name","no"),new TypeToken<ArrayList<NewMission>>(){}.getType());
        }
        PreferencesManager.getInstance(this).putString("Names",new Gson().toJson(rMissions));
        Button OpenAddMision =  findViewById(R.id.ToAddButton);
        mRecyclerView =  findViewById(R.id.Recycle);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new NameAdapter(rMissions, this);
        mRecyclerView.setAdapter(mAdapter);
        OpenAddMision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });
    }

    @Override
    public void onLongClicked(int i) {
        rMissions.remove(i);
        mAdapter.notifyDataSetChanged();
        PreferencesManager.getInstance(this).putString("Names",new Gson().toJson(rMissions));
        PreferencesManager.getInstance(this).putString("Name",new Gson().toJson(rMissions));
    }
}
