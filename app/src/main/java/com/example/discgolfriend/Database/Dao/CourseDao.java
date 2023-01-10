package com.example.discgolfriend.Database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.discgolfriend.Database.Entities.Course;
import com.example.discgolfriend.Database.Entities.Player;


@Dao
public interface CourseDao {

    @Query("SELECT * FROM course WHERE courseName LIKE :name LIMIT 1")
    Course findCourseByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Course... courses);

    @Query("SELECT * FROM course")
    Course[] getAllCourses();

    @Query("DELETE FROM course")
    void deleteAll();

}
