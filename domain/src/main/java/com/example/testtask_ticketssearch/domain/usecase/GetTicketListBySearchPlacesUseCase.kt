package com.example.testtask_ticketssearch.domain.usecase

import com.example.testtask_ticketssearch.domain.model.Ticket
import com.example.testtask_ticketssearch.domain.repository.OffersRepository
import kotlinx.coroutines.flow.Flow

class GetTicketListBySearchPlacesUseCase(private val repository: OffersRepository) {

    fun execute(): Flow<List<Ticket>> {
        return repository.getTickets()
    }

}
