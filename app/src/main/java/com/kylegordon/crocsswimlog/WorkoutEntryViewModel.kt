package com.kylegordon.crocsswimlog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylegordon.crocsswimlog.data.SwimLogDao
import com.kylegordon.crocsswimlog.data.SwimLogEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WorkoutEntryViewModel(
    private val dao: SwimLogDao
) : ViewModel() {

    private val _workoutDate = MutableStateFlow("")
    val workoutDate = _workoutDate.asStateFlow()

    private val _duration = MutableStateFlow("")
    val duration = _duration.asStateFlow()

    private val _mainStroke = MutableStateFlow("")
    val mainStroke = _mainStroke.asStateFlow()

    private val _totalYardage = MutableStateFlow("")
    val totalYardage = _totalYardage.asStateFlow()

    private val _photoLog = MutableStateFlow<String?>(null)
    val photoLog = _photoLog.asStateFlow()

    fun setWorkoutDate(e: String) { _workoutDate.value = e }
    fun setWorkoutDuration(e: String) { _duration.value = e }
    fun setMainStroke(e: String) { _mainStroke.value = e }
    fun setTotalYardage(e: String) { _totalYardage.value = e }
    fun setPhotoLog(uri: String?) { _photoLog.value = uri }



    // SAVE ENTRY TO ROOM DATABASE
    fun saveEntry(onSaved: () -> Unit = {}) {
        viewModelScope.launch {
            val parsedDate = parseDate(_workoutDate.value)

            val entry = SwimLogEntry(
                dow = parsedDate,
                workoutLength = _duration.value.toIntOrNull() ?: 0,
                mainStroke = _mainStroke.value,
                totalYardage = _totalYardage.value.toIntOrNull() ?: 0,
                photoLog = _photoLog.value
            )

            dao.insert(entry)

            onSaved()
        }
    }

    private fun parseDate(text: String): Date {
        return try {
            SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(text) ?: Date()
        } catch (e: Exception) {
            Date()
        }
    }
}
