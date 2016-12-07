package com.example.mac.musicote;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.Random;

/**
 * Created by mac on 3/12/16.
 */

public class Note {
    private Integer semitone;/*En salo semitonal*/
    private Duration duration;
    private int sampleRate = 2000;
    private int numSamples;
    private byte[] generatedSnd;
    private double sample[];
    private double tonalidad;
    private double salto=1.059463;

    Note(int semitone, int duration, double tonalidad) {
        this.semitone = semitone;
        this.tonalidad = tonalidad;
        genDuration(duration);
        numSamples = getDuration().getDuration() * sampleRate;
        sample = new double[numSamples];
        generatedSnd = new byte[2 * numSamples];

    }


    public byte[] getByteArray(){
        Double freq = tonalidad*Math.pow(salto, semitone);

        for (int i = 0; i < numSamples; ++i) {
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freq));
        }

        int idx = 0;
        for (final double dVal : sample) {
            final short val = (short) ((dVal * 32767));
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);

        }
        return generatedSnd;
    }

    public Duration getDuration() {
        return duration;
    }

    private void genDuration(int duration) {
        switch (duration) {
            case 0:
                this.duration = Duration.SEMICORCHEA;
                break;
            case 1:
                this.duration = Duration.CORCHEA;
                break;
            case 2:
                this.duration = Duration.NEGRA;
                break;
            case 3:
                this.duration = Duration.BLANCA;
                break;
            case 4:
                this.duration = Duration.REDONDA;
                break;
        }
    }
}
