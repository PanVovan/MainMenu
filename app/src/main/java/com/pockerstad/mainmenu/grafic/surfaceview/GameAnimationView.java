package com.pockerstad.mainmenu.grafic.surfaceview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GameAnimationView extends SurfaceView implements Runnable {

    private boolean mustRunning;
    private Thread gameThread = null;
    private SurfaceHolder holder;

    public GameAnimationView(Context context) {
        super(context);
        holder=getHolder();
    }

    public GameAnimationView(Context context, AttributeSet attr) {
        super(context, attr);
        holder = getHolder();
    }

    @Override
    public void run() {
    }

    public void pause() {
        mustRunning = false;
        try {
            // Stop the thread == rejoin the main thread.
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        mustRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}
