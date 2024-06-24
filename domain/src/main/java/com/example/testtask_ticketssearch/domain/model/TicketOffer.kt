package com.example.testtask_ticketssearch.domain.model

data class TicketOffer(
    val id: Long,
    val title: String,
    val timeFlights: List<String>,
    val price: Long,
)
