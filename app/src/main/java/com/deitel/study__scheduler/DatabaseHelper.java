package com.deitel.study__scheduler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by natas on 4/12/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StudyScheduler.db";
    private static final String TABLE_NAME_USERS = "USERS";
    private static final String TABLE_NAME_SUBJECTS = "SUBJECTS";
    private static final String UID = "_id";
    private static final String USERNAME = "Username";
    private static final String PASWORD = "Pasword";
    private static final String NAME = "Name";
    private static final String DATE = "Date";
    private static final String INFORMATION= "Information";
    private static final String SID = "_id_S";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE USERS (_id INTEGER PRIMARY KEY AUTOINCREMENT, Username VARCHAR(255), Pasword VARCHAR(255));");
        db.execSQL("CREATE TABLE SUBJECTS (_id_S INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR(255), Information BLOB," +
                " FOREIGN KEY(_id_S) REFERENCES USERS(_id) );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}

