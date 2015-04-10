package com.example.prince.mytoolkit;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Prince on 7/4/2015.
 */

public class StopClock extends ActionBarActivity {

    private Button startpause;
    private Button lapbutton;
    private Button reset;

    private TextView clockView;
    private LinearLayout lapRows;

    private long startTime = 0L;
    private int start = 0;
    private int countLap = 0;

    private Handler customHandler = new Handler();

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopclock);

        clockView = (TextView) findViewById(R.id.clockView);
        lapRows = (LinearLayout) findViewById(R.id.lapRows);
        startpause = (Button) findViewById(R.id.startpause);
        lapbutton = (Button) findViewById(R.id.lapbutton);
        reset = (Button) findViewById(R.id.reset);

        startpause.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (start == 0) {
                    start = 1;
                    startTime = SystemClock.uptimeMillis();
                    customHandler.postDelayed(updateTimerThread, 0);
                    startpause.setText("Pause");
                }
                else {
                    start = 0;
                    timeSwapBuff += timeInMilliseconds;
                    customHandler.removeCallbacks(updateTimerThread);
                    startpause.setText("Start");
                }
            }
        });

        lapbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LinearLayout.LayoutParams paramsExample = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                TextView laptime = new TextView(getApplicationContext());
                laptime.setTextSize((float)30); laptime.setTextColor(Color.BLACK);
                countLap++;
                int secs = (int) (updatedTime / 1000);
                int mins = secs / 60;
                secs = secs % 60;
                int milliseconds = (int) (updatedTime % 1000);
                laptime.setText(""+ countLap + ".  " + String.format("%02d", mins) + ":"
                        + String.format("%02d", secs) + ":"
                        + String.format("%03d", milliseconds));
                laptime.setLayoutParams(paramsExample);
                lapRows.addView(laptime);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startTime = 0L; start = 0; countLap = 0;
                timeInMilliseconds = 0L;
                timeSwapBuff = 0L;
                updatedTime = 0L;
                startpause.setText("Start");
                clockView.setText("00:00:000");
                lapRows.removeAllViews();
                customHandler.removeCallbacks(updateTimerThread);
            }
        });
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            clockView.setText("" + String.format("%02d", mins) + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
        }

    };

}