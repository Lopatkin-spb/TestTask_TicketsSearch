package com.example.testtask_ticketssearch.domain

import kotlinx.coroutines.flow.Flow

interface OffersRepository {

    fun getTicketsOffers(): Flow<List<TicketOffer>>

}