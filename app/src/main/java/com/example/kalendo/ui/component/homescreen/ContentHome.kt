package com.example.kalendo.ui.component.homescreen


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kalendo.R
import com.example.kalendo.domain.model.AssignmentWithCourseColor
import com.example.kalendo.ui.viewmodel.CalendarViewModel
import com.example.kalendo.domain.model.ImageBannerModel
import com.example.kalendo.domain.model.MonthModel
import com.example.kalendo.ui.theme.defaultColor
import com.example.kalendo.ui.viewmodel.AssignmentViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
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

    var isDateContentDetailsVisible by remember { mutableStateOf(false) }

    // Calculate the initial scroll position
    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
    val currentDate = Calendar.getInstance().get(Calendar.DATE)

    // (IMPORTANT!!) The offset is calculated Manually not Dynamically, so might need to recalculate
    val initialIndexOffset = 625 + ((160 * currentDate) - 160 * 4)

    LazyColumn(
        state = scrollState,
        modifier = modifier
            .fillMaxSize()
            .then(if (isDateContentDetailsVisible) Modifier.blur(16.dp) else Modifier)
    ) {
        items(months) {month ->
            MonthItem(month = month, assignmentViewModel = assignmentViewModel, onLongClick = {isDateContentDetailsVisible = true})
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

    if (isDateContentDetailsVisible) {
        DateContentDetailsHome(
            onDismissRequest = { isDateContentDetailsVisible = false }
        )
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MonthItem(month: MonthModel, assignmentViewModel: AssignmentViewModel, onLongClick: () -> Unit) {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)
    val currentDate = Calendar.getInstance().get(Calendar.DATE)

    val assignmentsMap by assignmentViewModel.assignmentsOfDate.observeAsState(emptyMap())

    // Fetch assignments for all days in the month
    LaunchedEffect(month) {
        month.days.forEach { day ->
            val date = LocalDate.of(month.year, month.monthIndex + 1, day.date)
            assignmentViewModel.getAssignmentsWithCourseColorByDate(date)
        }
    }

    BannerHeader(year = month.year, month = month.month, imageBanner = month.banner)
    Row(
        modifier = Modifier
            .padding(top = 8.dp, start = 50.dp, end = 50.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "To-Do",
            style = MaterialTheme.typography.bodyLarge.copy(
                textDecoration = TextDecoration.Underline
            )
        )
        Text(
            text = "Deadline",
            style = MaterialTheme.typography.bodyLarge.copy(
                textDecoration = TextDecoration.Underline
            )
        )
    }
    month.days.forEachIndexed { index, day ->
        val isToday = month.year == currentYear && month.monthIndex == currentMonth && day.date == currentDate
        val date = LocalDate.of(month.year, month.monthIndex + 1, day.date)
        val assignmentsWithColor = assignmentsMap[date] ?: emptyList()
        DayItem(
            date = day.date,
            dayOfWeek = day.dayOfWeek,
            isToday = isToday,
            assignmentsWithColor = assignmentsWithColor,
            onLongClick = {onLongClick()}
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DayItem(
    date: Int,
    dayOfWeek: String,
    isToday: Boolean,
    assignmentsWithColor: List<AssignmentWithCourseColor>,
    onLongClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = { /* Handle click */ },
                onLongClick = { onLongClick() }
            )
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Row(
                modifier = Modifier
                    .weight(2f)
                    .padding(end = 16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                var count = 0
                assignmentsWithColor.forEach { course ->
                    if (!course.isDeadline) {
                        if (count < 3) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_course_label),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(16.dp),
                                tint = Color(course.courseColor)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        count ++
                    }
                }
                if (count > 3) {
                    count -= 3
                    Box(
                        modifier = Modifier
                            .size(25.dp)
                            .clip(CircleShape)
                            .background(defaultColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "+$count",
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                        )
                    }
                }

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
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            var count = 0
            assignmentsWithColor.forEach { course ->
                if (course.isDeadline) {
                    if (count < 3) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_course_label),
                            contentDescription = null,
                            modifier = Modifier
                                .size(16.dp),
                            tint = Color(course.courseColor)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    count ++
                }
            }
            if (count > 3) {
                count -= 3
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .clip(CircleShape)
                        .background(defaultColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "+$count",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                    )
                }
            }

        }
    }

}
