package com.example.kalendo.ui.component.homescreen


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kalendo.ui.viewmodel.CalendarViewModel
import com.example.kalendo.util.calender.ImageBanner
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce

@OptIn(FlowPreview::class)
@Composable
fun ContentHome(modifier: Modifier = Modifier, viewModel: CalendarViewModel = viewModel()) {
    val scrollState = rememberLazyListState()
    val months by rememberUpdatedState(viewModel.months)

    LazyColumn(
        state = scrollState,
        modifier = modifier.fillMaxSize()
    ) {
        months.forEach { monthData ->
            item {
                BannerHeader(year = monthData.year, month = monthData.month, imageBanner = monthData.banner)
            }
            items(monthData.days) { day ->
                DayItem(date = day.date, dayOfWeek = day.dayOfWeek)
            }
        }
    }

    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.firstVisibleItemIndex }
            .map { it }
            .distinctUntilChanged()
            .debounce(300)  // Debounce to avoid rapid triggering
            .collectLatest { index ->
                if (index >= months.size - 1) {
                    val nextMonthIndex = months.size
                    val year = 2022 + nextMonthIndex / 12
                    val month = nextMonthIndex % 12
                    viewModel.loadMoreMonths(year, month)
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