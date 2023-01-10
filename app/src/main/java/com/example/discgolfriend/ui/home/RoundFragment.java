package com.example.discgolfriend.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.discgolfriend.Database.Entities.Hole;
import com.example.discgolfriend.Database.Entities.Round;
import com.example.discgolfriend.MainActivity;
import com.example.discgolfriend.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Martin Kakko
 * Fragment shown during the round
 */
public class RoundFragment extends Fragment {


    public RoundFragment() {

            super(R.layout.fragment_round);

    }


    Hole hole;
    public static int currentHole = 1;
    public static Round[] rounds;
    TextView holeNbm;
    TextView holeLength;
    TextView holePar;
    TextView avgPar;
    public static HashMap<String, int[]> playerScores;
    Button nextHole;
    Button prevHole;
    static int playerAmount;
    String courseName;
    int holeAmount;
    DecimalFormat df;
    RoundPlayerListAdapter rvItemListAdapter;



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        super.onViewCreated(view, savedInstanceState);
        MainActivity.changeTitle("Round");
        holeNbm = view.findViewById(R.id.tvHoleNmb);
        holeLength = view.findViewById(R.id.tvHoleLenght);
        holePar = view.findViewById(R.id.tvHolePar);
        avgPar = view.findViewById(R.id.tvHoleAvg);
        df = new DecimalFormat("#.##");

        courseName = getArguments().getString("courseName");
        holeAmount = MainActivity.database.courseDao().findCourseByName(courseName).holes;
        ArrayList<Hole> holes = (ArrayList)MainActivity.database.holeDao().findHoleByCourse(courseName);
        hole = holes.get(currentHole-1);
        RecyclerView rvItemList = view.findViewById(R.id.rvRoundPlayers);
        holeNbm.setText(getString(R.string.round_holenumber)+" "+hole.holeNumber);
        holeLength.setText(getString(R.string.round_holelength)+hole.length + "m");
        holePar.setText("Par: " +hole.par);

        avgPar.setText(getString(R.string.round_holeavg)+" " + df.format(hole.avgPar));
        playerAmount = getArguments().getInt("players");
        String[] players = new String[playerAmount];


        for(int i  = 0; i<playerAmount; i++){
            players[i] = getArguments().getString("player"+i);
        }

         rounds = new Round[playerAmount];

        playerScores = new HashMap<>();

        for(int i = 0; i<playerAmount; i++){
            Round round = new Round();
            round.player = players[i];
            round.course = courseName;
            round.result = "";
            rounds[i] = round;
            playerScores.put(players[i],new int[holes.size()] );
        }

        System.out.println("Players size: " + players.length);

        RoundPlayerListAdapter rvItemListAdapter = new RoundPlayerListAdapter(players, hole);
        rvItemList.setAdapter(rvItemListAdapter);
        rvItemList.setLayoutManager(new LinearLayoutManager(getContext()));

        nextHole = view.findViewById(R.id.btnNextHole);
        prevHole = view.findViewById(R.id.btnLastHole);

        nextHole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holes.size()>currentHole) {
                    hole = holes.get(currentHole++);
                    System.out.println("Currenthole: " + currentHole);
                    System.out.println("Hole: " + hole.holeNumber);
                    holeNbm.setText(getString(R.string.round_holenumber)+" " + hole.holeNumber);
                    holeLength.setText(getString(R.string.round_holelength) +" "+ hole.length+"m");
                    holePar.setText(getString(R.string.round_holepar) +" "+ hole.par);
                    System.out.println("Avg par: "+hole.avgPar);
                    avgPar.setText(getString(R.string.round_holeavg)+" " + df.format(hole.avgPar));
                    RoundPlayerListAdapter.newHole(hole);
                    rvItemListAdapter.notifyDataSetChanged();


                }
                else {

                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());

                    builder.setMessage("Do you want to end round?");

                    builder.setPositiveButton("Yes", ((dialogInterface, i) -> endRound(view)));

                    builder.setNegativeButton("No", ((dialogInterface, i) -> {}));

                    builder.show();

                }

            }
        });

        prevHole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentHole>1) {
                    currentHole--;
                    hole = holes.get(currentHole-1);
                    System.out.println("Currenthole: " + currentHole);
                    System.out.println("Hole: " + hole.holeNumber);
                    holeNbm.setText(getString(R.string.round_holenumber) + hole.holeNumber);
                    holeLength.setText(getString(R.string.round_holelength) + hole.length+"m");
                    holePar.setText(getString(R.string.round_holepar) +" "+ hole.par);
                    avgPar.setText(getString(R.string.round_holeavg)+" " + df.format(hole.avgPar));
                    RoundPlayerListAdapter.newHole(hole);
                    rvItemListAdapter.notifyDataSetChanged();

                }


            }
        });



    }

    public static void setScore(String player, int score, int index){

       // System.out.println(score);
       // System.out.println("Player");
        int[] scores = playerScores.get(player);
        scores[index-1] = score;

        playerScores.replace(player, scores);

    }

    public void endRound(View view){
        int playernbm = 1;
        Bundle bundle = new Bundle();
        bundle.putInt("playerAmount", playerAmount);
        for(Map.Entry<String, int[]> set : playerScores.entrySet() ){
            String player = set.getKey();
            int[] scores = set.getValue();
            String score = "";
            for(Round r: rounds ){
                if(r.player.equals(player)){
                    bundle.putString("player"+playernbm, r.player);
                    for(int i : scores){
                        score = score+String.valueOf(i);
                    }
                    r.result=score;
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date();
                    String time = format.format(date);
                    r.time = time;
                    bundle.putString("score"+playernbm,r.result);
                    System.out.println("Final score: "+ score);
                    playernbm++;
                }
            }
        }


        MainActivity.database.roundDao().insertAll(rounds);
        updateAverage();
        bundle.putString("course", courseName);
        Navigation.findNavController(view).navigate(R.id.resultFragment, bundle);
    }

    //Sets average result on holes after completing round
    public void updateAverage(){
        System.out.println(courseName);
       ArrayList<Round> allRounds = (ArrayList) MainActivity.database.roundDao().findRoundByCourse(courseName);
       ArrayList<Hole> holes = (ArrayList)MainActivity.database.holeDao().findHoleByCourse(courseName);

       int sum = 0;
       for(int i = 0; i < holeAmount; i++){
           for(Round r: allRounds){
               int score = Character.getNumericValue(r.result.charAt(i));
               sum = sum+score;
           }
           Hole hole = holes.get(i);
           double avg = (double)  sum/allRounds.size();
           System.out.println("Counted average: " + avg + "="+sum+ " / " + allRounds.size());
           hole.avgPar = avg;
           MainActivity.database.holeDao().updateHole(hole);
           sum = 0;
       }


    }




}