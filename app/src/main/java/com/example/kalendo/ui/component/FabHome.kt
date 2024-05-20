package com.example.kalendo.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.kalendo.ui.theme.KalendoTheme
import com.example.kalendo.ui.theme.fab
import com.example.kalendo.ui.theme.onPrimaryDark
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kalendo.ui.theme.KalendoTheme
import com.example.kalendo.ui.theme.fab

//@Composable
//fun MyFloatingActionButton(
//    onClick: () -> Unit,
//) {
//    FloatingActionButton(
//        onClick = onClick,
//        containerColor = fab,
//        contentColor = onPrimaryDark
//    ) {
//        Icon(
//            imageVector = Icons.Default.Add,
//            contentDescription = "Add"
//        )
//    }
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun MyFloatingActionButtonPreview() {
//    KalendoTheme {
//        MyFloatingActionButton(
//            onClick = { /* Handle FAB click */ }
//        )
//    }
//}


@Composable
fun AnimatedFAB(
    onAddClick: () -> Unit,
    onSecondaryClick: () -> Unit,
    onTertiaryClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        // Secondary button
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            FloatingActionButton(
                onClick = onSecondaryClick,
                containerColor = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(bottom = 72.dp, end = 16.dp)
                    .size(56.dp)
            ) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "Secondary Action")
            }
        }

        // Tertiary button
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            FloatingActionButton(
                onClick = onTertiaryClick,
                containerColor = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(bottom = 128.dp, end = 16.dp)
                    .size(56.dp)
            ) {
                Icon(imageVector = Icons.Default.Email, contentDescription = "Tertiary Action")
            }
        }

        // Main FAB
        FloatingActionButton(
            onClick = { expanded = !expanded },
            containerColor = fab,
            contentColor = Color.White,
            modifier = Modifier.size(56.dp)
        ) {
            Icon(
                imageVector = if (expanded) Icons.Default.Close else Icons.Default.Add,
                contentDescription = if (expanded) "Close" else "Add"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MyFloatingActionButtonPreview() {
    KalendoTheme {
        AnimatedFAB(
            onAddClick = { /* Handle primary action */ },
            onSecondaryClick = { /* Handle secondary action */ },
            onTertiaryClick = { /* Handle tertiary action */ }
        )
    }
}