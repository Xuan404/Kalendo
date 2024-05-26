package com.example.kalendo.domain.repository

import com.example.kalendo.domain.model.CourseModel

interface CourseRepository {
    suspend fun insertCourse(course: CourseModel)
    suspend fun deleteCourse(courseId: Int)
    suspend fun getAllCourses(): List<CourseModel>
}