package com.example.shorter.di

import com.example.shorter.presentation.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [ShorterComponent::class])
class ShorterModule {

    @Singleton
    @Provides
    fun provideViewModelFactory(): ViewModelFactory {
        return ViewModelFactory()
    }

}