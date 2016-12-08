package com.example.mac.musicote;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mac on 3/12/16.
 */

public class Riff {
    private int longitud;
    private ArrayList<Note> notes;
    private ArrayList<String> notes_name;
    private ArrayList<Duration> rythim;
    private Scale scale;
    private int minduration;
    private int maxduration;
    private static final double TONALIDAD=261.62/4;

    Riff(Scale scale, int longitud, int minduration, int maxduration){
        int note,duration;
        this.longitud=longitud;
        this.notes = new ArrayList<>();
        this.notes_name=new ArrayList<>();
        this.rythim=new ArrayList<>();
        this.scale = scale;
        this.minduration = minduration;
        this.maxduration = maxduration;
        Random rand = new Random();
        for (int i=0; i<longitud; i++){
            note=scale.getIntervals()[rand.nextInt(scale.getIntervals().length)];
            duration=rand.nextInt(maxduration - minduration + 1) + minduration;/*Los +1 es porque el numero que se mete no esta incluido*/
            notes.add(new Note(note,duration,TONALIDAD));
            getNoteNames(note);
            getRythim(duration);
        }
    }
    private void getRythim(int duration){
        switch (duration) {
            case 0:
                rythim.add(Duration.REDONDA);
                break;
            case 1:
                rythim.add(Duration.BLANCA);
                break;
            case 2:
                rythim.add(Duration.NEGRA);
                break;
            case 3:
                rythim.add(Duration.CORCHEA);
                break;
            case 4:
                rythim.add(Duration.SEMICORCHEA);
                break;
        }
    }
    private void getNoteNames(int note){
        switch (note){
            case 0:
                notes_name.add("C");
                break;
            case 1:
                notes_name.add("C#");
                break;
            case 2:
                notes_name.add("D");
                break;
            case 3:
                notes_name.add("D#");
                break;
            case 4:
                notes_name.add("E");
                break;
            case 5:
                notes_name.add("F");
                break;
            case 6:
                notes_name.add("F#");
                break;
            case 7:
                notes_name.add("G");
                break;
            case 8:
                notes_name.add("G#");
                break;
            case 9:
                notes_name.add("A");
                break;
            case 10:
                notes_name.add("A#");
                break;
            case 11:
                notes_name.add("B");
                break;
        }
    }
    public ArrayList<String> Notes(){
        return notes_name;
    }
    public ArrayList<Duration> Rythim(){
        return rythim;
    }
   public void play(){
       for(int i=0; i<notes.size(); i++) {
        final AudioTrack  mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                notes.get(i).getByteArray().length, AudioFormat.CHANNEL_OUT_MONO,
                   AudioFormat.ENCODING_PCM_16BIT,
                notes.get(i).getByteArray().length, AudioTrack.MODE_STATIC);
           mAudioTrack.write(notes.get(i).getByteArray(), 0, notes.get(i).getByteArray().length);
           mAudioTrack.play();
           try {
               Thread space = new Thread(new Runnable() {
                   @Override
                   public void run() {
                   mAudioTrack.pause();
                   }
               });
               space.sleep(notes.get(i).getDuration().getDuration()*250);
               mAudioTrack.release();

           } catch (InterruptedException e) {
               e.printStackTrace();
           }

       }
    }


}
