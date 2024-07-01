package com.example.testtask_ticketssearch.data.remote.offers

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDto(
    @SerializedName("tickets_offers") @SerialName("tickets_offers") var ticketsOffers: List<TicketOfferDto> = emptyList(),
    @SerializedName("offers") @SerialName("offers") var eventsOffers: List<EventOfferDto> = emptyList(),
)
