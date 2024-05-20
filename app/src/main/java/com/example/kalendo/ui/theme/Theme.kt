package com.example.kalendo.ui.theme
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
)

@Composable
fun KalendoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    //dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
  val colorScheme = if (darkTheme) {
      darkScheme
  } else {
      lightScheme
  }

    val view = LocalView.current
    if (!view.isInEditMode) {
        val currentActivity = LocalContext.current as Activity

        SideEffect {
            val window = currentActivity.window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()

            WindowCompat.setDecorFitsSystemWindows(window, false)

            val wic = WindowInsetsControllerCompat(window, view)
            wic.isAppearanceLightStatusBars = !darkTheme
            wic.isAppearanceLightNavigationBars = !darkTheme
        }
    }

  MaterialTheme(
    //colorScheme = colorScheme,
    colorScheme = darkScheme,
    typography = AppTypography,
    content = content
  )
}












//@Composable
//fun MyApplicationTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
//    content: @Composable () -> Unit
//) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
//  val view = LocalView.current
//  if (!view.isInEditMode) {
//    SideEffect {
//      val window = (view.context as Activity).window
//      window.statusBarColor = colorScheme.primary.toArgb()
//      //window.statusBarColor = Color.Transparent.toArgb()
//      //WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//    }
//  }
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//        content = content
//    )
//}