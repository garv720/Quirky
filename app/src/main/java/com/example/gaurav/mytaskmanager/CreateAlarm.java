package com.example.gaurav.mytaskmanager;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateAlarm extends AppCompatActivity {
    private ImageView timeIcon;
    private ImageView dateIcon;
    private TextView time;
    private TextView date;
    private Button create;
    private PendingIntent pendingIntent;
    private EditText taskName;
    public static String task;

    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        init();
    }

    private void init() {
        timeIcon = (ImageView)findViewById(R.id.timePicker);
        dateIcon = (ImageView)findViewById(R.id.datePicker);
        time = (TextView)findViewById(R.id.setTime);
        date = (TextView)findViewById(R.id.setDate);
        taskName = (EditText)findViewById(R.id.createTaskName);
        create = (Button)findViewById(R.id.create);

        final Calendar calendar = Calendar.getInstance();

        dateIcon.setOnClickListener(new View.OnClickListener() {
            public DatePickerDialog datePicker;
            @Override
            public void onClick(View v) {
                    datePicker = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            date.setText(dayOfMonth+"/"+month+"/"+year);
                        }
                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePicker.getDatePicker().setMinDate(calendar.get(Calendar.YEAR));
                    datePicker.show();
                }
        });

        timeIcon.setOnClickListener(new View.OnClickListener() {
            public TimePickerDialog timePicker;

            @Override
            public void onClick(View v) {
                timePicker = new TimePickerDialog(CreateAlarm.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)
                                ,hourOfDay,minute,0);
                        if (hourOfDay >= 12 ){
                            if (hourOfDay == 12)
                                time.setText(12+":"+minute+" PM");
                            else
                                time.setText(hourOfDay-12+":"+minute+" PM");
                        }
                        else {
                            if (hourOfDay == 0)
                                time.setText(12+":"+minute+" AM");
                            else
                            time.setText(hourOfDay+":"+minute+" AM");
                        }
                    }
                },calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
                timePicker.show();
            }
        });

        Intent intent = new Intent(CreateAlarm.this,AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = String.valueOf(taskName.getText());

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                finish();
                Toast.makeText(context, "Task created!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
