package com.example.macaron.mobile_project.Class;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BookmarkDBHelper extends SQLiteOpenHelper{

    public BookmarkDBHelper(Context context){
        super(context, "BookmarkDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("Log", "Create table BookmarkDB");
        try{
            db.execSQL("DROP TABLE IF EXISTS BookmarkDB");
            Log.e("Tag", "Drop Success");
        }catch (Exception e){
            Log.e("Tag", "Error Drop table");
        }

        try{
            db.execSQL("CREATE TABLE BookmarkDB (_id integer PRIMARY KEY autoincrement, class text, thema text, title text);");
            Log.e("Tag", "CREATE Success");
        }catch (Exception e){
            Log.e("Tag", "Error Create table");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
