package com.example.kalendo.util.calender

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun GenerateCalendarData(): List<Month> {
    val calendar = Calendar.getInstance()
    val months = mutableListOf<Month>()

    for (year in 2020..2100) { // Adjust the range as needed
        for (month in 0..11) {
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, 1) // Reset to the first day of the month

            val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
            val days = mutableListOf<Day>()

            for (day in 1..daysInMonth) {
                calendar.set(Calendar.DAY_OF_MONTH, day)
                val dayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(calendar.time)
                days.add(Day(date = day, dayOfWeek = dayOfWeek))
            }

            val monthName = SimpleDateFormat("MMMM", Locale.getDefault()).format(calendar.time)
            val monthBanner = CalendarImageBanners.banners.find { it.monthInt == month }
            Log.i("MonthCheck", monthName)
            months.add(Month(year = year, month = monthName, days = days, banner = monthBanner))
        }
    }
    return months
}