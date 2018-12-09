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
import android.widget.Toast;

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

    String title_knew;
    String thema_knew;
    String table_knew;
    long time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.drawer_home_layout);

        prefs = getSharedPreferences("Pref", MODE_PRIVATE);
        Log.e("Tag", "Pref = " + prefs.getBoolean("isFirst", true));
        checkFirstRun();

        thema_knew = databaseOpenHelper.getThema();
        table_knew = "알림";
        title_knew = databaseOpenHelper.getTitle();

        DBModule dbModule = new DBModule();
        dbModule.openBookmarkDB(this);
        is_Bookmark = dbModule.executeRawQuery_Bookmark(table_knew,thema_knew,title_knew);
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
        textView.setText(databaseOpenHelper.getKnow());

        TextView txtTitle = (TextView)findViewById(R.id.todaytitle);
        txtTitle.setText(databaseOpenHelper.getTitle());

        //푸쉬알람
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(MainActivity.this, BroadcastActivity.class);
        PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), databaseOpenHelper.getHour(), databaseOpenHelper.getMinute(), 0);

        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24 * 60 * 60 * 1000, sender);

    }

    public void click_Favorite(View view){

        ImageView bookmark = (ImageView)findViewById(R.id.bookmark_home);

        DBModule dbModule = new DBModule();
        dbModule.openBookmarkDB(this);
        if(is_Bookmark){
            bookmark.setImageResource(R.drawable.ic_unfavorite);
            dbModule.delete_Bookmark(table_knew, thema_knew, title_knew);
            is_Bookmark = false;
            Toast.makeText(this, "즐겨찾기 삭제 완료", Toast.LENGTH_SHORT).show();
        }else{
            bookmark.setImageResource(R.drawable.ic_favorite);
            dbModule.insert_Bookmark(table_knew, thema_knew,title_knew);
            is_Bookmark = true;
            Toast.makeText(this, "즐겨찾기 추가 완료", Toast.LENGTH_SHORT).show();
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
            if(System.currentTimeMillis() - time >= 1500){
                time = System.currentTimeMillis();
                Toast.makeText(this, "뒤로 버튼을 한 번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
            }else if(System.currentTimeMillis() - time < 1500){
                finish();
            }
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
