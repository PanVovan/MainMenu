package com.poker.holdem.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.poker.holdem.R;
import com.poker.holdem.lobby.Room;
import com.poker.holdem.view.customparts.lobby_view.RoomRecyclerViewAdapter;
import com.poker.holdem.view.util.NavigationHost;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseLobbyFragment extends Fragment {

    @BindView(R.id.stat_of_menu) ImageView label;
    @BindView(R.id.private_games) ImageButton privateButton;
    @BindView(R.id.public_games) ImageButton publicButton;
    @BindView(R.id.btn_navigate_to_main_menu) ImageButton exitBtn;
    //Заглушка
    private ArrayList<Room> lobbies = new ArrayList<>();

    @BindView(R.id.lobby_container) RecyclerView recyclerView;
    private RoomRecyclerViewAdapter adapter;

    //конец заглушки

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_lobby, container, false);
        ButterKnife.bind(this, view);

        //Заглушка

        Room room = new Room();
        room.setName("III");
        room.setLength(9);
        room.setRate(88);

        lobbies.add(room);

        adapter = new RoomRecyclerViewAdapter(lobbies, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //конец заглушки

        return view;
    }

    @OnClick(R.id.public_games)
    void showPublicLobby() {
        label.setBackgroundResource(R.drawable.public_label);
    }
    @OnClick(R.id.btn_navigate_to_main_menu)
    void navigateToMainMenu (){
        ((NavigationHost) getActivity()).navigateTo(new MainMenuFragment(), false);
    }
}
