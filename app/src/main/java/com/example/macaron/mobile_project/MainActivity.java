package com.example.macaron.mobile_project;

import android.content.Intent;
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


public class MainActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    ImageView bookmark;
    ImageView menu;
    boolean is_Bookmark = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_home_layout);
        menu = (ImageView)findViewById(R.id.menu_home);
        drawer = (DrawerLayout)findViewById(R.id.drawer_home_layout);
        bookmark = (ImageView)findViewById(R.id.bookmark_home);
        if(is_Bookmark){
            bookmark.setImageResource(R.drawable.ic_favorite);
        }else{
            bookmark.setImageResource(R.drawable.ic_unfavorite);
        }

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void click_Favorite(View view){
        if(is_Bookmark){
            bookmark.setImageResource(R.drawable.ic_unfavorite);
            is_Bookmark = false;
        }else{
            bookmark.setImageResource(R.drawable.ic_favorite);
            is_Bookmark = true;
        }
    }

    public void click_menu_home(View view){
        drawer.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent it = null;
        if(id == R.id.today_knew){
            Log.e("1", "오늘의 지식");
        }else if(id == R.id.philosophy){
            Log.e("2", "철학");
            it = new Intent(MainActivity.this, ThemaActivity.class);
        }else if(id == R.id.history){
            Log.e("3", "역사");
            it = new Intent(MainActivity.this, ThemaActivity.class);
        }else if(id == R.id.humanities){
            Log.e("4", "인문학");
            it = new Intent(MainActivity.this, ThemaActivity.class);
        }else if(id == R.id.music){
            Log.e("5", "음악");
            it = new Intent(MainActivity.this, ThemaActivity.class);
        }else if(id == R.id.economy){
            Log.e("6", "경제학");
            it = new Intent(MainActivity.this, ThemaActivity.class);
        }else if(id == R.id.bookmark){
            Log.e("7", "즐겨찾기");
        }else if(id == R.id.settings){
            Log.e("8", "설정");
        }else if(id == R.id.info){
            Log.e("9", "앱 정보");
        }

        it.putExtra("it_tag", item.toString());
        startActivity(it);
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_home_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }
}
