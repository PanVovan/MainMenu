package com.pockerstad.mainmenu.fragments;

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
import com.pockerstad.mainmenu.customview.Lobby;
import com.pockerstad.mainmenu.customview.LobbyRecycleAdapter;
import com.pockerstad.mainmenu.util.NavigationHost;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseLobbyFragment extends Fragment {

    private ImageView label;
    ImageButton privateButton;
    ImageButton publicButton;
    ImageButton newLobbyButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_lobby, container, false);

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

        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.lobby_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Lobby> l = new ArrayList<>();
        l.add(new Lobby("MyLobby1", "lobby.123", "1"));
        l.add(new Lobby("MyLobby2", "lobby.321", "2"));
        l.add(new Lobby("MyLobby3", "lobby.234", "3"));
        l.add(new Lobby("MyLobby4", "lobby.432", "4"));
        l.add(new Lobby("MyLobby5", "lobby.345", "5"));
        l.add(new Lobby("MyLobby6", "lobby.543", "5"));
        l.add(new Lobby("MyLobby7", "lobby.345", "5"));
        l.add(new Lobby("MyLobby8", "lobby.543", "3"));
        l.add(new Lobby("MyLobby9", "lobby.456", "4"));
        l.add(new Lobby("MyLobby10", "lobby.654", "2"));
        l.add(new Lobby("MyLobby11", "lobby.567", "3"));
        l.add(new Lobby("MyLobby12", "lobby.765", "5"));

        LobbyRecycleAdapter adapter = new LobbyRecycleAdapter(getContext(), l);
        recyclerView.setAdapter(adapter);

        return view;
    }


}
