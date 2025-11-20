package com.kylegordon.crocsswimlog

import androidx.lifecycle.ViewModel

data class Analytics(
    val totalWorkouts: Int,
    val avgYardage: Int,
    val avgDuration: Int,
    val mcstroke: String
)
class MainScreenViewModel : ViewModel() {
    val analytics = listOf(
        Analytics(6, 2500, 120, "Freestyle")
    )
}

