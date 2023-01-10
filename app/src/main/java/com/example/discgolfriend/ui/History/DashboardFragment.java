package com.example.discgolfriend.ui.History;

import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.discgolfriend.Database.Entities.Course;

import com.example.discgolfriend.Database.Entities.Player;
import com.example.discgolfriend.Database.Entities.Round;
import com.example.discgolfriend.MainActivity;

import com.example.discgolfriend.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


/**
 * @author Martin Kakko
 * Fragment showing round history
 */
public class DashboardFragment extends Fragment {



    public DashboardFragment() {
        super(R.layout.fragment_dashboard);
    }

    AutoCompleteTextView listPopupWindow;
    AutoCompleteTextView coursePopupWindow;
    TextInputLayout selectPlayers;
    TextInputLayout selectCourse;
    ArrayAdapter<String> playerAdapter;
    ArrayAdapter<String> courseAdapter;
    static Player chosenPlayer;
    static Course chosenCourse;
    Round[] rounds;


    /**
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rounds = MainActivity.database.roundDao().getAllRounds();
        Player[] players = MainActivity.database.playerDao().getAllPlayers();
        Course[] courses = MainActivity.database.courseDao().getAllCourses();
        RecyclerView rvRoundList = view.findViewById(R.id.rvHistory);
        String[] courseNames = new String[courses.length];
        for(int j = 0; j<courses.length; j++){
            courseNames[j] = courses[j].courseName;
        }

        String[] names = new String[players.length];
        for(int i = 0; i<names.length; i++){
            names[i] = players[i].name;
        }

        //Recyclerview adapter for list of rounds
        HistoryRoundListAdapter listAdapter = new HistoryRoundListAdapter(rounds);
        rvRoundList.setAdapter(listAdapter);
        rvRoundList.setLayoutManager(new LinearLayoutManager(getContext()));


        //Buttons
        selectPlayers = view.findViewById(R.id.btnChoosePlayer);
        selectCourse= view.findViewById(R.id.btnChooseCourse);

        listPopupWindow = view.findViewById(R.id.auto_completePlayer);
        coursePopupWindow = view.findViewById(R.id.auto_completeCourse);
        //Adapter for the list of courses
        courseAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.list_popup_window_item, courseNames);
        coursePopupWindow.setAdapter(courseAdapter);

        coursePopupWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.list_popup_window_item, courseNames);
                coursePopupWindow.setAdapter(courseAdapter);
            }
        });

        //When item on the list gets clicked
        coursePopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String course = adapterView.getItemAtPosition(i).toString();
                for(Course c : courses){
                    if(c.courseName.equals(course)){
                        chosenCourse = c;
                    }
                }

                if(chosenPlayer== null) {
                    ArrayList<Round> newRounds = (ArrayList<Round>) MainActivity.database.roundDao().findRoundByCourse(chosenCourse.courseName);
                    rounds = new Round[newRounds.size()];
                    rounds = newRounds.toArray(rounds);
                }
                else{
                    ArrayList<Round> newRounds = (ArrayList<Round>) MainActivity.database.roundDao().findRoundByPlayerAndCourse(chosenCourse.courseName,chosenPlayer.name);
                    rounds = new Round[newRounds.size()];
                    rounds = newRounds.toArray(rounds);
                }
                HistoryRoundListAdapter listAdapter = new HistoryRoundListAdapter(rounds);
                rvRoundList.setAdapter(listAdapter);
                rvRoundList.setLayoutManager(new LinearLayoutManager(getContext()));
                listAdapter.notifyDataSetChanged();
                Toast.makeText(view.getContext(), "Course: " + course, Toast.LENGTH_SHORT).show();
            }
        });

        //Same as above
        playerAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.list_popup_window_item, names);
        listPopupWindow.setAdapter(playerAdapter);

        listPopupWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.list_popup_window_item, names);
                listPopupWindow.setAdapter(playerAdapter);
            }
        });

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String player = adapterView.getItemAtPosition(i).toString();
                for(Player p : players){
                    if(p.name.equals(player)){
                        chosenPlayer = p;
                    }
                }

                if(chosenCourse == null) {
                    ArrayList<Round> newRounds = (ArrayList<Round>) MainActivity.database.roundDao().findByPlayer(chosenPlayer.name);
                    rounds = new Round[newRounds.size()];
                    rounds = newRounds.toArray(rounds);
                }
                else{
                    ArrayList<Round> newRounds = (ArrayList<Round>) MainActivity.database.roundDao().findRoundByPlayerAndCourse(chosenCourse.courseName,chosenPlayer.name);
                    rounds = new Round[newRounds.size()];
                    rounds = newRounds.toArray(rounds);
                }
                HistoryRoundListAdapter listAdapter = new HistoryRoundListAdapter(rounds);
                rvRoundList.setAdapter(listAdapter);
                rvRoundList.setLayoutManager(new LinearLayoutManager(getContext()));
                listAdapter.notifyDataSetChanged();
                for(Round r : rounds){
                    System.out.println("Player:" + r.player);
                }
                Toast.makeText(view.getContext(), "Player: " + player, Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}