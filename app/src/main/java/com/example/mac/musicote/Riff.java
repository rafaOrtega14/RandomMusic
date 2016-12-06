package com.example.mac.musicote;

import android.content.Context;
import android.util.Log;

import java.util.Random;

/**
 * Created by mac on 3/12/16.
 */

public class Riff {
    private int longitud;
    private Note[] notes;
    private Context ctx;
    Riff(int longitud,Context ctx){
        this.longitud=longitud;
        this.notes = new Note[longitud];
        this.ctx=ctx;
    }

    public void play(){
        for(int i=0; i<notes.length; i++){
            notes[i]= new Note(ctx);
            //try {
                notes[i].playSound();
                //Thread.sleep(notes[i].getDuration().getDuration()*(250));
            //} catch (InterruptedException e) {
              //  e.printStackTrace();
            //}
        }
    }
}
