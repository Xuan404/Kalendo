package com.example.kalendo.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kalendo.ui.component.FabHome
import com.example.kalendo.ui.component.MyTopAppBar
import com.example.kalendo.ui.component.SampleContent
import com.example.kalendo.ui.theme.KalendoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent() {

    Box(modifier = Modifier.fillMaxSize()) {
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
//            floatingActionButtonPosition = FabPosition.End,
//            floatingActionButton = {
//                AnimatedFAB(
//                    onAddClick = { /* Handle primary action */ },
//                    onSecondaryClick = { /* Handle secondary action */ },
//                    onTertiaryClick = { /* Handle tertiary action */ }
//                )
//            },
            content = { innerPadding ->
                SampleContent(modifier = Modifier.padding(innerPadding))
            }
        )

        FabHome(
            onAddClick = { /* Handle primary action */ },
            onSecondaryClick = { /* Handle secondary action */ },
            onTertiaryClick = { /* Handle tertiary action */ }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenContentPreview() {
    KalendoTheme {
        MainScreenContent()
    }
}