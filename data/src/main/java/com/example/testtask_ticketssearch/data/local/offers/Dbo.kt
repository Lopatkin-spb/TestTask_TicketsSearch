package com.example.testtask_ticketssearch.data.local.offers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
internal data class ResponseDbo(
    @SerialName("tickets_offers") var ticketsOffers: List<TicketOfferDbo> = emptyList(),
    @SerialName("offers") var eventsOffers: List<EventOfferDbo> = emptyList(),
)

@Serializable
internal data class TicketOfferDbo(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String,
    @SerialName("time_range") val timeRange: List<String>,
    @SerialName("price") val price: PriceDbo,
)

@Serializable
internal data class EventOfferDbo(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String,
    @SerialName("town") val town: String,
    @SerialName("price") val price: PriceDbo,
)

@Serializable
internal data class PriceDbo(
    @SerialName("value") val value: Long,
)