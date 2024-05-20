package com.example.kalendo.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kalendo.ui.component.AnimatedFAB
import com.example.kalendo.ui.component.MyTopAppBar
import com.example.kalendo.ui.component.SampleContent
import com.example.kalendo.ui.theme.KalendoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent() {
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "May",
                onNavigationIconClick = { /* Handle navigation icon click */ },
                actions = {
                    IconButton(onClick = { /* Handle search icon click */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { /* Handle profile icon click */ }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Profile")
                    }
                },
                onDateSelected = { selectedMonth ->
                    // Handle date selection
                }
            )
        },
        floatingActionButton = {
            AnimatedFAB(
                onAddClick = { /* Handle primary action */ },
                onSecondaryClick = { /* Handle secondary action */ },
                onTertiaryClick = { /* Handle tertiary action */ }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { innerPadding ->
            SampleContent(Modifier.padding(innerPadding))
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenContentPreview() {
    KalendoTheme {
        MainScreenContent()
    }
}