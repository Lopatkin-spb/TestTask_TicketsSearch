package com.example.testtask_ticketssearch.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface CoroutineDispatchers {
    fun main(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
    fun unconfined(): CoroutineDispatcher
}


internal class AppCoroutineDispatchers(
    private val main: CoroutineDispatcher = Dispatchers.Main,
    private val default: CoroutineDispatcher = Dispatchers.Default,
    private val io: CoroutineDispatcher = Dispatchers.IO,
    private val unconfined: CoroutineDispatcher = Dispatchers.Unconfined,
) : CoroutineDispatchers {
    override fun main(): CoroutineDispatcher {
        return main
    }

    override fun default(): CoroutineDispatcher {
        return default
    }

    override fun io(): CoroutineDispatcher {
        return io
    }

    override fun unconfined(): CoroutineDispatcher {
        return unconfined
    }
}