package com.example.kalendo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kalendo.ui.theme.KalendoTheme
import com.example.kalendo.ui.view.MainScreenContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KalendoTheme {
                KalendoTheme {
                    MainScreenContent()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenContentPreview() {
    KalendoTheme {
        MainScreenContent()
    }
}