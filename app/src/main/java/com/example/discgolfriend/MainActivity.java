package com.example.discgolfriend;

import android.os.Bundle;

import com.example.discgolfriend.Database.Dao.CourseDao;
import com.example.discgolfriend.Database.Dao.HoleDao;
import com.example.discgolfriend.Database.Dao.PlayerDao;
import com.example.discgolfriend.Database.Dao.RoundDao;
import com.example.discgolfriend.Database.Entities.Course;
import com.example.discgolfriend.Database.Entities.Hole;
import com.example.discgolfriend.Database.Entities.Player;
import com.example.discgolfriend.Database.PlayerDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import com.example.discgolfriend.databinding.ActivityMainBinding;


/**
 * @author Martin Kakko
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public static PlayerDatabase database;
    private static ActionBar actionBar;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        database = Room.databaseBuilder(
                getApplicationContext(),
                PlayerDatabase.class,
                "database_name").createFromAsset("database/dgdatabase.db").allowMainThreadQueries().build();

        PlayerDao playerDao = database.playerDao();
        CourseDao courseDao = database.courseDao();
        HoleDao holeDao = database.holeDao();
        RoundDao roundDao = database.roundDao();

        actionBar = getSupportActionBar();

        Course[] allCourses = courseDao.getAllCourses();
        if(allCourses.length == 0) InitiateCourses(courseDao);

        Player[] allPlayers = playerDao.getAllPlayers();
        if(allPlayers.length == 0) InitiatePlayers(playerDao);

        Hole[] allHoles = holeDao.getAllHoles();




        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    /**
     * @param dao
     * Adds few players, mainly for authors personal use, not necessary
     */
    private void InitiatePlayers(PlayerDao dao ){

        Player player1 = new Player();

        player1.name = "Martin";

        Player player2 = new Player();

        player2.name = "Heino";

        Player player3 = new Player();

        player3.name = "Asser";


        dao.insertAll(player1, player2, player3);
    }

    /**
     * @param title
     * Method to change actionBar title so it wont show ugly fragment names
     */
    public static void changeTitle(String title){
        actionBar.setTitle(title);
    }

    /**
     * @param dao
     * Not necessary at the moment since the database is created from .db file
     */
    private void InitiateCourses(CourseDao dao ){

        Course course1 = new Course();

        course1.courseName = "Utra";
        course1.holes = 18;
        course1.par = 60;

        Course course2 = new Course();

        course2.courseName = "Kirkkopuisto";
        course2.holes = 9;
        course2.par = 27;

        Course course3 = new Course();

        course3.courseName = "Kontioranta";
        course3.holes = 20;
        course3.par = 68;

        dao.insertAll(course1,course2,course3);
    }


    /**
     * @param dao
     * Not necessary at the moment but can be used to add new courses alongside InitiateCourses
     */
    private void InitiateHoles(HoleDao dao){
        Hole hole1 = new Hole();
        hole1.par = 3;
        hole1.length = 160;
        hole1.holeCourse = "Kontioranta";
        hole1.holeNumber = 1;
        hole1.avgPar = 0.0;
        Hole hole2 = new Hole();
        hole2.avgPar = 0.0;
        hole2.par = 4;
        hole2.length = 217;
        hole2.holeCourse = "Kontioranta";
        hole2.holeNumber = 2;
        Hole hole3 = new Hole();
        hole3.avgPar = 0.0;
        hole3.holeCourse = "Kontioranta";
        hole3.par = 3;
        hole3.length = 107;
        hole3.holeNumber = 3;
        Hole hole4 = new Hole();
        hole4.avgPar = 0.0;
        hole4.holeCourse = "Kontioranta";
        hole4.par = 3;
        hole4.length = 99;
        hole4.holeNumber = 4;
        Hole hole5 = new Hole();
        hole5.avgPar = 0.0;
        hole5.holeCourse = "Kontioranta";
        hole5.par = 4;
        hole5.length = 210;
        hole5.holeNumber = 5;
        Hole hole6 = new Hole();
        hole6.avgPar = 0.0;
        hole6.holeCourse = "Kontioranta";
        hole6.par = 4;
        hole6.length = 171;
        hole6.holeNumber = 6;
        Hole hole7 = new Hole();
        hole7.avgPar = 0.0;
        hole7.holeCourse = "Kontioranta";
        hole7.par = 3;
        hole7.length = 92;
        hole7.holeNumber = 7;
        Hole hole8 = new Hole();
        hole8.avgPar = 0.0;
        hole8.holeCourse = "Kontioranta";
        hole8.par = 3;
        hole8.length = 76;
        hole8.holeNumber = 8;
        Hole hole9 = new Hole();
        hole9.avgPar = 0.0;
        hole9.holeCourse = "Kontioranta";
        hole9.par = 3;
        hole9.length = 88;
        hole9.holeNumber = 9;
        Hole hole10 = new Hole();
        hole10.avgPar = 0.0;
        hole10.holeCourse = "Kontioranta";
        hole10.length = 60;
        hole10.par = 3;
        hole10.holeNumber = 10;
        Hole hole11 = new Hole();
        hole11.avgPar = 0.0;
        hole11.holeCourse = "Kontioranta";
        hole11.par = 4;
        hole11.length = 155;
        hole11.holeNumber = 11;
        Hole hole12 = new Hole();
        hole12.avgPar = 0.0;
        hole12.holeCourse = "Kontioranta";
        hole12.length = 114;
        hole12.par = 3;
        hole12.holeNumber = 12;
        Hole hole13 = new Hole();
        hole13.avgPar = 0.0;
        hole13.holeCourse = "Kontioranta";
        hole13.length = 104;
        hole13.par = 3;
        hole13.holeNumber = 13;
        Hole hole14 = new Hole();
        hole14.avgPar = 0.0;
        hole14.holeCourse = "Kontioranta";
        hole14.length = 221;
        hole14.par = 5;
        hole14.holeNumber = 14;
        Hole hole15 = new Hole();
        hole15.avgPar = 0.0;
        hole15.holeCourse = "Kontioranta";
        hole15.length = 107;
        hole15.par = 3;
        hole15.holeNumber = 15;
        Hole hole16 = new Hole();
        hole16.avgPar = 0.0;
        hole16.holeCourse = "Kontioranta";
        hole16.length = 112;
        hole16.par = 3;
        hole16.holeNumber = 16;
        Hole hole17 = new Hole();
        hole17.avgPar = 0.0;
        hole17.holeCourse = "Kontioranta";
        hole17.length = 170;
        hole17.par = 4;
        hole17.holeNumber = 17;
        Hole hole18 = new Hole();
        hole18.avgPar = 0.0;
        hole18.holeCourse = "Kontioranta";
        hole18.length = 88;
        hole18.par = 3;
        hole18.holeNumber = 18;
        Hole hole19 = new Hole();
        hole19.avgPar = 0.0;
        hole19.holeCourse = "Kontioranta";
        hole19.length = 160;
        hole19.par = 4;
        hole19.holeNumber = 19;
        Hole hole20 = new Hole();
        hole20.avgPar = 0.0;
        hole20.holeCourse = "Kontioranta";
        hole20.length = 108;
        hole20.par = 3;
        hole20.holeNumber = 20;

        dao.insertAll(hole1,hole2,hole3,hole4,hole5,hole6,hole7,hole8, hole9, hole10, hole11, hole12, hole13, hole14, hole15, hole16, hole17, hole18, hole19, hole20);
    }



}