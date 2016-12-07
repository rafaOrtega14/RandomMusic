package com.example.mac.musicote;

/**
 * Created by mac on 3/12/16.
 */

public enum Duration {
    SEMICORCHEA(1), CORCHEA(2), NEGRA(4), BLANCA(8), REDONDA(16);
    private final int duration;
    Duration(int duration){
        this.duration=duration;
    }
    public int getDuration(){
        return duration;
    }
}
