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

    private val startYear = 2000
    private val endYear = 2002

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
        val monthsInYear = 12
        return (startYear..startYear + 1).flatMap { year ->
            (0 until monthsInYear).map { month ->
                generateMonthData(year, month)
            }
        }
    }

    fun loadMoreMonths(year: Int, month: Int) {
        if (year in startYear..endYear) {
            viewModelScope.launch {
                withContext(Dispatchers.Default) {
                    val newMonth = generateMonthData(year, month)
                    _months.add(newMonth)
                }
            }
        }
    }
}