package com.example.testtask_ticketssearch.app

import android.app.Application
import com.example.profile.di.ProfileComponent
import com.example.profile.di.ProfileComponentProvider
import com.example.subscriptions.di.SubscriptionsComponent
import com.example.subscriptions.di.SubscriptionsComponentProvider
import com.example.testtask_ticketssearch.di.PresentationComponent
import com.example.testtask_ticketssearch.di.PresentationComponentProvider
import com.example.testtask_ticketssearch.di.AppComponent
import com.example.testtask_ticketssearch.di.DaggerAppComponent

internal class TTTSApp : Application(),
    PresentationComponentProvider, ProfileComponentProvider, SubscriptionsComponentProvider {

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

    override fun providePresentationComponent(): PresentationComponent {
        return appComponent.presentationComponent().create()
    }

    override fun provideProfileComponent(): ProfileComponent {
        return appComponent.profileComponent().create()
    }

    override fun provideSubscriptionsComponent(): SubscriptionsComponent {
        return appComponent.subscriptionsComponent().create()
    }

}