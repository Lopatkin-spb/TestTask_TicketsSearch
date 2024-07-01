package com.example.testtask_ticketssearch.di

import android.content.Context
import com.example.testtask_ticketssearch._interface.CoroutineDispatchers
import com.example.testtask_ticketssearch.data.OffersRepositoryImpl
import com.example.testtask_ticketssearch.data.SettingsRepositoryImpl
import com.example.testtask_ticketssearch.data.local.dataSource.OffersLocalDataSource
import com.example.testtask_ticketssearch.data.local.dataSource.SettingsDataSource
import com.example.testtask_ticketssearch.data.local.offers.OffersStubDataSource
import com.example.testtask_ticketssearch.data.local.settings.SettingsLocalDataSource
import com.example.testtask_ticketssearch.data.remote.dataSource.OffersRemoteDataSource
import com.example.testtask_ticketssearch.data.remote.offers.NetworkProvider
import com.example.testtask_ticketssearch.data.remote.offers.OffersApiDataSource
import com.example.testtask_ticketssearch.domain.repository.OffersRepository
import com.example.testtask_ticketssearch.domain.repository.SettingsRepository
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
        remoteDataSource: OffersRemoteDataSource,
        localDataSource: OffersLocalDataSource,
    ): OffersRepository {
        return OffersRepositoryImpl(
            offersRemoteDataSource = remoteDataSource,
            offersLocalDataSource = localDataSource,
        )
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

    @Singleton
    @Provides
    fun provideOffersLocalDataSource(
        context: Context,
        dispatchers: CoroutineDispatchers,
    ): OffersLocalDataSource {
        return OffersStubDataSource(
            context = context,
            dispatchers = dispatchers,
        )
    }

    /**
     * Remote data sources
     */
    @Singleton
    @Provides
    fun provideOffersRemoteDataSource(
        dispatchers: CoroutineDispatchers,
    ): OffersRemoteDataSource {
        return OffersApiDataSource(
            api = NetworkProvider.registerApi,
            dispatchers = dispatchers,
        )
    }

}