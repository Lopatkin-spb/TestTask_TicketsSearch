package com.example.testtask_ticketssearch.presentation.airtickets.search.ticketList

import com.example.testtask_ticketssearch.domain.model.TicketUi

internal data class TicketListUiState(
    val tickets: List<TicketUi> = emptyList(),
)
