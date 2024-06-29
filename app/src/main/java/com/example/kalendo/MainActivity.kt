package com.example.kalendo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.kalendo.navigation.KalendoNavGraph
import com.example.kalendo.ui.theme.KalendoTheme
import com.example.kalendo.ui.view.HomeScreenContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            KalendoTheme {

                val navController = rememberNavController()
                KalendoNavGraph(navController = navController)
                //HomeScreenContent(navController = NavCon)

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenContentPreview() {
    KalendoTheme {
        val navController = rememberNavController()
        KalendoNavGraph(navController = navController)
    }
}