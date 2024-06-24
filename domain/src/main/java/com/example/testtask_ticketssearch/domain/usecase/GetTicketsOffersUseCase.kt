package com.example.testtask_ticketssearch.domain.usecase

import com.example.testtask_ticketssearch.domain.repository.OffersRepository
import com.example.testtask_ticketssearch.domain.model.TicketOfferUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTicketsOffersUseCase(private val repository: OffersRepository) {

    fun execute(): Flow<List<TicketOfferUi>> {
        return repository.getTicketsOffers()
            .map { offers ->

                offers.map { offer ->
                    val numbers = offer.price.toString().toCharArray()
                    var priceWithDotsRevers = ""
                    for (index in numbers.indices.reversed()) {
                        if (index % 3 == 0) {
                            priceWithDotsRevers = "$priceWithDotsRevers.${numbers[index]}"
                        } else {
                            priceWithDotsRevers = "$priceWithDotsRevers${numbers[index]}"
                        }
                    }
                    val newPrice = priceWithDotsRevers.reversed().substringBeforeLast(".")
                    TicketOfferUi(
                        id = offer.id,
                        title = offer.title,
                        timeFlights = offer.timeFlights.toString().removeSurrounding("[", "]"),
                        price = newPrice,
                    )
                }
            }
    }

}
