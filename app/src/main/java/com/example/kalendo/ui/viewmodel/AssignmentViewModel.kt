package com.example.kalendo.ui.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kalendo.domain.model.AssignmentModel
import com.example.kalendo.domain.model.AssignmentWithCourseColor
import com.example.kalendo.domain.repository.AssignmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AssignmentViewModel @Inject constructor(
    private val assignmentRepository: AssignmentRepository
) : ViewModel() {

    val assignments = MutableLiveData<List<AssignmentModel>>()

    private val _assignmentsColor = MutableLiveData<Map<LocalDate, List<AssignmentWithCourseColor>>>()
    val assignmentsColor: LiveData<Map<LocalDate, List<AssignmentWithCourseColor>>> = _assignmentsColor

    fun getAssignmentsForCourse(courseId: Int) {
        viewModelScope.launch {
            assignments.value = assignmentRepository.getAssignmentsForCourse(courseId)
        }
    }

    fun addAssignment(courseId: Int, title: String, date: LocalDate, time: LocalTime, isDeadline: Boolean) {
        viewModelScope.launch {
            assignmentRepository.insertAssignment(AssignmentModel(courseId = courseId, title = title, date = date, time = time, isDeadline = isDeadline))
            getAssignmentsForCourse(courseId) // Refresh the list
        }
    }

    fun deleteAssignment(assignmentId: Int) {
        viewModelScope.launch {
            val courseId = assignments.value?.find { it.id == assignmentId }?.courseId ?: return@launch
            assignmentRepository.deleteAssignment(assignmentId)
            getAssignmentsForCourse(courseId) // Refresh the list

        }
    }

    fun getAssignmentsWithCourseColorByDate(date: LocalDate) {
        viewModelScope.launch {
            val newAssignments = assignmentRepository.getAssignmentsWithCourseColorByDate(date)
           _assignmentsColor.setValue(_assignmentsColor.value.orEmpty() + (date to newAssignments))
        }
    }
}