package com.kylegordon.crocsswimlog

import androidx.lifecycle.ViewModel

data class Workout(
    val date: String,
    val duration: Int,
    val stroke: String,
    val distance: Int
)

class SwimLogViewModel : ViewModel(){
    val workouts = listOf(
        Workout("9/29/25", 120, "Freestyle", 2500),
        Workout("10/1/25", 120, "Backstroke", 2000),
        Workout("10/6/25", 120, "Freestyle", 1500),
        Workout("10/8/25", 120, "Freestyle", 3500),
        Workout("10/13/25", 120, "Backstroke", 2000),
        Workout("10/15/25", 120, "IM", 1250)
    )
}