package com.pockerstad.mainmenu.grafic.surfaceview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GameAnimationView extends SurfaceView implements SurfaceHolder.Callback {

    AnimationThread animationThread;

    public GameAnimationView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    public GameAnimationView(Context context, AttributeSet attr) {
        super(context, attr);
        getHolder().addCallback(this);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        animationThread = new AnimationThread(getHolder());
        animationThread.setRunning(false);
        animationThread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        animationThread.setRunning(false); //останавливает процесс

        while(retry) {
            try {
                animationThread.join(); //ждет окончательной остановки процесса
                retry = false;
            }
            catch (InterruptedException e) {
                //не более чем формальность
            }
        }

    }
}
