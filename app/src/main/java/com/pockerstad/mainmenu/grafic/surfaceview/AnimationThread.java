package com.pockerstad.mainmenu.grafic.surfaceview;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class AnimationThread extends Thread {

    private boolean mustRunning;
    private final SurfaceHolder surfaceHolder;


    public AnimationThread(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        mustRunning = false;
    }

    public void setRunning(boolean mustRunning) {
        this.mustRunning = mustRunning;
    }

    @Override
    public void run(){
        Canvas canvas;
        while (mustRunning){
            canvas = surfaceHolder.lockCanvas();
            try {

            } catch (Exception e){}
            finally {
                if(canvas != null){
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }

    }
}
