package com.example.testtask_ticketssearch.data.local.offers

import android.content.Context
import com.example.testtask_ticketssearch.core.CoroutineDispatchers
import com.example.testtask_ticketssearch.data.local.dataSource.OffersLocalDataSource
import com.example.testtask_ticketssearch.domain.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

internal class OffersStubDataSource(
    private val context: Context,
    private val dispatchers: CoroutineDispatchers,
) : OffersLocalDataSource {

    @OptIn(ExperimentalSerializationApi::class)
    override fun getEventsOffers(): Flow<List<EventOffer>> {
        return flow {
            val stream = context.assets.open("ad9a46ba-276c-4a81-88a6-c068e51cce3a.json")
            val response = Json.decodeFromStream<ResponseDbo>(stream)
            emit(response)
        }
            .map { response -> response.eventsOffers }
            .map { dtos -> //TODO: correct names
                var counter = 0

                dtos.map { dto ->
                    counter++
                    if (counter > 3) counter = 0
                    val imageStub = "$counter.png"
                    val urlStub = "file:///android_asset/$imageStub"
                    EventOffer(
                        id = dto.id,
                        title = dto.title,
                        town = dto.town,
                        price = dto.price.value,
                        url = urlStub,
                    )
                }
            }
            .flowOn(dispatchers.io())
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun getTicketsOffers(): Flow<List<TicketOffer>> {
        return flow {
            val stream = context.assets.open("38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a.json")
            val response = Json.decodeFromStream<ResponseDbo>(stream)
            emit(response)
        }
            .map { response -> response.ticketsOffers }
            .map { dtos -> //TODO: correct names
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