package com.example.kalendo.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class AssignmentWithCourseColorModel(
    val id: Int,
    val courseId: Int,
    val courseTitle: String,
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
    val isDeadline: Boolean,
    var isCompleted: Boolean,
    val courseColor: Long
)
