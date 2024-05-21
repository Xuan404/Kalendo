package com.example.kalendo.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.kalendo.ui.theme.KalendoTheme


// Animated FAB with inflation animation and '+' rotation
//@Composable
//fun AnimatedFAB(
//    onAddClick: () -> Unit,
//    onSecondaryClick: () -> Unit,
//    onTertiaryClick: () -> Unit
//) {
//    var expanded by remember { mutableStateOf(false) }
//
//    Box(
//        contentAlignment = Alignment.BottomEnd,
//        modifier = Modifier.fillMaxSize().padding(
//            bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
//        )
//    ) {
//
//        // Darkening background overlay
//        if (expanded) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.75f))
//                    .clickable { expanded = false }
//            )
//        }
//
//        Column(
//            horizontalAlignment = Alignment.End,
//            verticalArrangement = Arrangement.Bottom,
//            modifier = Modifier.padding(16.dp)
//        ) {
//            // Tertiary button
//            val tertiaryScale by animateFloatAsState(if (expanded) 1f else 0f)
//            AnimatedVisibility(
//                visible = expanded,
//                enter = fadeIn(animationSpec = tween(delayMillis = 175)),
//                //exit = fadeOut(animationSpec = tween(durationMillis = 100))
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier
//                        .scale(tertiaryScale)
//                        .padding(bottom = 8.dp)
//                ) {
//                    Text(
//                        text = "Email",
//                        fontSize = 15.sp,
//                        color = Color.White,
//                        modifier = Modifier.padding(end = 25.dp)
//                    )
//                    FloatingActionButton(
//                        onClick = onTertiaryClick,
//                        containerColor = MaterialTheme.colorScheme.secondary,
//                        modifier = Modifier
//                            .size(35.dp)
//                            .offset(x = -10.dp)
//                    ) {
//                        Icon(imageVector = Icons.Default.Email, contentDescription = "Tertiary Action")
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.height(10.dp))
//
//            // Secondary button
//            val secondaryScale by animateFloatAsState(if (expanded) 1f else 0f)
//            AnimatedVisibility(
//                visible = expanded,
//                enter = fadeIn(animationSpec = tween(delayMillis = 100)),
//                //exit = fadeOut(animationSpec = tween(durationMillis = 100, delayMillis = 200))
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier
//                        .scale(secondaryScale)
//                        .padding(bottom = 8.dp)
//                ) {
//
//                    Text(
//                        text = "Date",
//                        fontSize = 15.sp,
//                        color = Color.White,
//                        modifier = Modifier.padding(end = 25.dp)
//                    )
//                    FloatingActionButton(
//                        onClick = onSecondaryClick,
//                        containerColor = MaterialTheme.colorScheme.secondary,
//                        modifier = Modifier
//                            .size(35.dp)
//                            .offset(x = -10.dp)
//                    ) {
//                        Icon(imageVector = Icons.Default.DateRange, contentDescription = "Secondary Action")
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.height(10.dp))
//
//            // Main FAB
//            val rotation by animateFloatAsState(targetValue = if (expanded) 135f else 0f)
//            FloatingActionButton(
//                onClick = { expanded = !expanded },
//                containerColor = MaterialTheme.colorScheme.surfaceVariant,
//                contentColor = MaterialTheme.colorScheme.onPrimary,
//                modifier = Modifier.size(56.dp)
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = if (expanded) "Close" else "Add",
//                    modifier = Modifier.rotate(rotation).size(30.dp)
//                )
//            }
//        }
//    }
//}






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