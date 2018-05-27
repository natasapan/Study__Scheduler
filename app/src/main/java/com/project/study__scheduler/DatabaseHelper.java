package com.project.study__scheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by natas on 4/12/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SchedulerDB.db";
    private static final String TABLE_NAME_TASKS = "Tasks";
    private static final String SUBJECT = "subject";
    private static final String DATE = "date";
    private static final String IS_CHECKED = "is_checked";
    private static final String TASK = "task";
    private static final String ID = "_id";

    private SQLiteDatabase db = null;

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME_TASKS + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SUBJECT + " VARCHAR(255)," +
                DATE + " TEXT," +
                TASK + "  TEXT, " +
                IS_CHECKED + " INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_TASKS));
        onCreate(db);
    }

    public void deleteTask(int id) {
        String selection = ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(id) };
        db.delete(TABLE_NAME_TASKS, selection, selectionArgs);
    }

    public void addTask(Task task) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SUBJECT, task.getSubject());
        contentValues.put(DATE, task.getDate());
        contentValues.put(TASK, task.getTask());
        contentValues.put(IS_CHECKED, getIsCheckedIntegerValue(task.isChecked()));
        db.insertOrThrow(TABLE_NAME_TASKS,"", contentValues);
    }

    public ArrayList<Task> getTasks(String date, String subject) {
        ArrayList<Task> tasks = new ArrayList<>();
        String[] selectionArgs = { date, subject };
        Cursor cursor = this.db.query(TABLE_NAME_TASKS,
                null,
                DATE + " = ? AND " + SUBJECT + " = ?",
                selectionArgs,
                null,
                null,
                ID
        );

        while(cursor.moveToNext()) {
            String taskStr = cursor.getString(cursor.getColumnIndexOrThrow(TASK));
            Task task = new Task(taskStr);

            String subjectStr = cursor.getString(cursor.getColumnIndexOrThrow(SUBJECT));
            task.setSubject(subjectStr);

            String dateStr = cursor.getString(cursor.getColumnIndexOrThrow(DATE));
            task.setDate(dateStr);

            Boolean is_checked = getIsCheckedBooleanValue(cursor.getInt(cursor.getColumnIndexOrThrow(IS_CHECKED)));
            task.setChecked(is_checked);

            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ID));
            task.setId(id);

            tasks.add(task);
        }
        cursor.getCount();
        cursor.close();
        return tasks;
    }

    public void updateTask(Task task) {
        ContentValues values = new ContentValues();
        values.put(SUBJECT, task.getSubject());
        values.put(DATE, task.getDate());
        values.put(TASK, task.getTask());
        values.put(IS_CHECKED, task.isChecked());

        String selection = ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(task.getId()) };

        this.db.update(
            TABLE_NAME_TASKS,
            values,
            selection,
            selectionArgs);
    }


    private int getIsCheckedIntegerValue(boolean is_checked) {
        if (is_checked) {
            return 1;
        } else {
            return 0;
        }
    }

    private boolean getIsCheckedBooleanValue(int is_checked) {
        return is_checked == 1;
    }
}

