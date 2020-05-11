package com.poker.holdem.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.poker.holdem.LobbyContract;
import com.poker.holdem.R;
import com.poker.holdem.view.util.NavigationHost;

public class LoginActivity extends AppCompatActivity implements LobbyContract.Registrator, NavigationHost {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    @Override
    public void sendMessageOnServerRegister() {

    }

    @Override
    public void sendMessageOnServerAuth() {

    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {

    }
}
