package com.deitel.study__scheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by natas on 5/15/2018.
 */

public class TaskListActivity extends AppCompatActivity {

    private Button timerButton;
    private ListView taskListView;
    private TaskListAdapter tlk;
    private TextView subject;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

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
    }
}