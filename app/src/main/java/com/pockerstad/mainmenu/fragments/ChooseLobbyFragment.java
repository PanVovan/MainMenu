package com.pockerstad.mainmenu.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.pockerstad.mainmenu.R;
import com.pockerstad.mainmenu.util.NavigationHost;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseLobbyFragment extends Fragment {

    private ImageView label;
    ImageButton privateButton;
    ImageButton publicButton;
    ImageButton newLobbyButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_lobby, container, false);

        label = view.findViewById(R.id.stat_of_menu);

        ImageButton exitBtn = view.findViewById(R.id.btnExit);
        exitBtn.setOnClickListener(v -> ((NavigationHost) getActivity()).navigateTo(new MainMenuFragment(), false));

        privateButton = view.findViewById(R.id.private_games);
        privateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                label.setBackgroundResource(R.drawable.private_label);
            }
        });

        publicButton = view.findViewById(R.id.public_games);
        publicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                label.setBackgroundResource(R.drawable.public_label);
            }
        });

        newLobbyButton = view.findViewById(R.id.new_lobby);
        newLobbyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                label.setBackgroundResource(R.drawable.new_lobby_label);
            }
        });

        return view;
    }


}
