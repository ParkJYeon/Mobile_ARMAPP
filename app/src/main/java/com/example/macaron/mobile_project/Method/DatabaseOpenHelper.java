package com.example.macaron.mobile_project.Method;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE = "setting.db";
    public static final String TIME_TABLE = "time";
    public static final int VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS TIME_TABEL");
        db.execSQL("CREATE TABLE TIME_TABLE (_id integer PRIMARY KEY autoincrement, hour integer, minute integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void update(int hour, int minute) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정

        Cursor cursor = db.rawQuery("SELECT * FROM TIME_TABLE", null);
        cursor.moveToFirst();

        if(cursor.getCount()>0){
            db.execSQL("UPDATE TIME_TABLE SET hour="+hour+", minute="+minute+" WHERE _id = '1';");
            //오류! no such column
        }else{
            db.execSQL("INSERT INTO TIME_TABLE VALUES(null,'" + hour + "','"+minute+"');");
        }
        db.close();
    }

    public int getHour() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        int hour;

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM TIME_TABLE ", null);
        cursor.moveToFirst();
        hour = cursor.getInt(1);
        return hour;
    }

    public int getMinute() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        int minute;

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM TIME_TABLE ", null);
        cursor.moveToFirst();
        minute = cursor.getInt(2);
        return minute;
    }


}
