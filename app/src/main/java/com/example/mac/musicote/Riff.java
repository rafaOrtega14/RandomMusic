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

    Riff(int longitud){
        this.longitud=longitud;
        this.notes = new ArrayList<>();
        Random rand = new Random();
        for (int i=0; i<longitud; i++){
            notes.add(new Note(rand.nextInt(11), rand.nextInt(4)+1,261.62));
        }
    }

   public void play(){
       byte[] mola =createRiff();

       for(int i=0; i<notes.size(); i++) {
        final AudioTrack  mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, notes.get(i).getByteArray().length, AudioFormat.CHANNEL_OUT_MONO,
                   AudioFormat.ENCODING_PCM_16BIT, notes.get(i).getByteArray().length, AudioTrack.MODE_STATIC);
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

    private byte[] createRiff(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );

        for(int i=0;i<longitud;i++) {
            try {
                outputStream.write(notes.get(i).getByteArray());
                //Log.w(notes.get(i).getByteArray().toString(),"dsf");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        byte riff[] = outputStream.toByteArray( );
        return riff;
    }
}
