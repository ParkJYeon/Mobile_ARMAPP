package com.example.macaron.mobile_project.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.macaron.mobile_project.R;
import com.example.macaron.mobile_project.TodayKnowledgeReceiver;

import java.util.Calendar;

public class IntroActivity extends FragmentActivity {

    Handler handler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_intro);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(IntroActivity.this, TodayKnowledgeReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(IntroActivity.this,0,intent,0);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DATE), 0, 0, 0);

        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), 24 * 60 * 60 * 1000, sender);

        handler.postDelayed(r, 5000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(r);
    }
}
