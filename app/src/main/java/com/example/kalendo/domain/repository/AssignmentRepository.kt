package com.example.kalendo.domain.repository

import com.example.kalendo.domain.model.AssignmentModel

interface AssignmentRepository {
    suspend fun insertAssignment(assignment: AssignmentModel)
    suspend fun deleteAssignment(assignmentId: Int)
    suspend fun getAssignmentsForCourse(courseId: Int): List<AssignmentModel>
}