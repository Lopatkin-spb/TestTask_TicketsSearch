package com.example.testtask_ticketssearch.presentation.airtickets.search.ticketList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask_ticketssearch._interface.CoroutineDispatchers
import com.example.testtask_ticketssearch.domain.usecase.GetTicketListBySearchPlacesUseCase
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

internal class TicketListViewModel(
    private val dispatchers: CoroutineDispatchers,
    private val getTicketListBySearchPlacesUseCase: GetTicketListBySearchPlacesUseCase,
) : ViewModel() {

    companion object {
        private const val LOAD_TICKET_LIST: String = "coroutine_load_ticket_list"
    }

    private val _uiState = MutableLiveData(TicketListUiState())
    val uiState: LiveData<TicketListUiState> = _uiState
    private var jobLoadTicketList: Job? = null

    private fun loadTicketList() {
        if (jobLoadTicketList != null) return
        jobLoadTicketList = viewModelScope.launch(dispatchers.main() + CoroutineName(LOAD_TICKET_LIST)) {

            getTicketListBySearchPlacesUseCase.execute()
                .cancellable()
                .onEach { list ->
                    val newUiState = _uiState.value?.copy(tickets = list)
                    _uiState.value = newUiState
                }
                .catch { cause -> handleThrowable(cause) }
                .onCompletion { finally -> jobLoadTicketList = null }
                .collect()
        }
    }

    private fun handleThrowable(cause: Throwable) {
        when (cause) {
            is Exception -> Log.w("TAG", "TicketListViewModel loadTicketsList: ${cause.message}", cause)
            else -> {
                Log.e("TAG", "TicketListViewModel loadTicketsList: ${cause.message}", cause)
                throw cause
            }
        }
    }

    fun handle(new: TicketListUserEvent) {
        when (new) {
            is TicketListUserEvent.OnScreenOpen -> {
                loadTicketList()
            }

            is TicketListUserEvent.OnSearchPlacesChange -> setSearchPlaces(new.text)
            is TicketListUserEvent.CreateSnackbar -> createMessage(new.text, new.action)
            is TicketListUserEvent.MessageShowed -> resetMessage()
        }
    }

    private fun setSearchPlaces(text: String?) {
        text?.let { new ->
            val newUiState = _uiState.value?.copy(searchPlaces = new)
            _uiState.value = newUiState
        }
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