package com.example.testtask_ticketssearch.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CoroutineDispatchers(
    val main: CoroutineDispatcher = Dispatchers.Main,
    val default: CoroutineDispatcher = Dispatchers.Default,
    val io: CoroutineDispatcher = Dispatchers.IO,
    val unconfined: CoroutineDispatcher = Dispatchers.Unconfined,
) {
    fun main(): CoroutineDispatcher {
        return main
    }

    fun default(): CoroutineDispatcher {
        return default
    }

    fun io(): CoroutineDispatcher {
        return io
    }

    fun unconfined(): CoroutineDispatcher {
        return unconfined
    }
}