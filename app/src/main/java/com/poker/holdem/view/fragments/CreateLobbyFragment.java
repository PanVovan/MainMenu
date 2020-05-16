package com.poker.holdem.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.poker.holdem.PokerApplicationManager;
import com.poker.holdem.R;
import com.poker.holdem.view.util.NavigationHost;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.socket.client.Socket;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateLobbyFragment extends Fragment {

    private View view;
    @BindView(R.id.lobby_name_et_create)
    EditText lobby_name_et;
    @BindView(R.id.lobby_rate_et_create)
    EditText lobby_rate_et;
    @BindView(R.id.create_lobby_btn)
    Button create_lobby_btn;

    public CreateLobbyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_lobby, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.create_lobby_btn)
    void createLobby(){
        try {
            Socket socket = PokerApplicationManager.getInstance().getSocket();
            socket.connect();
            String lobbyName = lobby_name_et.getText().toString().trim();
            int lobbyrate = Integer.parseInt(lobby_rate_et.getText().toString().trim());
            socket.emit("addlobby", "{\"lobbyname\":\""+lobbyName+"\",\"lobbyrate\":"+lobbyrate+"}");
            socket.disconnect();
            Objects.requireNonNull( (NavigationHost)getActivity() ).navigateTo(new ChooseLobbyFragment(), false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
