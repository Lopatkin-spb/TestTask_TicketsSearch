package com.example.testtask_ticketssearch.presentation.airtickets.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask_ticketssearch.data.CoroutineDispatchers
import com.example.testtask_ticketssearch.domain.usecase.GetTicketsOffersUseCase
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    private val dispatchers: CoroutineDispatchers,
    private val getTicketsOffersUseCase: GetTicketsOffersUseCase,
) : ViewModel() {

    companion object {
        private const val LOAD_TICKETS_OFFERS: String = "coroutine_load_tickets_offers"
    }

    private val _uiState = MutableLiveData(SearchUiState())
    val uiState: LiveData<SearchUiState> = _uiState
    private var jobTicketOffers: Job? = null

    fun serchFieldCompleted() {
        val newUiState = _uiState.value?.copy(isArrivalCompleted = true)
        _uiState.value = newUiState
    }

    fun serchFieldEmpty() {
        val newUiState = _uiState.value?.copy(isArrivalCompleted = false)
        _uiState.value = newUiState
    }

    fun getTicketsOffers() {
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

}