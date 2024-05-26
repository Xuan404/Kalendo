package com.example.kalendo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kalendo.data.local.entity.CourseEntity

@Dao
interface CourseDao {
    @Insert
    suspend fun insert(course: CourseEntity)

    @Query("DELETE FROM courses WHERE id = :courseId")
    suspend fun delete(courseId: Int)

    @Query("SELECT * FROM courses")
    suspend fun getAllCourses(): List<CourseEntity>

    //Add other Queries as necessary
}