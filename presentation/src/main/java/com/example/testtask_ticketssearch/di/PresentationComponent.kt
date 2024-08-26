package com.example.testtask_ticketssearch.di

import com.example.testtask_ticketssearch.presentation.AppActivity
import com.example.testtask_ticketssearch.presentation.airtickets.AirticketsFragment
import com.example.testtask_ticketssearch.presentation.airtickets.search.SearchDialog
import com.example.testtask_ticketssearch.presentation.hotels.HotelsFragment
import com.example.testtask_ticketssearch.presentation.shorter.ShorterFragment
import dagger.Subcomponent


@Subcomponent
interface PresentationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): PresentationComponent
    }

    fun inject(activity: AppActivity)

    fun inject(fragment: AirticketsFragment)

    fun inject(dialog: SearchDialog)

    fun inject(fragment: HotelsFragment)

    fun inject(fragment: ShorterFragment)

}