package com.deitel.study__scheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by natas on 4/12/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "SchedulerDB.db";
    private static String TABLE_NAME_TASKS = "TASKS";
    private static String NAME = "Name";
    private static String DATE = "Date";
    private static String INFORMATION= "Information";
    private static String ID = "_id";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS TASKS (_id INTEGER PRIMARY KEY AUTOINCREMENT, SUBJECT VARCHAR(255), DATE TEXT, TASK TEXT, " +
                "IS_CHECKED INT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(String.format("DROP TABLE IF EXISTS%s", TABLE_NAME_TASKS));
        onCreate(db);

    }

    public void deleteTask(int id){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(String.format("DELETE FROM%sWHERE%s=;", TABLE_NAME_TASKS, ID));
        db.close();
    }

    public void addTask(String subject,String date, String task, int boo){
        ContentValues contentValues = new ContentValues();
        contentValues.put("SUBJECT",subject);
        contentValues.put("DATE",date);
        contentValues.put("TASK",task);
        contentValues.put("IS_CHECKED",boo);
        this.getWritableDatabase().insertOrThrow("TASKS","",contentValues);
    }
}

