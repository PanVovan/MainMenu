package com.pockerstad.mainmenu.customparts.lobby_view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pockerstad.mainmenu.R;

import java.util.ArrayList;

public class LobbyRecyclerViewAdapter extends RecyclerView.Adapter<LobbyRecyclerViewAdapter.ViewHolder> {

    ArrayList<Lobby> lobbies;
    OnLobbyClickListener onLobbyClickListener;

    public LobbyRecyclerViewAdapter(ArrayList<Lobby> lobbies, OnLobbyClickListener onLobbyClickListener) {
        this.lobbies = lobbies;
        this.onLobbyClickListener = onLobbyClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lobby_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return lobbies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView playersActive;
        TextView minRate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            playersActive = itemView.findViewById(R.id.players_active);
            minRate = itemView.findViewById(R.id.min_rate);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            onLobbyClickListener.onLobbyClicked(lobbies.get(position));
        }
    }

    public interface OnLobbyClickListener {
        void onLobbyClicked(Lobby lobby);
    }
}
