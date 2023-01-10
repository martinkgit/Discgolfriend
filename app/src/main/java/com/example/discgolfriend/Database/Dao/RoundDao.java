package com.example.discgolfriend.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.discgolfriend.Database.CourseWithRounds;
import com.example.discgolfriend.Database.Entities.Round;
import com.example.discgolfriend.Database.RoundsWithPlayers;

import java.util.List;

@Dao
public interface RoundDao {

    @Query("SELECT * FROM round WHERE player LIKE :name")
    List<Round> findByPlayer(String name);

    @Query("SELECT * FROM round WHERE course LIKE :name")
    List<Round> findRoundByCourse(String name);

    @Query("SELECT * FROM round WHERE time LIKE :name")
    List<Round> findRoundByTime(String name);

    @Query("SELECT * FROM round WHERE course LIKE :course AND player LIKE :name")
    List<Round> findRoundByPlayerAndCourse(String course, String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Round... rounds);

    @Transaction
    @Query("SELECT * FROM Round")
    public List<CourseWithRounds> getRoundsFromCourses();

    @Transaction
    @Query("SELECT * FROM Round ")
    public List<RoundsWithPlayers> getRoundsFromPlayers();

    @Query("SELECT * FROM round")
    Round[] getAllRounds();

    @Query("DELETE FROM round")
    void deleteAll();


}
