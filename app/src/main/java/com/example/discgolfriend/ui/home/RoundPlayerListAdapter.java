package com.example.discgolfriend.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discgolfriend.Database.Entities.Hole;
import com.example.discgolfriend.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * @author Martin Kakko
 * Adapter for RecyclerView showing players during round
 */
public class RoundPlayerListAdapter extends RecyclerView.Adapter<RoundPlayerListAdapter.ViewHolder> {


    String[] localDataset;
    static Hole playedHole;
    int score;


    public RoundPlayerListAdapter(String[] dataset, Hole hole){
        localDataset = dataset;
        playedHole = hole;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.round_player_list_item, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){

        int pos = position;
        score = playedHole.par;
        int parValue = score;
        holder.itemView.setTag(position);

        holder.textView.setText(localDataset[position]);

        holder.score.setText(String.valueOf(score));
        RoundFragment.setScore(localDataset[pos], score, playedHole.holeNumber);

        int roundScore = RoundFragment.getScore(localDataset[pos]);
        holder.roundScore.setText(Integer.toString(roundScore));

        holder.buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = (String) holder.score.getText();
                int newScore = Integer.parseInt(s)+1;
                holder.score.setText(String.valueOf(newScore));
                RoundFragment.setScore(localDataset[pos], newScore, playedHole.holeNumber);
                int roundScore = RoundFragment.getScore(localDataset[pos]);
                holder.roundScore.setText(Integer.toString(roundScore));
            }
        });
        holder.buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = (String) holder.score.getText();
                int newScore = Integer.parseInt(s)-1;
                holder.score.setText(String.valueOf(newScore));
                RoundFragment.setScore(localDataset[pos], newScore, playedHole.holeNumber);
                int roundScore = RoundFragment.getScore(localDataset[pos]);
                holder.roundScore.setText(Integer.toString(roundScore));
            }
        });


    }

    @Override
    public int getItemCount(){
        return localDataset.length;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;
        public TextView score;
        public TextView roundScore;
        public FloatingActionButton buttonDown;
        public FloatingActionButton buttonUp;

        public ViewHolder(View itemView){
            super(itemView);

            score = (TextView) itemView.findViewById(R.id.tvRoundPlayerScore);
            roundScore = (TextView) itemView.findViewById(R.id.tvRoundPlayerWholeScore);
            textView = (TextView) itemView.findViewById(R.id.tvRoundPlayer);
            buttonDown =  itemView.findViewById(R.id.btnScoreDown);
            buttonUp = itemView.findViewById(R.id.btnScoreUp);
        }
    }

    public static void newHole(Hole hole){
        playedHole = hole;
    }



}
