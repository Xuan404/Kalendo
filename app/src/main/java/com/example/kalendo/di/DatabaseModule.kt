package com.example.kalendo.di

import android.content.Context
import androidx.room.Room
import com.example.kalendo.data.local.dao.AssignmentDao
import com.example.kalendo.data.local.dao.CourseDao
import com.example.kalendo.data.local.database.AppDatabase
import com.example.kalendo.data.repository.AssignmentRepositoryImpl
import com.example.kalendo.data.repository.CourseRepositoryImpl
import com.example.kalendo.domain.repository.AssignmentRepository
import com.example.kalendo.domain.repository.CourseRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "database2"
        ).build()
    }

    @Provides
    fun provideCourseDao(database: AppDatabase): CourseDao {
        return database.courseDao()
    }

    @Provides
    fun provideAssignmentDao(database: AppDatabase): AssignmentDao {
        return database.assignmentDao()
    }
}
