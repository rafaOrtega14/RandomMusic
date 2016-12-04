package com.example.mac.musicote;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
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
    File tempMp3;

    Note(Context ctx){
        Random rand = new Random();
        Integer randomNum = rand.nextInt(5);
        genDuration(randomNum);
        numSamples = duration.getDuration() * sampleRate;
        sample = new double[numSamples];
        generatedSnd = new byte[2 * numSamples];
        randomNum = rand.nextInt(11);
        genTone(randomNum);
        try {
            Log.w(""+ctx,"");
            tempMp3 = File.createTempFile("kurchina", "mp3",ctx.getExternalCacheDir());
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            // create temp file that will hold byte array

            tempMp3.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(tempMp3);
            fos.write(generatedSnd);
            fos.close();

            // resetting mediaplayer instance to evade problems
            mediaPlayer.reset();

            // In case you run into issues with threading consider new instance like:
            // MediaPlayer mediaPlayer = new MediaPlayer();

            // Tried passing path directly, but kept getting
            // "Prepare failed.: status=0x1"
            // so using file descriptor instead
            FileInputStream fis = new FileInputStream(tempMp3);
            mediaPlayer.setDataSource(fis.getFD());
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException ex) {
            String s = ex.toString();
            ex.printStackTrace();
        }
    }
}
