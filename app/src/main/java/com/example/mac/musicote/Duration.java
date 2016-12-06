package com.example.mac.musicote;

/**
 * Created by mac on 3/12/16.
 */

public enum Duration {
    REDONDA(4), BLANCA(4), NEGRA(4), CORCHEA(2), SEMICORCHEA(1);
    private final int duration;
    Duration(int duration){
        this.duration=duration;
    }
    public int getDuration(){
        return duration;
    }
}
