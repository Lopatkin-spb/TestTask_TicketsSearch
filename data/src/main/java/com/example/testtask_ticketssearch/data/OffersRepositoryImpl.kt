package com.example.testtask_ticketssearch.data

import android.util.Log
import com.example.testtask_ticketssearch.data.local.dataSource.OffersLocalDataSource
import com.example.testtask_ticketssearch.data.remote.dataSource.OffersRemoteDataSource
import com.example.testtask_ticketssearch.domain.model.EventOffer
import com.example.testtask_ticketssearch.domain.model.TicketOffer
import com.example.testtask_ticketssearch.domain.repository.OffersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first

internal class OffersRepositoryImpl(
    private val offersRemoteDataSource: OffersRemoteDataSource,
    private val offersLocalDataSource: OffersLocalDataSource,
) : OffersRepository {

    override fun getEventsOffers(): Flow<List<EventOffer>> {
        return offersRemoteDataSource.getEventsOffers()
            .catch { cause ->
                if (cause is Exception) {
                    Log.w("TAG", "OffersRepositoryImpl getEventsOffers: ${cause.message}", cause)
                    emit(offersLocalDataSource.getEventsOffers().first())
                } else {
                    throw cause
                }
            }
    }

    override fun getTicketsOffers(): Flow<List<TicketOffer>> {
        return offersRemoteDataSource.getTicketsOffers()
            .catch { cause ->
                if (cause is Exception) {
                    Log.w("TAG", "OffersRepositoryImpl getTicketsOffers: ${cause.message}", cause)
                    emit(offersLocalDataSource.getTicketsOffers().first())
                } else {
                    throw cause
                }
            }
    }

}

