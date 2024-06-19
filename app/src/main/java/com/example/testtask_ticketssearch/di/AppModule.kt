package com.example.testtask_ticketssearch.di

import com.example.testtask_ticketssearch.domain.usecase.GetPlaceArrivalByLastSearchUseCase
import com.example.testtask_ticketssearch.domain.usecase.GetPlaceDepartureByLastSearchUseCase
import com.example.testtask_ticketssearch.domain.usecase.SavePlaceArrivalByLastSearchUseCase
import com.example.testtask_ticketssearch.domain.usecase.SavePlaceDepartureByLastSearchUseCase
import com.example.testtask_ticketssearch.presentation.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideViewModelFactory(
        savePlaceDepartureByLastSearchUseCase: SavePlaceDepartureByLastSearchUseCase,
        getPlaceDepartureByLastSearchUseCase: GetPlaceDepartureByLastSearchUseCase,
        savePlaceArrivalByLastSearchUseCase: SavePlaceArrivalByLastSearchUseCase,
        getPlaceArrivalByLastSearchUseCase: GetPlaceArrivalByLastSearchUseCase,
    ): ViewModelFactory {
        return ViewModelFactory(
            getPlaceDepartureByLastSearchUseCase = getPlaceDepartureByLastSearchUseCase,
            savePlaceDepartureByLastSearchUseCase = savePlaceDepartureByLastSearchUseCase,
            savePlaceArrivalByLastSearchUseCase = savePlaceArrivalByLastSearchUseCase,
            getPlaceArrivalByLastSearchUseCase = getPlaceArrivalByLastSearchUseCase,
        )
    }

}