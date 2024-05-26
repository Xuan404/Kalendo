package com.example.kalendo.data.repository

import com.example.kalendo.data.local.dao.AssignmentDao
import com.example.kalendo.data.mapper.toDomainModel
import com.example.kalendo.data.mapper.toEntityModel
import com.example.kalendo.domain.model.AssignmentModel
import com.example.kalendo.domain.repository.AssignmentRepository
import javax.inject.Inject

class AssignmentRepositoryImpl @Inject constructor(
    private val assignmentDao: AssignmentDao
) : AssignmentRepository {
    override suspend fun insertAssignment(assignment: AssignmentModel) {
        assignmentDao.insert(assignment.toEntityModel())
    }

    override suspend fun deleteAssignment(assignmentId: Int) {
        assignmentDao.delete(assignmentId)
    }

    override suspend fun getAssignmentsForCourse(courseId: Int): List<AssignmentModel> {
        return assignmentDao.getAssignmentsForCourse(courseId).map { it.toDomainModel() }
    }
}