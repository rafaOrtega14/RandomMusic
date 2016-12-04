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
    Riff(int longitud,Context ctx){
        this.longitud=longitud;
        this.notes = new Note[longitud];
        for(int i=0; i<longitud; i++){
            notes[i]= new Note(ctx);
        }
    }

    public void play(){
        for(Note note:notes){
            note.playSound();
            try {
                Thread.sleep(note.getDuration().getDuration()*(250));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
