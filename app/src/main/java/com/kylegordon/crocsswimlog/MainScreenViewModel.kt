package com.kylegordon.crocsswimlog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylegordon.crocsswimlog.data.SwimLogDao
import com.kylegordon.crocsswimlog.data.SwimLogEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class Analytics(
    val totalWorkouts: Int,
    val avgYardage: Int,
    val avgDuration: Int,
    val mcstroke: String
)

class MainScreenViewModel(
    private val dao: SwimLogDao
) : ViewModel() {

    private val _analytics = MutableStateFlow(
        Analytics(0, 0, 0, "")
    )
    val analytics: StateFlow<Analytics> = _analytics

    init {
        viewModelScope.launch {
            dao.getAllEntriesFlow().collectLatest { entries ->
                _analytics.value = calculateAnalytics(entries)
            }
        }
    }

    private fun calculateAnalytics(entries: List<SwimLogEntry>): Analytics {
        if (entries.isEmpty()) return Analytics(0, 0, 0, "")

        val totalWorkouts = entries.size
        val avgYardage = entries.map { it.totalYardage }.average().toInt()
        val avgDuration = entries.map { it.workoutLength }.average().toInt()

        val mcstroke = entries
            .groupingBy { it.mainStroke }
            .eachCount()
            .maxByOrNull { it.value }
            ?.key ?: ""

        return Analytics(totalWorkouts, avgYardage, avgDuration, mcstroke)
    }
}
