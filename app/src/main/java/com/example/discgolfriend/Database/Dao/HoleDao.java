package com.example.discgolfriend.Database.Dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.discgolfriend.Database.Entities.Hole;

import java.util.List;

@Dao
public interface HoleDao {

    @Query("SELECT * FROM hole WHERE holeCourse LIKE :name")
    List<Hole> findHoleByCourse(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Hole... holes);

    @Update
    void updateHole(Hole hole);

    @Query("SELECT*FROM hole")
    Hole[] getAllHoles();

    @Query("DELETE FROM hole WHERE holeCourse LIKE :name")
    void deleteByCourse(String name);


    @Query("DELETE FROM hole")
    void deleteAll();
}
