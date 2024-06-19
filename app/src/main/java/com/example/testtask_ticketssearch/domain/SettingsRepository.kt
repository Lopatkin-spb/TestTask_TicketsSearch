package com.example.testtask_ticketssearch.domain

interface SettingsRepository {

    fun getLastPlaceDeparture(): SearchPlace

    fun saveLastPlaceDeparture(place: SearchPlace)

    fun getLastPlaceArrival(): SearchPlace

    fun saveLastPlaceArrival(place: SearchPlace)

}