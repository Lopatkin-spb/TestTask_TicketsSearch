package com.example.subscriptions.di

import com.example.subscriptions.presentation.SubscriptionsFragment
import dagger.Subcomponent

@Subcomponent
interface SubscriptionsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SubscriptionsComponent
    }

    fun inject(fragment: SubscriptionsFragment)

}