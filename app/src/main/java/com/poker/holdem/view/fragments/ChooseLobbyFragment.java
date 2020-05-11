package com.poker.holdem.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.poker.holdem.LobbyContract;
import com.poker.holdem.PokerApplicationManager;
import com.poker.holdem.R;
import com.poker.holdem.server.deserialization.MyDeserializer;
import com.poker.holdem.server.deserialization.getlobbies.RespRoom;
import com.poker.holdem.server.deserialization.getlobbies.RespRooms;
import com.poker.holdem.view.customparts.lobby_view.RoomRecyclerViewAdapter;
import com.poker.holdem.view.util.NavigationHost;

import java.util.ArrayList;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseLobbyFragment extends Fragment implements LobbyContract.MenuLobbies {

    private Logger logger = Logger.getAnonymousLogger();

    @BindView(R.id.stat_of_menu) ImageView label;
    @BindView(R.id.private_games) Button privateButton;
    @BindView(R.id.public_games) Button publicButton;
    @BindView(R.id.btn_navigate_to_main_menu) Button exitBtn;

    private ArrayList<RespRoom> lobbies = new ArrayList<>();

    @BindView(R.id.lobby_container) RecyclerView recyclerView;
    private RoomRecyclerViewAdapter adapter;

    private Socket socket;

    private LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_lobby, container, false);
        ButterKnife.bind(this, view);


        adapter = new RoomRecyclerViewAdapter(lobbies, getContext());
        layoutManager = new LinearLayoutManager(getContext());
        sendMessageOnServerGetLobbies();



        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        socket.off("getlobbies", onGetLobbies);
    }

    private void setLobbies(){
        adapter.setRooms(lobbies);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @OnClick(R.id.public_games)
    void showPublicLobby() {
        label.setBackgroundResource(R.drawable.public_label);
    }

    @OnClick(R.id.btn_navigate_to_main_menu)
    void navigateToMainMenu (){
        ((NavigationHost) getActivity()).navigateTo(new MainMenuFragment(), false);
    }

    @Override
    public void sendMessageOnServerGetLobbies() {
        PokerApplicationManager applicationManager = (PokerApplicationManager)getActivity().getApplication();
        if(applicationManager.getSocket() != null){
            socket = applicationManager.getSocket();
            socket.connect();
            socket.on("getlobbies", onGetLobbies);
            socket.emit("getlobbies");
        }else
            Logger.getAnonymousLogger().info("PokerApplicationManager return null object! ChooseLobby");
    }

    private Emitter.Listener onGetLobbies = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            getActivity().runOnUiThread(() -> {
                RespRooms roomsObject = MyDeserializer.desGetLobbiesResponce(args[0].toString());
                lobbies.addAll(roomsObject.getRooms());
                logger.info("<-------------got responce"+roomsObject.getRooms().get(0).getName());
                setLobbies();
            });
        }
    };
}
