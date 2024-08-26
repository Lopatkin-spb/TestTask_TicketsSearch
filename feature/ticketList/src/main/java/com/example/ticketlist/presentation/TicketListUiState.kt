package com.example.ticketlist.presentation

import com.example.ticketlist.domain.model.TicketUi

internal data class TicketListUiState(
    val tickets: List<TicketUi> = emptyList(),
)
