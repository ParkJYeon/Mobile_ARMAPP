package com.example.macaron.mobile_project.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macaron.mobile_project.Class.Bookmark;
import com.example.macaron.mobile_project.Method.DBModule;
import com.example.macaron.mobile_project.R;

import java.util.ArrayList;

public class KnewledgeActivity extends FragmentActivity {

    boolean is_Bookmark = false;

    String classi;
    String thema;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_knewledge_thema);

        Intent intent = getIntent();
        classi = intent.getExtras().getString("datatable");
        thema = intent.getExtras().getString("thema");
        title = intent.getExtras().getString("title");
        String content = intent.getExtras().getString("content");

        DBModule dbModule = new DBModule();
        dbModule.openBookmarkDB(this);
        is_Bookmark = dbModule.executeRawQuery_Bookmark(classi,thema, title);

        ImageView bookmark = (ImageView)findViewById(R.id.bookmark_knew);
        if(is_Bookmark){
            bookmark.setImageResource(R.drawable.ic_favorite);
        }else{
            bookmark.setImageResource(R.drawable.ic_unfavorite);
        }

        TextView title_Activity = (TextView)findViewById(R.id.knewledge_Title);
        TextView content_Activity = (TextView)findViewById(R.id.knewledge_Content);

        title_Activity.setText(title);
        content_Activity.setText(content);
    }

    public void click_Favorite(View view){

        DBModule dbModule = new DBModule();
        dbModule.openBookmarkDB(this);

        ImageView bookmark = (ImageView)findViewById(R.id.bookmark_knew);

        if(is_Bookmark){
            bookmark.setImageResource(R.drawable.ic_unfavorite);
            dbModule.delete_Bookmark(classi, thema, title);
            is_Bookmark = false;
            Toast.makeText(this, "즐겨찾기 삭제 완료", Toast.LENGTH_SHORT).show();
        }else{
            bookmark.setImageResource(R.drawable.ic_favorite);
            dbModule.insert_Bookmark(classi, thema, title);
            is_Bookmark = true;
            Toast.makeText(this, "즐겨찾기 추가 완료", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }
}
