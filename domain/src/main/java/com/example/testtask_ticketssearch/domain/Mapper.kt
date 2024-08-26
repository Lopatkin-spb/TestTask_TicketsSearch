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
