package com.kylegordon.crocsswimlog

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

object SnackbarController {
    private val _messageQueue = Channel<String>()
    val messageQueue = _messageQueue.receiveAsFlow()

    suspend fun sendMessage(msg: String){
        _messageQueue.send(msg)
    }
}