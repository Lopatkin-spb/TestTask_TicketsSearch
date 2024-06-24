package com.example.testtask_ticketssearch.presentation.airtickets.search

import com.example.testtask_ticketssearch.domain.model.TicketOfferUi

data class SearchUiState(
    val isArrivalCompleted: Boolean = false,
    val ticketsOffers: List<TicketOfferUi> = emptyList(),
)
