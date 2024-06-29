package com.example.testtask_ticketssearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask_ticketssearch._interface.CoroutineDispatchers
import com.example.testtask_ticketssearch.domain.usecase.*
import com.example.testtask_ticketssearch.presentation.airtickets.AirticketsViewModel
import com.example.testtask_ticketssearch.presentation.airtickets.search.SearchViewModel
import com.example.testtask_ticketssearch.presentation.hotels.HotelsViewModel
import com.example.testtask_ticketssearch.presentation.profile.ProfileViewModel
import com.example.testtask_ticketssearch.presentation.shorter.ShorterViewModel
import com.example.testtask_ticketssearch.presentation.subscriptions.SubscriptionsViewModel


class ViewModelFactory(
    private val dispatchers: CoroutineDispatchers,
    private val savePlaceDepartureByLastSearchUseCase: SavePlaceDepartureByLastSearchUseCase,
    private val getPlaceDepartureByLastSearchUseCase: GetPlaceDepartureByLastSearchUseCase,
    private val savePlaceArrivalByLastSearchUseCase: SavePlaceArrivalByLastSearchUseCase,
    private val getPlaceArrivalByLastSearchUseCase: GetPlaceArrivalByLastSearchUseCase,
    private val getTicketsOffersUseCase: GetTicketsOffersUseCase,
    private val getEventsOffersUseCase: GetEventsOffersUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AirticketsViewModel::class.java)) {
            return AirticketsViewModel(
                dispatchers = dispatchers,
                getPlaceDepartureByLastSearchUseCase = getPlaceDepartureByLastSearchUseCase,
                savePlaceDepartureByLastSearchUseCase = savePlaceDepartureByLastSearchUseCase,
                getPlaceArrivalByLastSearchUseCase = getPlaceArrivalByLastSearchUseCase,
                savePlaceArrivalByLastSearchUseCase = savePlaceArrivalByLastSearchUseCase,
                getEventsOffersUseCase = getEventsOffersUseCase,
            ) as T
        } else if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(
                dispatchers = dispatchers,
                getTicketsOffersUseCase = getTicketsOffersUseCase,
            ) as T
        } else if (modelClass.isAssignableFrom(HotelsViewModel::class.java)) {
            return HotelsViewModel() as T
        } else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel() as T
        } else if (modelClass.isAssignableFrom(ShorterViewModel::class.java)) {
            return ShorterViewModel() as T
        } else if (modelClass.isAssignableFrom(SubscriptionsViewModel::class.java)) {
            return SubscriptionsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }

}