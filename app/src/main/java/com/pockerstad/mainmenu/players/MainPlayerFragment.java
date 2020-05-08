package com.pockerstad.mainmenu.players;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pockerstad.mainmenu.Player;
import com.pockerstad.mainmenu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPlayerFragment extends Fragment {

    Player player;

    public MainPlayerFragment(Player player) {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_player, container, false);
    }
}
