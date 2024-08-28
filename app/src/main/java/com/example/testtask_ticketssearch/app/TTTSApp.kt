package com.example.testtask_ticketssearch.app

import android.app.Application
import com.example.profile.di.ProfileComponent
import com.example.profile.di.ProfileComponentProvider
import com.example.shorter.di.ShorterComponent
import com.example.shorter.di.ShorterComponentProvider
import com.example.subscriptions.di.SubscriptionsComponent
import com.example.subscriptions.di.SubscriptionsComponentProvider
import com.example.testtask_ticketssearch.di.PresentationComponent
import com.example.testtask_ticketssearch.di.PresentationComponentProvider
import com.example.testtask_ticketssearch.di.AppComponent
import com.example.testtask_ticketssearch.di.DaggerAppComponent
import com.example.ticketlist.di.TicketListComponent
import com.example.ticketlist.di.TicketListComponentProvider

internal class TTTSApp : Application(),
    PresentationComponentProvider, ProfileComponentProvider, SubscriptionsComponentProvider,
    TicketListComponentProvider, ShorterComponentProvider {

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

    override fun provideTicketListComponent(): TicketListComponent {
        return appComponent.ticketListComponent().create()
    }

    override fun provideShorterComponent(): ShorterComponent {
        return appComponent.shorterComponent().create()
    }

}