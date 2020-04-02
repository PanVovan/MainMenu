package com.pockerstad.mainmenu.grafic.surfaceview;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameAnimationView extends SurfaceView implements SurfaceHolder.Callback {

    AnimationThread animationThread;
    
    public GameAnimationView(Context context, SurfaceView sv) {
        super(context);
        sv.getHolder().addCallback(this);
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

    }
}
