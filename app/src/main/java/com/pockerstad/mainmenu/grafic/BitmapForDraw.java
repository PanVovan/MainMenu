package com.pockerstad.mainmenu.grafic;

import android.graphics.Bitmap;

public class BitmapForDraw {
    public BitmapForDraw(Bitmap bitmap, int posX, int posY) {
        this.bitmap = bitmap;
        this.posX = posX;
        this.posY = posY;
    }

    private Bitmap bitmap;
    private int posX;
    private int posY;

    public void setPosX(int posX) {
        this.posX = posX;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }


    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
}
