package com.example.profile.di

import com.example.profile.presentation.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [ProfileComponent::class])
class ProfileModule {

    @Singleton
    @Provides
    fun provideViewModelFactory(): ViewModelFactory {
        return ViewModelFactory()
    }

}