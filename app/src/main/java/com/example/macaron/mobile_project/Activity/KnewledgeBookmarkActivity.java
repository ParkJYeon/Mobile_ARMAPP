package com.example.macaron.mobile_project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macaron.mobile_project.Method.DBModule;
import com.example.macaron.mobile_project.R;

public class KnewledgeBookmarkActivity extends FragmentActivity {

    String classi;
    String thema;
    String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_knewledge_bookmark);

        Intent intent = getIntent();
        classi = intent.getExtras().getString("datatable");
        thema = intent.getExtras().getString("thema");
        title = intent.getExtras().getString("title");
        String content = intent.getExtras().getString("content");

        TextView tvTitle = (TextView)findViewById(R.id.book_Title);
        TextView tvContent = (TextView)findViewById(R.id.book_Content);

        tvTitle.setText(title);
        tvContent.setText(content);
    }

    public void click_Delete(View view){
        DBModule dbModule = new DBModule();
        dbModule.openBookmarkDB(this);

        dbModule.delete_Bookmark(classi, thema, title);

        Toast.makeText(this, "즐겨찾기 삭제 완료", Toast.LENGTH_SHORT).show();
        finish();
    }
}
