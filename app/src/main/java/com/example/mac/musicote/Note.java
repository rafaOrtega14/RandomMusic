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
import java.util.Random;

/**
 * Created by mac on 3/12/16.
 */

public class Note {
    private Tone tone;
    private Duration duration;
    private int sampleRate = 2000;
    private int numSamples;
    private double sample[];
    private double minfreq=261.62/4;
    private double salto=1.059463;
    private byte generatedSnd[];
    Context ctx;

    Note(Context ctx){
        Random rand = new Random();
        Integer randomNum = rand.nextInt(5);
        genDuration(randomNum);
        numSamples = duration.getDuration() * sampleRate;
        sample = new double[numSamples];
        generatedSnd = new byte[2 * numSamples];
        randomNum = rand.nextInt(11);
        this.ctx=ctx;
        genTone(randomNum);

    }

    public Duration getDuration() {
        return duration;
    }

    private void genTone(Integer intervalo){
        //this.tone = Tone[intervalo];
        Double freq = minfreq*Math.pow(salto, intervalo);
        // fill out the array
        for (int i = 0; i < numSamples; ++i) {

            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freq));
        }

        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalised.
        int idx = 0;
        for (final double dVal : sample) {
            // scale to maximum amplitude
            final short val = (short) ((dVal * 32767));
            // in 16 bit wav PCM, first byte is the low order byte
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);

        }
    }

    private void genDuration (int duration){
        switch (duration) {
            case 0:
                this.duration = Duration.REDONDA;
                break;
            case 1:
                this.duration = Duration.BLANCA;
                break;
            case 2:
                this.duration = Duration.NEGRA;
                break;
            case 3:
                this.duration = Duration.CORCHEA;
                break;
            case 4:
                this.duration = Duration.SEMICORCHEA;
                break;
        }
    }

    public void playSound(){
   final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
           8000, AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_DEFAULT, generatedSnd.length,
            AudioTrack.MODE_STATIC);
        audioTrack.write(generatedSnd, 0, generatedSnd.length);
        Log.w(""+generatedSnd.length,"sdfasdf");
        audioTrack.play();
    }

}
