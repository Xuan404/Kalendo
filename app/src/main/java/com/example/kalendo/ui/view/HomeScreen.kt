package com.example.kalendo.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kalendo.navigation.NavRoute
import com.example.kalendo.ui.component.homescreen.FabHome
import com.example.kalendo.ui.component.homescreen.TopAppBarHome
import com.example.kalendo.ui.component.homescreen.ContentHome
import com.example.kalendo.ui.theme.KalendoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBarHome(
                    title = "Kalendo",
                    onNavigationIconClick = { /* Handle navigation icon click */ },
                    actions = {}
                )
            },
            content = { innerPadding ->
                ContentHome(modifier = Modifier.padding(innerPadding))
            }
        )
        FabHome(
            onAddClick = { /* Handle primary action */ },
            onSecondaryClick = { navController.navigate(NavRoute.NoteScreen.toString()) },
            onTertiaryClick = { navController.navigate(NavRoute.CourseScreen.toString()) }
        )
    }
}


//@Preview(showBackground = true)
//@Composable
//fun HomeScreenContentPreview() {
//    KalendoTheme {
//        HomeScreenContent(navController = NavContro)
//    }
//}