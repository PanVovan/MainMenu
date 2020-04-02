package com.pockerstad.mainmenu.grafic.surfaceview;

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
}
