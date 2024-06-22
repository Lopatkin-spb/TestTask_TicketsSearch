package com.example.testtask_ticketssearch.presentation.airtickets.search

data class SearchUiState(
    val isArrivalCompleted: Boolean = false,
    val ticketsOffers: List<TicketOfferUi> = emptyList(),
)
