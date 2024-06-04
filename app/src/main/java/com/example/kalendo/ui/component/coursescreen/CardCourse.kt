package com.example.kalendo.ui.component.coursescreen


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kalendo.R
import com.example.kalendo.domain.model.CourseModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardCourse(
    course: CourseModel,
    onClick: () -> Unit,
    isSelected: Boolean,
    onLongClick: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(
            if (isSelected) MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 7.dp)
            .combinedClickable (
                onLongClick = {onLongClick()},
                onClick = {onClick()},
            )
            .border(
                width = if (isSelected) 2.dp else 0.5.dp,
                color = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(25.dp)),

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp),
            verticalArrangement = Arrangement.Center // Center the Row vertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_course_label),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = course.color
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = course.title,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun CardPreview() {
//    KalendoTheme {
//        CardCourse(course = CourseModel(title = "CMPUT 300", color = courseColor1),  onClick = {})
//    }
//}
