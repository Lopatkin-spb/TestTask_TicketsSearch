package com.example.ticketlist.data.local.dataSource

import com.example.ticketlist.domain.model.Ticket
import kotlinx.coroutines.flow.Flow

interface OffersLocalDataSource {

    fun getTickets(): Flow<List<Ticket>>

}