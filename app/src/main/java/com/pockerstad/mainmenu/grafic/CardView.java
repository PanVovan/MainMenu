package com.pockerstad.mainmenu.grafic;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.pockerstad.mainmenu.R;

public class CardView {

    public static Drawable initDrawableInvisibleCard(Context context){
        return ContextCompat.getDrawable(context, R.drawable.card_back);
    }

    public static Drawable initDrawableVisibleCard(Context context, int cardCode){
        switch (cardCode%100) {

            case 2:
                switch (cardCode/100) {

                    case 1:
                        return ContextCompat.getDrawable(context, R.drawable.two_diamonds);
                    case 2:
                        return ContextCompat.getDrawable(context, R.drawable.two_clubs);
                    case 3:
                        return ContextCompat.getDrawable(context, R.drawable.two_hearts);
                    case 4:
                        return ContextCompat.getDrawable(context, R.drawable.two_spades);
                }
            case 3:
                switch (cardCode/100) {

                    case 1:
                        return ContextCompat.getDrawable(context, R.drawable.three_diamonds);
                    case 2:
                        return ContextCompat.getDrawable(context, R.drawable.three_clubs);
                    case 3:
                        return ContextCompat.getDrawable(context, R.drawable.three_hearts);
                    case 4:
                        return ContextCompat.getDrawable(context, R.drawable.three_spades);
                }
            case 4:
                switch (cardCode/100) {

                    case 1:
                        return ContextCompat.getDrawable(context, R.drawable.four_diamonds);
                    case 2:
                        return ContextCompat.getDrawable(context, R.drawable.four_clubs);
                    case 3:
                        return ContextCompat.getDrawable(context, R.drawable.four_hearts);
                    case 4:
                        return ContextCompat.getDrawable(context, R.drawable.four_spades);
                }
            case 5:
                switch (cardCode/100) {

                    case 1:
                        return ContextCompat.getDrawable(context, R.drawable.five_diamonds);
                    case 2:
                        return ContextCompat.getDrawable(context, R.drawable.five_clubs);
                    case 3:
                        return ContextCompat.getDrawable(context, R.drawable.five_hearts);
                    case 4:
                        return ContextCompat.getDrawable(context, R.drawable.five_spades);
                }
            case 6:
                switch (cardCode/100) {

                    case 1:
                        return ContextCompat.getDrawable(context, R.drawable.six_diamonds);
                    case 2:
                        return ContextCompat.getDrawable(context, R.drawable.six_clubs);
                    case 3:
                        return ContextCompat.getDrawable(context, R.drawable.six_hearts);
                    case 4:
                        return ContextCompat.getDrawable(context, R.drawable.six_spades);
                }
            case 7:
                switch (cardCode/100) {

                    case 1:
                        return ContextCompat.getDrawable(context, R.drawable.seven_diamonds);
                    case 2:
                        return ContextCompat.getDrawable(context, R.drawable.seven_clubs);
                    case 3:
                        return ContextCompat.getDrawable(context, R.drawable.seven_hearts);
                    case 4:
                        return ContextCompat.getDrawable(context, R.drawable.seven_spades);
                }
                break;
            case 8:
                switch (cardCode/100) {

                    case 1:
                        return ContextCompat.getDrawable(context, R.drawable.eight_diamonds);
                    case 2:
                        return ContextCompat.getDrawable(context, R.drawable.eight_clubs);
                    case 3:
                        return ContextCompat.getDrawable(context, R.drawable.eight_hearts);
                    case 4:
                        return ContextCompat.getDrawable(context, R.drawable.eight_spades);
                }
            case 9:
                switch (cardCode/100) {

                    case 1:
                        return ContextCompat.getDrawable(context, R.drawable.nine_diamonds);
                    case 2:
                        return ContextCompat.getDrawable(context, R.drawable.nine_clubs);
                    case 3:
                        return ContextCompat.getDrawable(context, R.drawable.nine_hearts);
                    case 4:
                        return ContextCompat.getDrawable(context, R.drawable.nine_spades);
                }
            case 10:
                switch (cardCode/100) {

                    case 1:
                        return ContextCompat.getDrawable(context, R.drawable.ten_diamonds);
                    case 2:
                        return ContextCompat.getDrawable(context, R.drawable.ten_clubs);
                    case 3:
                        return ContextCompat.getDrawable(context, R.drawable.ten_hearts);
                    case 4:
                        return ContextCompat.getDrawable(context, R.drawable.ten_spades);
                }
            case 11:
                switch (cardCode/100) {

                    case 1:
                        return ContextCompat.getDrawable(context, R.drawable.jack_diamonds);
                    case 2:
                        return ContextCompat.getDrawable(context, R.drawable.jack_clubs);
                    case 3:
                        return ContextCompat.getDrawable(context, R.drawable.jack_hearts);
                    case 4:
                        return ContextCompat.getDrawable(context, R.drawable.jack_spades);
                }
            case 12:
                switch (cardCode/100) {

                    case 1:
                        return ContextCompat.getDrawable(context, R.drawable.queen_diamonds);
                    case 2:
                        return ContextCompat.getDrawable(context, R.drawable.queen_clubs);
                    case 3:
                        return ContextCompat.getDrawable(context, R.drawable.queen_hearts);
                    case 4:
                        return ContextCompat.getDrawable(context, R.drawable.queen_spades);
                }
            case 13:
                switch (cardCode/100) {

                    case 1:
                        return ContextCompat.getDrawable(context, R.drawable.king_diamonds);
                    case 2:
                        return ContextCompat.getDrawable(context, R.drawable.king_clubs);
                    case 3:
                        return ContextCompat.getDrawable(context, R.drawable.king_hearts);
                    case 4:
                        return ContextCompat.getDrawable(context, R.drawable.king_spades);
                }
            case 14:
                switch (cardCode/100) {

                    case 1:
                        return ContextCompat.getDrawable(context, R.drawable.ace_diamonds);
                    case 2:
                        return ContextCompat.getDrawable(context, R.drawable.ace_clubs);
                    case 3:
                        return ContextCompat.getDrawable(context, R.drawable.ace_hearts);
                    case 4:
                        return ContextCompat.getDrawable(context, R.drawable.ace_spades);
                }
        }
        return null;
    }
}
