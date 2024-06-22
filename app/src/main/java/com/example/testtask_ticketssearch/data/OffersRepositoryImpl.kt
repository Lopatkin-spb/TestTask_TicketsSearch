package com.example.testtask_ticketssearch.data

import com.example.testtask_ticketssearch.data.remote.OffersApi
import com.example.testtask_ticketssearch.di.AppModule
import com.example.testtask_ticketssearch.domain.OffersRepository
import com.example.testtask_ticketssearch.domain.TicketOffer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class OffersRepositoryImpl(
    private val api: OffersApi,
    private val dispatchers: AppModule.CoroutineDispatchers,
) : OffersRepository {

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

