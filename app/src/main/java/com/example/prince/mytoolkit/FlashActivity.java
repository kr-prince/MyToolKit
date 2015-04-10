package com.example.prince.mytoolkit;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Prince on 6/4/2015.
 */

public class FlashActivity extends ActionBarActivity implements View.OnClickListener {

    private int flash;
    ImageButton fbutton;
    private Camera cam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashlight);

        fbutton = (ImageButton) findViewById (R.id.torchswitch);
        fbutton.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        cam = Camera.open();
        flash  = 0;
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        flash = (flash +1) % 2;
        Camera.Parameters camParam = cam.getParameters();
        ImageButton fbutton = (ImageButton) v;
        if (flash == 0) {
            fbutton.setImageResource(R.drawable.poweron);
            camParam.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        }
        if (flash == 1) {
            fbutton.setImageResource(R.drawable.poweroff);
            camParam.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        }
        cam.setParameters(camParam);
        cam.startPreview();
    }

    @Override
    protected void onStop() {
        if (null != cam){
            cam.stopPreview();
            cam.release();
            cam = null;
        }
        super.onStop();
    }
}
