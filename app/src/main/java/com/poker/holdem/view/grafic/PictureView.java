package com.poker.holdem.view.grafic;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.poker.holdem.R;

public class PictureView {
    public static Drawable getPic(Context context, int picCode){
        //TODO:сделать больше аватарок
        switch (picCode){
            default:
                return ContextCompat.getDrawable(context, R.drawable.playerpic_default);
        }
    }
}
