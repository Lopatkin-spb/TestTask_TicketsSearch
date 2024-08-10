package com.example.testtask_ticketssearch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.testtask_ticketssearch.R
import com.example.testtask_ticketssearch.di.PresentationComponent
import com.example.testtask_ticketssearch.di.PresentationComponentProvider

class AppActivity : ComponentActivity() {

    lateinit var presentationComponent: PresentationComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        presentationComponent = (applicationContext as PresentationComponentProvider).providePresentationComponent()
        presentationComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContent {
            Screen()
        }
    }
}

@Composable
private fun Screen(
    modifier: Modifier = Modifier,
) {
    MaterialTheme(
        colors = darkColors(
            background = colorResource(R.color.black),
        ),
    ) {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = { BottomNavBar(navController) },
        ) { innerPadding ->
            BottomNavHost(
                modifier = modifier
                    .padding(innerPadding),
                navController = navController,
            )
        }
    }
}

@Composable
private fun BottomNavBar(
    navController: NavHostController,
) {
    BottomNavigation(
        backgroundColor = colorResource(R.color.black),
    ) {
        val currentBackstack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackstack?.destination

        bottomNavDestinations.forEach { destination ->

            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(destination.iconId),
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = stringResource(destination.titleId),
                    )
                },
                selectedContentColor = colorResource(R.color.blue),
                unselectedContentColor = colorResource(R.color.gray_6),
                alwaysShowLabel = false,
                selected = currentDestination?.hierarchy?.any { it.route == destination.uniqueTag } == true,
                onClick = { navController.navigateSingleTopTo(route = destination.uniqueTag) },
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ScreenPreview() {
    Screen()
}