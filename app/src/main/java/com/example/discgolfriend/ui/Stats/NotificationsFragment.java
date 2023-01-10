package com.example.discgolfriend.ui.Stats;

import android.os.Bundle;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discgolfriend.Database.Entities.Course;
import com.example.discgolfriend.Database.Entities.Player;
import com.example.discgolfriend.MainActivity;
import com.example.discgolfriend.R;

/**
 * @author Martin Kakko
 * Fragment showing player and course statistics
 */
public class NotificationsFragment extends Fragment {

    public NotificationsFragment() {
        super(R.layout.fragment_notifications);
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Player[] players = MainActivity.database.playerDao().getAllPlayers();
        Course[] courses = MainActivity.database.courseDao().getAllCourses();
        RecyclerView rvPlayerList = view.findViewById(R.id.rvStatsPlayerList);
        RecyclerView rvCourseList = view.findViewById(R.id.rvStatsCourseList);

        String[] courseNames = new String[courses.length];
        for(int j = 0; j<courseNames.length; j++){
            courseNames[j] = courses[j].courseName;
        }

        String[] names = new String[players.length];
        for(int i = 0; i<names.length; i++){
            names[i] = players[i].name;
        }

        StatsPlayerListAdapter playerListAdapter = new StatsPlayerListAdapter(players);
        rvPlayerList.setAdapter(playerListAdapter);
        rvPlayerList.setLayoutManager(new LinearLayoutManager(getContext()));

        StatsCourseListAdapter courseListAdapter = new StatsCourseListAdapter(courses);
        rvCourseList.setAdapter(courseListAdapter);
        rvCourseList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}