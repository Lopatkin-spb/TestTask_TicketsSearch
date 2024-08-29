package com.example.testtask_ticketssearch.di

import android.content.Context
import com.example.profile.di.ProfileComponent
import com.example.profile.di.ProfileModule
import com.example.shorter.di.ShorterComponent
import com.example.shorter.di.ShorterModule
import com.example.subscriptions.di.SubscriptionsComponent
import com.example.subscriptions.di.SubscriptionsModule
import com.example.ticketlist.di.TicketListComponent
import com.example.ticketlist.di.TicketListModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        DomainModule::class,
        PresentationModule::class,
        CoreModule::class,
        ProfileModule::class,
        SubscriptionsModule::class,
        TicketListModule::class,
        ShorterModule::class,
    ]
)
interface AppComponent {

    fun presentationComponent(): PresentationComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent

    }

    fun profileComponent(): ProfileComponent.Factory

    fun subscriptionsComponent(): SubscriptionsComponent.Factory

    fun ticketListComponent(): TicketListComponent.Factory

    fun shorterComponent(): ShorterComponent.Factory

}