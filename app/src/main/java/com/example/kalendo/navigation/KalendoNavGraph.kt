package com.example.kalendo.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kalendo.ui.view.CourseScreenContent
import com.example.kalendo.ui.view.EditScreenContent
import com.example.kalendo.ui.view.HomeScreenContent
import com.example.kalendo.ui.view.NoteScreenContent


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun KalendoNavGraph(navController: NavHostController, modifier: Modifier = Modifier){

    NavHost(
        navController = navController,
        startDestination = NavRoute.HomeScreen.toString(),
        modifier = modifier
    ) {

        composable(
            route = NavRoute.HomeScreen.toString(),

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
                if (navController.currentDestination?.route == NavRoute.HomeScreen.toString()) {
                    // Closing Course Screen
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(400)
                    )

                } else {
                    // Closing Edit Screen
                    null
                }
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

        composable(
            route ="edit/{courseJson}",
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

        ) { backStackEntry ->
            val courseJson = backStackEntry.arguments?.getString("courseJson")
            if (courseJson != null) {
                Log.i("NavJson", courseJson)
            }
            EditScreenContent(navController, courseJson)
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