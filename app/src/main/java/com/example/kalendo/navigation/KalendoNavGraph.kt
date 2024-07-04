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

        val tweenDuration = 400

        composable(
            route = NavRoute.HomeScreen.toString(),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(tweenDuration)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(tweenDuration)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(tweenDuration)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(tweenDuration)
                )
            }

        ){
            HomeScreenContent(navController = navController)
        }

        composable(
            route = NavRoute.CourseScreen.toString(),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(tweenDuration)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(tweenDuration)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(tweenDuration)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(tweenDuration)
                )
            }
        ){
            CourseScreenContent(navController = navController)
        }

        composable(
            route = NavRoute.NoteScreen.toString(),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(tweenDuration)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(tweenDuration)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(tweenDuration)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(tweenDuration)
                )
            }
        ){
            NoteScreenContent(navController = navController)
        }

        composable(
            route ="edit/{courseJson}",
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(tweenDuration)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(tweenDuration)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(tweenDuration)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(tweenDuration)
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

