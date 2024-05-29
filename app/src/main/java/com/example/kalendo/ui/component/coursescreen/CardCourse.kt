package com.example.kalendo.ui.component.coursescreen


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kalendo.R
import com.example.kalendo.domain.model.CourseModel
import com.example.kalendo.ui.theme.KalendoTheme
import com.example.kalendo.ui.theme.courseColor1

@Composable
fun CardCourse(course: CourseModel, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(5.dp)
            .border(0.5.dp, MaterialTheme.colorScheme.onSurface, RoundedCornerShape(25.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
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
                    painter = painterResource(id = R.drawable.course_label),
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


@Preview(showBackground = true)
@Composable
fun CardPreview() {
    KalendoTheme {
        CardCourse(course = CourseModel(title = "CMPUT 300", color = courseColor1),  onClick = {})
    }
}
