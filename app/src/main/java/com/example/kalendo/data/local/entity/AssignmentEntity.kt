package com.example.kalendo.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime
import java.util.Date

@Entity(
    tableName = "assignments",
    foreignKeys = [ForeignKey(
        entity = CourseEntity::class,
        parentColumns = ["id"],
        childColumns = ["courseId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class AssignmentEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val courseId: Int,
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
    val isDeadline: Boolean
)