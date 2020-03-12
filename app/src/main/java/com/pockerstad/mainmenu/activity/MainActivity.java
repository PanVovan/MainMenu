package com.pockerstad.mainmenu.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.pockerstad.mainmenu.R;
import com.pockerstad.mainmenu.fragments.MainMenuFragment;
import com.pockerstad.mainmenu.util.NavigationHost;

public class MainActivity extends AppCompatActivity implements NavigationHost {

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //задаем горизонтальную ориентацию
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);

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
