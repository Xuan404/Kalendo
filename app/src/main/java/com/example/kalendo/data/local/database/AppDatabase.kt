package com.example.kalendo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kalendo.data.local.dao.AssignmentDao
import com.example.kalendo.data.local.dao.CourseDao
import com.example.kalendo.data.local.entity.AssignmentEntity
import com.example.kalendo.data.local.entity.CourseEntity
import com.example.kalendo.data.mapper.Converters

@Database(entities = [CourseEntity::class, AssignmentEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
    abstract fun assignmentDao(): AssignmentDao
}