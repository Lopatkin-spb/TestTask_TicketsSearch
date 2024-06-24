package com.example.testtask_ticketssearch.domain.repository

import com.example.testtask_ticketssearch.domain.model.SearchPlace

interface SettingsRepository {

    fun getLastPlaceDeparture(): SearchPlace

    fun saveLastPlaceDeparture(place: SearchPlace)

    fun getLastPlaceArrival(): SearchPlace

    fun saveLastPlaceArrival(place: SearchPlace)

}