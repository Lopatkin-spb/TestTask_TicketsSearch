package com.example.testtask_ticketssearch.data

import com.example.testtask_ticketssearch._interface.CoroutineDispatchers
import com.example.testtask_ticketssearch.data.remote.OffersApi
import com.example.testtask_ticketssearch.domain.model.EventOffer
import com.example.testtask_ticketssearch.domain.repository.OffersRepository
import com.example.testtask_ticketssearch.domain.model.TicketOffer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class OffersRepositoryImpl(
    private val api: OffersApi,
    private val dispatchers: CoroutineDispatchers,
) : OffersRepository {

    override fun getEventsOffers(): Flow<List<EventOffer>> {
        return flow {

            val list = api.getEventsOffers().eventsOffers
                .map { dto ->
                    EventOffer(
                        id = dto.id,
                        title = dto.title,
                        town = dto.town,
                        price = dto.price.value,
                    )
                }
            emit(list)
        }.flowOn(dispatchers.io())
    }

    override fun getTicketsOffers(): Flow<List<TicketOffer>> {
        return flow {

            val list = api.getTicketsOffers().ticketsOffers
                .map { dto ->
                    TicketOffer(
                        id = dto.id,
                        title = dto.title,
                        timeFlights = dto.timeRange,
                        price = dto.price.value,
                    )
                }
            emit(list)
        }.flowOn(dispatchers.io())
    }

}

