package com.example.testtask_ticketssearch.data

import com.example.testtask_ticketssearch.data.local.dataSource.SettingsDataSource
import com.example.testtask_ticketssearch.domain.model.SearchPlace
import com.example.testtask_ticketssearch.domain.repository.SettingsRepository

class SettingsRepositoryImpl(
    private val settingsDataSource: SettingsDataSource,
) : SettingsRepository {

    override fun getLastPlaceDeparture(): SearchPlace {
        return settingsDataSource.getLastPlaceDeparture()
    }

    override fun saveLastPlaceDeparture(place: SearchPlace) {
        settingsDataSource.saveLastPlaceDeparture(place)
    }

    override fun getLastPlaceArrival(): SearchPlace {
        return settingsDataSource.getLastPlaceArrival()
    }

    override fun saveLastPlaceArrival(place: SearchPlace) {
        settingsDataSource.saveLastPlaceArrival(place)
    }

}