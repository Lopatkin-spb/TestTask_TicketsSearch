package com.example.testtask_ticketssearch.app

import android.app.Application
import com.example.testtask_ticketssearch.di.AppComponent
import com.example.testtask_ticketssearch.di.DaggerAppComponent

class TTTSApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .context(context = this)
            .build()
    }

}