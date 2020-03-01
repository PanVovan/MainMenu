package com.pockerstad.mainmenu.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pockerstad.mainmenu.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseLobbyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_choose_lobby, container, false);
    }


}
