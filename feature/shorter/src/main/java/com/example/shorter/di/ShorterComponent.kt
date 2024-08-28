package com.example.shorter.di

import com.example.shorter.presentation.ShorterFragment
import dagger.Subcomponent

@Subcomponent
interface ShorterComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ShorterComponent
    }

    fun inject(fragment: ShorterFragment)

}