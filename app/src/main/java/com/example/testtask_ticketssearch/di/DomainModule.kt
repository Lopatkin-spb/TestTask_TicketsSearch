package com.example.testtask_ticketssearch.di

import com.example.testtask_ticketssearch.domain.SettingsRepository
import com.example.testtask_ticketssearch.domain.usecase.GetPlaceArrivalByLastSearchUseCase
import com.example.testtask_ticketssearch.domain.usecase.GetPlaceDepartureByLastSearchUseCase
import com.example.testtask_ticketssearch.domain.usecase.SavePlaceArrivalByLastSearchUseCase
import com.example.testtask_ticketssearch.domain.usecase.SavePlaceDepartureByLastSearchUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideSavePlaceDepartureByLastSearchUseCase(
        repository: SettingsRepository
    ): SavePlaceDepartureByLastSearchUseCase {
        return SavePlaceDepartureByLastSearchUseCase(repository = repository)
    }

    @Provides
    fun provideGetPlaceDepartureByLastSearchUseCase(
        repository: SettingsRepository
    ): GetPlaceDepartureByLastSearchUseCase {
        return GetPlaceDepartureByLastSearchUseCase(repository = repository)
    }

    @Provides
    fun provideSavePlaceArrivalByLastSearchUseCase(
        repository: SettingsRepository
    ): SavePlaceArrivalByLastSearchUseCase {
        return SavePlaceArrivalByLastSearchUseCase(repository = repository)
    }

    @Provides
    fun provideGetPlaceArrivalByLastSearchUseCase(
        repository: SettingsRepository
    ): GetPlaceArrivalByLastSearchUseCase {
        return GetPlaceArrivalByLastSearchUseCase(repository = repository)
    }

}