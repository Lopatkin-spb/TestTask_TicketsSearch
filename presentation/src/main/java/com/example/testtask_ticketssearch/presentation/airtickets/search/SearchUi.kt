package com.example.testtask_ticketssearch.presentation.airtickets.search

import com.example.testtask_ticketssearch.domain.model.TicketOfferUi

internal data class SearchUiState(
    val isArrivalCompleted: Boolean = false,
    val ticketsOffers: List<TicketOfferUi> = emptyList(),
    val searchDeparture: String = "",
    val searchArrival: String = "",
    val message: String? = null,
    val messageAction: String? = null,
    val navigateBack: Boolean = false,
    val navigateTo: Boolean = false,
)

internal sealed interface SearchUserEvent {
    data class OnSearchDepartureChange(val text: String?) : SearchUserEvent
    data class OnSearchArrivalChange(val text: String, val done: Boolean = false) : SearchUserEvent
    data object OnSearchPlaceChange : SearchUserEvent
    data object OnSearchDone : SearchUserEvent
    data class CreateSnackbar(val text: String, val action: String? = null) : SearchUserEvent
    data object MessageShowed : SearchUserEvent
    data object NavigateBack : SearchUserEvent
    data object NavigationFinished : SearchUserEvent
    data object NavigateTo : SearchUserEvent

}