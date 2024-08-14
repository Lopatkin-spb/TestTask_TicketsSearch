package com.example.testtask_ticketssearch.presentation.airtickets

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask_ticketssearch._interface.CoroutineDispatchers
import com.example.testtask_ticketssearch.domain.model.SearchPlace
import com.example.testtask_ticketssearch.domain.usecase.*
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

internal class AirticketsViewModel(
    private val dispatchers: CoroutineDispatchers,
    private val savePlaceDepartureByLastSearchUseCase: SavePlaceDepartureByLastSearchUseCase,
    private val getPlaceDepartureByLastSearchUseCase: GetPlaceDepartureByLastSearchUseCase,
    private val savePlaceArrivalByLastSearchUseCase: SavePlaceArrivalByLastSearchUseCase,
    private val getPlaceArrivalByLastSearchUseCase: GetPlaceArrivalByLastSearchUseCase,
    private val getEventsOffersUseCase: GetEventsOffersUseCase,
) : ViewModel() {

    companion object {
        private const val LOAD_EVENTS_OFFERS: String = "coroutine_load_events_offers"
    }

    private val _uiState = MutableLiveData(AirticketsUiState())
    val uiState: LiveData<AirticketsUiState> = _uiState
    private var jobEventsOffers: Job? = null

    fun handle(new: AirticketsUserEvent) {
        when (new) {
            is AirticketsUserEvent.OnScreenOpen -> {
                loadEventsOffers()
                getPlaceDeparture()
                getPlaceArrival()
            }

            is AirticketsUserEvent.OnSearchDepartureChange -> setNewPlaceDeparture(new.text)
            is AirticketsUserEvent.OnScreenClose -> savePlaceDeparture()
            is AirticketsUserEvent.OnBottomSheetStateChange -> setBottomSheetVisibility(new.isVisible)
        }
    }

    private fun savePlaceDeparture(text: String) {
        savePlaceDepartureByLastSearchUseCase.execute(SearchPlace(text))
    }

    private fun savePlaceDeparture() {
        _uiState.value?.placeDeparture
            ?.let { searchPlace ->
                savePlaceDepartureByLastSearchUseCase.execute(searchPlace)
            }
            ?: savePlaceDepartureByLastSearchUseCase.execute(SearchPlace(""))
    }

    private fun setNewPlaceDeparture(text: String) {
        val newUiState = _uiState.value?.copy(placeDeparture = SearchPlace(text))
        _uiState.value = newUiState
    }

    private fun getPlaceDeparture() {
        val place = getPlaceDepartureByLastSearchUseCase.execute()
        val newUiState = _uiState.value?.copy(placeDeparture = place)
        _uiState.value = newUiState
    }

    private fun savePlaceArrival(text: String) {
        savePlaceArrivalByLastSearchUseCase.execute(SearchPlace(text))
    }

    private fun getPlaceArrival() {
        val place = getPlaceArrivalByLastSearchUseCase.execute()
        val newUiState = _uiState.value?.copy(placeArrival = place)
        _uiState.value = newUiState
    }

    private fun loadEventsOffers() {
        if (jobEventsOffers != null) return
        jobEventsOffers =
            viewModelScope.launch(dispatchers.main() + CoroutineName(LOAD_EVENTS_OFFERS)) {

                getEventsOffersUseCase.execute()
                    .cancellable()
                    .onEach { newOffers ->
                        val newUiState = _uiState.value?.copy(eventsOffers = newOffers)
                        _uiState.value = newUiState
                    }
                    .catch { cause -> handleThrowable(cause) }
                    .onCompletion { finally -> jobEventsOffers = null }
                    .collect()
            }
    }

    private fun handleThrowable(cause: Throwable) {
        when (cause) {
            is Exception -> Log.w("TAG", "AirticketsViewModel loadEventsOffers: ${cause.message}", cause)
            else -> {
                Log.e("TAG", "AirticketsViewModel loadEventsOffers: ${cause.message}", cause)
                throw cause
            }
        }
    }

    private fun setBottomSheetVisibility(isVisible: Boolean?) {
        val newUiState = _uiState.value?.copy(stateBottomSheet = isVisible)
        _uiState.value = newUiState
    }

}