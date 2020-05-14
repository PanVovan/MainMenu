package com.poker.holdem.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.poker.holdem.R;
import com.poker.holdem.constants.Constants;
import com.poker.holdem.view.grafic.PictureView;
import com.poker.holdem.view.util.NavigationHost;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenuFragment extends Fragment {

    public static final String TAG = "MAIN_MENU_FRAGMENT";

    @BindView(R.id.bthStartGame) ImageButton startGameBtn;
    @BindView(R.id.btn_navigate_to_main_menu) ImageButton exitBtn;
    @BindView(R.id.settingsBtn) ImageButton settingsBtn;
    @BindView(R.id.helpBtn) ImageButton helpBtn;
    @BindView(R.id.money) TextView playerMoney;
    @BindView(R.id.menu_player_pic) ImageView player_pic;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        ButterKnife.bind(this, view);
        //хз, тут ошибку кидает
        //playerMoney.setText(
        //        getActivity()
        //        .getIntent()
        //         .getIntExtra("money", 0)
        //);
        //player_pic.setBackground(PictureView.getPic(
        //        getContext()
        //        ,getActivity()
        //                .getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)
        //                .getInt(Constants.PLAYER_PICTURE, 1)
        //));
        return view;
    }

    @OnClick(R.id.bthStartGame)
    void startFindLobbies () {
        ((NavigationHost) getActivity()).navigateTo(new ChooseLobbyFragment(), true);
    }

    @OnClick(R.id.btn_navigate_to_main_menu)
    void exit(){
        getActivity().finish();
    }

    @OnClick(R.id.settingsBtn)
    void settings(){
        ((NavigationHost) getActivity()).navigateTo(new SettingsFragment(), true);
    }

    @OnClick(R.id.helpBtn)
    void help(){
        ((NavigationHost) getActivity()).navigateTo(new HelpFragment(), true);
    }

}
