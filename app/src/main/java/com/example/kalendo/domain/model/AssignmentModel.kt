package com.example.kalendo.domain.model

import java.util.Date
import java.time.LocalTime

data class AssignmentModel(
    val date: Date,
    val time: LocalTime,
    val isDeadline: Boolean
)
