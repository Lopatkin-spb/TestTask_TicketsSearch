package com.example.ticketlist.domain.usecase

import com.example.ticketlist.domain.GetTicketListBySearchPlacesUseCase
import com.example.ticketlist.domain.model.Ticket
import com.example.ticketlist.domain.repository.OffersRepository
import kotlinx.coroutines.flow.Flow

internal class GetTicketListBySearchPlacesUseCaseImpl(
    private val repository: OffersRepository,
) : GetTicketListBySearchPlacesUseCase {

    override fun execute(): Flow<List<Ticket>> {
        return repository.getTickets()
    }

}
