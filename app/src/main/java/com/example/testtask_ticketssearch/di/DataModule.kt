package com.example.testtask_ticketssearch.di

import android.content.Context
import com.example.testtask_ticketssearch.data.OffersRepositoryImpl
import com.example.testtask_ticketssearch.data.SettingsRepositoryImpl
import com.example.testtask_ticketssearch.data.local.dataSource.SettingsDataSource
import com.example.testtask_ticketssearch.data.local.settings.SettingsLocalDataSource
import com.example.testtask_ticketssearch.data.remote.NetworkProvider
import com.example.testtask_ticketssearch.domain.OffersRepository
import com.example.testtask_ticketssearch.domain.SettingsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    /**
     * Repository's
     */
    @Singleton
    @Provides
    fun provideSettingsRepositoryImpl(
        settingsDataSource: SettingsDataSource,
    ): SettingsRepository {
        return SettingsRepositoryImpl(settingsDataSource)
    }

    @Singleton
    @Provides
    fun provideOffersRepository(
        dispatchers: AppModule.CoroutineDispatchers,
    ): OffersRepository {
        return OffersRepositoryImpl(NetworkProvider.registerApi, dispatchers)
    }

    /**
     * Local data sources
     */
    @Singleton
    @Provides
    fun provideSettingsLocalDataSource(
        context: Context,
    ): SettingsDataSource {
        return SettingsLocalDataSource(context)
    }

}