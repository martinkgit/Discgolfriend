package com.example.discgolfriend.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.discgolfriend.Database.Entities.Player;

@Dao
public interface PlayerDao {

    @Query("SELECT * FROM player WHERE name LIKE :name LIMIT 1")
    Player findByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Player... players);

    @Query("SELECT * FROM player")
    Player[] getAllPlayers();

    @Query("DELETE FROM player")
    void deleteAll();



}
