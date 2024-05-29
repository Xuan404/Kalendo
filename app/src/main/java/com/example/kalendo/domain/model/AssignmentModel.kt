package com.example.kalendo.domain.model

import java.time.LocalDate
import java.util.Date
import java.time.LocalTime

data class AssignmentModel(
    val id: Int = 0,
    val courseId: Int,
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
    val isDeadline: Boolean
)
