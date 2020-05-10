package com.poker.holdem.view.activity;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.poker.holdem.R;
import com.poker.holdem.view.fragments.MainMenuFragment;
import com.poker.holdem.view.util.NavigationHost;

public class MainActivity extends AppCompatActivity implements NavigationHost {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //если активность пустая, то отображаем главное меню (пока не придумали что делать с регистрацией)
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_main_menu, new MainMenuFragment())
                    .commit();
        }
    }

    //реализуем интерфейс для дальнейшего использования
    @Override
    public void navigateTo(Fragment fragment, boolean addToBackstack) {
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
