package com.example.testtask_ticketssearch.data.remote.offers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseDto(
    @SerialName("tickets_offers") var ticketsOffers: List<TicketOfferDto> = emptyList(),
    @SerialName("offers") var eventsOffers: List<EventOfferDto> = emptyList(),
)

@Serializable
data class TicketOfferDto(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String,
    @SerialName("time_range") val timeRange: List<String>,
    @SerialName("price") val price: PriceDto,
)

@Serializable
data class EventOfferDto(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String,
    @SerialName("town") val town: String,
    @SerialName("price") val price: PriceDto,
)

@Serializable
data class PriceDto(
    @SerialName("value") val value: Long,
)