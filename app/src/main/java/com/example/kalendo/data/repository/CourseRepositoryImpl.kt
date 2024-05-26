package com.example.kalendo.data.repository

import com.example.kalendo.data.local.dao.CourseDao
import com.example.kalendo.data.mapper.toDomainModel
import com.example.kalendo.data.mapper.toEntityModel
import com.example.kalendo.domain.model.CourseModel
import com.example.kalendo.domain.repository.CourseRepository
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val courseDao: CourseDao
) : CourseRepository {
    override suspend fun insertCourse(course: CourseModel) {
        courseDao.insert(course.toEntityModel())
    }

    override suspend fun deleteCourse(courseId: Int) {
        courseDao.delete(courseId)
    }

    override suspend fun getAllCourses(): List<CourseModel> {
        return courseDao.getAllCourses().map { it.toDomainModel() }
    }
}