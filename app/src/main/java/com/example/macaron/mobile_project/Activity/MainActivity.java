package com.example.macaron.mobile_project.Activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macaron.mobile_project.BroadcastActivity;
import com.example.macaron.mobile_project.Method.ChangeModule;
import com.example.macaron.mobile_project.Method.DBModule;
import com.example.macaron.mobile_project.Method.DatabaseOpenHelper;
import com.example.macaron.mobile_project.R;
import com.example.macaron.mobile_project.TodayKnowledgeReceiver;

import java.util.Calendar;


public class MainActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener{

    final DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(MainActivity.this);
    boolean is_Bookmark = false;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.drawer_home_layout);

        prefs = getSharedPreferences("Pref", MODE_PRIVATE);
        Log.e("Tag", "Pref = " + prefs.getBoolean("isFirst", true));
        checkFirstRun();

        final ImageView bookmark = (ImageView)findViewById(R.id.bookmark_home);
        if(is_Bookmark){
            bookmark.setImageResource(R.drawable.ic_favorite);
        }else{
            bookmark.setImageResource(R.drawable.ic_unfavorite);
        }

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView textView = (TextView)findViewById(R.id.today);

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //오늘의 지식
        Intent intent2 = new Intent(MainActivity.this, TodayKnowledgeReceiver.class);
        PendingIntent sender2 = PendingIntent.getBroadcast(MainActivity.this,0,intent2,0);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DATE), 0, 0, 0);

        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(), 24 * 60 * 60 * 1000, sender2);
        textView.setText(databaseOpenHelper.getKnow());

        //푸쉬알람
        Intent intent = new Intent(MainActivity.this, BroadcastActivity.class);
        PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), databaseOpenHelper.getHour(), databaseOpenHelper.getMinute(), 0);

        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24 * 60 * 60 * 1000, sender);

    }

    public void click_Favorite(View view){

        ImageView bookmark = (ImageView)findViewById(R.id.bookmark_home);

        if(is_Bookmark){
            bookmark.setImageResource(R.drawable.ic_unfavorite);
            is_Bookmark = false;
        }else{
            bookmark.setImageResource(R.drawable.ic_favorite);
            is_Bookmark = true;
        }

    }

    public void clickMenuHome(View view){
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_home_layout);
        drawer.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        ChangeModule ca = new ChangeModule();
        int id = item.getItemId();
        Activity preActivity = MainActivity.this;
        Class curActivity = MainActivity.class;
        Class nextActivity = null;
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_home_layout);

        if(id == R.id.today_knew){
            nextActivity = MainActivity.class;
        }else if(id == R.id.philosophy){
            nextActivity = PhilosophyActivity.class;
        }else if(id == R.id.humanities){
            nextActivity = HumanitiesActivity.class;
        }else if(id == R.id.history){
            nextActivity = HistoryActivity.class;
        }else if(id == R.id.economy){
            nextActivity = EconomyActivity.class;
        }else if(id == R.id.music){
            nextActivity = MusicActivity.class;
        }else if(id == R.id.bookmark){
            nextActivity = BookMarkActivity.class;
        }else if(id == R.id.settings){
            nextActivity = SettingActivity.class;
        }else if(id == R.id.info){
            nextActivity = InformActivity.class;
        }
        ca.chActi(preActivity, nextActivity, curActivity, drawer);

        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_home_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            /*DBModule dbModule = new DBModule();
            dbModule.closeBookmarkDB();
            dbModule.closeReadDB();*/
            super.onBackPressed();
        }
    }

    public void checkFirstRun(){
        boolean isFirst = prefs.getBoolean("isFirst", true);
        if(isFirst){
            DBModule dbModule = new DBModule();
            dbModule.createDB(this);
            prefs.edit().putBoolean("isFirst", false).apply();
        }
    }

}
