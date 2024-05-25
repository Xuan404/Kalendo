package com.example.kalendo.ui.component.editscreen

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
import androidx.compose.material3.Divider
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
import com.example.kalendo.domain.model.CourseModel
import com.example.kalendo.ui.theme.defaultColor
import com.google.gson.Gson

@Composable
fun ContentEdit(modifier: Modifier = Modifier, courseJson: String?) {

    // Deserialize the Object
    val gson = Gson()
    val course = courseJson?.let { gson.fromJson(it, CourseModel::class.java) }

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
                modifier = Modifier.weight(6f)
            )
        }
    }

}

@Composable
fun TopPart(modifier: Modifier = Modifier,
            color: Color,
            title: String
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



// Work on this after making the viewmodel**************
@Composable
fun BottomPart(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Hello",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 24.sp
            )
        }
    }


}