package com.example.ticketlist.di

import com.example.ticketlist.presentation.TicketListFragment
import dagger.Subcomponent

@Subcomponent
interface TicketListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): TicketListComponent
    }

    fun inject(fragment: TicketListFragment)

}