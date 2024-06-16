package com.example.kalendo.ui.component.editscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kalendo.R
import com.example.kalendo.domain.model.AssignmentModel
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CardEdit(
    assignment: AssignmentModel,
    onClick: () -> Unit,
    isSelected: Boolean,
    onLongClick: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            if (isSelected) MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 1.dp)
            .fillMaxWidth()
            .combinedClickable (
                onLongClick = {onLongClick()},
                onClick = {onClick()},
            )
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp)),
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
                value = assignment.date.format(DateTimeFormatter.ofPattern("E, MMM d, yyyy")).toString()
            )
            Spacer(modifier = Modifier.height(8.dp))
            DetailRow(
                label = "Time",
                value = assignment.time.format(DateTimeFormatter.ofPattern("h:mm a")).toString()
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
                    painter = painterResource(
                        id = if (isDeadline) R.drawable.icon_deadline else R.drawable.icon_to_do
                    ),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}



//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true)
//@Composable
//fun CardEditPreview() {
//    KalendoTheme {
//        CardEdit(assignment = AssignmentModel(id = 1, courseId = 1, title = "Quiz 9", date = LocalDate.now(), time = LocalTime.now() , isDeadline = false),  onClick = {})
//    }
//}
