package com.poker.holdem;

import android.os.Handler;
import android.os.HandlerThread;

public class MyThread extends HandlerThread {
    private Handler mWorkerHandler;

    public MyThread(String name) {
        super(name);
    }

    public void postTask(Runnable task){
        mWorkerHandler.post(task);
    }

    public void prepareHandler(){
        mWorkerHandler = new Handler(getLooper());
    }
}
