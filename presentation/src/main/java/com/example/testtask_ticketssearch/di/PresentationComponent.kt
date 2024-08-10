package com.example.testtask_ticketssearch.di

import com.example.testtask_ticketssearch.presentation.AppActivity
import com.example.testtask_ticketssearch.presentation.airtickets.AirticketsDaggerContainer
import com.example.testtask_ticketssearch.presentation.airtickets.search.SearchDaggerContainer
import com.example.testtask_ticketssearch.presentation.airtickets.search.ticketList.TicketListDaggerContainer
import com.example.testtask_ticketssearch.presentation.hotels.HotelsDaggerContainer
import com.example.testtask_ticketssearch.presentation.profile.ProfileDaggerContainer
import com.example.testtask_ticketssearch.presentation.shorter.ShorterDaggerContainer
import com.example.testtask_ticketssearch.presentation.subscriptions.SubscriptionsDaggerContainer
import dagger.Subcomponent


@Subcomponent
interface PresentationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): PresentationComponent
    }

    fun inject(activity: AppActivity)

    fun inject(container: AirticketsDaggerContainer)

    fun inject(container: TicketListDaggerContainer)

    fun inject(container: SearchDaggerContainer)

    fun inject(container: HotelsDaggerContainer)

    fun inject(container: ProfileDaggerContainer)

    fun inject(container: ShorterDaggerContainer)

    fun inject(container: SubscriptionsDaggerContainer)

}