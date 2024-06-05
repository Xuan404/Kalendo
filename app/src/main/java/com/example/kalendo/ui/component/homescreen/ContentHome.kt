package com.example.kalendo.ui.component.homescreen


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kalendo.util.calender.CalendarImageBanners
import com.example.kalendo.util.calender.GenerateCalendarData
import com.example.kalendo.util.calender.ImageBanner

@Composable
fun ContentHome(modifier: Modifier = Modifier) {
    val scrollState = rememberLazyListState()
    val months = GenerateCalendarData()
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            state = scrollState,
        ) {
            months.forEach { month ->
                item {
                    BannerHeader(month.year, month.month, month.banner)
                }
                items(month.days) { day ->
                    DayItem(day.date, day.dayOfWeek)
                }
            }

        }
    }
}

@Composable
fun BannerHeader(year: Int, month: String, imageBanner: ImageBanner?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clipToBounds() // Ensures the content doesn't overflow the bounds
    ) {
        if (imageBanner != null) {
            Image(
                painter = painterResource(id = imageBanner.banner),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(175.dp) // Use the actual height of the long image
            )
        }
        Text(
            text = "$month $year",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(horizontal = 50.dp, vertical = 20.dp),
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.background
        )

    }
}


@Composable
fun DayItem(date: Int, dayOfWeek: String) {
    Text(
        text = "$dayOfWeek, $date",
        modifier = Modifier.padding(8.dp)
    )
}