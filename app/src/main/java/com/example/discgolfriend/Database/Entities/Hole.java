package com.example.discgolfriend.Database.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Hole {

    @PrimaryKey(autoGenerate = true)
    public int holeId;

    @ColumnInfo(name = "holeNumber")
    public int holeNumber;

    @ColumnInfo(name = "holeCourse")
    public String holeCourse;

    @ColumnInfo(name = "par")
    public int par;

    @ColumnInfo(name = "length")
    public int length;

    @ColumnInfo(name = "avgPar")
    public Double avgPar;
}
