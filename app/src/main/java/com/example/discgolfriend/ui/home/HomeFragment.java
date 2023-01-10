package com.example.discgolfriend.ui.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discgolfriend.Database.Entities.Course;
import com.example.discgolfriend.Database.Entities.Player;
import com.example.discgolfriend.MainActivity;
import com.example.discgolfriend.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    static Course selectedCourse;

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> arrayAdapter;
    ArrayAdapter<String> playerAdapter;
    Button selectPlayers;
    Button startRound;
    Button addPlayer;
    ListPopupWindow listPopupWindow;
    static Player[] activePlayers;
    static PlayerListAdapter playerListAdapter;
    private static ArrayList<Player> chosenPlayers;
    Player[] dataset;
    String[] players;

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Course[] courses = MainActivity.database.courseDao().getAllCourses();
        String[] names =  new String[courses.length];
        for(int i = 0; i<courses.length; i++){
            names[i] = courses[i].courseName;
        }

        startRound = view.findViewById(R.id.btnStart);
        selectPlayers = view.findViewById(R.id.btnAddPlayer);
        addPlayer = view.findViewById(R.id.btnNewPlayer);

        //Player selection
        listPopupWindow = new ListPopupWindow(view.getContext());

        listPopupWindow.setAnchorView(selectPlayers);

        RecyclerView rvItemList = view.findViewById(R.id.rvPlayerList);
         dataset = MainActivity.database.playerDao().getAllPlayers();
        players =  new String[dataset.length];
        activePlayers = new Player[1];
        for(int i = 0; i<dataset.length; i++){
            players[i] = dataset[i].name;
        }


        chosenPlayers = new ArrayList<>();
        playerAdapter = new ArrayAdapter<>(view.getContext(), R.layout.list_popup_window_item, players);

        listPopupWindow.setAdapter(playerAdapter);

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String player = adapterView.getItemAtPosition(i).toString();
                for(Player p : dataset){
                    if(p.name.equals(player)){
                        if(!chosenPlayers.contains(p)) {
                            chosenPlayers.add(p);
                            Toast.makeText(view.getContext(), "Player: " + player, Toast.LENGTH_SHORT).show();
                            activePlayers = new Player[chosenPlayers.size()];

                            for (int s = 0; s < chosenPlayers.size(); s++) {
                                activePlayers[s] = chosenPlayers.get(s);
                            }

                            playerListAdapter = new PlayerListAdapter(activePlayers);
                            rvItemList.setAdapter(playerListAdapter);
                            rvItemList.setLayoutManager(new LinearLayoutManager(getContext()));


                            playerListAdapter.notifyDataSetChanged();
                        }
                        else Toast.makeText(view.getContext(), "Player " + player + " already chosen", Toast.LENGTH_SHORT).show();
                    }
                }



                listPopupWindow.dismiss();
            }
        });

        selectPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listPopupWindow.show();
            }
        });

        addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());

                EditText input = new EditText(getActivity());
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setTitle(getString(R.string.new_player_hint))
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String name = input.getText().toString();
                                System.out.println(name);
                                Player player = new Player();
                                player.name  = name;
                                MainActivity.database.playerDao().insertAll(player);
                                dataset = MainActivity.database.playerDao().getAllPlayers();
                                players =  new String[dataset.length];
                                for(int s = 0; s<dataset.length; s++){
                                    players[s] = dataset[s].name;
                                }
                                playerAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });



        rvItemList.setAdapter(playerListAdapter);
        rvItemList.setLayoutManager(new LinearLayoutManager(getContext()));

        //Choose course
        autoCompleteTextView = view.findViewById(R.id.auto_complete);
        arrayAdapter = new ArrayAdapter<>(view.getContext(), R.layout.list_item, names);
        autoCompleteTextView.setAdapter(arrayAdapter);


       autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayAdapter = new ArrayAdapter<>(view.getContext(), R.layout.list_item, names);
                autoCompleteTextView.setAdapter(arrayAdapter);
            }
        });



        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String course = adapterView.getItemAtPosition(i).toString();
                setCourse(course, courses);
                Toast.makeText(view.getContext(), "Course: " + course, Toast.LENGTH_SHORT).show();
            }
        });

        startRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedCourse != null && chosenPlayers.size()>0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("courseName", selectedCourse.courseName);
                    for (int i = 0; i < chosenPlayers.size(); i++) {
                        Player p = chosenPlayers.get(i);
                        String name = "player" + i;
                        bundle.putString(name, p.name);
                    }
                    bundle.putInt("players", chosenPlayers.size());
                    Navigation.findNavController(view).navigate(R.id.RoundFragment, bundle);
                }
                else Toast.makeText(view.getContext(), "Choose player and course", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public static void removeFromActivePlayers(Player player){

        chosenPlayers.remove(player);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);

    }

    public static void clearPlayers(){
        chosenPlayers.clear();
        playerListAdapter.notifyDataSetChanged();
    }

    public static void setCourse(String name, Course[] courses){
        for(Course c : courses){
            if(c.courseName.equals(name)){
                selectedCourse = c;
            }
        }
    }

    public static class AddNewPlayerDialogFragmen extends DialogFragment{
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());

            final EditText input = new EditText(getActivity());
            builder.setView(input);
            builder.setTitle(getString(R.string.new_player_hint))
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String name = input.getText().toString();
                            Player player = new Player();
                            player.name  = name;
                            MainActivity.database.playerDao().insertAll(player);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            AddNewPlayerDialogFragmen.this.getDialog().cancel();
                        }
                    });
                    return builder.create();

        }
    }


}

