package com.example.testtask_ticketssearch.domain.usecase

import com.example.testtask_ticketssearch.domain.model.EventOfferUi
import com.example.testtask_ticketssearch.domain.repository.OffersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetEventsOffersUseCase(private val repository: OffersRepository) {

    fun execute(): Flow<List<EventOfferUi>> {
        return repository.getEventsOffers()
            .map { offers ->

                offers.map { offer ->
                    val numbers = offer.price.toString().toCharArray()
                    var priceWithDotsRevers = ""
                    for (index in numbers.indices.reversed()) {
                        if (index % 3 == 0) {
                            priceWithDotsRevers = "$priceWithDotsRevers ${numbers[index]}"
                        } else {
                            priceWithDotsRevers = "$priceWithDotsRevers${numbers[index]}"
                        }
                    }
                    val newPrice = priceWithDotsRevers.reversed().substringBeforeLast(" ")
                    EventOfferUi(
                        title = offer.title,
                        town = offer.town,
                        price = newPrice,
                    )
                }
            }
    }

}
