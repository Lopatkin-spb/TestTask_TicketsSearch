package com.example.testtask_ticketssearch.domain

import com.example.testtask_ticketssearch.domain.model.*


fun EventOffer.toUi(): EventOfferUi {
    val numbers = this.price.toString().toCharArray()
    var priceWithDotsRevers = ""
    for (index in numbers.indices.reversed()) {
        if (index % 3 == 0) {
            priceWithDotsRevers = "$priceWithDotsRevers ${numbers[index]}"
        } else {
            priceWithDotsRevers = "$priceWithDotsRevers${numbers[index]}"
        }
    }
    val newPrice = priceWithDotsRevers.reversed().substringBeforeLast(" ")

    return EventOfferUi(
        id = this.id,
        title = this.title,
        town = this.town,
        price = newPrice,
        url = this.url
    )
}


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

fun TicketOffer.toUi(): TicketOfferUi {
    val numbers = this.price.toString().toCharArray()
    var priceWithDotsRevers = ""
    for (index in numbers.indices.reversed()) {
        if (index % 3 == 0) {
            priceWithDotsRevers = "$priceWithDotsRevers.${numbers[index]}"
        } else {
            priceWithDotsRevers = "$priceWithDotsRevers${numbers[index]}"
        }
    }
    val newPrice = priceWithDotsRevers.reversed().substringBeforeLast(".")

    return TicketOfferUi(
        id = this.id,
        title = this.title,
        timeFlights = this.timeFlights.toString().removeSurrounding("[", "]"),
        price = newPrice,
    )
}
