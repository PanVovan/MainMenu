package com.poker.holdem.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.poker.holdem.GameContract;
import com.poker.holdem.R;
import com.poker.holdem.view.fragments.GameViewFragment;
import com.poker.holdem.view.util.NavigationHost;

public class GameActivity extends AppCompatActivity implements NavigationHost {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_game, new GameViewFragment(getIntent().getStringExtra("room")))
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

    //@Override
    public void clearCards(int typeOfClear) {

    }

   // @Override
    public void setCardView(int action, int card) {

    }

    //@Override
    public void setOpponentView(int pos, String name, Integer money, int picture) {

    }

    //@Override
    public void setPlayerView(Integer money, int picture) {

    }
}
