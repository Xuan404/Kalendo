package com.example.kalendo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kalendo.data.local.entity.AssignmentEntity

@Dao
interface AssignmentDao {
    @Insert
    suspend fun insert(assignment: AssignmentEntity)

    @Query("DELETE FROM assignments WHERE id = :assignmentId")
    suspend fun delete(assignmentId: Int)

    @Query("SELECT * FROM assignments WHERE courseId = :courseId")
    suspend fun getAssignmentsForCourse(courseId: Int): List<AssignmentEntity>

    //Add other Queries as necessary
}