package com.example.testtask_ticketssearch.data.local.dataSource

import com.example.testtask_ticketssearch.domain.model.EventOffer
import com.example.testtask_ticketssearch.domain.model.Ticket
import com.example.testtask_ticketssearch.domain.model.TicketOffer
import kotlinx.coroutines.flow.Flow

interface OffersLocalDataSource {

    fun getEventsOffers(): Flow<List<EventOffer>>

    fun getTicketsOffers(): Flow<List<TicketOffer>>

    fun getTickets(): Flow<List<Ticket>>

}