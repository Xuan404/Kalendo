package com.example.kalendo.ui.component.coursescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kalendo.domain.model.CourseModel
import com.google.gson.Gson

@Composable
fun ContentCourse(
    modifier: Modifier = Modifier,
    courses: List<CourseModel> = emptyList(),
    navController: NavController,
    selectedItems: Set<Int>,
    showSelectionAppBar: Boolean,
    onItemSelected: (CourseModel, Boolean) -> Unit,
    onItemLongClicked: (CourseModel) -> Unit
) {
    val gson = Gson()

    LazyColumn(
        contentPadding = PaddingValues(start = 25.dp, end = 25.dp, top = 40.dp, bottom = 40.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier

    ) {
        items(courses) { course ->
            // Is initially inserted as false but becomes true after being added to list
            val isSelected = selectedItems.contains(course.id)
            CardCourse(
                course = course,
                isSelected = isSelected,
                onClick = {
                    if (showSelectionAppBar) {
                        onItemSelected(course, isSelected)
                    } else {
                        val courseJson = gson.toJson(course)
                        navController.navigate("edit/$courseJson")
                    }
                },
                onLongClick = {
                    onItemLongClicked(course)
                }
            )
        }

    }
}

