package com.example.ticketlist.di

import android.content.Context
import com.example.testtask_ticketssearch._interface.CoroutineDispatchers
import com.example.ticketlist.data.OffersRepositoryImpl
import com.example.ticketlist.data.local.dataSource.OffersLocalDataSource
import com.example.ticketlist.data.local.offers.OffersStubDataSource
import com.example.ticketlist.data.remote.dataSource.OffersRemoteDataSource
import com.example.ticketlist.data.remote.offers.NetworkProvider
import com.example.ticketlist.data.remote.offers.OffersApiDataSource
import com.example.ticketlist.domain.repository.OffersRepository
import com.example.ticketlist.domain.usecase.GetTicketListBySearchPlacesUseCase
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
    ): OffersRepository {
        return OffersRepositoryImpl(
            offersRemoteDataSource = remoteDataSource,
            offersLocalDataSource = localDataSource,
        )
    }

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
        return GetTicketListBySearchPlacesUseCase(repository = repository)
    }

    @Singleton
    @Provides
    fun provideViewModelFactory(
        dispatchers: CoroutineDispatchers,
        getTicketListBySearchPlacesUseCase: GetTicketListBySearchPlacesUseCase,
    ): ViewModelFactory {
        return ViewModelFactory(
            dispatchers = dispatchers,
            getTicketListBySearchPlacesUseCase = getTicketListBySearchPlacesUseCase,
        )
    }

}