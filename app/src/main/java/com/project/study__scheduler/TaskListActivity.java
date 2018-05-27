package com.project.study__scheduler;

import android.app.Application;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by natas on 5/15/2018.
 */

public class TaskListActivity extends AppCompatActivity {
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView displayDate;
    private DatabaseHelper dbHelper;

    String[] valueOfEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final TaskListAdapter taskListAdapter;
        final ListView taskListView;
        final Button timerButton;
        final Button searchButton;
        final TextView subjectTextView;
        final String currentDate = getIntent().getStringExtra("DATE");
        final Button searchInternet;
        dbHelper = new DatabaseHelper(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        displayDate = (TextView) findViewById(R.id.dateTextView);
        displayDate.setText(currentDate);

        subjectTextView = (TextView) findViewById(R.id.subjectTextView);

        final String subject = getIntent().getStringExtra("SUBJECT");
        subjectTextView.setText(subject);

        subjectTextView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        subjectTextView.setText(subject);

        taskListView = (ListView) findViewById(R.id.itemListView);
        taskListAdapter = new TaskListAdapter(this, getAllTasks(currentDate, subject));
        taskListView.setAdapter(taskListAdapter);
        taskListView.setFooterDividersEnabled(true);

        TextView footerView;
        footerView = (TextView) getLayoutInflater().inflate(
                R.layout.footerview, null);
        taskListView.addFooterView(footerView);

        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task("");
                task.setId(0);
                task.setSubject(subject);
                task.setDate(currentDate);

                taskListAdapter.add(task);
                taskListAdapter.notifyDataSetChanged();
            }
        });

        View saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTasks(taskListAdapter);
            }
        });

        timerButton = (Button) findViewById(R.id.timerButton);

        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskListActivity.this, TimerActivity.class);
                startActivity(intent);
            }
        });

        searchButton = (Button) findViewById(R.id.surch_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(TaskListActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                displayDate.setText(date);
            }
        };

        searchInternet = (Button) findViewById(R.id.searchButton);
        searchInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                browsing(v);
            }
        });
    }

    public ArrayList<Task> getAllTasks(String date, String subject) {
        return dbHelper.getTasks(date, subject);
    }

    public void saveTasks(TaskListAdapter taskListAdapter) {
        int listLength = taskListAdapter.getCount();

        for (int i = 0; i < listLength; i++) {
            Task task = taskListAdapter.getItem(i);

            if (task.getId() == 0) {
                dbHelper.addTask(task);
            } else {

                dbHelper.updateTask(task);
            }
        }
        Toast.makeText(TaskListActivity.this, "Tasks saved", Toast.LENGTH_LONG).show();
    }

    private void browsing(View v) {
        String[] subjects = getResources().getStringArray(R.array.subject_list);
        String[] sites = getResources().getStringArray(R.array.educational_websites);
        String name = getIntent().getStringExtra("SUBJECT");

        for (int i = 0; i < subjects.length; i++) {
            if (subjects[i].equals(name)) {
                String esite = sites[i];
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(esite));
                startActivity(browserIntent);
            }
        }
    }
}

