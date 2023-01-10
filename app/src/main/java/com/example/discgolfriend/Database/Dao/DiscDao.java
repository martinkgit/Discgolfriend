package com.example.discgolfriend.Database.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.discgolfriend.Database.Entities.Disc;

import java.util.List;

@Dao
public interface DiscDao {
    @Query("SELECT * FROM disc WHERE manufacturer LIKE :name")
    List<Disc> findByManufacturer(String name);

    @Query("SELECT * FROM disc WHERE name LIKE :name")
    List<Disc> findByName(String name);

    @Query("SELECT * FROM disc WHERE plastic LIKE :name")
    List<Disc> findByPlastic(String name);

}
