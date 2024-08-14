package com.example.testtask_ticketssearch.presentation.airtickets

import com.example.testtask_ticketssearch.domain.model.EventOfferUi
import com.example.testtask_ticketssearch.domain.model.SearchPlace

internal data class AirticketsUiState(
    val eventsOffers: List<EventOfferUi> = emptyList(),
    val placeDeparture: SearchPlace? = null,
    val placeArrival: SearchPlace? = null,
    val stateBottomSheet: Boolean? = null,
)

internal sealed interface AirticketsUserEvent {
    data object OnScreenOpen : AirticketsUserEvent
    data class OnSearchDepartureChange(val text: String) : AirticketsUserEvent
    data object OnScreenClose : AirticketsUserEvent
    data class OnBottomSheetStateChange(val isVisible: Boolean?) : AirticketsUserEvent
}