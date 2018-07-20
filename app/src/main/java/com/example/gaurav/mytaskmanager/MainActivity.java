package com.example.gaurav.mytaskmanager;

import android.app.IntentService;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Alarm> alarmList = new ArrayList<>();
    private MyAdapter mAdapter;
    private FloatingActionButton createAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rview = (RecyclerView)findViewById(R.id.alarmLists);

        mAdapter = new MyAdapter(alarmList);
        rview.setLayoutManager(new LinearLayoutManager(this));
        rview.setAdapter(mAdapter);

        prepareAlarm();

        init();
    }

    private void init() {
        createAlarm = (FloatingActionButton)findViewById(R.id.createTask);

        createAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAlarm.class);
                startActivity(intent);
            }
        });
    }

    private void prepareAlarm() {

        Alarm alarm = new Alarm("Wake up!","02:23 am");
        alarmList.add(alarm);

        alarm = new Alarm("Wake up!","02:23 pm");
        alarmList.add(alarm);


        alarm = new Alarm("Wake up!","06:32 am");
        alarmList.add(alarm);

        alarm = new Alarm("Wake up!","12:13 pm");
        alarmList.add(alarm);

        mAdapter.notifyDataSetChanged();
    }
}
