package com.poker.holdem.view.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
import java.util.Objects;
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
    //@BindView(R.id.private_games) Button privateButton;
    @BindView(R.id.reload_lobbies_btn) Button reloadLobbiesBtn;
    @BindView(R.id.btn_navigate_to_main_menu) Button exitBtn;
    //@BindView(R.id.clear_lobbies) Button clear_lobbies_button;
    @BindView(R.id.new_lobby) Button newLobby;

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

    private void setLobbies(){
        adapter.setRooms(lobbies);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @OnClick(R.id.reload_lobbies_btn)
    void reloadLobbies() {
        sendMessageOnServerGetLobbies();
    }

    @OnClick(R.id.new_lobby)
    void createLobby(){
        Objects.requireNonNull((NavigationHost)getActivity()).navigateTo(new CreateLobbyFragment(), true);
    }

    @OnClick(R.id.btn_navigate_to_main_menu)
    void navigateToMainMenu (){
        ((NavigationHost) Objects.requireNonNull(getActivity())).navigateTo(new MainMenuFragment(), false);
    }
    //@OnClick(R.id.clear_lobbies)
    //void clearLobbies(){
    //    PokerApplicationManager applicationManager = (PokerApplicationManager) Objects.requireNonNull(getActivity()).getApplication();
    //    if(applicationManager.getSocket() != null){
    //        socket = applicationManager.getSocket();
    //        socket.connect();
    //        socket.emit("clearlobbies");
    //        socket.disconnect();
    //    }else
    //        Logger.getAnonymousLogger().info("PokerApplicationManager return null object! ChooseLobby");
    //}

    @Override
    public void sendMessageOnServerGetLobbies() {
        PokerApplicationManager applicationManager = (PokerApplicationManager) Objects.requireNonNull(getActivity()).getApplication();
        if(applicationManager.getSocket() != null){
            socket = applicationManager.getSocket();

            socket.on("getlobbies", onGetLobbies);
            socket.emit("getlobbies");
            socket.connect();
        }else
            Logger.getAnonymousLogger().info("PokerApplicationManager return null object! ChooseLobby");
    }

    private Emitter.Listener onGetLobbies = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Logger.getAnonymousLogger().info("<-"+args[0].toString());
            PokerApplicationManager applicationManager = PokerApplicationManager.getInstance();
            if(applicationManager.getSocket() != null){
                socket = applicationManager.getSocket();
                socket.disconnect();
            }
            RespRooms roomsObject = MyDeserializer.desGetLobbiesResponce(args[0].toString());
            lobbies.clear();
            lobbies.addAll(roomsObject.getRooms());
            if(Objects.requireNonNull(getActivity()!=null))
                Objects.requireNonNull(getActivity())
                    .runOnUiThread(() -> setLobbies());
            else
                Toast.makeText(
                        getContext()
                        ,"Something is wrong. Please, try again."
                       ,Toast.LENGTH_LONG ).show();
        }
    };
}
