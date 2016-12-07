package com.example.mac.musicote;

import android.graphics.Color;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Random;

import static android.R.attr.max;

public class MainActivity extends AppCompatActivity {
private ImageView img;
    private static final String[] SCALELIST = {"Standard Major",
            "Pentatonic Menor",
            "Pentatonic Major"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SCALELIST);
        final MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner);
        materialDesignSpinner.setAdapter(arrayAdapter);
        materialDesignSpinner.setHintTextColor(Color.WHITE);
        img=(ImageView)findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Scale scale=null;
                switch (materialDesignSpinner.getText().toString()){
                    case "Standard Major":
                        scale=Scale.StandardMayor;
                        break;
                    case "Pentatonic Menor":
                        scale=Scale.PentatonicaMenor;
                        break;
                    case "Pentatonic Major":
                        scale=Scale.PentatonicaMayor;
                        break;

                }
                Riff prueba = new Riff(scale, 10, Duration.SEMICORCHEA.ordinal(), Duration.NEGRA.ordinal());
                prueba.play();
                prueba.showNotes();
                prueba.showRythim();
            }
        });
    }

}
