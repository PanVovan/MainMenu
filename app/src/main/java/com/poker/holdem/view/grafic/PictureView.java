package com.poker.holdem.view.grafic;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.poker.holdem.R;

public class PictureView {
    public static Drawable getPic(Context context, int picCode){
        switch (picCode){
            case 1:
                return ContextCompat.getDrawable(context, R.drawable.pokerist1);
            case 2:
                return ContextCompat.getDrawable(context, R.drawable.pokerist2);
            case 3:
                return ContextCompat.getDrawable(context, R.drawable.pokerist3);
            case 4:
                return ContextCompat.getDrawable(context, R.drawable.pokerist4);
            case 5:
                return ContextCompat.getDrawable(context, R.drawable.pokerist5);
            case 6:
                return ContextCompat.getDrawable(context, R.drawable.pokerist6);
            case 7:
                return ContextCompat.getDrawable(context, R.drawable.pokerist7);
            default:
                return ContextCompat.getDrawable(context, R.drawable.playerpic_default);
        }
    }
}
