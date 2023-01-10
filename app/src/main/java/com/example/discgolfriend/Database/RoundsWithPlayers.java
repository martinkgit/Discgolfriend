package com.example.discgolfriend.Database;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.discgolfriend.Database.Entities.Player;
import com.example.discgolfriend.Database.Entities.Round;

public class RoundsWithPlayers {
    @Embedded
    public Round round;

    @Relation(
            parentColumn = "roundId",
            entityColumn = "playerId"
    )
    public Player player;
}
