package com.example.testtask_ticketssearch.presentation.airtickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask_ticketssearch.domain.SearchPlace
import com.example.testtask_ticketssearch.domain.usecase.GetPlaceArrivalByLastSearchUseCase
import com.example.testtask_ticketssearch.domain.usecase.GetPlaceDepartureByLastSearchUseCase
import com.example.testtask_ticketssearch.domain.usecase.SavePlaceArrivalByLastSearchUseCase
import com.example.testtask_ticketssearch.domain.usecase.SavePlaceDepartureByLastSearchUseCase

class AirticketsViewModel(
    private val savePlaceDepartureByLastSearchUseCase: SavePlaceDepartureByLastSearchUseCase,
    private val getPlaceDepartureByLastSearchUseCase: GetPlaceDepartureByLastSearchUseCase,
    private val savePlaceArrivalByLastSearchUseCase: SavePlaceArrivalByLastSearchUseCase,
    private val getPlaceArrivalByLastSearchUseCase: GetPlaceArrivalByLastSearchUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData(AirticketsUiState())
    val uiState: LiveData<AirticketsUiState> = _uiState

    fun savePlaceDeparture(text: String) {
        savePlaceDepartureByLastSearchUseCase.execute(SearchPlace(text))
    }

    fun getPlaceDeparture() {
        val place = getPlaceDepartureByLastSearchUseCase.execute()
        val newUiState = _uiState.value?.copy(placeDeparture = place)
        _uiState.value = newUiState
    }

    fun savePlaceArrival(text: String) {
        savePlaceArrivalByLastSearchUseCase.execute(SearchPlace(text))
    }

    fun getPlaceArrival() {
        val place = getPlaceArrivalByLastSearchUseCase.execute()
        val newUiState = _uiState.value?.copy(placeArrival = place)
        _uiState.value = newUiState
    }

    fun loadListOffers() {
        val newUiState = _uiState.value?.copy(
            listOf(
                OfferUi(1, "Die Antwoord", "Будапешт", "5000"),
                OfferUi(2, "Socrat&Lera", "Санкт-Петербург", "1999"),
                OfferUi(3, "Лампабикт", "Москва", "2430"),
                OfferUi(3, "Lllllll", "Москва", "3453"),
                OfferUi(3, "Лампабикт", "Москва", "5566"),
                OfferUi(3, "Лампабикт", "Москва", "12"),
                OfferUi(3, "Лампабикт", "Москва", "3"),
                OfferUi(3, "Лампабикт", "Москва", "989"),
            )
        )
        _uiState.value = newUiState
    }

}

/**
 *
 * 5 - лента с предложениями. Просмотр ленты осуществляется посредством свайпа.
 * Исходное положение ленты, как на макете. Данные берутся из ответа  сервера. См. JSON.
 * Данные берутся из массива offers. Каждый отображаемый объект некликабелен.
 * Объекты отображаются в том порядке, в котором приходят в json. Заголовок берется из поля title,
 * название города - из поля town, цена - из поля price.value. Цена должна отображаться согласно макету
 * - с указанными иконками, в цене должно быть разделение между разрядами через пробел.
 * Изображение хардкодится по id объекта согласно таблице:
 *
 * https://run.mocky.io/v3/214a1713-bac0-4853-907c-a1dfc3cd05fd
 * https://run.mocky.io/v3/214a1713-bac0-4853-907c-a1dfc3cd05fd
 * возможно отсюда
 *
 *
 * исходник
 * https://drive.google.com/file/d/1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav/view?pli=1
 *
 *
 * или из жсона
 *
 */