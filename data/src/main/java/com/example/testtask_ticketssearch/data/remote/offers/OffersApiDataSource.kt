package com.example.testtask_ticketssearch.data.remote.offers

import com.example.testtask_ticketssearch._interface.CoroutineDispatchers
import com.example.testtask_ticketssearch.data.remote.dataSource.OffersRemoteDataSource
import com.example.testtask_ticketssearch.domain.model.EventOffer
import com.example.testtask_ticketssearch.domain.model.TicketOffer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class OffersApiDataSource(
    private val api: OffersApi,
    private val dispatchers: CoroutineDispatchers,
) : OffersRemoteDataSource {

    override fun getEventsOffers(): Flow<List<EventOffer>> {
        return flow { emit(api.getEventsOffers()) }
            .map { response -> response.eventsOffers }
            .map { dtos ->
                dtos.map { dto ->
                    EventOffer(
                        id = dto.id,
                        title = dto.title,
                        town = dto.town,
                        price = dto.price.value,
                    )
                }
            }
            .flowOn(dispatchers.io())
    }

    override fun getTicketsOffers(): Flow<List<TicketOffer>> {
        return flow { emit(api.getTicketsOffers()) }
            .map { response -> response.ticketsOffers }
            .map { dtos ->
                dtos.map { dto ->
                    TicketOffer(
                        id = dto.id,
                        title = dto.title,
                        timeFlights = dto.timeRange,
                        price = dto.price.value,
                    )
                }
            }
            .flowOn(dispatchers.io())
    }

}