package com.poker.holdem.view.activity;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.poker.holdem.R;
import com.poker.holdem.view.fragments.ChooseLobbyFragment;
import com.poker.holdem.view.fragments.MainMenuFragment;
import com.poker.holdem.view.util.NavigationHost;

public class MainActivity extends AppCompatActivity implements NavigationHost {

    private ChooseLobbyFragment chooseLobbyFragment;
    private MainMenuFragment mainMenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainMenuFragment = new MainMenuFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_main_menu, mainMenuFragment)
                .commit();
    }

    //@Override
    //protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    //    super.onRestoreInstanceState(savedInstanceState);
    //    mainMenuFragment = new MainMenuFragment();
    //    getSupportFragmentManager()
    //            .beginTransaction()
    //            .add(R.id.container_main_menu, mainMenuFragment)
    //            .commit();
    //}

    @Override
    protected void onPause() {
        super.onPause();
        if (chooseLobbyFragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(chooseLobbyFragment)
                    .commit();
        }
        if (mainMenuFragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(mainMenuFragment)
                    .commit();
        }
        mainMenuFragment = null;
    }

    //реализуем интерфейс для дальнейшего использования
    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
        if(fragment instanceof ChooseLobbyFragment)
            this.chooseLobbyFragment = (ChooseLobbyFragment) fragment;
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_main_menu, fragment);

        if (addToBackstack) {
            //определяем, будет ли добавляться фрагмент в стек или нет
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }
}
