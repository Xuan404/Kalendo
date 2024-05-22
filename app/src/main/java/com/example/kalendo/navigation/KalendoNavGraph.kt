package com.example.kalendo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kalendo.ui.view.CourseScreenContent
import com.example.kalendo.ui.view.HomeScreenContent
import com.example.kalendo.ui.view.NoteScreenContent


@Composable
fun KalendoNavGraph(navController: NavHostController, modifier: Modifier = Modifier){

    NavHost(
        navController = navController,
        startDestination = NavRoute.HomeScreen.toString(),
        modifier = modifier
    ) {

        composable(route = NavRoute.HomeScreen.toString()){
            HomeScreenContent(navController = navController)
        }
        composable(route = NavRoute.CourseScreen.toString()){
            CourseScreenContent(navController = navController)
        }
        composable(route = NavRoute.NoteScreen.toString()){
            NoteScreenContent(navController = navController)
        }
    }
    
}

