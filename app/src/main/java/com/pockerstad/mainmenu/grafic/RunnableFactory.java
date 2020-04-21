package com.pockerstad.mainmenu.grafic;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.ArrayList;

public class RunnableFactory {
    public static Runnable drawAnim(ArrayList<BitmapForDraw> bitmaps, BitmapForDraw bitmap, int reqX, int reqY, SurfaceHolder holder, Paint paint){
        return () -> {
            Canvas canvas;
            while (bitmap.getPosX() != reqX && bitmap.getPosY() != reqY) {
                canvas = holder.lockCanvas();
                if (!bitmaps.isEmpty()) {
                    for (BitmapForDraw element : bitmaps) {
                        canvas.drawBitmap(element.getBitmap(), element.getPosX(), element.getPosY(), paint);
                    }
                }

                if (bitmap.getPosX() > reqX) {
                    bitmap.setPosX(bitmap.getPosX() - 1);
                }

                if (bitmap.getPosX() < reqX) {
                    bitmap.setPosX(bitmap.getPosX() + 1);
                }

                if (bitmap.getPosY() > reqY) {
                    bitmap.setPosY(bitmap.getPosY() - 1);
                }

                if (bitmap.getPosX() < reqX) {
                    bitmap.setPosY(bitmap.getPosY() + 1);
                }
                canvas.drawBitmap(bitmap.getBitmap(), bitmap.getPosX(), bitmap.getPosY(), paint);
                holder.unlockCanvasAndPost(canvas);
            }
        };
    }
}
