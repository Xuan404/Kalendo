package com.example.kalendo.data.local.database

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kalendo.data.local.dao.AssignmentDao
import com.example.kalendo.data.local.dao.CourseDao
import com.example.kalendo.data.local.entity.AssignmentEntity
import com.example.kalendo.data.local.entity.CourseEntity
import com.example.kalendo.data.mapper.Converters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.util.Date

@Database(entities = [CourseEntity::class, AssignmentEntity::class], version = 1)
@TypeConverters(Converters::class)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun courseDao(): CourseDao
//    abstract fun assignmentDao(): AssignmentDao
//}



// For Production/Testing purpose only
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
    abstract fun assignmentDao(): AssignmentDao

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.courseDao(), database.assignmentDao())
                }
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        suspend fun populateDatabase(courseDao: CourseDao, assignmentDao: AssignmentDao) {
            // Insert dummy courses
            val course1 = CourseEntity(title = "CMPUT 300", color = Color(0xFFE54F41).toArgb())
            val course2 = CourseEntity(title = "CMPUT 267", color = Color(0xFF4E9466).toArgb())
            val course3 = CourseEntity(title = "CMPUT 301", color = Color(0xFFE5BF4C).toArgb())
            val course4 = CourseEntity(title = "CMPUT 367", color = Color(0xFFE5673E).toArgb())
            val course5 = CourseEntity(title = "CMPUT 401", color = Color(0xFFAE5FC1).toArgb())
            val course6 = CourseEntity(title = "CMPUT 175", color = Color(0xFFD98479).toArgb())
            courseDao.insert(course1)
            courseDao.insert(course2)
            courseDao.insert(course3)
            courseDao.insert(course4)
            courseDao.insert(course5)
            courseDao.insert(course6)

            // Insert dummy assignments
            val assignment1 = AssignmentEntity(courseId = 1, title = "Quiz 8", date = LocalDate.now(), time = LocalTime.now(), isDeadline = true)
            val assignment3 = AssignmentEntity(courseId = 1, title = "Quiz 9", date = LocalDate.now(), time = LocalTime.now(), isDeadline = false)
            val assignment2 = AssignmentEntity(courseId = 2, title = "Lab 1", date = LocalDate.now(), time = LocalTime.now(), isDeadline = false)
            assignmentDao.insert(assignment1)
            assignmentDao.insert(assignment2)
            assignmentDao.insert(assignment3)
            Log.i("DatabaseAssignment", assignment1.toString())
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "dummy_database8"
                )
                    .addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}