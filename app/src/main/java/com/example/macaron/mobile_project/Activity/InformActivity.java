package com.example.macaron.mobile_project.Activity;

import android.app.Activity;
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

import com.example.macaron.mobile_project.Method.ChangeModule;
import com.example.macaron.mobile_project.R;

public class InformActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.drawer_info_layout);

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button btnLisnece = (Button)findViewById(R.id.license);
        btnLisnece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformActivity.this, PopUpActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        ChangeModule ca = new ChangeModule();
        int id = item.getItemId();
        Activity preActivity = InformActivity.this;
        Class curActivity = InformActivity.class;
        Class nextActivity = null;
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_info_layout);

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

    public void clickMenuInfo(View view){
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_info_layout);
        drawer.openDrawer(GravityCompat.START);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_info_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }


}
