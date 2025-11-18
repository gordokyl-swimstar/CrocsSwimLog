package com.kylegordon.crocsswimlog

import android.net.Uri
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.File

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