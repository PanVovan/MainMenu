package com.pockerstad.mainmenu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.pockerstad.mainmenu.Player;
import com.pockerstad.mainmenu.R;
import com.pockerstad.mainmenu.customparts.lobby_view.Lobby;
import com.pockerstad.mainmenu.grafic.CardView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameViewFragment extends Fragment {


    ImageView firstHoleCard, secondHoleCard;
    ImageView firstCommunityCard, secondCommunityCard, thirdCommunityCard, fourthCommunityCard, fifthCommunityCard;

    ImageView firstOpponentFirstCard, firstOpponentSecondCard;
    ImageView secondOpponentFirstCard, secondOpponentSecondCard;
    ImageView thirdOpponentFirstCard, thirdOpponentSecondCard;
    ImageView fourthOpponentFirstCard, fourthOpponentSecondCard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_view, container, false);

        ImageView imageView = view.findViewById(R.id.first_community_card);
        imageView.setBackground(CardView.initDrawableVisibleCard(getContext(), 404));


        ImageButton imageButton = view.findViewById(R.id.raise_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setBackground(CardView.initDrawableVisibleCard(getContext(), 414));
            }
        });



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


        /*
        switch(SocketCommand)
            case ADD_FIRST_HOLE_HAND:
                imageView.setBackground(CardView.initDrawableVisibleCard(getContext(), socketCardNum));
                builder.addHoleCard(Optional.of(new Card(socketCardNum)));
            case ADD_FIRST_OPPONENT_FIRST_HOLE_HAND:
                imageView.setBackground(CardView.initDrawableVisibleCard(getContext(), socketCardNum));

            case ADD_SECOND_OPPONENT_FIRST_HOLE_HAND:
                imageView.setBackground(CardView.initDrawableVisibleCard(getContext(), socketCardNum));

            case ADD_THIRD_OPPONENT_FIRST_HOLE_HAND:
                imageView.setBackground(CardView.initDrawableVisibleCard(getContext(), socketCardNum));

            case ADD_FOURTH_OPPONENT_FIRST_HOLE_HAND:
                imageView.setBackground(CardView.initDrawableVisibleCard(getContext(), socketCardNum));

            case ADD_SECOND_HOLE_HAND:
                imageView.setBackground(CardView.initDrawableVisibleCard(getContext(), socketCardNum));
                builder.addHoleCard(Optional.of(new Card(socketCardNum)));
            case ADD_FIRST_OPPONENT_SECOND_HOLE_HAND:
                imageView.setBackground(CardView.initDrawableVisibleCard(getContext(), socketCardNum));

            case ADD_SECOND_OPPONENT_SECOND_HOLE_HAND:
                imageView.setBackground(CardView.initDrawableVisibleCard(getContext(), socketCardNum));

            case ADD_THIRD_OPPONENT_SECOND_HOLE_HAND:
                imageView.setBackground(CardView.initDrawableVisibleCard(getContext(), socketCardNum));

            case ADD_FOURTH_OPPONENT_SECOND_HOLE_HAND:
                imageView.setBackground(CardView.initDrawableVisibleCard(getContext(), socketCardNum));

            case ADD_COMMUNITY_CARD_FIRST:
                imageView.setBackground(CardView.initDrawableVisibleCard(getContext(), socketCardNum));
                builder.addCommunityCard(Optional.of(new Card(socketCardNum)));
            case ADD_COMMUNITY_CARD_SECOND:
                imageView.setBackground(CardView.initDrawableVisibleCard(getContext(), socketCardNum));
                builder.addCommunityCard(Optional.of(new Card(socketCardNum)));
            case ADD_COMMUNITY_CARD_THIRD:
                imageView.setBackground(CardView.initDrawableVisibleCard(getContext(), socketCardNum));
                builder.addCommunityCard(Optional.of(new Card(socketCardNum)));
            case ADD_COMMUNITY_CARD_FOURTH:
                imageView.setBackground(CardView.initDrawableVisibleCard(getContext(), socketCardNum));
                builder.addCommunityCard(Optional.of(new Card(socketCardNum)));
            case ADD_COMMUNITY_CARD_FIFTH:
                imageView.setBackground(CardView.initDrawableVisibleCard(getContext(), socketCardNum));
                builder.addCommunityCard(Optional.of(new Card(socketCardNum))) ;

            case SHOW_CARDS_FIRST_OPPONENT:


            case SHOW_CARDS_SECOND_OPPONENT:


            case SHOW_CARDS_THIRD_OPPONENT:


            case SHOW_CARDS_FOURTH_OPPONENT:



        */
    }




}
