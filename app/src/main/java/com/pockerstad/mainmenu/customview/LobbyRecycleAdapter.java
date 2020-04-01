package com.pockerstad.mainmenu.customview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pockerstad.mainmenu.R;

import java.util.List;

public class LobbyRecycleAdapter  extends RecyclerView.Adapter<LobbyRecycleAdapter.LobbyAdapterHolder>{

    private Context context;
    private List<Lobby> lobbies;

    public LobbyRecycleAdapter (Context c, List<Lobby> lobbyList){
        this.lobbies = lobbyList;
        this.context = c;
    }

    //при создании recycleView создаётся класс взаимодействия с выводом LobbyAdapterHolder
    @NonNull
    @Override
    public LobbyAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_lobby_layout, parent, false);
        LobbyAdapterHolder lobbyAdapterHolder = new LobbyAdapterHolder(view);
        return lobbyAdapterHolder;
    }

    //загрузка параметров лобби
    @Override
    public void onBindViewHolder(@NonNull LobbyRecycleAdapter.LobbyAdapterHolder holder, int position) {
        Lobby lobby = lobbies.get(position);
        holder.lobbyPlayers.setText(lobby.getLobbyPlayers());
        holder.lobbyName.setText(lobby.getLobbyName());
        holder.lobbyCode.setText(lobby.getLobbyCode());
    }

    @Override
    public int getItemCount() {   return lobbies.size();    }

    public class LobbyAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView lobbyName;
        public TextView lobbyCode;
        public TextView lobbyPlayers;

        public LobbyAdapterHolder(@NonNull View itemView) {
            super(itemView);
            lobbyName = itemView.findViewById(R.id.lobby_name);
            lobbyCode = itemView.findViewById(R.id.lobby_code);
            lobbyPlayers = itemView.findViewById(R.id.lobby_number_of_players);
        }

        @Override
        public void onClick(View v) {
            //начало игры
        }
    }
}
