package com.example.kalendo.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.TypeConverter
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date

class Converters {
//    @TypeConverter
//    fun fromColor(color: Color): Int {
//        return color.toArgb()
//    }
//    @TypeConverter
//    fun toColor(argb: Int): Color {
//        return Color(argb)
//    }
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromLocalTime(localTime: LocalTime): String {
        return localTime.format(DateTimeFormatter.ISO_LOCAL_TIME)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalTime(timeString: String): LocalTime {
        return LocalTime.parse(timeString, DateTimeFormatter.ISO_LOCAL_TIME)
    }
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }
    @TypeConverter
    fun toDate(timestamp: Long): Date {
        return Date(timestamp)
    }
}