package com.example.ticketlist.domain

import com.example.ticketlist.domain.model.Ticket
import kotlinx.coroutines.flow.Flow


interface GetTicketListBySearchPlacesUseCase {
    fun execute(): Flow<List<Ticket>>
}