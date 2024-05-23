package com.example.kalendo.ui.component.coursescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kalendo.data.model.AssignmentModel
import com.example.kalendo.data.model.CourseModel

@Composable
fun ContentCourse(
    modifier: Modifier = Modifier,
    courses: MutableList<CourseModel> = mutableListOf()
) {
    LazyColumn(
        contentPadding = PaddingValues(start = 25.dp, end = 25.dp, top = 40.dp, bottom = 40.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier

    ) {
        items(courses) { course ->
            CardCourse(course = course)
        }
    }
}

