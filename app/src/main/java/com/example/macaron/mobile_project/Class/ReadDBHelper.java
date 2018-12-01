package com.example.macaron.mobile_project.Class;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ReadDBHelper extends SQLiteOpenHelper{

    public ReadDBHelper(Context context){
        super(context, "ReadDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("Log", "Create table ReadDB");
        try{
            db.execSQL("DROP TABLE IF EXISTS ReadDB");
            Log.e("Tag", "Drop Success");
        }catch (Exception e){
            Log.e("Tag", "Error Drop table");
        }

        try{
            db.execSQL("CREATE TABLE ReadDB (_id integer PRIMARY KEY autoincrement, thema text, title text);");
            Log.e("Tag", "CREATE Success");
        }catch (Exception e){
            Log.e("Tag", "Error Create table");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
