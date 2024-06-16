package com.example.kalendo.ui.component.homescreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kalendo.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import com.example.kalendo.domain.model.AssignmentWithCourseColor
import com.example.kalendo.ui.theme.defaultColor


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateContentDetailsHome(
    onDismissRequest: () -> Unit,
    selectedDate: LocalDate?,
    assignments: List<AssignmentWithCourseColor>
) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val boxWidth = screenWidth * 0.95f
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val boxHeight = screenHeight * 0.8f

    // Formatter for month name and date
    val monthFormatter = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH)
    val dateFormatter = DateTimeFormatter.ofPattern("dd", Locale.ENGLISH)
    // Retrieve month name and date
    val monthName = selectedDate?.format(monthFormatter)
    val date = selectedDate?.format(dateFormatter)

    // Handle the sorting of items
    //val groupedAssignments = sortAndGroupAssignments(assignments)
    val assignmentsTodo = sortAndGroupAssignments(assignments.filter { !it.isDeadline })
    val assignmentsDue = sortAndGroupAssignments(assignments.filter { it.isDeadline })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.1f))
            .clickable(
                onClick = onDismissRequest,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        Box(
            modifier = Modifier
                .width(boxWidth)
                .height(boxHeight)
                .align(Alignment.Center)
                .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(20.dp))
                .padding(horizontal = 16.dp, vertical = 25.dp)
                .clickable(
                    onClick = { },
                    indication = null, // Remove the click animation
                    interactionSource = remember { MutableInteractionSource() }
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Header(
                    month = monthName,
                    date = date,
                    modifier = Modifier.weight(0.2f)
                )
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 10.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                TypeList(
                    modifier = Modifier.weight(1f),
                    assignments = assignmentsTodo,
                    iconDrawable = R.drawable.icon_to_do,
                    typeName = "To-Do"
                )
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 10.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                TypeList(
                    modifier = Modifier.weight(1f),
                    assignments = assignmentsDue,
                    iconDrawable = R.drawable.icon_deadline,
                    typeName = "Deadline"
                )
            }
        }
    }
}

@Composable
private fun Header(
    month: String?,
    date: String?,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "$month $date",
            fontSize = 30.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@Composable
private fun TypeList(
    modifier: Modifier = Modifier,
    assignments: Map<String, List<AssignmentWithCourseColor>>,
    iconDrawable : Int,
    typeName: String
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                painter = painterResource(id = iconDrawable),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = typeName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 5.dp)
        ) {
            assignments.forEach { (courseTitle, assignments) ->
                val courseColor = assignments.first().courseColor
                item {
                    CourseItem(
                        courseColor = courseColor,
                        courseTitle = courseTitle
                    )
                }
                items(assignments) { item ->
                    AssignmentItem(item = item)
                }
            }
        }

    }
}

private fun sortAndGroupAssignments(assignments: List<AssignmentWithCourseColor>): Map<String, List<AssignmentWithCourseColor>> {
    return assignments
        .sortedWith(compareBy({ it.courseId }, { it.courseTitle }))
        .groupBy { it.courseTitle }
}

@Composable
private fun CourseItem(courseColor: Long, courseTitle: String){
    Box(contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .height(25.dp)
                .background(
                    shape = RoundedCornerShape(45.dp),
                    color = Color(courseColor)
                )
        ){
            Text(
                text = courseTitle,
                fontSize = 12.sp,
                color = Color(courseColor),
                modifier = Modifier.padding(20.dp)
            )
        }
        Text(
            text = courseTitle,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

    }
}

@Composable
private fun AssignmentItem(item: AssignmentWithCourseColor){
    Text(
        text = item.title,
        fontSize = 15.sp,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = FontWeight.ExtraLight,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp, horizontal = 20.dp)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun PreviewAlignedRow() {
    val localDate = LocalDate.of(2024, 6, 15)

    DateContentDetailsHome(
        onDismissRequest = {},
        selectedDate = localDate,
        assignments = emptyList()
    )
}