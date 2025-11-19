package com.kylegordon.crocsswimlog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylegordon.crocsswimlog.data.SwimLogDao
import com.kylegordon.crocsswimlog.data.SwimLogEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SwimLogViewModel(
    private val dao: SwimLogDao
) : ViewModel() {

    private val _entries = MutableStateFlow<List<SwimLogEntry>>(emptyList())
    val entries: StateFlow<List<SwimLogEntry>> = _entries.asStateFlow()

    init {
        viewModelScope.launch {
            dao.getAllEntriesFlow().collectLatest { list ->
                _entries.value = list
            }
        }
    }

    fun deleteEntry(entry: SwimLogEntry) {
        viewModelScope.launch {
            dao.deleteEntry(entry)
        }
    }
}
