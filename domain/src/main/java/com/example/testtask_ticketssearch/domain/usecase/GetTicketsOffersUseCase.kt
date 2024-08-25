package com.example.testtask_ticketssearch.domain.usecase

import com.example.testtask_ticketssearch.domain.model.TicketOffer
import com.example.testtask_ticketssearch.domain.repository.OffersRepository
import kotlinx.coroutines.flow.Flow

class GetTicketsOffersUseCase(private val repository: OffersRepository) {

    fun execute(): Flow<List<TicketOffer>> {
        return repository.getTicketsOffers()
    }

}
