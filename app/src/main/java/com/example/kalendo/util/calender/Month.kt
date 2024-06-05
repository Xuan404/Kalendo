package com.example.kalendo.util.calender

import com.example.kalendo.util.calender.Day
import com.example.kalendo.util.calender.ImageBanner

data class Month(val year: Int, val month: String, val days: List<Day>, val banner: ImageBanner?)
