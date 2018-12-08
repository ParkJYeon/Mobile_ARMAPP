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
        db.execSQL("CREATE TABLE TIME_TABLE (_id integer PRIMARY KEY autoincrement, hour integer, minute integer, onoff integer, know text, week integer)");
        db.execSQL("INSERT INTO TIME_TABLE VALUES(null," + 9 + "," + 0 + ", " + 1 + ",'"+"null"+"',"+6+");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void timeUpdate(int hour, int minute) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("UPDATE TIME_TABLE SET hour=" + hour + ", minute="+minute+" WHERE _id=" + 1 + ";");

        db.close();
    }

    public void ofUpdate(int onoff) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("UPDATE TIME_TABLE SET onoff="+onoff+" WHERE _id=" + 1 + ";");

        db.close();
    }

    public void  knUpdate(String know) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("UPDATE TIME_TABLE SET know='"+know+"' WHERE _id=" + 1 + ";");

        db.close();
    }

    public void weUpdate(int week) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("UPDATE TIME_TABLE SET know="+week+" WHERE _id=" + 1 + ";");

        db.close();
    }

    public int getHour() {
        SQLiteDatabase db = getReadableDatabase();
        int hour;

        Cursor cursor = db.rawQuery("SELECT * FROM TIME_TABLE ", null);
        cursor.moveToFirst();
        hour = cursor.getInt(1);
        return hour;
    }

    public int getMinute() {
        SQLiteDatabase db = getReadableDatabase();
        int minute;

        Cursor cursor = db.rawQuery("SELECT * FROM TIME_TABLE ", null);
        cursor.moveToFirst();
        minute = cursor.getInt(2);
        return minute;
    }

    public int getOnoff() {
        SQLiteDatabase db = getReadableDatabase();
        int onoff;

        Cursor cursor = db.rawQuery("SELECT * FROM TIME_TABLE ", null);
        cursor.moveToFirst();
        onoff = cursor.getInt(3);
        return onoff;
    }

    public String getKnow() {
        SQLiteDatabase db = getReadableDatabase();
        String know;

        Cursor cursor = db.rawQuery("SELECT * FROM TIME_TABLE ", null);
        cursor.moveToFirst();
        know = cursor.getString(4);
        return know;
    }

    public int getWeek() {
        SQLiteDatabase db = getReadableDatabase();
        int week;

        Cursor cursor = db.rawQuery("SELECT * FROM TIME_TABLE ", null);
        cursor.moveToFirst();
        week = cursor.getInt(5);
        return week;
    }
}
