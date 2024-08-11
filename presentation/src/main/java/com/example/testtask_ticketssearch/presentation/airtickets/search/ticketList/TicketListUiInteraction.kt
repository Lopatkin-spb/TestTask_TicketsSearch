package com.example.testtask_ticketssearch.presentation.airtickets.search.ticketList

import com.example.testtask_ticketssearch.domain.model.TicketUi

internal data class TicketListUiState(
    val tickets: List<TicketUi> = emptyList(),
    val searchPlaces: String = "",
    val message: String? = null,
    val messageAction: String? = null,
)


internal sealed interface TicketListUserEvent {
    data object OnScreenOpen : TicketListUserEvent
    data class OnSearchPlacesChange(val text: String?) : TicketListUserEvent
    data class CreateSnackbar(val text: String, val action: String? = null) : TicketListUserEvent
    data object MessageShowed : TicketListUserEvent
}
