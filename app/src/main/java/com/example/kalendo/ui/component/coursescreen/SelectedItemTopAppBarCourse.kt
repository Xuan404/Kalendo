package com.example.kalendo.ui.component.coursescreen

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kalendo.R
import com.example.kalendo.ui.theme.KalendoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectedItemTopAppBarCourse(
    selectedItemCount: Int,
    onClearSelection: () -> Unit,
    onDeleteSelected: () -> Unit
) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            titleContentColor = MaterialTheme.colorScheme.onSecondary
        ),
        title = {
            Text(
                text = "$selectedItemCount" ,
                modifier = Modifier.padding(10.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = { onClearSelection }) {
                Icon(
                    painter = painterResource(id = R.drawable.cancel),
                    contentDescription = "Cancel Button",
                    modifier = Modifier.padding(12.dp),
                    tint = MaterialTheme.colorScheme.onSecondary

                )
            }
        },
        actions = {
            IconButton(onClick = { onDeleteSelected }) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "Delete Course",
                    modifier = Modifier.padding(8.dp),
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }

        }
    )


}

@Preview(showBackground = true)
@Composable
fun MySelectedItemTopAppBarCoursePreview() {
    KalendoTheme {
        SelectedItemTopAppBarCourse(
            selectedItemCount = 5,
            onClearSelection = { Unit },
            onDeleteSelected = { Unit }

        )
    }
}

