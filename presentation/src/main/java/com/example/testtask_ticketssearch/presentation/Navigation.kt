package com.example.testtask_ticketssearch.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testtask_ticketssearch.presentation.airtickets.AirticketsScreen
import com.example.testtask_ticketssearch.presentation.airtickets.search.SearchScreen
import com.example.testtask_ticketssearch.presentation.airtickets.search.ticketList.TicketListScreen
import com.example.testtask_ticketssearch.presentation.hotels.HotelsScreen
import com.example.testtask_ticketssearch.presentation.profile.ProfileScreen
import com.example.testtask_ticketssearch.presentation.shorter.ShorterScreen
import com.example.testtask_ticketssearch.presentation.subscriptions.SubscriptionsScreen


@Composable
internal fun BottomNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Airtickets.uniqueTag,
    ) {
        composable(route = Airtickets.uniqueTag) {
            AirticketsScreen(
                navigateToSearchScreen = { placesDeparture ->
                    // After updated navigation with fix empty args - join to 1
                    if (placesDeparture.isNotEmpty()) {
                        navController.navigateSingleTopTo(Search.uniqueTag, placesDeparture)
                    } else {
                        navController.navigateSingleTopTo(Search.uniqueTag)
                    }
                }
            )
        }
        composable(route = Hotels.uniqueTag) {
            HotelsScreen()
        }
        composable(route = Shorter.uniqueTag) {
            ShorterScreen()
        }
        composable(route = Subscriptions.uniqueTag) {
            SubscriptionsScreen()
        }
        composable(route = Profile.uniqueTag) {
            ProfileScreen()
        }
        // After updated navigation with fix empty args - join to 1 composable
        composable(
            route = Search.uniqueTagWithOptionalArgs,
            arguments = Search.arguments,
        ) { currentBackstack ->
            SearchScreen(
                placeDeparture = currentBackstack.arguments?.getString(Search.PLACE_DEPARTURE_ARG),
                navigateToTicketListScreen = { searchPlaces ->
                    navController.navigateSingleTopTo(TicketList.uniqueTag, searchPlaces)
                },
                onNavigationBack = { navController.popBackStack() },
            )
        }
        // After updated navigation with fix empty args - join to 1 composable
        composable(
            route = Search.uniqueTagWithArgs,
            arguments = Search.arguments,
        ) { currentBackstack ->
            SearchScreen(
                placeDeparture = currentBackstack.arguments?.getString(Search.PLACE_DEPARTURE_ARG),
                navigateToTicketListScreen = { searchPlaces ->
                    navController.navigateSingleTopTo(TicketList.uniqueTag, searchPlaces)
                },
                onNavigationBack = { navController.popBackStack() },
            )
        }
        composable(
            route = TicketList.uniqueTagWithArgs,
            arguments = TicketList.arguments,
        ) { currentBackstack ->
            val searchPlaces = currentBackstack.arguments?.getString(TicketList.SEARCH_PLACES_ARG)
            TicketListScreen(
                searchPlaces = searchPlaces,
                onNavigationBack = { navController.popBackStack() },
            )
        }
    }
}

internal fun NavHostController.navigateSingleTopTo(route: String) {
    this.navigate(route = route) {

        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
            saveState = false
        }

        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true

        // Restore state when reselecting a previously selected item
        restoreState = false
    }
}

internal fun NavHostController.navigateSingleTopTo(route: String, arg: String) {
    this.navigateSingleTopTo("$route/$arg")
}