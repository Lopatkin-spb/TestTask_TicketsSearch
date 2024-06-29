package com.example.testtask_ticketssearch.di

import com.example.testtask_ticketssearch._interface.CoroutineDispatchers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class InterfaceModule {

    @Singleton
    @Provides
    fun provideDispatchers(): CoroutineDispatchers {
        return CoroutineDispatchers()
    }

}