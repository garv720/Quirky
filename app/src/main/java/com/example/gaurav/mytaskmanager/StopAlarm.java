package com.example.gaurav.mytaskmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StopAlarm extends AppCompatActivity {
    private TextView msg;
    private Button stop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_alarm);
        init();
    }

    private void init() {
        msg = (TextView)findViewById(R.id.msg);
        stop = (Button)findViewById(R.id.stopalarm);

        msg.setText(CreateAlarm.task);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmReceiver.mp.stop();
                finish();
            }
        });
    }
}
