package com.example.gaurav.mytaskmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Alarm> alarmList = new ArrayList<>();
    private MyAdapter mAdapter;
    private FloatingActionButton createAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        RecyclerView rview = (RecyclerView) findViewById(R.id.alarmLists);

        mAdapter = new MyAdapter(alarmList);
        rview.setLayoutManager(new LinearLayoutManager(this));
        rview.setAdapter(mAdapter);

        prepareAlarm();

        init();
    }

    private void init() {
        createAlarm = (FloatingActionButton) findViewById(R.id.createTask);

        createAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAlarm.class);
                startActivity(intent);
            }
        });
    }

    private void prepareAlarm() {

        Alarm alarm = new Alarm("Wake up!", "02:23 am");
        alarmList.add(alarm);

        alarm = new Alarm("Wake up!", "02:23 pm");
        alarmList.add(alarm);

        alarm = new Alarm("Wake up!", "06:32 am");
        alarmList.add(alarm);

        alarm = new Alarm("Wake up!", "12:13 pm");
        alarmList.add(alarm);

        mAdapter.notifyDataSetChanged();
    }

    public void createDailog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder
                .setTitle("Alarm")
                .setMessage(CreateAlarm.task)
                .setPositiveButton("GoTo Alarm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(MainActivity.this, StopAlarm.class);
                        startActivity(i);
                        finish();
                    }
                })
                .setNegativeButton("Stop", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlarmReceiver.mp.stop();
                        finish();
                    }
                })
                .setIcon(R.drawable.alarm_icon)
                .show();
    }
}