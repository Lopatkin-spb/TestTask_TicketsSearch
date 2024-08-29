package com.example.ticketlist.di

import android.content.Context
import com.example.testtask_ticketssearch.core.CoroutineDispatchers
import com.example.testtask_ticketssearch.core.Logger
import com.example.testtask_ticketssearch.core.NetworkManager
import com.example.ticketlist.data.OffersRepositoryImpl
import com.example.ticketlist.data.local.dataSource.OffersLocalDataSource
import com.example.ticketlist.data.local.offers.OffersStubDataSource
import com.example.ticketlist.data.remote.dataSource.OffersRemoteDataSource
import com.example.ticketlist.data.remote.offers.OffersApiDataSource
import com.example.ticketlist.data.remote.offers.registerOffersApi
import com.example.ticketlist.domain.repository.OffersRepository
import com.example.ticketlist.domain.GetTicketListBySearchPlacesUseCase
import com.example.ticketlist.domain.usecase.GetTicketListBySearchPlacesUseCaseImpl
import com.example.ticketlist.presentation.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [TicketListComponent::class])
class TicketListModule {

    @Singleton
    @Provides
    fun provideOffersRepository(
        remoteDataSource: OffersRemoteDataSource,
        localDataSource: OffersLocalDataSource,
        logger: Logger,
    ): OffersRepository {
        return OffersRepositoryImpl(
            offersRemoteDataSource = remoteDataSource,
            offersLocalDataSource = localDataSource,
            logger = logger,
        )
    }

    @Singleton
    @Provides
    fun provideOffersRemoteDataSource(
        dispatchers: CoroutineDispatchers,
        manager: NetworkManager,
    ): OffersRemoteDataSource {
        return OffersApiDataSource(
            api = registerOffersApi(manager.getProvider()),
            dispatchers = dispatchers,
        )
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

    @Provides
    fun provideGetTicketListBySearchPlacesUseCase(
        repository: OffersRepository
    ): GetTicketListBySearchPlacesUseCase {
        return GetTicketListBySearchPlacesUseCaseImpl(repository = repository)
    }

    @Singleton
    @Provides
    fun provideViewModelFactory(
        dispatchers: CoroutineDispatchers,
        logger: Logger,
        getTicketListBySearchPlacesUseCase: GetTicketListBySearchPlacesUseCase,
    ): ViewModelFactory {
        return ViewModelFactory(
            dispatchers = dispatchers,
            logger = logger,
            getTicketListBySearchPlacesUseCase = getTicketListBySearchPlacesUseCase,
        )
    }

}