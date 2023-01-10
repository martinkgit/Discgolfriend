package com.example.discgolfriend.ui.home;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discgolfriend.Database.Entities.Player;
import com.example.discgolfriend.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.ViewHolder> {

    Player[] localDataset;


    public PlayerListAdapter(Player[] dataset){
        localDataset = dataset;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_list_item, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        holder.itemView.setTag(position);

        holder.textView.setText(localDataset[position].name);



        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removePlayer(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount(){
        return localDataset.length;
    }

    public void removePlayer(int pos){
        Player[] dataset = new Player[localDataset.length - 1];
        Player toBeRemoved = null;
        for (int i = 0, k = 0; i < localDataset.length; i++) {
            if (i == pos) {
                toBeRemoved = localDataset[i];
                continue;
            }
            dataset[k++] = localDataset[i];
        }
        HomeFragment.removeFromActivePlayers(toBeRemoved);
        localDataset = dataset;
        notifyItemRemoved(pos);
        notifyItemRangeChanged(pos, localDataset.length);

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;
      public FloatingActionButton button;

        public ViewHolder(View itemView){
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.tvPlayerName);
            button =  itemView.findViewById(R.id.btnRemovePlayer);
        }
    }



}



