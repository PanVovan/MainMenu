package com.pockerstad.mainmenu.fragments;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;

import androidx.fragment.app.Fragment;

import com.pockerstad.mainmenu.R;
import com.pockerstad.mainmenu.grafic.GameView;
import com.pockerstad.mainmenu.util.HandlerMessages;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameViewFragment extends Fragment {

    GameView game;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_view, container, false);

        Point point = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(point);
        game = view.findViewById(R.id.game_view_surface);
        game.SET_SCREEN(point.x, point.y);

        ImageButton imageButton = view.findViewById(R.id.raise_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              game.addItem();
            }
        });


        return view;
    }
}
