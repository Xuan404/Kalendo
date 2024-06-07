package com.example.kalendo.util

import com.example.kalendo.domain.model.DayModel
import com.example.kalendo.domain.model.MonthModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun generateMonthData(year: Int, month: Int): MonthModel {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month)
    calendar.set(Calendar.DAY_OF_MONTH, 1)

    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val days = mutableListOf<DayModel>()

    for (day in 1..daysInMonth) {
        calendar.set(Calendar.DAY_OF_MONTH, day)
        val dayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(calendar.time)
        days.add(DayModel(date = day, dayOfWeek = dayOfWeek))
    }

    val monthName = SimpleDateFormat("MMMM", Locale.getDefault()).format(calendar.time)
    val monthBanner = CalendarImageBanners.banners.find { it.monthInt == month }
    return MonthModel(year = year, month = monthName, days = days, banner = monthBanner)
}