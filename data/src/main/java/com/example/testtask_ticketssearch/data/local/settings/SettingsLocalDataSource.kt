package com.example.testtask_ticketssearch.data.local.settings

import android.content.Context
import android.content.SharedPreferences
import com.example.testtask_ticketssearch.data.local.dataSource.SettingsDataSource
import com.example.testtask_ticketssearch.domain.SearchPlace

class SettingsLocalDataSource(
    private val context: Context,
) : SettingsDataSource {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)

    companion object {
        private const val PREFERENCE_FILE_KEY = "com.example.testtask_ticketssearch.PREFERENCE_FILE_KEY"
        private const val LAST_PLACE_DEPARTURE_KEY = "com.example.testtask_ticketssearch.LAST_PLACE_DEPARTURE_KEY"
        private const val LAST_PLACE_ARRIVAL_KEY = "com.example.testtask_ticketssearch.LAST_PLACE_ARRIVAL_KEY"
        private const val DEFAULT = ""
    }

    override fun getLastPlaceDeparture(): SearchPlace {
        val text = preferences.getString(LAST_PLACE_DEPARTURE_KEY, DEFAULT) ?: DEFAULT
        return SearchPlace(name = text)
    }

    override fun saveLastPlaceDeparture(place: SearchPlace) {
        preferences
            .edit()
            .putString(LAST_PLACE_DEPARTURE_KEY, place.name)
            .apply()
    }

    override fun getLastPlaceArrival(): SearchPlace {
        val text = preferences.getString(LAST_PLACE_ARRIVAL_KEY, DEFAULT) ?: DEFAULT
        return SearchPlace(name = text)
    }

    override fun saveLastPlaceArrival(place: SearchPlace) {
        preferences
            .edit()
            .putString(LAST_PLACE_ARRIVAL_KEY, place.name)
            .apply()
    }

}