package com.example.kalendo.domain.model

import androidx.compose.ui.graphics.Color

data class CourseModel(
    val id: Int = 0,
    val title: String,
    val color: Color,
    val tasks: MutableList<AssignmentModel> = mutableListOf()
)
