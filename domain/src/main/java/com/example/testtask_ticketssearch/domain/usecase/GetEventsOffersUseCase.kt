package com.example.testtask_ticketssearch.domain.usecase

import com.example.testtask_ticketssearch.domain.model.EventOffer
import com.example.testtask_ticketssearch.domain.repository.OffersRepository
import kotlinx.coroutines.flow.Flow

class GetEventsOffersUseCase(private val repository: OffersRepository) {

    fun execute(): Flow<List<EventOffer>> {
        return repository.getEventsOffers()
    }

}
