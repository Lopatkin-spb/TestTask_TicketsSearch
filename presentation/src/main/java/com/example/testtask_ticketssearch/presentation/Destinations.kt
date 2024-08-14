package com.example.testtask_ticketssearch.presentation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.testtask_ticketssearch.R


internal interface AppDestination {
    val uniqueTag: String
    val titleId: Int
}

internal interface BottomNavDestination : AppDestination {
    override val uniqueTag: String
    override val titleId: Int
    val iconId: Int
}

internal val bottomNavDestinations = listOf(
    Airtickets,
    Hotels,
    Shorter,
    Subscriptions,
    Profile,
)

/**
 * Bottom navigation menu destinations
 */
internal data object Airtickets : BottomNavDestination {
    override val uniqueTag: String = "Airtickets"
    override val titleId: Int = R.string.title_airtickets
    override val iconId: Int = R.drawable.ic_air_24dp
}

internal data object Hotels : BottomNavDestination {
    override val uniqueTag: String = "Hotels"
    override val titleId: Int = R.string.title_hotels
    override val iconId: Int = R.drawable.ic_hotel_24dp
}

internal data object Shorter : BottomNavDestination {
    override val uniqueTag: String = "Shorter"
    override val titleId: Int = R.string.title_shorter
    override val iconId: Int = R.drawable.ic_location_24dp
}

internal data object Subscriptions : BottomNavDestination {
    override val uniqueTag: String = "Subscriptions"
    override val titleId: Int = R.string.title_subscriptions
    override val iconId: Int = R.drawable.ic_bell_24dp
}

internal data object Profile : BottomNavDestination {
    override val uniqueTag: String = "Profile"
    override val titleId: Int = R.string.title_profile
    override val iconId: Int = R.drawable.ic_person_24dp
}

/**
 * Other destinations
 */
internal data object TicketList : AppDestination {
    override val uniqueTag: String = "TicketList"
    override val titleId: Int = R.string.title_ticket_list
    const val SEARCH_PLACES_ARG = "com.example.testtask_ticketssearch.SEARCH_PLACES_ARG"
    val uniqueTagWithArgs: String = "$uniqueTag/{$SEARCH_PLACES_ARG}"
    val arguments = listOf(
        navArgument(SEARCH_PLACES_ARG) { type = NavType.StringType },
    )
}

