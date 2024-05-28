package com.example.kalendo.ui.viewmodel

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kalendo.domain.model.CourseModel
import com.example.kalendo.domain.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val courseRepository: CourseRepository
) : ViewModel() {

    val courses = MutableLiveData<List<CourseModel>>()

    init {
        //Log.i("DatabaseCourse", courses.toString())
        getAllCourses()
    }

    fun getAllCourses() {
        viewModelScope.launch {
            courses.value = courseRepository.getAllCourses()
        }
    }

    fun addCourse(title: String, color: Color) {
        viewModelScope.launch {
            courseRepository.insertCourse(CourseModel(title = title, color = color)) // Might need to add ID
            getAllCourses() // Refresh the list
        }
    }

    fun deleteCourse(courseId: Int) {
        viewModelScope.launch {
            courseRepository.deleteCourse(courseId)
            getAllCourses() // Refresh the list
        }
    }
}