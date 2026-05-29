package com.example.catalog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalog.model.Course
import com.example.catalog.model.Enrollment
import com.example.catalog.model.Review
import com.example.catalog.network.CatalogApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatalogViewModel : ViewModel() {
    private val api = CatalogApi.create()

    private val _courses = MutableStateFlow<List<Course>>(emptyList())
    val courses: StateFlow<List<Course>> = _courses.asStateFlow()

    private val _selectedCourse = MutableStateFlow<Course?>(null)
    val selectedCourse: StateFlow<Course?> = _selectedCourse.asStateFlow()

    init {
        loadCourses()
    }

    fun loadCourses() {
        viewModelScope.launch {
            try {
                _courses.value = api.getCourses()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadCourseDetails(id: Int) {
        viewModelScope.launch {
            try {
                _selectedCourse.value = api.getCourse(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addReview(courseId: Int, rating: Int, comment: String) {
        viewModelScope.launch {
            try {
                api.addReview(courseId, Review(rating = rating, comment = comment))
                loadCourseDetails(courseId)
                loadCourses()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun enroll(courseId: Int, phone: String, email: String) {
        viewModelScope.launch {
            try {
                api.enroll(courseId, Enrollment(phone = phone, email = email))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
