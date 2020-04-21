package com.pockerstad.mainmenu.grafic;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.SurfaceHolder;


import androidx.annotation.NonNull;

import com.pockerstad.mainmenu.R;
import com.pockerstad.mainmenu.util.CardViewFactory;
import com.pockerstad.mainmenu.util.HandlerMessages;
import com.pockerstad.mainmenu.util.MyBitmapFactory;

import java.util.ArrayList;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {



    private static GameThread gameThread;
    Context context;

    private int SCREEN_X;
    private int SCREEN_Y;

    public void SET_SCREEN(int x, int y) {
        this.SCREEN_X = x;
        this.SCREEN_Y = y;
    }

    public GameView(Context context) {
        this(context, null);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        this.context = context;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameThread = new GameThread(holder);
        gameThread.start();
        gameThread.setHandler();
    }



    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        gameThread.quit();
        gameThread = null;
    }


    public void addItem() {
        gameThread.addItem();
    }



    public class GameThread extends HandlerThread  implements  Handler.Callback{


        private Paint mPaint;
        private Handler mReceiver;
        private SurfaceHolder holder;
        ArrayList<BitmapForDraw> bitmapForDrawArrayList;


        public GameThread(SurfaceHolder holder) {
            super("DrawingThread");
            mPaint= new Paint();
            this.holder = holder;
            bitmapForDrawArrayList = new ArrayList<>();

        }

        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.what == HandlerMessages.ADD_FIRST_HOLE_HAND){
                BitmapForDraw card = new BitmapForDraw(CardViewFactory.initView(context, 403, 120), 0, 0);
                BitmapForDraw bitmapForDraw = new BitmapForDraw(MyBitmapFactory.getBitmapFromResources(context, R.drawable.background_casino, SCREEN_X, SCREEN_Y), 0, 0);
                bitmapForDrawArrayList.add(bitmapForDraw);
                mReceiver.post(RunnableFactory.drawAnim(bitmapForDrawArrayList, card, 800, 500, holder, mPaint));
                bitmapForDrawArrayList.add(card);
            }
            return false;
        }


        public void addItem() {
            mReceiver.sendEmptyMessage(HandlerMessages.ADD_FIRST_HOLE_HAND);
        }


        public void setHandler() {
            this.mReceiver = new Handler(getLooper(),this);
        }
    }

}
