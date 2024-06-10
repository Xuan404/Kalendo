package com.example.kalendo.domain.repository

import com.example.kalendo.domain.model.AssignmentModel
import com.example.kalendo.domain.model.AssignmentWithCourseColor
import java.time.LocalDate

interface AssignmentRepository {
    suspend fun insertAssignment(assignment: AssignmentModel)
    suspend fun deleteAssignment(assignmentId: Int)
    suspend fun getAssignmentsForCourse(courseId: Int): List<AssignmentModel>
    suspend fun getAssignmentsWithCourseColorByDate(date: LocalDate): List<AssignmentWithCourseColor>

}