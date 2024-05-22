package com.example.kalendo.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kalendo.ui.component.notescreen.ContentNote
import com.example.kalendo.ui.component.notescreen.TopAppBarNote
import com.example.kalendo.ui.theme.KalendoTheme

@Composable
fun NoteScreenContent() {

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBarNote(
                    title = "Courses",
                    onNavigationIconClick = { /* Handle navigation icon click */ },
                    actions = {}
                )
            },
            content = { innerPadding ->
                ContentNote(modifier = Modifier.padding(innerPadding))
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun NoteScreenContentPreview() {
    KalendoTheme {
        NoteScreenContent()
    }
}