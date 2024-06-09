package com.example.kalendo.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.kalendo.navigation.NavRoute
import com.example.kalendo.ui.component.homescreen.FabHome
import com.example.kalendo.ui.component.homescreen.TopAppBarHome
import com.example.kalendo.ui.component.homescreen.ContentHome
import com.example.kalendo.ui.theme.KalendoTheme
import com.example.kalendo.util.Strings

@Composable
fun HomeScreenContent(navController: NavController) {

    // State to trigger the scroll action
    var triggerScrollToCurrentDate by remember{mutableStateOf(false)}

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBarHome(
                    title = Strings.APP_NAME,
                    onNavigationIconClick = { /* Handle navigation icon click */ },
                    onReturnToDateClick = {
                        triggerScrollToCurrentDate = true
                    }
                )
            },
            content = { innerPadding ->
                ContentHome(
                    modifier = Modifier.padding(innerPadding),
                    triggerScrollToCurrentDate = triggerScrollToCurrentDate,
                    onScrollToCurrentDateHandled = { triggerScrollToCurrentDate = false }
                )
            }
        )
        FabHome(
            onAddClick = { /* Handle primary action */ },
            onSecondaryClick = { navController.navigate(NavRoute.NoteScreen.toString()) },
            onTertiaryClick = { navController.navigate(NavRoute.CourseScreen.toString()) }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenContentPreview() {
    KalendoTheme {
        HomeScreenContent(navController = rememberNavController())
    }
}