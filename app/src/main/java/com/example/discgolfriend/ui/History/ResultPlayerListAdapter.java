package com.example.discgolfriend.ui.History;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discgolfriend.Database.Entities.Hole;
import com.example.discgolfriend.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Martin Kakko
 * Adapter for RecyclerView showing list of players after round, used with ResultHoleListAdapter
 */
public class ResultPlayerListAdapter extends RecyclerView.Adapter<ResultPlayerListAdapter.ViewHolder> {



    HashMap<String, int[]> playersAndScores;
    String[] players;
    ArrayList<Hole> holes;
    int[] holePars;
    int totalPar;

    public ResultPlayerListAdapter(HashMap<String, int[]> dataset, ArrayList<Hole> sentHoles){
     playersAndScores = dataset;
     totalPar = 0;
     holes = sentHoles;
        players = new String[playersAndScores.size()];
        int i = 0;
        for(String s : playersAndScores.keySet()){
            players[i++] = s;
        }
      holePars = new int[holes.size()];
        for(int j = 0; j< holes.size(); j++){
            holePars[j] = holes.get(j).par;

        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){


        holder.itemView.setTag(position);
        holder.textView.setText(players[position]);
        int[] dataset = playersAndScores.get(players[position]);
        int playerResult = Arrays.stream(dataset).sum();
        totalPar = Arrays.stream(holePars).sum();
        holder.finalScore.setText(Integer.toString(playerResult-totalPar));
        ResultHoleListAdapter listAdapter = new ResultHoleListAdapter(dataset, holePars);
        holder.rvHoleScores.setAdapter(listAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.rvHoleScores.getContext(), LinearLayoutManager.HORIZONTAL, false);
        holder.rvHoleScores.setLayoutManager(layoutManager);

    }

    @Override
    public int getItemCount(){
        return playersAndScores.size();
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;
        public TextView finalScore;
        public RecyclerView rvHoleScores;


        public ViewHolder(View itemView){
            super(itemView);

            finalScore = (TextView) itemView.findViewById(R.id.tvResultFinalScore);
            textView = (TextView) itemView.findViewById(R.id.tvResultPlayer);
            rvHoleScores = itemView.findViewById(R.id.rvPlayerHoleResults);
        }
    }





}
