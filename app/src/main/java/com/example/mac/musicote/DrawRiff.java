package com.example.mac.musicote;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by mac on 8/12/16.
 */

public class DrawRiff extends AppCompatActivity{

    private CanvasView customCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_riff);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);

    }

}
