package com.example.discgolfriend.ui.History;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discgolfriend.Database.Entities.Course;
import com.example.discgolfriend.Database.Entities.Hole;
import com.example.discgolfriend.Database.Entities.Round;
import com.example.discgolfriend.MainActivity;
import com.example.discgolfriend.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Martin Kakko
 *
 */
public class HistoryRoundListAdapter extends RecyclerView.Adapter<HistoryRoundListAdapter.ViewHolder> {



    HashMap<String, int[]> playersAndScores;
    String[] players;
    ArrayList<Hole> holes;
    int[] holePars;
    int coursePar;
    Round[] roundList;

    public HistoryRoundListAdapter(Round[] rounds){
       roundList = rounds;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){

        Round r = roundList[position];
        Course c = MainActivity.database.courseDao().findCourseByName(r.course);
        ArrayList<Hole> holes = (ArrayList<Hole>) MainActivity.database.holeDao().findHoleByCourse(c.courseName);
        holder.itemView.setTag(position);
        holder.textView.setText(r.player);
        holePars = new int[c.holes];
        for(int s = 0; s<holes.size(); s++){
            holePars[s] = holes.get(s).par;
        }

        int[] dataset = new int[c.holes];
        for(int j = 0; j<dataset.length; j++){
            dataset[j] = Character.getNumericValue(r.result.charAt(j));
        }
        int playerResult = 0;
        for(int i : dataset){
            playerResult += i;
        }

        holder.course.setText(c.courseName);
        holder.time.setText(r.time);
        holder.result.setText(String.valueOf(playerResult-c.par));
        ResultHoleListAdapter listAdapter = new ResultHoleListAdapter(dataset, holePars);
        holder.rvHoleScores.setAdapter(listAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.rvHoleScores.getContext(), LinearLayoutManager.HORIZONTAL, false);
        holder.rvHoleScores.setLayoutManager(layoutManager);

    }

    @Override
    public int getItemCount(){
        return roundList.length;
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;
        public RecyclerView rvHoleScores;
        public TextView result;
        public TextView course;
        public TextView time;


        public ViewHolder(View itemView){
            super(itemView);

            result = (TextView) itemView.findViewById(R.id.tvResultHistory);
            textView = (TextView) itemView.findViewById(R.id.tvResultPlayerHistory);
            rvHoleScores = itemView.findViewById(R.id.rvPlayerHoleResultsHistory);
            course = (TextView) itemView.findViewById(R.id.tvResultCourseHistory);
            time = (TextView) itemView.findViewById(R.id.tvResultTimeHistory);

        }
    }





}
