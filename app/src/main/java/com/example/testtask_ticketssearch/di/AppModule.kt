package com.example.testtask_ticketssearch.di

import com.example.testtask_ticketssearch.domain.usecase.*
import com.example.testtask_ticketssearch.presentation.ViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideViewModelFactory(
        dispatchers: AppModule.CoroutineDispatchers,
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

    class CoroutineDispatchers(
        val main: CoroutineDispatcher = Dispatchers.Main,
        val default: CoroutineDispatcher = Dispatchers.Default,
        val io: CoroutineDispatcher = Dispatchers.IO,
        val unconfined: CoroutineDispatcher = Dispatchers.Unconfined,
    ) {
        fun main(): CoroutineDispatcher {
            return main
        }

        fun default(): CoroutineDispatcher {
            return default
        }

        fun io(): CoroutineDispatcher {
            return io
        }

        fun unconfined(): CoroutineDispatcher {
            return unconfined
        }
    }

}