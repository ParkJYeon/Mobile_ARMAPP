package com.example.macaron.mobile_project;

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
import android.widget.ImageView;
import android.widget.TextView;

public class ThemaActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    TextView textTitle;
    ImageView imgMenu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_thema_layout);
        imgMenu = (ImageView)findViewById(R.id.menu_thema);
        textTitle = (TextView)findViewById(R.id.thema_title);
        drawer = (DrawerLayout)findViewById(R.id.drawer_thema_layout);

        Intent it = getIntent();
        String title = it.getStringExtra("it_tag");
        textTitle.setText(title);


        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void click_menu_thema(View view){
        drawer.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
