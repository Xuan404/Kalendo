package com.example.kalendo.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kalendo.data.model.CourseModel
import com.example.kalendo.ui.component.coursescreen.ContentCourse
import com.example.kalendo.ui.component.coursescreen.TopAppBarCourse
import com.example.kalendo.ui.theme.KalendoTheme
import com.example.kalendo.ui.theme.courseColor1
import com.example.kalendo.ui.theme.courseColor2
import com.example.kalendo.ui.theme.courseColor3
import com.example.kalendo.ui.theme.courseColor5
import com.example.kalendo.util.Strings

@Composable
fun CourseScreenContent(navController:NavController, courses: MutableList<CourseModel> = mutableListOf()) {

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBarCourse(
                    title = Strings.COURSES_SCREEN_TITLE,
                    onNavigationIconClick = {navController.popBackStack()},
                    actions = {}
                )
            },
            content = { innerPadding ->
                ContentCourse(modifier = Modifier.padding(innerPadding), courses = courses)
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CourseScreenContentPreview() {
    KalendoTheme {
        val navController = rememberNavController()
        val courses = mutableListOf(
            CourseModel(title = "CMPUT 300", color = courseColor1),
            CourseModel(title = "CMPUT 301", color = courseColor5),
            CourseModel(title = "CMPUT 367", color = courseColor3),
            CourseModel(title = "CMPUT 300", color = courseColor1),
            CourseModel(title = "CMPUT 301", color = courseColor5),
            CourseModel(title = "CMPUT 367", color = courseColor3),
            CourseModel(title = "CMPUT 300", color = courseColor1),
            CourseModel(title = "CMPUT 301", color = courseColor5),
            CourseModel(title = "CMPUT 367", color = courseColor3)
        )
        CourseScreenContent(navController = navController, courses = courses)
    }
}