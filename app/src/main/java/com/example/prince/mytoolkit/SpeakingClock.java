package com.example.prince.mytoolkit;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.Calendar;

/**
 * Created by Prince on 5/4/2015.
 */

public class SpeakingClock extends ActionBarActivity {

    Calendar ctime;
    int hr, min, ampm;
    MediaPlayer speak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speaking_clock);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        ctime = Calendar.getInstance();
        hr = ctime.get(Calendar.HOUR);
        min = ctime.get(Calendar.MINUTE);
        ampm = ctime.get(Calendar.AM_PM);

        hr %= 12;
        if (hr == 0) {hr = 12;}
    }

    @Override
    protected void onStart() {
        super.onStart();
        playClock();
    }

    public void playClock() {
        delay(10);
        speak = MediaPlayer.create(SpeakingClock.this,R.raw.thetimeis);
        speak.start(); delay(5);

        playHourMin(hr);

        if (min >= 50) {
            speak = MediaPlayer.create(SpeakingClock.this, R.raw.fifty);
            min -= 50; if (min == 0) min = -1;
        } else if (min >= 40) {
            speak = MediaPlayer.create(SpeakingClock.this, R.raw.forty);
            min -= 40; if (min == 0) min = -1;
        } else if (min >= 30) {
            speak = MediaPlayer.create(SpeakingClock.this, R.raw.thirty);
            min -= 30; if (min == 0) min = -1;
        } else if (min >= 20) {
            speak = MediaPlayer.create(SpeakingClock.this, R.raw.twenty);
            min -= 20; if (min == 0) min = -1;
        } else if (min >= 10) {
            // the teens are a special case
            switch(min) {
                case 10:
                    speak = MediaPlayer.create(SpeakingClock.this, R.raw.ten);
                    break;
                case 11:
                    speak = MediaPlayer.create(SpeakingClock.this, R.raw.eleven);
                    break;
                case 12:
                    speak = MediaPlayer.create(SpeakingClock.this, R.raw.twelve);
                    break;
                case 13:
                    speak = MediaPlayer.create(SpeakingClock.this, R.raw.thirteen);
                    break;
                case 14:
                    speak = MediaPlayer.create(SpeakingClock.this, R.raw.fourteen);
                    break;
                case 15:
                    speak = MediaPlayer.create(SpeakingClock.this, R.raw.fifteen);
                    break;
                case 16:
                    speak = MediaPlayer.create(SpeakingClock.this, R.raw.sixteen);
                    break;
                case 17:
                    speak = MediaPlayer.create(SpeakingClock.this, R.raw.seventeen);
                    break;
                case 18:
                    speak = MediaPlayer.create(SpeakingClock.this, R.raw.eighteen);
                    break;
                case 19:
                    speak = MediaPlayer.create(SpeakingClock.this, R.raw.nineteen);
                    break;
                default:
                    speak = MediaPlayer.create(SpeakingClock.this, R.raw.errormsg);
            }
            min = -1;
        } else {
            speak = MediaPlayer.create(SpeakingClock.this, R.raw.oh);
        }
        speak.start();
        delay(2);

        playHourMin(min);

        if (ampm == 1) { speak = MediaPlayer.create(SpeakingClock.this, R.raw.pm); }
        else { speak = MediaPlayer.create(SpeakingClock.this, R.raw.am); }
        speak.start();
    }

    public void playHourMin(int hr) {
        switch(hr) {
            case -1:
                break;
            case 0:
                speak = MediaPlayer.create(SpeakingClock.this,R.raw.oh);
                break;
            case 1:
                speak = MediaPlayer.create(SpeakingClock.this,R.raw.one);
                break;
            case 2:
                speak = MediaPlayer.create(SpeakingClock.this, R.raw.two);
                break;
            case 3:
                speak = MediaPlayer.create(SpeakingClock.this, R.raw.three);
                break;
            case 4:
                speak = MediaPlayer.create(SpeakingClock.this, R.raw.four);
                break;
            case 5:
                speak = MediaPlayer.create(SpeakingClock.this, R.raw.five);
                break;
            case 6:
                speak = MediaPlayer.create(SpeakingClock.this, R.raw.six);
                break;
            case 7:
                speak = MediaPlayer.create(SpeakingClock.this, R.raw.seven);
                break;
            case 8:
                speak = MediaPlayer.create(SpeakingClock.this, R.raw.eight);
                break;
            case 9:
                speak = MediaPlayer.create(SpeakingClock.this, R.raw.nine);
                break;
            case 10:
                speak = MediaPlayer.create(SpeakingClock.this, R.raw.ten);
                break;
            case 11:
                speak = MediaPlayer.create(SpeakingClock.this, R.raw.eleven);
                break;
            case 12:
                speak = MediaPlayer.create(SpeakingClock.this, R.raw.twelve);
                break;
            default:
                speak = MediaPlayer.create(SpeakingClock.this, R.raw.errormsg);
        }
        if (hr != -1) { speak.start(); delay(3); }
    }

    @Override
    protected void onDestroy() {
        if (null != speak){
            speak.release();
        }
        super.onDestroy();
    }

    public void delay(int x) {
        try { Thread.sleep(250 * x); }
        catch (InterruptedException ex) {}
    }
}