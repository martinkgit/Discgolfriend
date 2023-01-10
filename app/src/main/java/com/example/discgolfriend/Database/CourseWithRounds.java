package com.example.discgolfriend.Database;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.discgolfriend.Database.Entities.Course;
import com.example.discgolfriend.Database.Entities.Round;

public class CourseWithRounds {
    @Embedded public Round round;

    @Relation(
            parentColumn = "roundId",
            entityColumn = "courseId"
    )
    public Course course;

}
