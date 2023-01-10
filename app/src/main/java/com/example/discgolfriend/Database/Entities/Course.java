package com.example.discgolfriend.Database.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

import java.util.HashMap;
import java.util.HashSet;

@Entity
public class Course {

    @PrimaryKey(autoGenerate = true)
    public int courseId;

    @ColumnInfo(name = "courseName")
    public String courseName;

    @ColumnInfo(name = "holes")
    public int holes;

    @ColumnInfo(name = "par")
    public int par;


}
