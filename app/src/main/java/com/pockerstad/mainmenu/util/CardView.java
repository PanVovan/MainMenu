package com.pockerstad.mainmenu.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.pockerstad.mainmenu.R;
import com.pockerstad.mainmenu.logic.card.Card;

public class CardView {

    public static Bitmap initView(boolean isVisible, Context context) {
        Bitmap cardView = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outHeight = 120;
        options.outWidth = 83;
        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.card_back, options);
        return cardView;
    }

    public static Bitmap initView(Card card, Context context) {
        Bitmap cardView = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outHeight = 120;
        options.outWidth = 83;

        switch (card.getRank()) {

            case TWO:
                switch (card.getSuit()) {

                    case DIAMONDS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.two_diamonds, options);
                        break;
                    case CLUBS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.two_clubs, options);
                        break;
                    case HEARTS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.two_hearts, options);
                        break;
                    case SPADES:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.two_spades, options);
                        break;
                }
                break;
            case THREE:
                switch (card.getSuit()) {

                    case DIAMONDS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.three_diamonds, options);
                        break;
                    case CLUBS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.three_clubs, options);
                        break;
                    case HEARTS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.three_hearts, options);
                        break;
                    case SPADES:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.three_spades, options);
                        break;
                }
                break;
            case FOUR:
                switch (card.getSuit()) {

                    case DIAMONDS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.four_diamonds, options);
                        break;
                    case CLUBS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.four_clubs, options);
                        break;
                    case HEARTS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.four_hearts, options);
                        break;
                    case SPADES:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.four_spades, options);
                        break;
                }
                break;
            case FIVE:
                switch (card.getSuit()) {

                    case DIAMONDS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.five_diamonds, options);
                        break;
                    case CLUBS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.five_clubs, options);
                        break;
                    case HEARTS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.five_hearts, options);
                        break;
                    case SPADES:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.five_spades, options);
                        break;
                }
                break;
            case SIX:
                switch (card.getSuit()) {

                    case DIAMONDS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.six_diamonds, options);
                        break;
                    case CLUBS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.six_clubs, options);
                        break;
                    case HEARTS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.six_hearts, options);
                        break;
                    case SPADES:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.five_spades, options);
                        break;
                }
                break;
            case SEVEN:
                switch (card.getSuit()) {

                    case DIAMONDS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.seven_diamonds, options);
                        break;
                    case CLUBS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.seven_clubs, options);
                        break;
                    case HEARTS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.seven_hearts, options);
                        break;
                    case SPADES:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.seven_spades, options);
                        break;
                }
                break;
            case EIGHT:
                switch (card.getSuit()) {

                    case DIAMONDS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.eight_diamonds, options);
                        break;
                    case CLUBS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.eight_clubs, options);
                        break;
                    case HEARTS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.eight_hearts, options);
                        break;
                    case SPADES:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.eight_spades, options);
                        break;
                }
                break;
            case NINE:
                switch (card.getSuit()) {

                    case DIAMONDS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.nine_diamonds, options);
                        break;
                    case CLUBS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.nine_clubs, options);
                        break;
                    case HEARTS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.nine_hearts, options);
                        break;
                    case SPADES:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.nine_spades, options);
                        break;
                }
                break;
            case TEN:
                switch (card.getSuit()) {

                    case DIAMONDS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.ten_diamonds, options);
                        break;
                    case CLUBS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.ten_clubs, options);
                        break;
                    case HEARTS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.ten_hearts, options);
                        break;
                    case SPADES:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.ten_spades, options);
                        break;
                }
                break;
            case JACK:
                switch (card.getSuit()) {

                    case DIAMONDS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.jack_diamonds, options);
                        break;
                    case CLUBS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.jack_clubs, options);
                        break;
                    case HEARTS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.jack_hearts, options);
                        break;
                    case SPADES:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.jack_spades, options);
                        break;
                }
                break;
            case QUEEN:
                switch (card.getSuit()) {

                    case DIAMONDS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.queen_diamonds, options);
                        break;
                    case CLUBS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.queen_clubs, options);
                        break;
                    case HEARTS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.queen_hearts, options);
                        break;
                    case SPADES:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.queen_spades, options);
                        break;
                }
                break;
            case KING:
                switch (card.getSuit()) {

                    case DIAMONDS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.king_diamonds, options);
                        break;
                    case CLUBS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.king_clubs, options);
                        break;
                    case HEARTS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.king_hearts, options);
                        break;
                    case SPADES:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.king_spades, options);
                        break;
                }
                break;
            case ACE:
                switch (card.getSuit()) {

                    case DIAMONDS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.ace_diamonds, options);
                        break;
                    case CLUBS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.ace_clubs, options);
                        break;
                    case HEARTS:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.ace_hearts, options);
                        break;
                    case SPADES:
                        cardView = BitmapFactory.decodeResource(context.getResources(), R.drawable.ace_spades, options);
                        break;
                }
                break;
        }
        return cardView;
    }

}
