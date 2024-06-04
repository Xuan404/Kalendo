package com.example.kalendo.ui.view

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kalendo.domain.model.CourseModel
import com.example.kalendo.ui.component.coursescreen.SelectedItemTopAppBarCourse
import com.example.kalendo.ui.component.coursescreen.TopAppBarCourse
import com.example.kalendo.ui.component.editscreen.ContentEdit
import com.example.kalendo.ui.component.editscreen.SelectedItemTopAppBarEdit
import com.example.kalendo.ui.component.editscreen.TopAppBarEdit
import com.example.kalendo.ui.theme.KalendoTheme
import com.example.kalendo.ui.viewmodel.AssignmentViewModel
import com.example.kalendo.util.Strings
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditScreenContent(
    navController: NavController,
    courseJson: String?,
    viewModel: AssignmentViewModel = hiltViewModel()
) {
    // vars required for selecting and deleting feature
    var selectedItems by remember { mutableStateOf(setOf<Int>()) } // Set of selected item ids
    var showSelectionAppBar by remember { mutableStateOf(false) } // Bool value of selection app bar
    // Required for Toast messages
    val context = LocalContext.current
    //observes the courses LiveData from the ViewModel and updates the UI when the data changes.
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

                if (showSelectionAppBar) {
                    SelectedItemTopAppBarEdit(
                        selectedItemCount = selectedItems.size,
                        onClearSelection = {
                            selectedItems = setOf()
                            showSelectionAppBar = false
                        },
                        onDeleteSelected = {
                            // Handle delete action here
                            selectedItems.forEach { item -> viewModel.deleteAssignment(item)}
                            Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show()
                            selectedItems = setOf()
                            showSelectionAppBar = false
                        }
                    )
                } else {
                    TopAppBarEdit(
                        title = Strings.EDIT_SCREEN_TITLE,
                        onNavigationIconClick = {navController.popBackStack()},
                        actions = {},
                        course = course
                    )
                }
            },
            content = { innerPadding ->
                ContentEdit(
                    modifier = Modifier.padding(innerPadding),
                    courseJson =  courseJson,
                    assignments =  assignments,

                    selectedItems = selectedItems,
                    showSelectionAppBar = showSelectionAppBar,
                    // Adds or removes from the list
                    onItemSelected = { item, isSelected ->
                        selectedItems = if (isSelected) {
                            selectedItems - item.id  // Selected so remove from list
                        } else {
                            selectedItems + item.id  // Not selected so add to list
                        }
                        if (selectedItems.isEmpty()) {
                            showSelectionAppBar = false
                        }
                    },
                    // initial long click
                    onItemLongClicked = { item ->
                        if (!showSelectionAppBar) {
                            selectedItems = setOf(item.id)
                            showSelectionAppBar = true
                        }
                    }
                )
            }
        )
    }
}


