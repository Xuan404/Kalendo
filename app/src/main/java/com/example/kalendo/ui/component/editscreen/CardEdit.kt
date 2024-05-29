package com.example.kalendo.ui.component.editscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.kalendo.domain.model.AssignmentModel
import com.example.kalendo.domain.model.CourseModel
import com.example.kalendo.ui.component.coursescreen.CardCourse
import com.example.kalendo.ui.theme.KalendoTheme
import com.example.kalendo.ui.theme.courseColor1
import java.security.KeyStore.TrustedCertificateEntry
import java.time.LocalDate
import java.time.LocalTime
import java.util.Date


@Composable
fun CardEdit(assignment: AssignmentModel, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = assignment.title,
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(15.dp))
            DetailRow(
                label = "Date",
                value = assignment.date.toString()
            )
            Spacer(modifier = Modifier.height(8.dp))
            DetailRow(
                label = "Time",
                value = assignment.time.toString()
            )
            Spacer(modifier = Modifier.height(8.dp))
            DetailRow(
                label = "Type",
                value =  if (assignment.isDeadline) "Deadline" else "To-Do",
                isDeadline = assignment.isDeadline)
        }
    }
}

@Composable
fun DetailRow(label: String, value: String, isDeadline: Boolean = false) {
    Row(
        modifier = Modifier
            .padding(start = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label :",
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = value,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(10.dp))
            if (label == "Type") {
                Icon(
                    //painter = painterResource(id = R.drawable.deadline), // Replace with your icon resource
                    painter = painterResource(
                        id = if (isDeadline) R.drawable.deadline else R.drawable.to_do
                    ),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun CardEditPreview() {
    KalendoTheme {
        CardEdit(assignment = AssignmentModel(id = 1, courseId = 1, title = "Quiz 9", date = LocalDate.now(), time = LocalTime.now() , isDeadline = false),  onClick = {})
    }
}
