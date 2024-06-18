package com.example.testtask_ticketssearch.di

import com.example.testtask_ticketssearch.presentation.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule{

    @Singleton
    @Provides
    fun provideViewModelFactory(): ViewModelFactory {
        return ViewModelFactory()
    }

}