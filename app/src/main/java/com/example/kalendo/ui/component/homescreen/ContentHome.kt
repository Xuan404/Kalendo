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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import java.util.Calendar

@OptIn(FlowPreview::class)
@Composable
fun ContentHome(modifier: Modifier = Modifier, viewModel: CalendarViewModel = viewModel()) {
    val scrollState = rememberLazyListState()
    val months by rememberUpdatedState(viewModel.months)

    // TODO: Figure out the calculation
    // Calculate the initial scroll position
    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
    val initialIndex = (viewModel.yearOffset * 12) + currentMonth
    //Log.i("CurrentTime", initialIndex.toString())

    // Scroll to the current month on first launch
    LaunchedEffect(Unit) {
        scrollState.scrollToItem(initialIndex)
    }

    LazyColumn(
        state = scrollState,
        modifier = modifier.fillMaxSize()
    ) {
        Log.i("CurrentIndex", scrollState.firstVisibleItemIndex.toString())
        items(months) {month ->
            BannerHeader(year = month.year, month = month.month, imageBanner = month.banner)
            for (day in month.days) {
                DayItem(date = day.date, dayOfWeek = day.dayOfWeek)
            }
        }
    }

    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.firstVisibleItemIndex to scrollState.firstVisibleItemScrollOffset}
            .map { it }
            .debounce(300)  // Debounce to avoid rapid triggering
            .collectLatest { (firstVisibleItemIndex, firstVisibleItemScrollOffset) ->
                if (firstVisibleItemIndex <= 12 ) {
                    // User has scrolled to the top, load previous month
                    val index = firstVisibleItemIndex + 1
                    scrollState.scrollToItem(index, firstVisibleItemScrollOffset)
                    viewModel.loadPreviousMonth()


                } else if (firstVisibleItemIndex >=  (scrollState.layoutInfo.totalItemsCount - 12)) {
                    // User has scrolled to the bottom, load next month
                    viewModel.loadNextMonth()

                }
            }
    }
}

@Composable
private fun BannerHeader(year: Int, month: String, imageBanner: ImageBanner?) {
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
private fun DayItem(date: Int, dayOfWeek: String) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ){
        Text(
            text = "$dayOfWeek, $date",
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center)
        )
    }
}

