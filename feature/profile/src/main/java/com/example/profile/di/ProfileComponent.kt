package com.example.profile.di

import com.example.profile.presentation.ProfileFragment
import dagger.Subcomponent

@Subcomponent
interface ProfileComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ProfileComponent
    }

    fun inject(fragment: ProfileFragment)

}