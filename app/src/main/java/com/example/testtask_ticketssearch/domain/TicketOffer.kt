package com.example.testtask_ticketssearch.domain

data class TicketOffer(
    val id: Long,
    val title: String,
    val timeFlights: List<String>,
    val price: Long,
)
