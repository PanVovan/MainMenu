package com.poker.holdem.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.poker.holdem.LobbyContract;
import com.poker.holdem.R;

import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {
    private View view;

    @BindView(R.id.sign_in_btn) Button sign_in_btn;
    @BindView(R.id.name_et) EditText name_et;
    @BindView(R.id.password_et) EditText password_et;

    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.sign_in_btn)
    void tryAuth(){
        Logger.getAnonymousLogger().info("<--------clicked");
        String name = name_et.getText().toString().trim();
        String password = password_et.getText().toString().trim();
        ((LobbyContract.Registrator)getActivity()).sendMessageOnServerAuthPassword(name, password);
    }
}
