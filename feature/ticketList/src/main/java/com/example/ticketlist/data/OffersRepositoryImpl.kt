package com.example.ticketlist.data

import android.util.Log
import com.example.ticketlist.data.local.dataSource.OffersLocalDataSource
import com.example.ticketlist.data.remote.dataSource.OffersRemoteDataSource
import com.example.ticketlist.domain.model.Ticket
import com.example.ticketlist.domain.repository.OffersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first

internal class OffersRepositoryImpl(
    private val offersRemoteDataSource: OffersRemoteDataSource,
    private val offersLocalDataSource: OffersLocalDataSource,
) : OffersRepository {

    override fun getTickets(): Flow<List<Ticket>> {
        return offersRemoteDataSource.getTickets()
            .catch { cause ->
                if (cause is Exception) {
                    Log.w("TAG", "OffersRepositoryImpl getTickets: ${cause.message}", cause)
                    emit(offersLocalDataSource.getTickets().first())
                } else {
                    throw cause
                }
            }
    }

}

