package com.example.kalendo.data.model

import androidx.compose.ui.graphics.Color

data class CourseModel(
    val title: String,
    val color: Color,
    val tasks: MutableList<AssignmentModel> = mutableListOf()
)
