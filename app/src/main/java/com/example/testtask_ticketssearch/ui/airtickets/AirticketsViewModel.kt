package com.example.testtask_ticketssearch.ui.airtickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AirticketsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is airtickets Fragment"
    }
    val text: LiveData<String> = _text
}