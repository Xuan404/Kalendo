package com.example.kalendo.navigation

sealed class NavRoute (val route: String){
    data object HomeScreen: NavRoute("HomeScreen")
    data object CourseScreen: NavRoute("CourseScreen")
    data object NoteScreen: NavRoute("NoteScreen")
}