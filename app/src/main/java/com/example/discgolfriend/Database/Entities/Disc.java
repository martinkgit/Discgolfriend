package com.example.discgolfriend.Database.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Disc {

    public int discId;

    public String name;

    public String manufacturer;

    public int speed;

    public int glide;

    public int turn;

    public int fade;

    public String plastic;
}
