package com.example.kalendo.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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


    // For Production Stage only, comment out this in the final version
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .createFromAsset("kalendoDummyData.db") // Specify the pre-populated database file
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}