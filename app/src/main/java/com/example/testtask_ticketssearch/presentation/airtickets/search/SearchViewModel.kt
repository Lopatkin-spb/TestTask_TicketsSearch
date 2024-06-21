package com.example.testtask_ticketssearch.presentation.airtickets.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    private val _uiState = MutableLiveData(SearchUiState())
    val uiState: LiveData<SearchUiState> = _uiState

    fun serchFieldCompleted() {
        val newUiState = _uiState.value?.copy(isArrivalCompleted = true)
        _uiState.value = newUiState
    }

    fun serchFieldEmpty() {
        val newUiState = _uiState.value?.copy(isArrivalCompleted = false)
        _uiState.value = newUiState
    }

}