package com.deitel.study__scheduler;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by natas on 5/18/2018.
 */

public class TimerActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 3540000;
    private TextView timer;
    private Button start_pause;
    private Button reset;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timer = (TextView)findViewById(R.id.timeTextView);
        start_pause = (Button) findViewById(R.id.startButton);
        reset = (Button) findViewById(R.id.resetButton);

        timer.setGravity(Gravity.CENTER);

        start_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mTimerRunning){
                    pauseTimer();
                }
                else{
                    startTimer();
                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();

            }
        });
        updateCountdownText();


    }

    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountdownText();

            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                start_pause.setText("Start");
                start_pause.setVisibility(View.INVISIBLE);
                reset.setVisibility(View.VISIBLE);

            }
        }.start();

        mTimerRunning = true;
        start_pause.setText("Pause");
        reset.setVisibility(View.INVISIBLE);


    }
    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = false;
        start_pause.setText("Start");
        reset.setVisibility(View.VISIBLE);

    }
    private void resetTimer(){
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountdownText();
        reset.setVisibility(View.INVISIBLE);
        start_pause.setVisibility(View.VISIBLE);

    }
    private void updateCountdownText(){
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormated = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        timer.setText(timeLeftFormated);
    }
}
