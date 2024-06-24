package com.example.testtask_ticketssearch.domain.usecase

import com.example.testtask_ticketssearch.domain.model.SearchPlace
import com.example.testtask_ticketssearch.domain.repository.SettingsRepository

class GetPlaceDepartureByLastSearchUseCase(private val repository: SettingsRepository) {

    fun execute(): SearchPlace {
        return repository.getLastPlaceDeparture()
    }

}
