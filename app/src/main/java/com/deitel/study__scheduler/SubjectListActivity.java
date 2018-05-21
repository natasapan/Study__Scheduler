package com.deitel.study__scheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by natas on 5/14/2018.
 */

public class SubjectListActivity extends AppCompatActivity{
    String[] mSubArray;
    ArrayList<String> mTaskArray;
    private String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_list);



        final ArrayAdapter<String> adapter;
        final ArrayAdapter<String> adapt;

        final ListView taskLv = (ListView) findViewById(R.id.taskListView);
        final ListView subjectLv = (ListView) findViewById(R.id.subjectsListView);

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.task_list_header,taskLv,false);
        taskLv.addHeaderView(header);

        LayoutInflater in = getLayoutInflater();
        ViewGroup head = (ViewGroup)in.inflate(R.layout.subjects_list_header,subjectLv,false);
        subjectLv.addHeaderView(head);

        mSubArray = getResources().getStringArray(R.array.subject_list);
        adapter = new ArrayAdapter<String>(
                this,
               android.R.layout.simple_list_item_1,
                mSubArray);
        subjectLv.setAdapter(adapter);

        mTaskArray = new ArrayList<>();
        adapt = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                mTaskArray);
        taskLv.setAdapter(adapt);

        taskLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    str = adapt.getItem(position - 1);
                    Intent intent = new Intent(getApplicationContext(), TaskListActivity.class);
                    intent.putExtra("SUBJECT", str);
                    startActivity(intent);
            }
        });

        subjectLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = adapter.getItem(position - 1);
                adapt.add(str);

            }
        });

    }
}
