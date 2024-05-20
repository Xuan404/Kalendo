package com.example.kalendo.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kalendo.ui.theme.KalendoTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kalendo.R


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MyTopAppBar(
//    title: String,
//    onNavigationIconClick: (() -> Unit)? = null,
//    actions: @Composable (RowScope.() -> Unit)? = null
//) {
//    TopAppBar(
//        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primary,
//            titleContentColor = MaterialTheme.colorScheme.onPrimary
//        ),
//        title = {
//            Text(text = title)
//        },
//        actions = {
//            IconButton(onClick = { /* Do nothing for preview */ }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.grid_linear),
//                    contentDescription = "Grid Linear",
//                    modifier = Modifier.padding(8.dp)
//                )
//            }
//            IconButton(onClick = { /* Do nothing for preview */ }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.date1),
//                    contentDescription = "Grid Linear",
//                    modifier = Modifier.padding(8.dp)
//                )
//            }
//            IconButton(onClick = { /* Profile action */ }) {
//                Icon(
//                    Icons.Default.AccountCircle,
//                    contentDescription = "Profile",
//                    modifier = Modifier.padding(0.dp)
//                )
//            }
//
//        }
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun MyTopAppBarPreview() {
//    KalendoTheme {
//        MyTopAppBar(
//            title = "May",
//        )
//    }
//}




//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MyTopAppBar(
//    title: String,
//    onNavigationIconClick: (() -> Unit)? = null,
//    actions: @Composable (RowScope.() -> Unit)? = null,
//    onDateSelected: (String) -> Unit
//) {
//    var expanded by remember { mutableStateOf(false) }
//
//    TopAppBar(
//        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primaryContainer,
//            titleContentColor = MaterialTheme.colorScheme.primary
//        ),
//        title = {
//            Box(
//                modifier = Modifier
//                    .clickable { expanded = true }
//                    .padding(8.dp)
//            ) {
//                Text(text = title, textAlign = TextAlign.Center)
//                DropdownMenu(
//                    expanded = expanded,
//                    onDismissRequest = { expanded = false }
//                ) {
//                    val months = listOf(
//                        "January", "February", "March", "April",
//                        "May", "June", "July", "August",
//                        "September", "October", "November", "December"
//                    )
//                    months.forEach { month ->
//                        DropdownMenuItem(
//                            text = { Text(month) },
//                            onClick = {
//                                expanded = false
//                                onDateSelected(month)
//                            }
//                        )
//                    }
//                }
//            }
//        },
//        actions = actions ?: {}
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun MyTopAppBarPreview() {
//    KalendoTheme {
//        MyTopAppBar(
//            title = "Select Month",
//            onNavigationIconClick = { /* Do nothing for preview */ },
//            actions = {
//                IconButton(onClick = { /* Do nothing for preview */ }) {
//                    Icon(Icons.Default.Search, contentDescription = "Search")
//                }
//                IconButton(onClick = { /* Profile action */ }) {
//                    Icon(Icons.Default.AccountCircle, contentDescription = "Profile")
//                }
//            },
//            onDateSelected = { selectedMonth ->
//                // Handle date selection
//            }
//        )
//    }
//}
//


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kalendo.ui.theme.KalendoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    onNavigationIconClick: (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null,
    onDateSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedMonth by remember { mutableStateOf(title) }

    val months = listOf(
        "Jan", "Feb", "Mar", "Apr",
        "May", "Jun", "Jul", "Aug",
        "Sep", "Oct", "Nov", "Dec"
    )

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Box(
                modifier = Modifier
                    .clickable { expanded = true }
                    .padding(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = selectedMonth, textAlign = TextAlign.Center)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown",
                        modifier = Modifier.size(24.dp)
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(MaterialTheme.colorScheme.surface)
                ) {
                    months.forEach { month ->
                        DropdownMenuItem(
                            text = {
                                Box(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .background(
                                            if (month == selectedMonth) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                                            else Color.Transparent,
                                            shape = RoundedCornerShape(4.dp)
                                        )
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = month,
                                        color = if (month == selectedMonth) MaterialTheme.colorScheme.primary else LocalContentColor.current
                                    )
                                }
                            },
                            onClick = {
                                expanded = false
                                selectedMonth = month
                                onDateSelected(month)
                            }
                        )
                    }
                }
            }
        },
        actions = actions ?: {}
    )
}

@Preview(showBackground = true)
@Composable
fun MyTopAppBarPreview() {
    KalendoTheme {
        MyTopAppBar(
            title = "May",
            onNavigationIconClick = { /* Do nothing for preview */ },
            actions = {
                IconButton(onClick = { /* Do nothing for preview */ }) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
                IconButton(onClick = { /* Do nothing for preview */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.grid_linear),
                        contentDescription = "Grid Linear",
                        modifier = Modifier.padding(4.dp),
                        tint = Color.Unspecified // Ensure original colors are used
                    )
                }
                IconButton(onClick = { /* Profile action */ }) {
                    Icon(Icons.Default.AccountCircle, contentDescription = "Profile")
                }
            },
            onDateSelected = { selectedMonth ->
                // Handle date selection
            }
        )
    }
}