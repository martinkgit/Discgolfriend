package com.example.discgolfriend.ui.Stats;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.TextView;

import com.example.discgolfriend.Database.Entities.Hole;
import com.example.discgolfriend.Database.Entities.Player;
import com.example.discgolfriend.Database.Entities.Round;
import com.example.discgolfriend.MainActivity;
import com.example.discgolfriend.R;

import java.util.ArrayList;


/**
 * @author Martin Kakko
 * Fragment showing player stats
 */
public class PlayerStatsFragment extends Fragment {





    public PlayerStatsFragment() {
        super(R.layout.fragment_player_stats);
    }

    TextView name;
    TextView rounds;
    TextView birdies;
    TextView pars;
    TextView bogeys;
    TextView aces;
    TextView eagles;
    static int birdiesAmount = 0;
    static int parsAmount = 0;
    static int bogeysAmount = 0;
    static int eaglesAmount = 0;
    static int acesAmount = 0;



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
    super.onViewCreated(view,savedInstanceState);
    MainActivity.changeTitle("Player stats");
        String playername = getArguments().getString("name");
        ArrayList<Round> allrounds = (ArrayList<Round>) MainActivity.database.roundDao().findByPlayer(playername);
        Player player = MainActivity.database.playerDao().findByName(playername);
        birdiesAmount = 0;
        parsAmount =0;
        bogeysAmount = 0;
        eaglesAmount = 0;
        acesAmount = 0;
        if(!allrounds.isEmpty())
        getBirdies(allrounds);

        name = view.findViewById(R.id.tvPlayerStatsName);
        name.setText(playername);
        rounds = view.findViewById(R.id.tvPlayerStatsRounds);
        rounds.setText(getString(R.string.player_stats_rounds)+ " "+allrounds.size());
        birdies = view.findViewById(R.id.tvPlayerStatsBirdies);
        birdies.setText(getString(R.string.player_stats_birdies)+" "+ birdiesAmount);
        aces = view.findViewById(R.id.tvPlayerStatsAces);
        aces.setText(getString(R.string.player_stats_aces)+" " + acesAmount);
        eagles = view.findViewById(R.id.tvPlayerStatsEagles);
        eagles.setText(getString(R.string.player_stats_eagles)+" "+eaglesAmount);
        pars = view.findViewById(R.id.tvPlayerStatsPars);
        pars.setText(getString(R.string.player_stats_pars) +" "+ parsAmount);
        bogeys = view.findViewById(R.id.tvPlayerStatsBogeys);
        bogeys.setText(getString(R.string.player_stats_bogeys) +" "+ bogeysAmount);



    }

    //Sets correct score amounts
    private static void getBirdies(ArrayList<Round> rounds){

        ArrayList<Hole> holes;
        for(Round r : rounds){
            String course = r.course;
            holes = (ArrayList<Hole>) MainActivity.database.holeDao().findHoleByCourse(course);
            for(int i = 0; i<holes.size(); i++){
                int score = Character.getNumericValue(r.result.charAt(i));

                if(score-holes.get(i).par==-2){
                    eaglesAmount++;
                    if(holes.get(i).par==3){
                        acesAmount++;
                    }
                }
                else if(score-holes.get(i).par==-1){
                   birdiesAmount++;
                }
                else if(score-holes.get(i).par==0){
                    parsAmount++;
                }
                else if(score-holes.get(i).par>=1){
                    bogeysAmount++;
                }
            }
        }

    }
}