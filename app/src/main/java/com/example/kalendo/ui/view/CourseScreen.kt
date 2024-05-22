package com.example.kalendo.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kalendo.ui.component.coursescreen.ContentCourse
import com.example.kalendo.ui.component.coursescreen.TopAppBarCourse
import com.example.kalendo.ui.theme.KalendoTheme

@Composable
fun CourseScreenContent(navController:NavController) {

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBarCourse(
                    title = "Courses",
                    onNavigationIconClick = {},
                    actions = {}
                )
            },
            content = { innerPadding ->
                ContentCourse(modifier = Modifier.padding(innerPadding))
            }
        )
    }
}


//@Preview(showBackground = true)
//@Composable
//fun CourseScreenContentPreview() {
//    KalendoTheme {
//        CourseScreenContent(navController = NavHostController)
//    }
//}