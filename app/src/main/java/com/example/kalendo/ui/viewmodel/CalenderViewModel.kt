package com.example.kalendo.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kalendo.util.calender.Month
import com.example.kalendo.util.calender.generateMonthData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CalendarViewModel : ViewModel() {
    private val _months = mutableStateListOf<Month>()
    val months: List<Month> = _months

    init {
        loadInitialMonths()
    }

    private fun loadInitialMonths() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val initialMonths = generateInitialMonths()
                _months.addAll(initialMonths)
            }
        }
    }

    private fun generateInitialMonths(): List<Month> {
        val startYear = 2022
        val endYear = 2023
        val monthsInYear = 12
        return (startYear..endYear).flatMap { year ->
            (0 until monthsInYear).map { month ->
                generateMonthData(year, month)
            }
        }
    }

    fun loadMoreMonths(year: Int, month: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val newMonth = generateMonthData(year, month)
                _months.add(newMonth)
            }
        }
    }
}