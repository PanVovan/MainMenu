package com.poker.holdem.view.customparts.lobby_view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poker.holdem.lobby.Room;
import com.poker.holdem.R;
import com.poker.holdem.view.activity.GameActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoomRecyclerViewAdapter extends RecyclerView.Adapter<RoomRecyclerViewAdapter.ViewHolder> {

    SharedPreferences prefs;

    private ArrayList<Room> rooms;
    private Context context;

    public RoomRecyclerViewAdapter(ArrayList<Room> rooms, Context context) {
        this.context = context;
        this.rooms = rooms;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lobby_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Room room = rooms.get(position);
        holder.playersActive.setText(room.getLength().toString());
        holder.minRate.setText(room.getRate().toString());
        holder.lobbyName.setText(room.getName());

    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.players_active) TextView playersActive;
        @BindView(R.id.min_rate) TextView minRate;
        @BindView(R.id.lobby_name) TextView lobbyName;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @OnClick
        public void onClick() {
            prefs = context.getSharedPreferences("Test", 0);
            SharedPreferences.Editor editor= prefs.edit();
            editor.putString("Name", lobbyName.getText().toString());
            editor.apply();
            context.startActivity(new Intent(context, GameActivity.class));
        }

    }
}
