package com.example.discgolfriend.ui.Stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discgolfriend.Database.Entities.Course;
import com.example.discgolfriend.R;

/**
 * @author Martin Kakko
 * Fragment for RecyclerView showing coourses
 */
public class StatsCourseListAdapter extends RecyclerView.Adapter<StatsCourseListAdapter.ViewHolder> {

    Course[] localDataset;


    public StatsCourseListAdapter(Course[] dataset){
        localDataset = dataset;
    }

    View.OnClickListener CourseListClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view){
            int position = (int) view.getTag();

            Bundle bundle = new Bundle();
            bundle.putString("name", localDataset[position].courseName);

            Navigation.findNavController(view).navigate(R.id.courseStats, bundle);
        }
    };

    @NonNull
    @Override
    public StatsCourseListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stats_list_item, parent, false);


        return new StatsCourseListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(CourseListClickListener);
        holder.textView.setText(localDataset[position].courseName);

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
