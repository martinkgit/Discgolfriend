package com.example.discgolfriend.ui.Stats;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.discgolfriend.Database.Entities.Hole;
import com.example.discgolfriend.Database.Entities.Round;
import com.example.discgolfriend.MainActivity;
import com.example.discgolfriend.R;


import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * @author Martin Kakko
 * Fragment showing the course stats
 */
public class CourseStats extends Fragment {



    public CourseStats() {
        super(R.layout.fragment_course_stats);
    }

    Hole hole;
    public static int currentHole = 1;
    public static Round[] rounds;
    TextView holeNbm;
    TextView holeLength;
    TextView holePar;
    TextView avgPar;
    Button nextHole;
    Button prevHole;
    String courseName;
    ImageView holeImage;
    int holeAmount;
    DecimalFormat df;
    boolean useImage = true;

    @Override
    public void onViewCreated(View view,
                             Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    MainActivity.changeTitle("Course stats");
        holeNbm = view.findViewById(R.id.tvHoleNmb);
        holeLength = view.findViewById(R.id.tvHoleLenght);
        holePar = view.findViewById(R.id.tvHolePar);
        avgPar = view.findViewById(R.id.tvHoleAvg);
        df = new DecimalFormat("#.##");
        holeImage =(ImageView) view.findViewById(R.id.ivHole);
        courseName = getArguments().getString("name");
        if(courseName.equalsIgnoreCase("kirkkopuisto")){
            holeImage.setVisibility(View.GONE);
            useImage = false;
        }
        else{
            String source  = courseName.toLowerCase()+currentHole;
            int id = getActivity().getResources().getIdentifier(source, "drawable", getActivity().getPackageName());
            System.out.println(R.drawable.utra1 + " ," + source + id);
            holeImage.setImageResource(id);
        }
        holeAmount = MainActivity.database.courseDao().findCourseByName(courseName).holes;
        ArrayList<Hole> holes = (ArrayList)MainActivity.database.holeDao().findHoleByCourse(courseName);
        hole = holes.get(currentHole-1);
        holeNbm.setText(getString(R.string.round_holenumber)+" "+hole.holeNumber);
        holeLength.setText(getString(R.string.round_holelength)+hole.length + "m");
        holePar.setText("Par: " +hole.par);






        avgPar.setText(getString(R.string.round_holeavg)+" " + df.format(hole.avgPar));



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
                    if(useImage)
                    holeImage.setImageResource(getActivity().getResources()
                            .getIdentifier(courseName.toLowerCase()+currentHole, "drawable", getActivity().getPackageName()));
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
                    if(useImage)
                        holeImage.setImageResource(getActivity().getResources()
                                .getIdentifier(courseName.toLowerCase()+currentHole, "drawable", getActivity().getPackageName()));
                }


            }
        });

    }
}