package com.example.testtask_ticketssearch.domain.usecase

import com.example.testtask_ticketssearch.domain.SearchPlace
import com.example.testtask_ticketssearch.domain.SettingsRepository

class SavePlaceArrivalByLastSearchUseCase(private val repository: SettingsRepository) {

    fun execute(place: SearchPlace) {
        return repository.saveLastPlaceArrival(place)
    }

}
