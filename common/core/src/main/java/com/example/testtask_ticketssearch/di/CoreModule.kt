package com.example.testtask_ticketssearch.di

import com.example.testtask_ticketssearch.core.*
import com.example.testtask_ticketssearch.core.LogcatLogger
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class CoreModule {

    @Singleton
    @Provides
    fun provideNetworkManager(): NetworkManager {
        return NetworkRetrofit()
    }

    @Singleton
    @Provides
    fun provideLogger(): Logger {
        return LogcatLogger()
    }

    @Singleton
    @Provides
    fun provideDispatchers(): CoroutineDispatchers {
        return AppCoroutineDispatchers()
    }

}