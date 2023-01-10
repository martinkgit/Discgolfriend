package com.example.discgolfriend.Database.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PlayedHole {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "course")
    String course;

    @ColumnInfo(name = "hole")
    int hole;

    @ColumnInfo(name = "result")
    int result;

    @ColumnInfo(name = "player")
    String player;

    @ColumnInfo(name = "time")
    String time;

}
