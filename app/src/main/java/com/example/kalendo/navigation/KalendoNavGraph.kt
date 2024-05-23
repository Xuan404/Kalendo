package com.example.kalendo.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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

        composable(
            route = NavRoute.HomeScreen.toString(),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(0)
                )
            },

        ){
            HomeScreenContent(navController = navController)
        }
        composable(
            route = NavRoute.CourseScreen.toString(),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(150)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(400)
                )
            }
        ){
            CourseScreenContent(navController = navController)
        }
        composable(
            route = NavRoute.NoteScreen.toString(),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(150)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(400)
                )
            }
        ){
            NoteScreenContent(navController = navController)
        }





    }
    
}








//        composable(route = NavRoute.HomeScreen.toString()){
//            HomeScreenContent(navController = navController)
//        }
//        composable(route = NavRoute.CourseScreen.toString()){
//            CourseScreenContent(navController = navController)
//        }
//        composable(route = NavRoute.NoteScreen.toString()){
//            NoteScreenContent(navController = navController)
//        }



//        composable(
//            route = NavRoute.HomeScreen.toString(),
//            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(70)) },
//            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(70)) }
//
//        ) {
//            HomeScreenContent(navController = navController)
//        }
//        composable(
//            route = NavRoute.CourseScreen.toString(),
//            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(70)) },
//            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(70)) },
//            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(70)) },
//            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(70)) }
//        ) {
//            CourseScreenContent(navController = navController)
//        }
//        composable(
//            route = NavRoute.NoteScreen.toString(),
//            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(70)) },
//            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(70)) },
//            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(70)) },
//            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(70)) }
//        ) {
//            NoteScreenContent(navController = navController)
//        }