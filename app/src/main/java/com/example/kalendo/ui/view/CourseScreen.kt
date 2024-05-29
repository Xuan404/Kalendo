package com.example.kalendo.ui.view

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kalendo.domain.model.CourseModel
import com.example.kalendo.ui.component.coursescreen.ContentCourse
import com.example.kalendo.ui.component.coursescreen.TopAppBarCourse
import com.example.kalendo.ui.theme.KalendoTheme
import com.example.kalendo.ui.theme.courseColor1
import com.example.kalendo.ui.theme.courseColor3
import com.example.kalendo.ui.theme.courseColor5
import com.example.kalendo.ui.viewmodel.CourseViewModel
import com.example.kalendo.util.Strings
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun CourseScreenContent(
    navController:NavController,
    //courses: MutableList<CourseModel> = mutableListOf(),
    viewModel: CourseViewModel = hiltViewModel()
) {

    val courses by viewModel.courses.observeAsState(emptyList()) //observes the courses LiveData from the ViewModel and updates the UI when the data changes.
    //Log.i("DatabaseCourse", courses.toString())

    // Will need to use this when I start adding the edit functionalities
    var courseTitle by remember { mutableStateOf("") } // defines a state variable to store the current input for the course title.
    var courseColor by remember { mutableStateOf(Color.Black) } // Add a color picker if needed // line defines a state variable to store the current input for the course color.

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
                ContentCourse(
                    modifier = Modifier.padding(innerPadding),
                    courses = courses, // contains the courses to be displayed
                    navController = navController
                )
            }
        )
    }
}


//@Preview(showBackground = true)
//@Composable
//fun CourseScreenContentPreview() {
//    KalendoTheme {
//        val navController = rememberNavController()
//        val courses = DummyData()
//        CourseScreenContent(
//            navController = navController,
//            //courses = courses
//        )
//    }
//}
//
//fun DummyData(): MutableList<CourseModel> {
//    val courses = mutableListOf(
//        CourseModel(title = "CMPUT 300", color = courseColor1),
//        CourseModel(title = "CMPUT 301", color = courseColor5),
//        CourseModel(title = "CMPUT 367", color = courseColor3),
//        CourseModel(title = "CMPUT 300", color = courseColor1),
//        CourseModel(title = "CMPUT 301", color = courseColor5),
//        CourseModel(title = "CMPUT 367", color = courseColor3),
//        CourseModel(title = "CMPUT 300", color = courseColor1),
//        CourseModel(title = "CMPUT 301", color = courseColor5),
//        CourseModel(title = "CMPUT 367", color = courseColor3)
//    )
//
//    return courses
//}