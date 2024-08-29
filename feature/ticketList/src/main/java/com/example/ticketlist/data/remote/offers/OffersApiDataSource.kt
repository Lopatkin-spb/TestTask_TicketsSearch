package com.example.ticketlist.data.remote.offers

import com.example.testtask_ticketssearch.core.CoroutineDispatchers
import com.example.ticketlist.data.remote.dataSource.OffersRemoteDataSource
import com.example.ticketlist.domain.model.Arrival
import com.example.ticketlist.domain.model.Departure
import com.example.ticketlist.domain.model.Ticket
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class OffersApiDataSource(
    private val api: OffersApi,
    private val dispatchers: CoroutineDispatchers,
) : OffersRemoteDataSource {

    override fun getTickets(): Flow<List<Ticket>> {
        return flow { emit(api.getTickets()) }
            .map { response -> response.tickets }
            .map { dtos ->
                dtos.map { dto ->
                    val departure = dto.departure?.let {
                        Departure(
                            town = it.town,
                            date = it.date,
                            airport = it.airport,
                        )
                    }
                    val arrival = dto.arrival?.let {
                        Arrival(
                            town = it.town,
                            date = it.date,
                            airport = it.airport,
                        )
                    }
                    Ticket(
                        id = dto.id,
                        badge = dto.badge,
                        price = dto.price?.value,
                        departure = departure,
                        arrival = arrival,
                        hasTransfer = dto.hasTransfer,
                    )
                }
            }
            .flowOn(dispatchers.io())
    }

}