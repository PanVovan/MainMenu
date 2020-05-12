package com.poker.holdem.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.poker.holdem.R;
import com.poker.holdem.view.util.NavigationHost;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthFragment extends Fragment {

    private  View view;

    @BindView(R.id.create_account_btn) Button create_acc_btn;
    @BindView(R.id.sign_in_btn) Button sign_in_btn;

    public AuthFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_auth, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.sign_in_btn)
    void startSignInFragment(){
        ((NavigationHost)getActivity()).navigateTo(new SignInFragment(), true);
    }

    @OnClick(R.id.create_account_btn)
    void startRegFragment(){
        ((NavigationHost)getActivity()).navigateTo(new CreateAccFragment(), true);
    }
}
