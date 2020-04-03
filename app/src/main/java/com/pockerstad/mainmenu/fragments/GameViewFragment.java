package com.pockerstad.mainmenu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.pockerstad.mainmenu.R;
import com.pockerstad.mainmenu.grafic.surfaceview.GameAnimationView;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameViewFragment extends Fragment {

    GameAnimationView gameAnimationView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_view, container, false);

        gameAnimationView = (GameAnimationView) view.findViewById(R.id.gameAnimationView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        gameAnimationView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        gameAnimationView.pause();
    }
}
