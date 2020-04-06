package com.pockerstad.mainmenu.util;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.DrawableRes;

import com.bumptech.glide.Glide;

final public class MyBitmapFactory {

    public static Bitmap getBitmapFromResources (Context context, @DrawableRes int id, int width, int height) {
        Bitmap bitmap = null;
        try {
            bitmap = Glide.with(context)
                    .asBitmap()
                    .load(id)
                    .override(width, height)
                    .centerCrop()
                    .submit()
                    .get();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
