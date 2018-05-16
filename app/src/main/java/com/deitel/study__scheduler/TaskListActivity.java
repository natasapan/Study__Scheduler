package com.deitel.study__scheduler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by natas on 5/15/2018.
 */

public class TaskListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        ListView listView = (ListView) findViewById(R.id.itemListView);
        TaskListAdapter tlk = new TaskListAdapter(this,R.layout.task_list_item);
        listView.setAdapter(tlk);


    }
}
