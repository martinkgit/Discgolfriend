package com.example.discgolfriend.Database.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;



@Entity
public class Round {

    @PrimaryKey(autoGenerate = true)
    public int roundId;

    @ColumnInfo(name = "course")
    public String course;

    @ColumnInfo (name = "player")
    public String player;

    @ColumnInfo (name = "result")
    public String result;

    @ColumnInfo (name ="time")
    public String time;




}

