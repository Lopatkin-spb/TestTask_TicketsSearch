package com.example.testtask_ticketssearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask_ticketssearch.presentation.airtickets.AirticketsViewModel
import com.example.testtask_ticketssearch.presentation.hotels.HotelsViewModel
import com.example.testtask_ticketssearch.presentation.profile.ProfileViewModel
import com.example.testtask_ticketssearch.presentation.shorter.ShorterFragment
import com.example.testtask_ticketssearch.presentation.shorter.ShorterViewModel
import com.example.testtask_ticketssearch.presentation.subscriptions.SubscriptionsViewModel

class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AirticketsViewModel::class.java)) {
            return AirticketsViewModel() as T
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