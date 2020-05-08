package com.pockerstad.mainmenu.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.pockerstad.mainmenu.R;

public final class CardViewFactory {

    public static Drawable initView(Context context, int width) {
        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.card_back, width, (int)(1.5*width));
    }

    public static Drawable initView(Context context, int cardCode, int width) {

        switch (cardCode%100) {

            case 2:
                switch (cardCode/100) {

                    case 1:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.two_diamonds, width, (int)(1.5*width));
                    case 2:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.two_clubs, width, (int)(1.5*width));
                    case 3:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.two_hearts, width, (int)(1.5*width));
                    case 4:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.two_spades, width, (int)(1.5*width));
                }
            case 3:
                switch (cardCode/100) {

                    case 1:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.three_diamonds, width, (int)(1.5*width));
                    case 2:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.three_clubs, width, (int)(1.5*width));
                    case 3:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.three_hearts, width, (int)(1.5*width));
                    case 4:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.three_spades, width, (int)(1.5*width));
                }
            case 4:
                switch (cardCode/100) {

                    case 1:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.four_diamonds, width, (int)(1.5*width));
                    case 2:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.four_clubs, width, (int)(1.5*width));
                    case 3:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.four_hearts, width, (int)(1.5*width));
                    case 4:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.four_spades, width, (int)(1.5*width));
                }
            case 5:
                switch (cardCode/100) {

                    case 1:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.five_diamonds, width, (int)(1.5*width));
                    case 2:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.five_clubs, width, (int)(1.5*width));
                    case 3:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.five_hearts, width, (int)(1.5*width));
                    case 4:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.five_spades, width, (int)(1.5*width));
                }
            case 6:
                switch (cardCode/100) {

                    case 1:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.six_diamonds, width, (int)(1.5*width));
                    case 2:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.six_clubs, width, (int)(1.5*width));
                    case 3:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.six_hearts, width, (int)(1.5*width));
                    case 4:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.six_spades, width, (int)(1.5*width));
                }
            case 7:
                switch (cardCode/100) {

                    case 1:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.seven_diamonds, width, (int)(1.5*width));
                    case 2:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.seven_clubs, width, (int)(1.5*width));
                    case 3:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.seven_hearts, width, (int)(1.5*width));
                    case 4:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.seven_spades, width, (int)(1.5*width));
                }
                break;
            case 8:
                switch (cardCode/100) {

                    case 1:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.eight_diamonds, width, (int)(1.5*width));
                    case 2:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.eight_clubs, width, (int)(1.5*width));
                    case 3:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.eight_hearts, width, (int)(1.5*width));
                    case 4:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.eight_spades, width, (int)(1.5*width));
                }
            case 9:
                switch (cardCode/100) {

                    case 1:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.nine_diamonds, width, (int)(1.5*width));
                    case 2:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.nine_clubs, width, (int)(1.5*width));
                    case 3:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.nine_hearts, width, (int)(1.5*width));
                    case 4:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.nine_spades, width, (int)(1.5*width));
                }
            case 10:
                switch (cardCode/100) {

                    case 1:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.ten_diamonds, width, (int)(1.5*width));
                    case 2:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.ten_clubs, width, (int)(1.5*width));
                    case 3:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.ten_hearts, width, (int)(1.5*width));
                    case 4:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.ten_spades, width, (int)(1.5*width));
                }
            case 11:
                switch (cardCode/100) {

                    case 1:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.jack_diamonds, width, (int)(1.5*width));
                    case 2:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.jack_clubs, width, (int)(1.5*width));
                    case 3:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.jack_hearts, width, (int)(1.5*width));
                    case 4:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.jack_spades, width, (int)(1.5*width));
                }
            case 12:
                switch (cardCode/100) {

                    case 1:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.queen_diamonds, width, (int)(1.5*width));
                    case 2:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.queen_clubs, width, (int)(1.5*width));
                    case 3:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.queen_hearts, width, (int)(1.5*width));
                    case 4:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.queen_spades, width, (int)(1.5*width));
                }
            case 13:
                switch (cardCode/100) {

                    case 1:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.king_diamonds, width, (int)(1.5*width));
                    case 2:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.king_clubs, width, (int)(1.5*width));
                    case 3:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.king_hearts, width, (int)(1.5*width));
                    case 4:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.king_spades, width, (int)(1.5*width));
                }
            case 14:
                switch (cardCode/100) {

                    case 1:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.ace_diamonds, width, (int)(1.5*width));
                    case 2:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.ace_clubs, width, (int)(1.5*width));
                    case 3:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.ace_hearts, width, (int)(1.5*width));
                    case 4:
                        return MyDrawableFactory.getDrawableFromResources(context, R.drawable.ace_spades, width, (int)(1.5*width));
                }
        }
        return null;
    }

}
