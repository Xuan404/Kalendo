package com.example.kalendo.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kalendo.domain.model.MonthModel
import com.example.kalendo.util.generateMonthData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor() : ViewModel() {
    private val _months = mutableStateListOf<MonthModel>()
    val months: List<MonthModel> = _months

    val yearOffset = 1

    private val startYear = 1900
    private val endYear = 2100

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

    private fun generateInitialMonths(): List<MonthModel> {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val monthsInYear = 12

        val monthsList = mutableListOf<MonthModel>()

        for (year in (currentYear - yearOffset)..(currentYear + yearOffset)) {
            for (month in 0 until monthsInYear) {
                monthsList.add(generateMonthData(year, month))
            }
        }

        return monthsList
    }

    fun loadPreviousMonth() {

        if (_months.isNotEmpty()) {
            val firstMonth = _months.first()
            val year = firstMonth.year
            val month = firstMonth.monthIndex

            if (year > startYear || (year == startYear && month > 0)) {
                val newMonth = if (month == 0) {
                    generateMonthData(year - 1, 11)
                } else {
                    generateMonthData(year, month - 1)
                }
                _months.add(0, newMonth)
            }
        }


    }

    fun loadNextMonth() {

        if (_months.isNotEmpty()) {
            val lastMonth = _months.last()
            val year = lastMonth.year
            val month = lastMonth.monthIndex

            if (year < endYear || (year == endYear && month < 11)) {
                val newMonth = if (month == 11) {
                    generateMonthData(year + 1, 0)
                } else {
                    generateMonthData(year, month + 1)
                }
                _months.add(newMonth)
            }
        }

    }
}
