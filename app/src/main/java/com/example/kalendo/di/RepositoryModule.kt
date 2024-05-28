package com.example.kalendo.di

import com.example.kalendo.data.repository.AssignmentRepositoryImpl
import com.example.kalendo.data.repository.CourseRepositoryImpl
import com.example.kalendo.domain.repository.AssignmentRepository
import com.example.kalendo.domain.repository.CourseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCourseRepository(
        courseRepositoryImpl: CourseRepositoryImpl
    ): CourseRepository

    @Binds
    @Singleton
    abstract fun bindAssignmentRepository(
        assignmentRepositoryImpl: AssignmentRepositoryImpl
    ): AssignmentRepository
}