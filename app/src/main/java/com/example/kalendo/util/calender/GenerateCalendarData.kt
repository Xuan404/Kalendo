package com.example.kalendo.util.calender

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun generateMonthData(year: Int, month: Int): Month {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month)
    calendar.set(Calendar.DAY_OF_MONTH, 1)

    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val days = mutableListOf<Day>()

    for (day in 1..daysInMonth) {
        calendar.set(Calendar.DAY_OF_MONTH, day)
        val dayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(calendar.time)
        days.add(Day(date = day, dayOfWeek = dayOfWeek))
    }

    val monthName = SimpleDateFormat("MMMM", Locale.getDefault()).format(calendar.time)
    val monthBanner = CalendarImageBanners.banners.find { it.monthInt == month }
    return Month(year = year, month = monthName, days = days, banner = monthBanner)
}