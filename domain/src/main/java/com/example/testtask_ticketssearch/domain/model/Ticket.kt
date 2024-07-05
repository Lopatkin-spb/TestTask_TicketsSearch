package com.example.testtask_ticketssearch.domain.model


data class Ticket(
    val id: Long,
    val badge: String?,
    val price: Long?,
    val departure: Departure?,
    val arrival: Arrival?,
    val hasTransfer: Boolean?,
)

data class Departure(
    val town: String?,
    val date: String?,
    val airport: String?,
)

data class Arrival(
    val town: String?,
    val date: String?,
    val airport: String?,
)

data class TicketUi(
    val id: Long,
    val isBadgeVisible: Boolean,
    val badgeText: String,
    val price: String,
    val departureTime: String,
    val departureAirport: String,
    val arrivalTime: String,
    val arrivalAirport: String,
    val hasTransfer: Boolean,
)