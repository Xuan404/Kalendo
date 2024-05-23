package com.example.kalendo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.kalendo.navigation.KalendoNavGraph
import com.example.kalendo.ui.theme.KalendoTheme
import com.example.kalendo.ui.view.HomeScreenContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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