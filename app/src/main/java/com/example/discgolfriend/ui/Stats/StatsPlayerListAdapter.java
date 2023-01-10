package com.example.discgolfriend.ui.Stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discgolfriend.Database.Entities.Player;
import com.example.discgolfriend.R;

/**
 * @author Martin Kakko
 * Adapter for RecyclerView showing players
 */
public class StatsPlayerListAdapter extends RecyclerView.Adapter<StatsPlayerListAdapter.ViewHolder> {

    Player[] localDataset;


    public StatsPlayerListAdapter(Player[] dataset){
        localDataset = dataset;
    }

    View.OnClickListener PlayerListClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view){
            int position = (int) view.getTag();

            Bundle bundle = new Bundle();
            bundle.putString("name", localDataset[position].name);

            Navigation.findNavController(view).navigate(R.id.playerStatsFragment, bundle);
        }
    };

    @NonNull
    @Override
    public StatsPlayerListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stats_list_item, parent, false);

        return new StatsPlayerListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(PlayerListClickListener);
        holder.textView.setText(localDataset[position].name);

    }


    @Override
    public int getItemCount(){
        return localDataset.length;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;

        public ViewHolder(View itemView){
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.tvStatsListPlayer);

        }
    }
}
