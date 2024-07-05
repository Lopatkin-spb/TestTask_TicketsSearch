package com.example.testtask_ticketssearch.data.local.offers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


//TODO: handle null to dbos

@Serializable
internal data class ResponseDbo(
    @SerialName("tickets_offers") var ticketsOffers: List<TicketOfferDbo> = emptyList(),
    @SerialName("offers") var eventsOffers: List<EventOfferDbo> = emptyList(),
    @SerialName("tickets") var tickets: List<TicketDbo> = emptyList(),
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

@Serializable
internal data class TicketDbo(
    @SerialName("id") val id: Long,
    @SerialName("badge") val badge: String? = null,
    @SerialName("price") val price: PriceDbo? = null,
    @SerialName("departure") val departure: DepartureDbo? = null,
    @SerialName("arrival") val arrival: ArrivalDbo? = null,
    @SerialName("has_transfer") val hasTransfer: Boolean? = null,
)

@Serializable
internal data class DepartureDbo(
    @SerialName("town") val town: String? = null,
    @SerialName("date") val date: String? = null,
    @SerialName("airport") val airport: String? = null,
)

@Serializable
internal data class ArrivalDbo(
    @SerialName("town") val town: String? = null,
    @SerialName("date") val date: String? = null,
    @SerialName("airport") val airport: String? = null,
)