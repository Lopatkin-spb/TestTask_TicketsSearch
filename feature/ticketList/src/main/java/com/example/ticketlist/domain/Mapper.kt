package com.example.ticketlist.domain

import com.example.ticketlist.domain.model.Ticket
import com.example.ticketlist.domain.model.TicketUi


fun Ticket.toUi(): TicketUi {
    return TicketUi(
        id = this.id,
        isBadgeVisible = this.badge?.isNotEmpty() == true,
        badgeText = this.badge ?: "",
        price = getModifiedPrice(this),
        departureTime = getTime(this.departure?.date),
        departureAirport = this.departure?.airport ?: "",
        arrivalTime = getTime(this.arrival?.date),
        arrivalAirport = this.arrival?.airport ?: "",
        hasTransfer = this.hasTransfer ?: true,
    )
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
