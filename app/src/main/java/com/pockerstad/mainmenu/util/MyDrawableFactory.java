package com.pockerstad.mainmenu.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.annotation.DrawableRes;

import com.bumptech.glide.Glide;


final public class MyDrawableFactory {

    public static Drawable getDrawableFromResources(Context context, @DrawableRes int id, int width, int height) {
        Drawable bitmap = null;
        try {
            bitmap = Glide.with(context)
                    .asDrawable()
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
