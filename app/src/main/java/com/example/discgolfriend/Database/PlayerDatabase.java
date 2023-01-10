package com.example.discgolfriend.Database;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.discgolfriend.Database.Dao.CourseDao;
import com.example.discgolfriend.Database.Dao.HoleDao;
import com.example.discgolfriend.Database.Dao.PlayerDao;
import com.example.discgolfriend.Database.Dao.RoundDao;
import com.example.discgolfriend.Database.Entities.Course;
import com.example.discgolfriend.Database.Entities.Player;
import com.example.discgolfriend.Database.Entities.Round;
import com.example.discgolfriend.Database.Entities.Hole;

/**
 * Database constructor
 */
@Database(entities = {Player.class, Course.class, Round.class, Hole.class}, version = 4)
public abstract class PlayerDatabase extends RoomDatabase {
public abstract PlayerDao playerDao();
public abstract CourseDao courseDao();
public abstract HoleDao holeDao();
public abstract RoundDao roundDao();

}
