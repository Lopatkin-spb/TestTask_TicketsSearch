package com.example.testtask_ticketssearch.data.remote.dataSource

import com.example.testtask_ticketssearch.domain.model.EventOffer
import com.example.testtask_ticketssearch.domain.model.TicketOffer
import kotlinx.coroutines.flow.Flow

interface OffersRemoteDataSource {

    fun getEventsOffers(): Flow<List<EventOffer>>

    fun getTicketsOffers(): Flow<List<TicketOffer>>

}