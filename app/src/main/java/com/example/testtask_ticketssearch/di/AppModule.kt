package com.example.testtask_ticketssearch.di

import com.example.testtask_ticketssearch.data.CoroutineDispatchers
import com.example.testtask_ticketssearch.domain.usecase.*
import com.example.testtask_ticketssearch.presentation.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideViewModelFactory(
        dispatchers: CoroutineDispatchers,
        savePlaceDepartureByLastSearchUseCase: SavePlaceDepartureByLastSearchUseCase,
        getPlaceDepartureByLastSearchUseCase: GetPlaceDepartureByLastSearchUseCase,
        savePlaceArrivalByLastSearchUseCase: SavePlaceArrivalByLastSearchUseCase,
        getPlaceArrivalByLastSearchUseCase: GetPlaceArrivalByLastSearchUseCase,
        getTicketsOffersUseCase: GetTicketsOffersUseCase,
        getEventsOffersUseCase: GetEventsOffersUseCase,
    ): ViewModelFactory {
        return ViewModelFactory(
            dispatchers = dispatchers,
            getPlaceDepartureByLastSearchUseCase = getPlaceDepartureByLastSearchUseCase,
            savePlaceDepartureByLastSearchUseCase = savePlaceDepartureByLastSearchUseCase,
            savePlaceArrivalByLastSearchUseCase = savePlaceArrivalByLastSearchUseCase,
            getPlaceArrivalByLastSearchUseCase = getPlaceArrivalByLastSearchUseCase,
            getTicketsOffersUseCase = getTicketsOffersUseCase,
            getEventsOffersUseCase = getEventsOffersUseCase,
        )
    }

    @Singleton
    @Provides
    fun provideDispatchers(): CoroutineDispatchers {
        return CoroutineDispatchers()
    }

}