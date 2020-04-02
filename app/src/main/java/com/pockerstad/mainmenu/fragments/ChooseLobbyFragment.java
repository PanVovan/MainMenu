package com.pockerstad.mainmenu.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.pockerstad.mainmenu.R;
import com.pockerstad.mainmenu.activity.GameActivity;
import com.pockerstad.mainmenu.activity.MainActivity;
import com.pockerstad.mainmenu.customparts.lobby_view.Lobby;
import com.pockerstad.mainmenu.customparts.lobby_view.LobbyRecyclerViewAdapter;
import com.pockerstad.mainmenu.util.NavigationHost;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseLobbyFragment extends Fragment implements LobbyRecyclerViewAdapter.OnLobbyClickListener {

    private ImageView label;
    private ImageButton privateButton;
    private ImageButton publicButton;
    private ImageButton newLobbyButton;

    //Заглушка
    private ArrayList<Lobby> lobbies = new ArrayList<>();
    RecyclerView recyclerView;
    LobbyRecyclerViewAdapter adapter;

    //конец заглушки

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_lobby, container, false);

        //Заглушка
        recyclerView = view.findViewById(R.id.lobby_container);

        lobbies.add(new Lobby(3, 4));
        lobbies.add(new Lobby(5, 6));

        adapter = new LobbyRecyclerViewAdapter(lobbies, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //конец заглушки

        label = view.findViewById(R.id.stat_of_menu);

        ImageButton exitBtn = view.findViewById(R.id.btnExit);
        exitBtn.setOnClickListener(v -> ((NavigationHost) getActivity()).navigateTo(new MainMenuFragment(), false));

        privateButton = view.findViewById(R.id.private_games);
        privateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                label.setBackgroundResource(R.drawable.private_label);
            }
        });

        publicButton = view.findViewById(R.id.public_games);
        publicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                label.setBackgroundResource(R.drawable.public_label);
            }
        });

        newLobbyButton = view.findViewById(R.id.new_lobby);
        newLobbyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                label.setBackgroundResource(R.drawable.new_lobby_label);
            }
        });




        return view;
    }

    @Override
    public void onLobbyClicked(Lobby lobby) {
        startActivity(new Intent(getContext(), GameActivity.class));
    }
}
