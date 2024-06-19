package com.example.testtask_ticketssearch.presentation.airtickets

import com.example.testtask_ticketssearch.domain.SearchPlace

data class AirticketsUiState(
    val offers: List<OfferUi> = emptyList(),
    val placeDeparture: SearchPlace? = null,
    val placeArrival: SearchPlace? = null,
)
