package com.pockerstad.mainmenu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.pockerstad.mainmenu.R;
import com.pockerstad.mainmenu.util.NavigationHost;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenuFragment extends Fragment {

    public static final String TAG = "MAIN_MENU_FRAGMENT";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        ImageButton startGameBtn = view.findViewById(R.id.bthStartGame);
        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new ChooseLobbyFragment(), true);
            }
        });

        ImageButton exitBtn = view.findViewById(R.id.btnExit);
        exitBtn.setOnClickListener(v -> getActivity().finish());

        ImageButton settingsBtn = view.findViewById(R.id.settingsBtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new SettingsFragment(), true);
            }
        });

        ImageButton helpBtn = view.findViewById(R.id.helpBtn);
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new HelpFragment(), true);
            }
        });

        return view;
    }

}
