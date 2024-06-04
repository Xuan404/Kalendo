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
fun TopAppBarCourse(
    title: String,
    onNavigationIconClick: (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null
) {

    var showDialog by remember { mutableStateOf(false) }

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        title = {

            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = {
                onNavigationIconClick?.invoke()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_back),
                    contentDescription = "Back Button",
                    modifier = Modifier.padding(8.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = { showDialog = true }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_add_course),
                    contentDescription = "Add Course",
                    modifier = Modifier.padding(8.dp)
                )
            }

        }
    )

    if (showDialog) {
        FullScreenDialogAddCourse(onDismiss = { showDialog = false })
    }
}

@Preview(showBackground = true)
@Composable
fun MyTopAppBarPreview() {
    KalendoTheme {
        TopAppBarCourse(
            title = "Courses",
        )
    }
}



















