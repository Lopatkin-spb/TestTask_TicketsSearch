package com.example.testtask_ticketssearch.data.remote.offers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


//TODO: handle null to dtos


@Serializable
data class ResponseDto(
    @SerialName("tickets_offers") var ticketsOffers: List<TicketOfferDto> = emptyList(),
    @SerialName("offers") var eventsOffers: List<EventOfferDto> = emptyList(),
    @SerialName("tickets") var tickets: List<TicketDto> = emptyList(),
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

@Serializable
data class TicketDto(
    @SerialName("id") val id: Long,
    @SerialName("badge") val badge: String? = null,
    @SerialName("price") val price: PriceDto? = null,
    @SerialName("departure") val departure: DepartureDto? = null,
    @SerialName("arrival") val arrival: ArrivalDto? = null,
    @SerialName("has_transfer") val hasTransfer: Boolean? = null,
)

@Serializable
data class DepartureDto(
    @SerialName("town") val town: String? = null,
    @SerialName("date") val date: String? = null,
    @SerialName("airport") val airport: String? = null,
)

@Serializable
data class ArrivalDto(
    @SerialName("town") val town: String? = null,
    @SerialName("date") val date: String? = null,
    @SerialName("airport") val airport: String? = null,
)