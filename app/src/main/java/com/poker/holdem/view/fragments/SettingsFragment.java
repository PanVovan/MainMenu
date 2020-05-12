package com.poker.holdem.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.poker.holdem.R;
import com.poker.holdem.constants.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {


    private View view;

    @BindView(R.id.sign_out_btn) Button sign_out;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_settings_main_menu, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.sign_out_btn)
    void signOut(){

        getActivity().getSharedPreferences(
                Constants.PREFS_NAME
                ,Context.MODE_PRIVATE
        ).edit().clear().apply();
        //getActivity().getSharedPreferences(
        //        Constants.PREFS_NAME
        //        ,Context.MODE_PRIVATE
        //).edit()
        //        .putString(Constants.PLAYER_NAME, "")
        //        .putString(Constants.AUTH_TOKEN, "")
        //        .apply();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(
                        getActivity().getApplication()
                        , "Sign out successfully!"
                        , Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}
