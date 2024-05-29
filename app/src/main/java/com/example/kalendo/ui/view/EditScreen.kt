package com.example.kalendo.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kalendo.domain.model.CourseModel
import com.example.kalendo.ui.component.editscreen.ContentEdit
import com.example.kalendo.ui.component.editscreen.TopAppBarEdit
import com.example.kalendo.ui.theme.KalendoTheme
import com.example.kalendo.ui.viewmodel.AssignmentViewModel
import com.example.kalendo.util.Strings
import com.google.gson.Gson

@Composable
fun EditScreenContent(
    navController: NavController,
    courseJson: String?,
    viewModel: AssignmentViewModel = hiltViewModel()
) {

    val assignments by viewModel.assignments.observeAsState(emptyList())
    var assignmentTitle by remember { mutableStateOf("") }
    var assignmentDescription by remember { mutableStateOf("") }

    // Deserialize the Object
    val gson = Gson()
    val course = courseJson?.let { gson.fromJson(it, CourseModel::class.java) }

    if (course != null) {
        LaunchedEffect(course.id) {
            viewModel.getAssignmentsForCourse(course.id)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBarEdit(
                    title = Strings.EDIT_SCREEN_TITLE,
                    onNavigationIconClick = {navController.popBackStack()},
                    actions = {}
                )
            },
            content = { innerPadding ->
                ContentEdit(modifier = Modifier.padding(innerPadding), courseJson =  courseJson, assignments =  assignments)
            }
        )
    }
}


//@Preview(showBackground = true)
//@Composable
//fun EditScreenContentPreview() {
//    KalendoTheme {
//        val navController = rememberNavController()
//        EditScreenContent(
//            navController = navController,
//            courseJson = "{\"color\":-7512683779719168,\"id\":1,\"title\":\"CMPUT 300\"}" )
//    }
//}