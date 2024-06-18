package com.example.testtask_ticketssearch.di

import android.content.Context
import com.example.testtask_ticketssearch.presentation.AppActivity
import com.example.testtask_ticketssearch.presentation.airtickets.AirticketsFragment
import com.example.testtask_ticketssearch.presentation.hotels.HotelsFragment
import com.example.testtask_ticketssearch.presentation.profile.ProfileFragment
import com.example.testtask_ticketssearch.presentation.shorter.ShorterFragment
import com.example.testtask_ticketssearch.presentation.subscriptions.SubscriptionsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
    ]
)
interface AppComponent {

    fun inject(activity: AppActivity)

    fun inject(fragment: AirticketsFragment)

    fun inject(fragment: HotelsFragment)

    fun inject(fragment: ProfileFragment)

    fun inject(fragment: ShorterFragment)

    fun inject(fragment: SubscriptionsFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent

    }

}