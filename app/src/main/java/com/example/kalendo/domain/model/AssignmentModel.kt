package com.example.kalendo.domain.model

import java.util.Date
import java.time.LocalTime

data class AssignmentModel(
    val id: Int = 0,
    val courseId: Int,
    val title: String,
    val date: Date,
    val time: LocalTime,
    val isDeadline: Boolean
)
