package com.example.testtask_ticketssearch._interface

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CoroutineDispatchers(
    private val main: CoroutineDispatcher = Dispatchers.Main,
    private val default: CoroutineDispatcher = Dispatchers.Default,
    private val io: CoroutineDispatcher = Dispatchers.IO,
    private val unconfined: CoroutineDispatcher = Dispatchers.Unconfined,
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