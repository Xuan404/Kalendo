package com.example.kalendo.ui.component.editscreen

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.unit.dp
import com.example.kalendo.R
import com.example.kalendo.domain.model.CourseModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarEdit(
    title: String,
    onNavigationIconClick: (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null,
    course: CourseModel?
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
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back Button",
                    modifier = Modifier.padding(8.dp)
                )
            }
        },
        actions = {
//            IconButton(onClick = { /* Do nothing for preview */ }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.refresh),
//                    contentDescription = "Refresh",
//                    modifier = Modifier.padding(8.dp)
//                )
//            }
            IconButton(onClick = { /* Do nothing for preview */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.sort),
                    contentDescription = "Grid Rectangle",
                    modifier = Modifier.padding(8.dp)
                )
            }
            IconButton(onClick = { showDialog = true }) {
                Icon(
                    painter = painterResource(id = R.drawable.add_component),
                    contentDescription = "Add Component",
                    modifier = Modifier.padding(8.dp)
                )
            }

        }
    )

    if (showDialog) {
        FullScreenDialogAddEdit(
            onDismiss = { showDialog = false },
            course = course)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MyTopAppBarPreview() {
//    KalendoTheme {
//        TopAppBarEdit(
//            title = "Edit",
//        )
//    }
//}
//
