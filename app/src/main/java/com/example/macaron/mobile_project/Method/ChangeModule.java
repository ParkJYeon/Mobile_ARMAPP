package com.example.macaron.mobile_project.Method;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import com.example.macaron.mobile_project.Activity.MainActivity;
import com.example.macaron.mobile_project.R;

public class ChangeModule {

    public void chActi(Activity pre, Class next, Class cur, String activity_Name){
        if (!next.equals(cur)){
            Intent intent = new Intent(pre, next);
            intent.putExtra("title_tag", activity_Name);
            pre.startActivity(intent);
        }
        return;
    }

    public void chActi(Activity pre, Class next, Class cur, DrawerLayout drawer){
        if (!next.equals(cur)){
            Intent intent = new Intent(pre, next);
            pre.startActivity(intent);
            drawer.closeDrawer(GravityCompat.START);
        }
        return;
    }


    public void chActi(Activity pre, Class next, Class cur, String activity_Name, DrawerLayout drawer){
        if (!next.equals(cur)){
            Intent intent = new Intent(pre, next);
            intent.putExtra("title_tag", activity_Name);
            pre.startActivity(intent);
            drawer.closeDrawer(GravityCompat.START);
        }
        return;
    }

}
