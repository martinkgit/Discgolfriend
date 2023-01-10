package com.example.discgolfriend.ui.History;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discgolfriend.R;


/**
 * @author Martin Kakko
 * RecycleView adapter for showing round results
 */
public class ResultHoleListAdapter extends RecyclerView.Adapter<ResultHoleListAdapter.ViewHolder> {

    int[] localDataset;
    int[] holes;


    public ResultHoleListAdapter(int[] dataset, int[] holePars){
        localDataset = dataset;
        holes = holePars;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hole_result_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        holder.itemView.setTag(position);
        int index = position;

        int parValue = holes[index];
        int scoreValue = localDataset[index];
        String par = String.valueOf(parValue);
        String score = String.valueOf(scoreValue);
        if(parValue<scoreValue){


            holder.score.setBackgroundColor(Color.argb(100, 211,89,89));
        }
        else if(parValue>scoreValue){
            if(scoreValue-parValue == -2){
                holder.score.setBackgroundColor(Color.argb(100, 30,136,229));
            }
            else holder.score.setBackgroundColor(Color.argb(100, 109, 202, 92));
        }

        else{
            holder.score.setBackgroundColor(Color.argb(100,181,181,185));
        }
        holder.par.setText(par);
        holder.score.setText(score);
        holder.hole.setText(String.valueOf(index+1));


    }

    @Override
    public int getItemCount(){
        return localDataset.length;
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView par;
        public TextView score;
        public TextView hole;


        public ViewHolder(View itemView){
            super(itemView);

            score = (TextView) itemView.findViewById(R.id.tvResultHoleResult);
            par = (TextView) itemView.findViewById(R.id.tvResultHolePar);
            hole = (TextView) itemView.findViewById(R.id.tvResultHoleNumber);

        }
    }





}