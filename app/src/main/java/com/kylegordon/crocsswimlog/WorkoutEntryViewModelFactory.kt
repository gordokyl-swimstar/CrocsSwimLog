package com.kylegordon.crocsswimlog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kylegordon.crocsswimlog.data.SwimLogDao

class WorkoutEntryViewModelFactory(
    private val dao: SwimLogDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutEntryViewModel::class.java)) {
            return WorkoutEntryViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
