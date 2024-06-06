package com.example.kalendo.util.calender

import com.example.kalendo.util.calender.Day
import com.example.kalendo.util.calender.ImageBanner
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class Month(val year: Int, val month: String, val days: List<Day>, val banner: ImageBanner?) {
    val monthIndex: Int
        get() = Calendar.getInstance().apply {
            time = SimpleDateFormat("MMMM", Locale.getDefault()).parse(month)!!
        }.get(Calendar.MONTH)
}
