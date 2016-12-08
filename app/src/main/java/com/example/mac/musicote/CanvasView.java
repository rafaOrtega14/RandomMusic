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
        for(int i=0;i<notes.size();i++){
            switch (notes.get(i)){

                case "C":
                    y=pentaSize/7;
                    break;
                case "C#":
                    y=pentaSize/7;
                    break;
                case "D":
                    y=pentaSize/6;
                    break;
                case "D#":
                    y=pentaSize/6;
                    break;
                case "E":
                    y=pentaSize/5;
                    break;
                case "F":
                    y=pentaSize/4;
                    break;
                case "F#":
                    y=pentaSize/4;
                    break;
                case "G":
                    y=pentaSize/3;
                    break;
                case "G#":
                    y=pentaSize/3;
                    break;
                case "A":
                    y=pentaSize/2;
                    break;
                case "A#":
                    y=pentaSize/2;
                    break;
                case "B":
                    y=pentaSize;
                    break;
            }
            if(i==0){
                x=screen-70;
            }else{
                x=screen-((screen/10)*i);
            }
            mPaint.setTextSize(40f);
            canvas.drawBitmap(placeNotes(i),(float)x,(float)y,mPaint);
            canvas.drawText(notes.get(i),(float)x-20,(float)y,mPaint);
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
