package com.example.shorter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShorterViewModel::class.java)) {
            return ShorterViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }

}