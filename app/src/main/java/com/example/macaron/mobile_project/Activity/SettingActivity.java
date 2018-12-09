package com.example.macaron.mobile_project.Activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.macaron.mobile_project.BroadcastActivity;
import com.example.macaron.mobile_project.Method.ChangeModule;
import com.example.macaron.mobile_project.Method.DatabaseOpenHelper;
import com.example.macaron.mobile_project.R;

import java.util.Calendar;

public class SettingActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener {

    final DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(SettingActivity.this);

    public int hour;
    public int minute;
    public int onoff;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.drawer_setting_layout);

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Boolean isSetting;

        if (databaseOpenHelper.getOnoff()==1)
        {
            isSetting = true;
        }
        else
        {
            isSetting = false;
        }

        Switch onnoff = (Switch)findViewById(R.id.onnoff);
        onnoff.setChecked(isSetting);

        LinearLayout layout_time = (LinearLayout)findViewById(R.id.timesetting);
        if(isSetting)
            layout_time.setVisibility(View.VISIBLE);
        else
            layout_time.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final Switch onnoff = (Switch)findViewById(R.id.onnoff);

        onnoff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LinearLayout layout_time = (LinearLayout)findViewById(R.id.timesetting);
                if(isChecked) {
                    layout_time.setVisibility(View.VISIBLE);
                    onoff = 1;
                }
                else {
                    layout_time.setVisibility(View.INVISIBLE);
                    onoff = 0;
                }

                databaseOpenHelper.ofUpdate(onoff);
            }
        });

        Button btnSetting = (Button)findViewById(R.id.btnsetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePicker timePicker = (TimePicker)findViewById(R.id.timePicker);

                hour = timePicker.getHour();
                minute = timePicker.getMinute();

                databaseOpenHelper.timeUpdate(hour, minute);

                Toast.makeText(SettingActivity.this, databaseOpenHelper.getHour() + "시 " + databaseOpenHelper.getMinute() + "분", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        ChangeModule ca = new ChangeModule();
        int id = item.getItemId();
        Activity preActivity = SettingActivity.this;
        Class curActivity = SettingActivity.class;
        Class nextActivity = null;
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_setting_layout);

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
        finish();
        return false;
    }

    public void clickMenuSetting(View view){
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_setting_layout);
        drawer.openDrawer(GravityCompat.START);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_setting_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}
