package com.example.gaurav.mytaskmanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    static MediaPlayer mp;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm Alarm", Toast.LENGTH_SHORT).show();

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, StopAlarm.class), PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setContentTitle("Alarm")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Alarm"))
                .setAutoCancel(true)
                .setPriority(0)
                .setContentIntent(contentIntent)
                .setContentText(CreateAlarm.task);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());

        mp = new MediaPlayer().create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mp.start();
        mp.setLooping(true);
    }
}
