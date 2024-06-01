package com.example.kalendo.ui.view

import android.widget.Toast
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
import androidx.navigation.NavController
import com.example.kalendo.ui.component.coursescreen.ContentCourse
import com.example.kalendo.ui.component.coursescreen.TopAppBarCourse
import com.example.kalendo.ui.viewmodel.CourseViewModel
import com.example.kalendo.util.Strings
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import com.example.kalendo.ui.component.coursescreen.SelectedItemTopAppBarCourse

@Composable
fun CourseScreenContent(
    navController:NavController,
    viewModel: CourseViewModel = hiltViewModel()
) {
    // vars required for selecting and deleting feature
    var selectedItems by remember { mutableStateOf(setOf<Int>()) } // Set of selected item ids
    var showSelectionAppBar by remember { mutableStateOf(false) } // Bool value of selection app bar
    // Required for Toast messages
    val context = LocalContext.current
    //observes the courses LiveData from the ViewModel and updates the UI when the data changes.
    val courses by viewModel.courses.observeAsState(emptyList())
    // Will need to use this 'if' I start adding the edit functionalities
    var courseTitle by remember { mutableStateOf("") } // defines a state variable to store the current input for the course title.
    var courseColor by remember { mutableStateOf(Color.Black) } // Add a color picker if needed // line defines a state variable to store the current input for the course color.

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                if (showSelectionAppBar) {
                    SelectedItemTopAppBarCourse(
                        selectedItemCount = selectedItems.size,
                        onClearSelection = {
                            selectedItems = setOf()
                            showSelectionAppBar = false
                        },
                        onDeleteSelected = {
                            // Handle delete action here
                            selectedItems.forEach { item -> viewModel.deleteCourse(item)}
                            Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show()
                            selectedItems = setOf()
                            showSelectionAppBar = false
                        }
                    )
                } else {
                    TopAppBarCourse(
                        title = Strings.COURSES_SCREEN_TITLE,
                        onNavigationIconClick = {navController.popBackStack()},
                        actions = {}
                    )
                }

            },
            content = { innerPadding ->
                ContentCourse(
                    modifier = Modifier.padding(innerPadding),
                    courses = courses, // contains the courses to be displayed
                    navController = navController,
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


