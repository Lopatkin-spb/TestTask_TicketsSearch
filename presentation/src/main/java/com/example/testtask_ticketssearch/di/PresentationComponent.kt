package com.example.testtask_ticketssearch.di

import com.example.testtask_ticketssearch.presentation.AppActivity
import com.example.testtask_ticketssearch.presentation.airtickets.AirticketsFragment
import com.example.testtask_ticketssearch.presentation.airtickets.search.SearchDialog
import com.example.testtask_ticketssearch.presentation.airtickets.search.ticketList.TicketListFragment
import com.example.testtask_ticketssearch.presentation.hotels.HotelsFragment
import com.example.testtask_ticketssearch.presentation.profile.ProfileFragment
import com.example.testtask_ticketssearch.presentation.shorter.ShorterFragment
import com.example.testtask_ticketssearch.presentation.subscriptions.SubscriptionsFragment
import dagger.Subcomponent


@Subcomponent
interface PresentationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): PresentationComponent
    }

    fun inject(activity: AppActivity)

    fun inject(fragment: AirticketsFragment)

    fun inject(fragment: TicketListFragment)

    fun inject(dialog: SearchDialog)

    fun inject(fragment: HotelsFragment)

    fun inject(fragment: ProfileFragment)

    fun inject(fragment: ShorterFragment)

    fun inject(fragment: SubscriptionsFragment)

}