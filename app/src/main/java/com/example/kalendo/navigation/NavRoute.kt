package com.example.kalendo.navigation

import com.example.kalendo.util.Strings

sealed class NavRoute (val route: String){
    data object HomeScreen: NavRoute(Strings.HOME_SCREEN_ROUTE)
    data object CourseScreen: NavRoute(Strings.COURSES_SCREEN_ROUTE)
    data object NoteScreen: NavRoute(Strings.NOTES_SCREEN_ROUTE)
}