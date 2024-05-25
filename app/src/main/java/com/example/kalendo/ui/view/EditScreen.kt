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
import com.example.kalendo.domain.model.CourseModel
import com.example.kalendo.ui.component.editscreen.ContentEdit
import com.example.kalendo.ui.component.editscreen.TopAppBarEdit
import com.example.kalendo.ui.component.notescreen.ContentNote
import com.example.kalendo.ui.component.notescreen.TopAppBarNote
import com.example.kalendo.ui.theme.KalendoTheme
import com.example.kalendo.util.Strings
import com.google.gson.Gson

@Composable
fun EditScreenContent(navController: NavController, courseJson: String?) {

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
                ContentEdit(modifier = Modifier.padding(innerPadding), courseJson)
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun EditScreenContentPreview() {
    KalendoTheme {
        val navController = rememberNavController()
        EditScreenContent(
            navController = navController,
            courseJson = "{\"color\":-7389491232768000,\"tasks\":[],\"title\":\"CMPUT 367\"}" )
    }
}