package com.example.testtask_ticketssearch.domain.usecase

import com.example.testtask_ticketssearch.domain.model.Ticket
import com.example.testtask_ticketssearch.domain.model.TicketUi
import com.example.testtask_ticketssearch.domain.repository.OffersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTicketListBySearchPlacesUseCase(private val repository: OffersRepository) {

    fun execute(): Flow<List<TicketUi>> {
        return repository.getTickets()
            .map { tickets ->
                tickets.map { ticket ->
                    TicketUi(
                        id = ticket.id,
                        isBadgeVisible = ticket.badge?.isNotEmpty() == true,
                        badgeText = ticket.badge ?: "",
                        price = getModifiedPrice(ticket),
                        departureTime = getTime(ticket.departure?.date),
                        departureAirport = ticket.departure?.airport ?: "",
                        arrivalTime = getTime(ticket.arrival?.date),
                        arrivalAirport = ticket.arrival?.airport ?: "",
                        hasTransfer = ticket.hasTransfer ?: true,
                    )
                }
            }
    }

    private fun getModifiedPrice(ticket: Ticket): String {
        val numbers = ticket.price.toString().toCharArray()
        var priceWithEmptysRevers = ""
        for (index in numbers.indices.reversed()) {
            if (index % 3 == 0) {
                priceWithEmptysRevers = "$priceWithEmptysRevers ${numbers[index]}"
            } else {
                priceWithEmptysRevers = "$priceWithEmptysRevers${numbers[index]}"
            }
        }
        val newPrice = priceWithEmptysRevers.reversed().substringBeforeLast(" ")
        return newPrice
    }

    private fun getTime(date: String?): String {
        return date?.let {
            if (it.length > 6) {
                return it.substring(it.length - 8, it.length - 3)
            } else {
                return ""
            }
        } ?: ""
    }

}
