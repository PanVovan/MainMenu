package com.pockerstad.mainmenu.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

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

        SurfaceView sv = view.findViewById(R.id.gameAnimationView);
        gameAnimationView = new GameAnimationView(getContext(), sv);

        return view;
    }
}
