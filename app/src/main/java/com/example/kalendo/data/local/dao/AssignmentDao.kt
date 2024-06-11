package com.example.kalendo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kalendo.data.local.entity.AssignmentEntity
import com.example.kalendo.domain.model.AssignmentWithCourseColor

@Dao
interface AssignmentDao {
    @Insert
    suspend fun insert(assignment: AssignmentEntity)

    @Query("DELETE FROM assignments WHERE id = :assignmentId")
    suspend fun delete(assignmentId: Int)

    @Query("SELECT * FROM assignments WHERE courseId = :courseId")
    suspend fun getAssignmentsForCourse(courseId: Int): List<AssignmentEntity>

    @Query("""
        SELECT assignments.*, courses.color AS courseColor, courses.title AS courseTitle
        FROM assignments 
        JOIN courses ON assignments.courseId = courses.id 
        WHERE assignments.date = :date
    """)
    suspend fun getAssignmentsWithCourseColorByDate(date: String): List<AssignmentWithCourseColor>
}