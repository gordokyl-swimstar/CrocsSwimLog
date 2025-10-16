package com.kylegordon.crocsswimlog

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WorkoutEntryViewModel : ViewModel() {
    private val _workoutDate = MutableStateFlow("")
    val workoutDate = _workoutDate.asStateFlow()

    private val _duration = MutableStateFlow("")
    val duration = _duration.asStateFlow()

    private val _mainStroke = MutableStateFlow("")
    val mainStroke = _mainStroke.asStateFlow()

    private val _totalYardage = MutableStateFlow("")
    val totalYardage = _totalYardage.asStateFlow()

    fun setWorkoutDate(e: String) {
        _workoutDate.value = e
    }

    fun setWorkoutDuration(e: String) {
        _duration.value = e
    }

    fun setMainStroke(e: String) {
        _mainStroke.value = e
    }

    fun setTotalYardage(e: String) {
        _totalYardage.value = e
    }
}