package com.example.testtask_ticketssearch.presentation.airtickets

import com.example.testtask_ticketssearch.domain.model.EventOfferUi
import com.example.testtask_ticketssearch.domain.model.SearchPlace

internal data class AirticketsUiState(
    val eventsOffers: List<EventOfferUi> = emptyList(),
    val placeDeparture: SearchPlace? = null,
    val placeArrival: SearchPlace? = null,
)

internal data class AirticketsUiNavigation(
    val toSearchDialog: String? = null,
)

internal sealed interface AirticketsUserEvent {

    data object OnScreenOpen : AirticketsUserEvent

    data class OnSearchDepartureChange(val text: String) : AirticketsUserEvent

    data class OnNavigationStart(val point: Destination) : AirticketsUserEvent

    data object OnNavigationFinish : AirticketsUserEvent

}

internal enum class Destination {
    SEARCH_DIALOG
}