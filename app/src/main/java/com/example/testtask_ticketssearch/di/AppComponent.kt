package com.example.testtask_ticketssearch.di

import android.content.Context
import com.example.profile.di.ProfileComponent
import com.example.profile.di.ProfileModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        DomainModule::class,
        PresentationModule::class,
        InterfaceModule::class,
        ProfileModule::class,
    ]
)
interface AppComponent {

    fun presentationComponent(): PresentationComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent

    }

    fun profileComponent(): ProfileComponent.Factory

}