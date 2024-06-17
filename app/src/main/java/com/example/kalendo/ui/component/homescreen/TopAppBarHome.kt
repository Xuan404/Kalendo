package com.example.kalendo.ui.component.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kalendo.ui.theme.KalendoTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kalendo.R
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarHome(
    title: String,
    onNavigationIconClick: (() -> Unit)? = null,
    onReturnToDateClick : () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        title = {
            Text(text = title)
        },
        actions = {
            IconButton(onClick = { /* Do nothing for preview */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_calender),
                    contentDescription = "Calender",
                    modifier = Modifier.padding(10.dp)
                )
            }
            Box(contentAlignment = Alignment.Center ) {
                val currentDate = Calendar.getInstance().get(Calendar.DATE)
                Text(
                    text = "$currentDate",
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(top = 8.dp)
                )
                IconButton(onClick =  {onReturnToDateClick()}) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_date1),
                        contentDescription = "Return to current date",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            IconButton(onClick = { /* Handle image button click */ }) {
                Image(
                    painter = painterResource(id = R.drawable.default_profile),
                    contentDescription = "Default Profile Pic",
                    modifier = Modifier
                        .size(41.dp)
                )
            }

        }
    )
}

@Preview(showBackground = true)
@Composable
fun MyTopAppBarPreview() {
    KalendoTheme {
        TopAppBarHome(
            title = "Kalendo",
            onReturnToDateClick = {}
        )
    }
}
