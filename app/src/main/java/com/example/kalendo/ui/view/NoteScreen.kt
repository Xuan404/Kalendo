package com.example.kalendo.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.kalendo.navigation.NavRoute
import com.example.kalendo.ui.component.notescreen.ContentNote
import com.example.kalendo.ui.component.notescreen.TopAppBarNote
import com.example.kalendo.ui.theme.KalendoTheme

@Composable
fun NoteScreenContent(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBarNote(
                    title = "Notes",
                    onNavigationIconClick = {},
                    actions = {}
                )
            },
            content = { innerPadding ->
                ContentNote(modifier = Modifier.padding(innerPadding))
            }
        )
    }
}


//@Preview(showBackground = true)
//@Composable
//fun NoteScreenContentPreview() {
//    KalendoTheme {
//        NoteScreenContent(navController)
//    }
//}