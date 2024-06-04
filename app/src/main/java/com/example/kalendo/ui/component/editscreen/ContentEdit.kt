package com.example.kalendo.ui.component.editscreen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kalendo.R
import com.example.kalendo.domain.model.AssignmentModel
import com.example.kalendo.domain.model.CourseModel
import com.example.kalendo.ui.component.coursescreen.CardCourse
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContentEdit(
    modifier: Modifier = Modifier,
    courseJson: String?,
    assignments: List<AssignmentModel>,
    selectedItems: Set<Int>,
    showSelectionAppBar: Boolean,
    onItemSelected: (AssignmentModel, Boolean) -> Unit,
    onItemLongClicked: (AssignmentModel) -> Unit
) {

    // Deserialize the Object
    val gson = Gson()
    val course = courseJson?.let { gson.fromJson(it, CourseModel::class.java) }
    Log.i("DatabaseCourse", courseJson.toString())

    Box(
        modifier = modifier
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            TopPart(
                modifier = Modifier.weight(1f),
                color = course?.color ?: Color.Gray, // Use course.color if course is not null, otherwise use default color
                title = course?.title ?: "Untitled" // Use course.title if course is not null, otherwise use default title
            )
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 25.dp),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.onBackground
            )
            BottomPart(
                modifier = Modifier.weight(6f),
                assignments = assignments,
                selectedItems = selectedItems,
                showSelectionAppBar = showSelectionAppBar,
                onItemSelected = onItemSelected,
                onItemLongClicked = onItemLongClicked
            )
        }
    }

}

@Composable
fun TopPart(
    modifier: Modifier = Modifier,
    color: Color,
    title: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 20.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.course_label),
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(14.dp),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }


}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomPart(
    modifier: Modifier = Modifier,
    assignments: List<AssignmentModel>,
    selectedItems: Set<Int>,
    showSelectionAppBar: Boolean,
    onItemSelected: (AssignmentModel, Boolean) -> Unit,
    onItemLongClicked: (AssignmentModel) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        // Get the list of assignments and pass it here
        LazyColumn(
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = modifier

        ) {
            items(assignments) { assignment ->
                // Is initially inserted as false but becomes true after being added to list
                val isSelected = selectedItems.contains(assignment.id)
                CardEdit(
                    assignment = assignment,
                    isSelected = isSelected,
                    onClick = {
                        if (showSelectionAppBar) {
                            onItemSelected(assignment, isSelected)
                        }
                    },
                    onLongClick = {
                        onItemLongClicked(assignment)
                    }
                )

            }

        }
    }


}