package com.example.subscriptions.di

import com.example.subscriptions.presentation.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [SubscriptionsComponent::class])
class SubscriptionsModule {

    @Singleton
    @Provides
    fun provideViewModelFactory(): ViewModelFactory {
        return ViewModelFactory()
    }

}