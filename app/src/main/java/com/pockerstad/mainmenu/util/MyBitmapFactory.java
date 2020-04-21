package com.pockerstad.mainmenu.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

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

            Log.d("  rr", "   f");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
