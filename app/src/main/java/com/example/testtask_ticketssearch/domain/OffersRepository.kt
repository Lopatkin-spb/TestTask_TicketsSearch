package com.example.testtask_ticketssearch.domain

import kotlinx.coroutines.flow.Flow

interface OffersRepository {

    fun getEventsOffers(): Flow<List<EventOffer>>

    fun getTicketsOffers(): Flow<List<TicketOffer>>

}