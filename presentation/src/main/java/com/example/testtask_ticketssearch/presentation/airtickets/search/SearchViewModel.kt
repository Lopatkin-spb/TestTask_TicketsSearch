package com.example.testtask_ticketssearch.presentation.airtickets.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask_ticketssearch._interface.CoroutineDispatchers
import com.example.testtask_ticketssearch.domain.usecase.GetTicketsOffersUseCase
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

internal class SearchViewModel(
    private val dispatchers: CoroutineDispatchers,
    private val getTicketsOffersUseCase: GetTicketsOffersUseCase,
) : ViewModel() {

    companion object {
        private const val LOAD_TICKETS_OFFERS: String = "coroutine_load_tickets_offers"
    }

    private val _uiState = MutableLiveData(SearchUiState())
    val uiState: LiveData<SearchUiState> = _uiState
    private var jobTicketOffers: Job? = null

    private fun searchFieldCompleted() {
        _uiState.value?.let { state ->
            if (state.searchArrival.isNotEmpty()) {
                val newUiState = _uiState.value?.copy(isArrivalCompleted = true)
                _uiState.value = newUiState
            }
        }
    }

    private fun searchFieldEmpty() {
        val newUiState = _uiState.value?.copy(isArrivalCompleted = false)
        _uiState.value = newUiState
    }

    private fun getTicketsOffers() {
        if (jobTicketOffers != null) return
        jobTicketOffers = viewModelScope.launch(dispatchers.main() + CoroutineName(LOAD_TICKETS_OFFERS)) {

            getTicketsOffersUseCase.execute()
                .cancellable()
                .onEach { offers ->
                    val newUiState = _uiState.value?.copy(ticketsOffers = offers)
                    _uiState.value = newUiState
                }
                .catch { cause -> handleThrowable(cause) }
                .onCompletion { finally -> jobTicketOffers = null }
                .collect()
        }
    }

    private fun handleThrowable(cause: Throwable) {
        when (cause) {
            is Exception -> Log.w("TAG", "SearchViewModel getTicketOffers: ${cause.message}", cause)
            else -> {
                Log.e("TAG", "SearchViewModel getTicketOffers: ${cause.message}", cause)
                throw cause
            }
        }
    }

    fun handle(new: SearchUserEvent) {
        when (new) {
            is SearchUserEvent.OnScreenOpen -> getTicketsOffers()
            is SearchUserEvent.OnSearchDepartureChange -> setSearchDeparture(new.text)
            is SearchUserEvent.OnSearchArrivalChange -> setSearchArrival(new.text, new.done)
            is SearchUserEvent.OnSearchPlacesChange -> swapSearchPlaces()
            is SearchUserEvent.OnSearchDone -> searchFieldCompleted()
            is SearchUserEvent.CreateSnackbar -> createMessage(new.text, new.action)
            is SearchUserEvent.MessageShowed -> resetMessage()
        }
    }

    private fun setSearchArrival(new: String, done: Boolean) {
        val newUiState = _uiState.value?.copy(searchArrival = new)
        _uiState.value = newUiState
        if (done) {
            searchFieldCompleted()
        } else {
            searchFieldEmpty()
        }
    }

    private fun setSearchDeparture(text: String?) {
        if (!text.isNullOrEmpty()) {
            val newUiState = _uiState.value?.copy(searchDeparture = text)
            _uiState.value = newUiState
        }
    }

    private fun swapSearchPlaces() {
        val departure = _uiState.value?.searchDeparture
            ?: ""
        val arrival = _uiState.value?.searchArrival
            ?: ""
        val newUiState = _uiState.value?.copy(searchDeparture = arrival, searchArrival = departure)
        _uiState.value = newUiState
    }

    private fun createMessage(text: String, action: String?) {
        val newUiState = _uiState.value?.copy(message = text, messageAction = action)
        _uiState.value = newUiState
    }

    private fun resetMessage() {
        val newUiState = _uiState.value?.copy(message = null, messageAction = null)
        _uiState.value = newUiState
    }

}