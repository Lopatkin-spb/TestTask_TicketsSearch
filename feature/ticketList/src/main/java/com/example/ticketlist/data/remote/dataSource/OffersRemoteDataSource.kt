package com.example.ticketlist.data.remote.dataSource

import com.example.ticketlist.domain.model.Ticket
import kotlinx.coroutines.flow.Flow

interface OffersRemoteDataSource {

    fun getTickets(): Flow<List<Ticket>>

}