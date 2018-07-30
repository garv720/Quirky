package com.example.gaurav.mytaskmanager;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.List;

public class AlarmReceiver extends BroadcastReceiver {

    static MediaPlayer mp;

    @Override
    public void onReceive(final Context context, Intent intent) {
        Toast.makeText(context, "Alarm Alarm", Toast.LENGTH_SHORT).show();

        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, StopAlarm.class), PendingIntent.FLAG_UPDATE_CURRENT);

        if (!isInBackground) {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                    .setContentTitle("Alarm")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText("Alarm"))
                    .setAutoCancel(true)
                    .setPriority(0)
                    .setContentIntent(contentIntent)
                    .setContentText(CreateAlarm.task);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notificationBuilder.build());
        }
        else {
                MainActivity dialog = new MainActivity();
                dialog.createDailog();
        }

        mp = new MediaPlayer().create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mp.start();
        mp.setLooping(true);
    }
}
