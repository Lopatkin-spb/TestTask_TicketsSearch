package com.example.testtask_ticketssearch.domain.model


data class EventOffer(
    val id: Long,
    val title: String,
    val town: String,
    val price: Long,
    val url: String? = null,
)

data class EventOfferUi(
    val id: Long,
    val title: String,
    val town: String,
    val price: String,
    val url: String?,
)

data class TicketOffer(
    val id: Long,
    val title: String,
    val timeFlights: List<String>,
    val price: Long,
)

data class TicketOfferUi(
    val id: Long,
    val title: String,
    val timeFlights: String,
    val price: String,
)