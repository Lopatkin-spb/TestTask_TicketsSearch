package com.example.ticketlist.domain.repository

import com.example.ticketlist.domain.model.Ticket
import kotlinx.coroutines.flow.Flow

interface OffersRepository {

    fun getTickets(): Flow<List<Ticket>>

}