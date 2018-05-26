package com.deitel.study__scheduler;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;


/**
 * Created by natas on 5/15/2018.
 */

public class TaskListActivity extends AppCompatActivity {

    private Button timerButton;
    private ListView taskListView;
    private TaskListAdapter tlk;
    private TextView subject;
    private static final String TAG = "TaskListActivity";
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Button surchButton;
    private TextView displayDate;
    private EditText et;
    String[] valueOfEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        displayDate = (TextView) findViewById(R.id.dateTextView);

        subject = (TextView) findViewById(R.id.subjectTextView);
        subject.setText(getIntent().getStringExtra("SUBJECT"));

        taskListView = (ListView) findViewById(R.id.itemListView);
        tlk = new TaskListAdapter(this);
        taskListView.setAdapter(tlk);
        taskListView.setFooterDividersEnabled(true);

        TextView footerView;
        footerView = (TextView) getLayoutInflater().inflate(
                R.layout.footerview, null);
        taskListView.addFooterView(footerView);

        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task item = new Task("");

                tlk.add(item);
                tlk.notifyDataSetChanged();
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

        surchButton = (Button) findViewById(R.id.surch_button);
        surchButton.setOnClickListener(new View.OnClickListener() {
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
                       year, month,day);
               dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
               dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month + 1;
                String date = dayOfMonth + "/"+ month + "/" + year;
                displayDate.setText(date);

            }
        };

        }
        public String[] getAllTasks(){

            View v;

            int listLength = taskListView.getChildCount();

            for (int i = 0; i < listLength; i++)
            {
                v = taskListView.getChildAt(i);
                et = (EditText) v.findViewById(R.id.ItemCaption);
                valueOfEditText[i] = et.getText().toString();
            }
            return valueOfEditText;

        }

        public void saveTasks(String[] tasks){



        }
    }

