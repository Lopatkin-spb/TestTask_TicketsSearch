package com.example.testtask_ticketssearch.di

import com.example.testtask_ticketssearch.presentation.CoroutineDispatchers
import com.example.testtask_ticketssearch.presentation.ViewModelFactory
import com.example.testtask_ticketssearch.domain.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(subcomponents = [PresentationComponent::class])
class PresentationModule {

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