package com.pockerstad.mainmenu.fragments;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.pockerstad.mainmenu.R;
import com.pockerstad.mainmenu.grafic.surfaceview.GameAnimationView;
import com.pockerstad.mainmenu.util.HandlerMessages;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameViewFragment extends Fragment {

    GameAnimationView gameAnimationView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_view, container, false);

        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(size);
        gameAnimationView = view.findViewById(R.id.gameAnimationView);
        gameAnimationView.setSize(size.x, size.y);


        ImageButton addCard = view.findViewById(R.id.raise_button);
        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameAnimationView.handleMessage(Message.obtain(gameAnimationView.getHandler(), HandlerMessages.ADD_FIRST_HOLE_HAND));
            }
        });

        return view;
    }
}
