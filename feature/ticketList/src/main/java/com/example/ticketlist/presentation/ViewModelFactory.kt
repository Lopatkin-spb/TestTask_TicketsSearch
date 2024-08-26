package com.example.ticketlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask_ticketssearch._interface.CoroutineDispatchers
import com.example.ticketlist.domain.usecase.GetTicketListBySearchPlacesUseCase


class ViewModelFactory(
    private val dispatchers: CoroutineDispatchers,
    private val getTicketListBySearchPlacesUseCase: GetTicketListBySearchPlacesUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TicketListViewModel::class.java)) {
            return TicketListViewModel(
                dispatchers = dispatchers,
                getTicketListBySearchPlacesUseCase = getTicketListBySearchPlacesUseCase,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }

}