package com.example.prince.mytoolkit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;


public class HomeActivity extends ActionBarActivity implements View.OnClickListener {

    public final static String EXTRA_MESSAGE = "com.example.prince.mytoolkit";
    ImageButton imgbtn1, imgbtn2, imgbtn3, imgbtn4, imgbtn5, imgbtn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        initialize();
    }


    public void initialize() {
        imgbtn1 = (ImageButton) findViewById (R.id.imageButton1);
        imgbtn1.setOnClickListener(this);
        imgbtn2 = (ImageButton) findViewById (R.id.imageButton2);
        imgbtn2.setOnClickListener(this);
        imgbtn3 = (ImageButton) findViewById (R.id.imageButton3);
        imgbtn3.setOnClickListener(this);
        imgbtn4 = (ImageButton) findViewById (R.id.imageButton4);
        imgbtn4.setOnClickListener(this);
        imgbtn5 = (ImageButton) findViewById (R.id.imageButton5);
        imgbtn5.setOnClickListener(this);
        imgbtn6 = (ImageButton) findViewById (R.id.imageButton6);
        imgbtn6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch(v.getId()) {
            case R.id.imageButton1 :
                intent = new Intent(this, SendSmsActivity.class);
                intent.putExtra(EXTRA_MESSAGE, 1);
                startActivity(intent);
                break;

            case R.id.imageButton2 :
                intent = new Intent(this, SpeakingClock.class);
                intent.putExtra(EXTRA_MESSAGE, 2);
                startActivity(intent);
                break;

            case R.id.imageButton3 :
                intent = new Intent(this, AlarmActivity.class);
                intent.putExtra(EXTRA_MESSAGE, 3);
                startActivity(intent);
                break;

            case R.id.imageButton4 :
                intent = new Intent(this, TodoActivity.class);
                intent.putExtra(EXTRA_MESSAGE, 4);
                startActivity(intent);
                break;

            case R.id.imageButton5 :
                intent = new Intent(this, FlashActivity.class);
                intent.putExtra(EXTRA_MESSAGE, 5);
                startActivity(intent);
                break;

            case R.id.imageButton6 :
                intent = new Intent(this, StopClock.class);
                intent.putExtra(EXTRA_MESSAGE, 6);
                startActivity(intent);
                break;
        }
    }
}
