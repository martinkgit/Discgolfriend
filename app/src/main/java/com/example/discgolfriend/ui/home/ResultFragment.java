package com.example.discgolfriend.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;

import com.example.discgolfriend.Database.Entities.Hole;
import com.example.discgolfriend.MainActivity;
import com.example.discgolfriend.R;
import com.example.discgolfriend.ui.History.ResultPlayerListAdapter;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author Martin Kakko
 * Fragment showing round results after round is finished
 */
public class ResultFragment extends Fragment {



    public ResultFragment() {
        super(R.layout.fragment_result);
    }

    Button exit;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        MainActivity.changeTitle("Result");
        RecyclerView rvPlayerList = view.findViewById(R.id.rvResultList);
        int playeramount = getArguments().getInt("playerAmount");
        HashMap<String, int[]> playersAndScores = new HashMap<>();
        String course = getArguments().getString("course");
        ArrayList<Hole> holes = (ArrayList)MainActivity.database.holeDao().findHoleByCourse(course);
        exit = view.findViewById(R.id.btnBackHome);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment.clearPlayers();
                Navigation.findNavController(view).navigate(R.id.navigation_home);
                RoundFragment.currentHole = 1;
            }
        });


        for(int i = 1; i<=playeramount; i++ ){
            String player = getArguments().getString("player"+i);
            String score = getArguments().getString("score"+i);
            int[] scores = new int[score.length()];
            for(int j = 0; j<score.length(); j++){
                int number = Character.getNumericValue(score.charAt(j));
                scores[j] = number;
            }
            playersAndScores.put(player, scores);
        }

        ResultPlayerListAdapter rvAdapter = new ResultPlayerListAdapter(playersAndScores, holes);
        rvPlayerList.setAdapter(rvAdapter);
        rvPlayerList.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}