package com.example.testtask_ticketssearch.presentation.airtickets.search.ticketList

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testtask_ticketssearch.R
import com.example.testtask_ticketssearch.domain.model.TicketUi
import com.example.testtask_ticketssearch.presentation.*
import com.example.testtask_ticketssearch.presentation.NavigationEvent
import com.example.testtask_ticketssearch.presentation.OnLifecycleScreen
import com.example.testtask_ticketssearch.presentation.airtickets.search.Snackbar
import javax.inject.Inject


@Stable
class TicketListDaggerContainer {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
}

@Composable
internal fun TicketListScreen(
    searchPlaces: String? = "",
    context: Context = LocalContext.current,
    container: TicketListDaggerContainer = remember {
        TicketListDaggerContainer().also { container ->
            (context as AppActivity).presentationComponent.inject(container)
        }
    },
    viewModel: TicketListViewModel = viewModel(factory = container.viewModelFactory),
    onNavigationEvent: (app: NavigationEvent) -> Unit,
) {
    val uiState by viewModel.uiState.observeAsState()

    OnLifecycleScreen(
        onStart = {
            viewModel.handle(TicketListUserEvent.OnScreenOpen)
            viewModel.handle(TicketListUserEvent.OnSearchPlacesChange(searchPlaces))
        }
    )

    uiState?.let { state ->
        Screen(
            uiState = state,
            onEvent = { event -> viewModel.handle(event) },
            onNavigationEvent = onNavigationEvent,
        )
    }
}

@Composable
private fun Screen(
    modifier: Modifier = Modifier,
    uiState: TicketListUiState,
    onEvent: (screen: TicketListUserEvent) -> Unit,
    onNavigationEvent: (app: NavigationEvent) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        SearchDetails(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            uiState = uiState,
            onNavigationEvent = onNavigationEvent,
        )
        TicketsList(
            modifier = Modifier
                .padding(start = 16.dp, top = 106.dp, end = 16.dp),
            new = uiState.tickets,
            onEvent = onEvent,
        )
        Box(
            modifier = Modifier
                .background(color = colorResource(R.color.blue), shape = RoundedCornerShape(50.dp))
                .height(37.dp)
                .wrapContentWidth()
                .clickable(onClick = {})
                .padding(horizontal = 16.dp)
                .align(Alignment.BottomCenter),
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = stringResource(R.string.text_bottom_stub),
                style = TextStyle(
                    color = colorResource(R.color.white),
                    fontSize = 14.sp,
                ),
            )
        }
        Snackbar(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            text = uiState.message,
            onShowed = { onEvent(TicketListUserEvent.MessageShowed) },
        )
    }
}

@Composable
private fun SearchDetails(
    modifier: Modifier = Modifier,
    uiState: TicketListUiState,
    onNavigationEvent: (app: NavigationEvent) -> Unit,
) {
    val stubText = stringResource(R.string.text_stub)
    val places = when (uiState.searchPlaces.isNotEmpty()) {
        true -> uiState.searchPlaces
        false -> stubText
    }

    Box(
        modifier = modifier
            .background(colorResource(R.color.gray_2))
            .fillMaxWidth()
            .height(56.dp),
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable(onClick = { onNavigationEvent(NavigationEvent.OnBack) }),
            painter = painterResource(R.drawable.ic_arrow_left_24dp),
            contentDescription = null,
            tint = colorResource(R.color.blue),
        )
        Text(
            modifier = Modifier
                .padding(start = 32.dp, top = 8.dp, end = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            text = places,
            maxLines = 1,
            style = TextStyle(
                color = colorResource(R.color.white),
                fontSize = 16.sp,
            ),
        )
        Text(
            modifier = Modifier
                .padding(start = 32.dp, top = 28.dp, end = 16.dp, bottom = 8.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.BottomCenter),
//            text = "23 февраля, 1 пассажир",
            text = stubText,
            maxLines = 1,
            style = TextStyle(
                color = colorResource(R.color.gray_6),
                fontSize = 14.sp,
            ),
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ScreenPreview() {
    TestTask_TicketsSearch_Theme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Screen(
                onEvent = {},
                onNavigationEvent = {},
                uiState = TicketListUiState(
                    searchPlaces = "Moskow-CPb",
                    tickets = listOf(
                        TicketUi(
                            id = 1,
                            isBadgeVisible = false,
                            badgeText = "String",
                            price = "0099",
                            departureTime = "12:55",
                            departureAirport = "RTY",
                            arrivalTime = "56:11",
                            arrivalAirport = "CV",
                            hasTransfer = false,
                        ),
                        TicketUi(
                            id = 2,
                            isBadgeVisible = true,
                            badgeText = "String",
                            price = "0099",
                            departureTime = "12:55",
                            departureAirport = "RTY",
                            arrivalTime = "56:11",
                            arrivalAirport = "CV",
                            hasTransfer = true,
                        ),
                        TicketUi(
                            id = 3,
                            isBadgeVisible = false,
                            badgeText = "",
                            price = "",
                            departureTime = "",
                            departureAirport = "",
                            arrivalTime = "",
                            arrivalAirport = "",
                            hasTransfer = false,
                        ),
                    )
                ),
            )
        }
    }
}