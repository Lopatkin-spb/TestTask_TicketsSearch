package com.example.testtask_ticketssearch.data.local.dataSource

import com.example.testtask_ticketssearch.domain.model.SearchPlace

interface SettingsDataSource {

    fun getLastPlaceDeparture(): SearchPlace

    fun saveLastPlaceDeparture(place: SearchPlace)

    fun getLastPlaceArrival(): SearchPlace

    fun saveLastPlaceArrival(place: SearchPlace)

}