package com.example.macaron.mobile_project;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.macaron.mobile_project.Activity.MainActivity;
import com.example.macaron.mobile_project.Class.Knewledge;
import com.example.macaron.mobile_project.Method.DatabaseOpenHelper;
import java.util.ArrayList;
import java.util.Calendar;

public class BroadcastActivity extends BroadcastReceiver {

    public static final CharSequence VERBOSE_NOTIFICATION_CHANNEL_NAME = "Verbose WorkManager Notifications";
    public static String VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION = "Shows notifications whenever work starts";
    public static final CharSequence NOTIFICATION_TITLE = "오늘의 지식";
    public static final String CHANNEL_ID = "VERBOSE_NOTIFICATION";
    public static final int NOTIFICATION_ID = 1;

    DatabaseOpenHelper databaseOpenHelper;
    final ArrayList<Knewledge> knewledges = new ArrayList<>();
    int i = 1;

    @Override
    public void onReceive(final Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        databaseOpenHelper = new DatabaseOpenHelper(context);
        Calendar calendar = Calendar.getInstance();
        int h = calendar.get(Calendar.HOUR);
        int m = calendar.get(Calendar.MINUTE);
        int hour = databaseOpenHelper.getHour();
        int minute = databaseOpenHelper.getMinute();

        if (databaseOpenHelper.getOnoff() == 1) {
                if (m-2<=minute) {
                    makeStatusNotification(databaseOpenHelper.getKnow(), context);
                }
        }
    }


    public  void makeStatusNotification(String message, Context context) {

        // 26레벨 이상이면 채널 만듬
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = VERBOSE_NOTIFICATION_CHANNEL_NAME;
            String description = VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION;
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // 채널을 더한다.
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                new Intent(context,MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

        // 알림 만드는 부분
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_icon)
                .setContentTitle(NOTIFICATION_TITLE)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[3])
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        // 알림 보여주기
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build());
    }
}
