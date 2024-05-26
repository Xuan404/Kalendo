package com.example.kalendo.data.local.entity

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kalendo.domain.model.AssignmentModel

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val color: Color,
    val tasks: MutableList<AssignmentModel> = mutableListOf()
)