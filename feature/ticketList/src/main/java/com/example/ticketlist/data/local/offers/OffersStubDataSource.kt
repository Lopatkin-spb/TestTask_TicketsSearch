package com.example.ticketlist.data.local.offers

import android.content.Context
import com.example.testtask_ticketssearch._interface.CoroutineDispatchers
import com.example.ticketlist.data.local.dataSource.OffersLocalDataSource
import com.example.ticketlist.domain.model.Arrival
import com.example.ticketlist.domain.model.Departure
import com.example.ticketlist.domain.model.Ticket
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
    override fun getTickets(): Flow<List<Ticket>> {
        return flow {
            val stream = context.assets.open("c0464573-5a13-45c9-89f8-717436748b69.json")
            val jsonModified = Json { ignoreUnknownKeys = true }
            val response = jsonModified.decodeFromStream<ResponseDbo>(stream)
            emit(response)
        }
            .map { response -> response.tickets }
            .map { dbos ->
                dbos.map { dbo ->

                    val departure = dbo.departure?.let {
                        Departure(
                            town = it.town,
                            date = it.date,
                            airport = it.airport,
                        )
                    }
                    val arrival = dbo.arrival?.let {
                        Arrival(
                            town = it.town,
                            date = it.date,
                            airport = it.airport,
                        )
                    }
                    Ticket(
                        id = dbo.id,
                        badge = dbo.badge,
                        price = dbo.price?.value,
                        departure = departure,
                        arrival = arrival,
                        hasTransfer = dbo.hasTransfer,
                    )
                }
            }
            .flowOn(dispatchers.io())
    }

}