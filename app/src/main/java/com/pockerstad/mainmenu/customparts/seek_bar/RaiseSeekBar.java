package com.pockerstad.mainmenu.customparts.seek_bar;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.bumptech.glide.Glide;
import com.pockerstad.mainmenu.R;

import java.util.concurrent.ExecutionException;


public class RaiseSeekBar extends androidx.appcompat.widget.AppCompatSeekBar {

    private int value;

    public int getValue(){
        return this.value;
    }

    public RaiseSeekBar(Context context) {
        super(context);
    }

    public RaiseSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.rotate(-90);
        canvas.translate(-getHeight(), 0);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        if ( e.getAction() == MotionEvent.ACTION_DOWN
                || e.getAction() == MotionEvent.ACTION_MOVE
                || e.getAction() == MotionEvent.ACTION_UP ){
            setProgress(getMax() - (int) (getMax() * e.getY() / getHeight()));
            onSizeChanged(getWidth(), getHeight(), 0, 0);
        }
        return true;
    }
}
