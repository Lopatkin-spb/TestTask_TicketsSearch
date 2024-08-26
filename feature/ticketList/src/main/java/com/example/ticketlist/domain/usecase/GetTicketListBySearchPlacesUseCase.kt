package com.example.ticketlist.domain.usecase

import com.example.ticketlist.domain.model.Ticket
import com.example.ticketlist.domain.repository.OffersRepository
import kotlinx.coroutines.flow.Flow

class GetTicketListBySearchPlacesUseCase(private val repository: OffersRepository) {

    fun execute(): Flow<List<Ticket>> {
        return repository.getTickets()
    }

}
