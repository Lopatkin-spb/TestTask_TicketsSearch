package com.example.testtask_ticketssearch.core

import android.util.Log


interface Logger {

    fun v(tag: String, msg: String)
    fun d(tag: String, msg: String)
    fun i(tag: String, msg: String)
    fun w(tag: String, msg: String, cause: Throwable)
    fun e(tag: String, msg: String, cause: Throwable)

}


internal class LogcatLogger : Logger {

    override fun v(tag: String, msg: String) {
        Log.v(tag, msg)
    }

    override fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }

    override fun i(tag: String, msg: String) {
        Log.i(tag, msg)
    }

    override fun w(tag: String, msg: String, cause: Throwable) {
        Log.w(tag, msg, cause)
    }

    override fun e(tag: String, msg: String, cause: Throwable) {
        Log.e(tag, msg, cause)
    }

}