package com.example.macaron.mobile_project.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macaron.mobile_project.R;

public class KnewledgeActivity extends FragmentActivity {

    boolean is_Bookmark = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_knewledge_thema);

        ImageView bookmark = (ImageView)findViewById(R.id.bookmark_knew);
        if(is_Bookmark){
            bookmark.setImageResource(R.drawable.ic_favorite);
        }else{
            bookmark.setImageResource(R.drawable.ic_unfavorite);
        }
        TextView title_Activity = (TextView)findViewById(R.id.knewledge_Title);
        TextView content_Activity = (TextView)findViewById(R.id.knewledge_Content);

        Intent intent = getIntent();
        title_Activity.setText(intent.getExtras().getString("title"));
        content_Activity.setText(intent.getExtras().getString("content"));
    }

    public void click_Favorite(View view){

        ImageView bookmark = (ImageView)findViewById(R.id.bookmark_knew);

        if(is_Bookmark){
            bookmark.setImageResource(R.drawable.ic_unfavorite);
            is_Bookmark = false;
        }else{
            bookmark.setImageResource(R.drawable.ic_favorite);
            is_Bookmark = true;
        }

    }


    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }
}
