package com.example.testtask_ticketssearch.di

import android.content.Context
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

}