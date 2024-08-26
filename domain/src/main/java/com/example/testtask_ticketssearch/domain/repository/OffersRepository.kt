package com.example.testtask_ticketssearch.domain.repository

import com.example.testtask_ticketssearch.domain.model.EventOffer
import com.example.testtask_ticketssearch.domain.model.TicketOffer
import kotlinx.coroutines.flow.Flow

interface OffersRepository {

    fun getEventsOffers(): Flow<List<EventOffer>>

    fun getTicketsOffers(): Flow<List<TicketOffer>>

}