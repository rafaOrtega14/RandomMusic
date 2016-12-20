package com.example.mac.musicote;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by mac on 20/12/16.
 */

public class ListenToNumbers extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AudioTrack mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                4000, AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                4000, AudioTrack.MODE_STATIC);
        mAudioTrack.write(toBytes(500), 0, 4000);
        mAudioTrack.play();
    }
    byte[] toBytes(int number)
    {
        byte[] result = new byte[number];
        for(int i=0;i<number;i++){
            result[i] = (byte) (number >> i*4);
        }
        return result;
    }
}
