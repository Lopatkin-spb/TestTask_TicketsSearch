package com.example.shorter.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal class ShorterViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is shorter Fragment"
    }
    val text: LiveData<String> = _text
}