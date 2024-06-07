package com.example.kalendo.domain.model

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class MonthModel(
    val year: Int,
    val month: String,
    val days: List<DayModel>,
    val banner: ImageBannerModel?
) {
    val monthIndex: Int
        get() = Calendar.getInstance().apply {
            time = SimpleDateFormat("MMMM", Locale.getDefault()).parse(month)!!
        }.get(Calendar.MONTH)
}
