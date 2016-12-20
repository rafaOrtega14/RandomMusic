package com.example.mac.musicote;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by mac on 8/12/16.
 */
public class CanvasView extends View {

    public int height;
    Context context;
    private Paint mPaint;
    private ArrayList<String>notes;
    private ArrayList<Duration>durations;
    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;
        notes=((Activity)c).getIntent().getStringArrayListExtra("notes");
        durations=(ArrayList<Duration>)((Activity)c).getIntent().getSerializableExtra("rythim");
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);
    }
    private void drawPentagram(Canvas canvas){
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        for(int i=1;i<=5;i++){
            canvas.drawRect(0,50*i,metrics.widthPixels,50*i,mPaint);
        }
    }
    private void drawNotes(Canvas canvas) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screen=metrics.widthPixels;
        double x=0,y=0;
        int pentaSize=250;
        double distance=41.6;
        double diference=0;
        for(int i=0;i<notes.size();i++){
            switch (notes.get(i)){
                case "B":
                    diference=distance*6;
                    break;
                case "A#":
                    diference=distance*6;
                    break;
                case "A":
                    diference=distance*5;
                    break;
                case "G#":
                    diference=distance*5;
                    break;
                case "G":
                    diference=distance*4;
                    break;
                case "F#":
                    diference=distance*3;
                    break;
                case "F":
                    diference=distance*3;
                    break;
                case "E":
                    diference=distance*2;
                    break;
                case "D#":
                    diference=distance*2;
                    break;
                case "D":
                    diference=distance;
                    break;
                case "C#":
                    diference=distance;
                    break;
                case "C":
                    diference=0;
                    break;
            }
            y=pentaSize-diference;
            if(i==0){
                x=screen-70;
            }else{
                x=screen-((screen/10)*i);
            }
            mPaint.setTextSize(40f);
            canvas.drawBitmap(placeNotes(i),(float)x,(float)y,mPaint);
            canvas.drawText(notes.get(i),(float)x-60,(float)y+40,mPaint);
        }

    }
    private Bitmap placeNotes(int pos){
        Bitmap b,bit=null;
        switch (durations.get(pos).name()){
            case "REDONDA":
                b= BitmapFactory.decodeResource(getResources(), R.drawable.redonda);
                bit=Bitmap.createScaledBitmap(b, 50, 50, false);
                break;
            case "BLANCA":
                b= BitmapFactory.decodeResource(getResources(), R.drawable.blanca);
                bit=Bitmap.createScaledBitmap(b, 50, 50, false);
                break;
            case "NEGRA":
                b= BitmapFactory.decodeResource(getResources(), R.drawable.negra);
                bit=Bitmap.createScaledBitmap(b, 50, 50, false);
                break;
            case "CORCHEA":
                b= BitmapFactory.decodeResource(getResources(), R.drawable.corch);
                bit=Bitmap.createScaledBitmap(b, 50, 50, false);
                break;
            case "SEMICORCHEA":
                b= BitmapFactory.decodeResource(getResources(), R.drawable.semicorchea);
                bit=Bitmap.createScaledBitmap(b, 50, 50, false);
                break;
        }
        return bit;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPentagram(canvas);
        drawNotes(canvas);
    }
}
