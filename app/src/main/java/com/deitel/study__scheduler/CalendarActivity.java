package com.deitel.study__scheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by natas on 5/13/2018.
 */

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendar;
    TextView create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendar = (CalendarView)findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int monthi = month + 1;
                Toast.makeText(getApplicationContext(), ""+dayOfMonth+"/"+monthi+"/"+year, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CalendarActivity.this, SubjectListActivity.class);
                startActivity(intent);
            }
        });
    }
}
