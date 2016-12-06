package com.example.mac.musicote;

/**
 * Created by mac on 3/12/16.
 */

public enum Duration {
    REDONDA(1), BLANCA(1), NEGRA(1), CORCHEA(2), SEMICORCHEA(2);
    private final int duration;
    Duration(int duration){
        this.duration=duration;
    }
    public int getDuration(){
        return duration;
    }
}
