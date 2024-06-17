package com.example.testtask_ticketssearch.ui.shorter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShorterViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is shorter Fragment"
    }
    val text: LiveData<String> = _text
}