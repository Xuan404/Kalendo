package com.example.kalendo.ui.component.homescreen


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kalendo.R
import com.example.kalendo.domain.model.AssignmentWithCourseColor
import com.example.kalendo.ui.viewmodel.CalendarViewModel
import com.example.kalendo.domain.model.ImageBannerModel
import com.example.kalendo.domain.model.MonthModel
import com.example.kalendo.ui.viewmodel.AssignmentViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate
import java.util.Calendar

@Composable
fun ContentHome(
    modifier: Modifier = Modifier,
    calendarViewModel: CalendarViewModel = hiltViewModel(),
    assignmentViewModel: AssignmentViewModel = hiltViewModel(),
    triggerScrollToCurrentDate: Boolean,
    onScrollToCurrentDateHandled: () -> Unit
) {
    val scrollState = rememberSaveable(saver = LazyListState.Saver) { LazyListState() }
    val months by rememberUpdatedState(calendarViewModel.months)
    var initialIndex by rememberSaveable { mutableIntStateOf(0) }

    // Calculate the initial scroll position
    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
    val currentDate = Calendar.getInstance().get(Calendar.DATE)

    // (IMPORTANT!!) The offset is calculated Manually not Dynamically, so might need to recalculate
    val initialIndexOffset = 625 + ((160 * currentDate) - 160 * 4)

    LazyColumn(
        state = scrollState,
        modifier = modifier.fillMaxSize()
    ) {
        items(months) {month ->
            MonthItem(month = month, assignmentViewModel = assignmentViewModel)
        }
    }

    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.firstVisibleItemIndex to scrollState.firstVisibleItemScrollOffset}
            .map { it }
            //.debounce(100)  // Debounce to avoid rapid triggering
            .collectLatest { (firstVisibleItemIndex, firstVisibleItemScrollOffset) ->
                if (firstVisibleItemIndex <= 1 ) {
                    // User has scrolled to the top, load previous month
                    scrollState.scrollToItem( firstVisibleItemIndex + 1, firstVisibleItemScrollOffset)
                    initialIndex += 1
                    calendarViewModel.loadPreviousMonth()
                } else if (firstVisibleItemIndex >=  (scrollState.layoutInfo.totalItemsCount - 1)) {
                    // User has scrolled to the bottom, load next month
                    calendarViewModel.loadNextMonth()
                }
            }
    }

    // Scroll to the current date on first launch
    LaunchedEffect(Unit) {
        if (scrollState.firstVisibleItemIndex == 0 && scrollState.firstVisibleItemScrollOffset == 0) {
            initialIndex = (calendarViewModel.yearOffset * 12) + currentMonth //Current Month Offset
            scrollState.scrollToItem(initialIndex, initialIndexOffset)
        }
    }

    // Scroll to the current date on button press
    LaunchedEffect(triggerScrollToCurrentDate) {
        if (triggerScrollToCurrentDate) {
            Log.i("CurrentIndexx", initialIndex.toString())
            scrollState.scrollToItem(initialIndex, initialIndexOffset)
            onScrollToCurrentDateHandled()
        }
    }

}

@Composable
private fun BannerHeader(
    year: Int,
    month: String,
    imageBanner: ImageBannerModel?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clipToBounds() // Ensures the content doesn't overflow the bounds
            .padding(top = 30.dp)
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
                .padding(horizontal = 40.dp, vertical = 25.dp),
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.background
        )
    }
}

@Composable
private fun DayItem(
    date: Int,
    dayOfWeek: String,
    isToday: Boolean,
    assignmentsWithColor: List<AssignmentWithCourseColor>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(2f)
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            assignmentsWithColor.forEach { course ->
                Icon(
                    painter = painterResource(id = R.drawable.icon_course_label),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = Color(course.courseColor)
                )
            }
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!isToday) {
                Text(
                    text = dayOfWeek.take(3),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 12.sp,
                )
                Text(
                    text = "$date",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
            } else {
                Text(
                    text = dayOfWeek.take(3),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 12.sp,
                )
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$date",
                        color = MaterialTheme.colorScheme.background,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .weight(2f)
                .padding(end = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Hello2",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 12.sp,
            )
        }
    }
}

@Composable
private fun MonthItem(month: MonthModel, assignmentViewModel: AssignmentViewModel) {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
    val currentDate = Calendar.getInstance().get(Calendar.DATE)

    BannerHeader(year = month.year, month = month.month, imageBanner = month.banner)
    Row(
        modifier = Modifier
            .padding(top = 8.dp, start = 50.dp, end = 50.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "To-Do")
        Text(text = "Deadline")
    }

    month.days.forEachIndexed { index, day ->
        val isToday = month.year == currentYear && month.monthIndex == currentMonth && day.date == currentDate
        val todayLocalDate = LocalDate.of(month.year, month.monthIndex + 1, day.date)

        LaunchedEffect(todayLocalDate) {
            assignmentViewModel.getAssignmentsWithCourseColorByDate(todayLocalDate)
        }

        val assignmentsWithColor by remember {
            assignmentViewModel.assignmentsWithColor
        }.observeAsState(emptyList())

        Log.i("CheckCourseColor", assignmentsWithColor.toString())
        Log.i("CheckCourseColor", todayLocalDate.toString())

        DayItem(
            date = day.date,
            dayOfWeek = day.dayOfWeek,
            isToday = isToday,
            assignmentsWithColor = assignmentsWithColor
        )

        if (index != month.days.size - 1) {
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
        }
    }
}