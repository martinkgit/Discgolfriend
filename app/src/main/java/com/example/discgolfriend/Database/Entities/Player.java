package com.example.discgolfriend.Database.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.HashSet;

@Entity
public class Player {

    @PrimaryKey(autoGenerate = true)
    public int playerId;

    @ColumnInfo (name = "name")
    public String name;


}
