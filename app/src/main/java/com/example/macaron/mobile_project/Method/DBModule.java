package com.example.macaron.mobile_project.Method;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.macaron.mobile_project.Class.Bookmark;
import com.example.macaron.mobile_project.Class.BookmarkDBHelper;
import com.example.macaron.mobile_project.Class.ReadDBHelper;

import java.util.ArrayList;

public class DBModule {

    public BookmarkDBHelper bookmarkDBHelper;
    public ReadDBHelper readDBHelper;
    public SQLiteDatabase dbBookmark;
    public SQLiteDatabase dbRead;

    public void createDB(Context context){
        bookmarkDBHelper = new BookmarkDBHelper(context);
        readDBHelper = new ReadDBHelper(context);
        dbBookmark = bookmarkDBHelper.getWritableDatabase();
        dbRead = readDBHelper.getWritableDatabase();
        bookmarkDBHelper.onCreate(dbBookmark);
        readDBHelper.onCreate(dbRead);
    }

    public boolean openBookmarkDB(Context context){
        bookmarkDBHelper = new BookmarkDBHelper(context);
        dbBookmark = bookmarkDBHelper.getWritableDatabase();

        return true;
    }

    public boolean openReadDB(Context context){
        readDBHelper = new ReadDBHelper(context);
        dbRead = readDBHelper.getWritableDatabase();

        return true;
    }

    public String[] executeRawQuery_Bookmark(String classi, String thema){
        Log.e("Tag", "executeRawQuery_Bookmark() start");
        try{

            Cursor cursor = dbBookmark.rawQuery("SELECT title FROM BookmarkDB WHERE class = '" + classi + "' AND thema = '" + thema + "';", null);

            int count = cursor.getCount();
            String[] title = new String[count];

            if(count == 0){
                cursor.close();
                Log.e("Tag", "No data in BookmarkDB");
            }else{
                for(int i = 0 ; i < count ; i++){
                    cursor.moveToNext();
                    title[i] = cursor.getString(0);
                }
                cursor.close();
                Log.e("Tag", "executeRawQuery_Bookmark() end");
                return title;
            }
        }catch (Exception e){
            Log.e("Tag", "Error Select to Bookmark");
        }

        return null;
    }

    public String[] executeRawQuery_BookmarkClass(){
        Log.e("Tag", "executeRawQuery_Bookmark() - Reading Clas");

        try{
            Cursor cursor = dbBookmark.rawQuery("SELECT class FROM BookmarkDB", null);

            int count = cursor.getCount();

            if(count == 0){
                cursor.close();
                Log.e("Tag", "No data in BookmarkDB");
            }else{
                String[] classi = new String[count];
                for(int i = 0 ; i < count ; i++){
                    cursor.moveToNext();
                    classi[i] = cursor.getString(0);
                }

                cursor.close();

                return classi;
            }
        }catch (Exception e){
            Log.e("Tag", "Error in Select BookmarkDB");
        }

        return null;
    }

    public String[] executeRawQuery_BookmarkThema(){
        Log.e("Tag", "executeRawQuery_Bookmark() - Reading Thema");

        try{
            Cursor cursor = dbBookmark.rawQuery("SELECT thema FROM BookmarkDB", null);

            int count = cursor.getCount();

            if(count == 0){
                cursor.close();
                Log.e("Tag", "No data in BookmarkDB");
            }else{
                String[] thema = new String[count];
                for(int i = 0 ; i < count ; i++){
                    cursor.moveToNext();
                    thema[i] = cursor.getString(0);
                }

                cursor.close();

                return thema;
            }
        }catch (Exception e){
            Log.e("Tag", "Error in Select BookmarkDB");
        }

        return null;
    }

    public String[] executeRawQuery_BookmarkTitle(){
        Log.e("Tag", "executeRawQuery_Bookmark() - Reading Title");

        try{
            Cursor cursor = dbBookmark.rawQuery("SELECT title FROM BookmarkDB", null);

            int count = cursor.getCount();

            if(count == 0){
                cursor.close();
                Log.e("Tag", "No data in BookmarkDB");
            }else{
                String[] title = new String[count];
                for(int i = 0 ; i < count ; i++){
                    cursor.moveToNext();
                    title[i] = cursor.getString(0);
                }

                cursor.close();

                return title;
            }
        }catch (Exception e){
            Log.e("Tag", "Error in Select BookmarkDB");
        }

        return null;
    }


    public boolean executeRawQuery_Bookmark(String classi, String thema, String title){
        Log.e("Tag", "executeRawQuery_Bookmark() start");
        try{

            Cursor cursor = dbBookmark.rawQuery("SELECT title FROM BookmarkDB WHERE class = '" + classi + "' AND thema = '" + thema + "' AND title = '" + title +"';", null);

            int count = cursor.getCount();
            if(count == 0){
                cursor.close();
                Log.e("Tag", "No data in BookmarkDB");
            }else{
                cursor.close();
                Log.e("Tag", "executeRawQuery_Bookmark() end");
                return true;
            }
        }catch (Exception e){
            Log.e("Tag", "Error Select to Bookmark");
        }

        return false;
    }

    public boolean executeRawQuery_Bookmark(int i){
        Log.e("Tag", "executeRawQuery_Bookmark(i) start");

        try{
            Cursor cursor = dbBookmark.rawQuery("SELECT * FROM BookmarkDB", null);
            int count = cursor.getCount();

            if(count == 0 ){
                cursor.close();
                Log.e("Tag", "No Data in Bookmark");
            }else {
                cursor.close();
                return true;
            }
        }catch (Exception e){
            Log.e("Tag", "Error in SELECT BookmarkDB");
        }

        return false;
    }

    public String[] executeRawQuery_Bookmark(){
        Log.e("Tag", "executeRawQuery_Bookmark() start");

        try{
            Cursor cursor = dbBookmark.rawQuery("SELECT title FROM BookmarkDB", null);
            int count = cursor.getCount();

            if(count == 0 ){
                cursor.close();
                Log.e("Tag", "No Data in Bookmark");
            }else {
                String[] list = new String[count];
                for(int i = 0 ; i < count ; i++){
                    cursor.moveToNext();
                    list[i] = cursor.getString(0);
                }

                return list;
            }
        }catch (Exception e){
            Log.e("Tag", "Error in SELECT BookmarkDB");
        }

        return null;
    }

    public String[] executeRawQuery_Read(String thema){
        Log.e("Tag", "executeRawQuery_Read() start");
        try{

            Cursor cursor = dbRead.rawQuery("SELECT title FROM ReadDB WHERE thema = '" + thema + "';", null);

            int count = cursor.getCount();
            String[] title = new String[count];

            if(count == 0){
                cursor.close();
                Log.e("Tag", "No data in ReadDB");
            }else{
                for(int i = 0 ; i < count ; i++){
                    cursor.moveToNext();
                    title[i] = cursor.getString(0);
                }
                cursor.close();
                Log.e("Tag", "executeRawQuery_Read() end");
                return title;
            }
        }catch (Exception e){
            Log.e("Tag", "Error Select to Read");
        }

        return null;
    }

    public boolean executeRawQuery_Read(String thema, String title){
        Log.e("Tag", "executeRawQuery_Read() start");
        try{

            Cursor cursor = dbRead.rawQuery("SELECT title FROM ReadDB WHERE thema = '" + thema + "' AND title = '" + title + "';", null);

            int count = cursor.getCount();

            if(count == 0){
                cursor.close();
                Log.e("Tag", "No data in ReadDB");
            }else{
                cursor.close();
                Log.e("Tag", "executeRawQuery_Read() end");
                return true;
            }
        }catch (Exception e){
            Log.e("Tag", "Error Select to Read");
        }

        return false;
    }

    public void insert_Bookmark(String classi, String thema, String title){
        Log.e("Tag", "insert_Bookmark() start");

        try{
            dbBookmark.execSQL("INSERT INTO BookmarkDB (class, thema, title) VALUES('" + classi + "','" + thema + "','" + title +"');" );
            Log.e("Tag", "Success insert to Bookmark");
        }catch (Exception e){
            Log.e("Tag", "Error in insert to Bookmark");
        }
    }

    public void insert_Read(String thema, String title){
        Log.e("Tag", "insert_Read() start");

        try{
            dbRead.execSQL("INSERT INTO ReadDB (thema, title) VALUES('" + thema + "','" + title +"');" );
            Log.e("Tag", "Success insert to Read");
        }catch (Exception e){
            Log.e("Tag", "Error in insert to Read");
        }
    }

    public void delete_Bookmark(String classi, String thema, String title){
        Log.e("Tag", "delete_Bookmark() start");

        try{
            dbBookmark.execSQL("DELETE FROM BookmarkDB WHERE class = '" + classi + "' AND thema = '" + thema + "' AND title = '" + title + "';" );
            Log.e("Tag", "Success delete data");
        }catch (Exception e){
            Log.e("Tag", "Error in delete to Bookmark");
        }
    }

    public void closeBookmarkDB(){
        dbBookmark.close();
    }

    public void closeReadDB(){
        dbRead.close();
    }
}
