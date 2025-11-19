package com.kylegordon.crocsswimlog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kylegordon.crocsswimlog.data.SwimLogDao

class SwimLogViewModelFactory(
    private val dao: SwimLogDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SwimLogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SwimLogViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
