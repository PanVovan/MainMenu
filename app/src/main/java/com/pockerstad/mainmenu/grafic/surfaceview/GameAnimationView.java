package com.pockerstad.mainmenu.grafic.surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.pockerstad.mainmenu.R;
import com.pockerstad.mainmenu.util.HandlerMessages;
import com.pockerstad.mainmenu.util.MyBitmapFactory;


public class GameAnimationView extends SurfaceView implements SurfaceHolder.Callback, Handler.Callback {

    private static final String LOGTAG = "Game Animation";
    private GameAnimationThread gameThread = null;
    private SurfaceHolder holder;

    private int sizeX;
    private int sizeY;

    public void setSize(int x, int y){
        sizeX = x;
        sizeY = y;
    }

    public GameAnimationView(Context context) {
        this(context, null);
    }


    public GameAnimationView(Context context, AttributeSet attr) {
        super(context, attr);
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameThread = new GameAnimationThread("name");
        gameThread.start();
        gameThread.prepareHandler();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        switch (msg.what){
            case HandlerMessages.ADD_FIRST_HOLE_HAND:
                gameThread.postTask(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = MyBitmapFactory.getBitmapFromResources(getContext(), R.drawable.background_casino, sizeX , sizeY);
                        Canvas canvas = holder.lockCanvas();
                        canvas.drawBitmap(bitmap, 0 , 0, new Paint());
                        holder.unlockCanvasAndPost(canvas);
                    }
                });
        }
        return false;
    }

    //Чтоб в дальнейшем можно было ченибудь отрисовывать по сообщению, и для взаимодействия с фрагментов
    class GameAnimationThread extends HandlerThread {

        Handler mWorkerHandler;

        public GameAnimationThread(String name) {
            super(name);
        }

        public void postTask(Runnable task){
            mWorkerHandler.post(task);
        }

        public void prepareHandler(){
            mWorkerHandler = new Handler(getLooper());
        }
    }
}
