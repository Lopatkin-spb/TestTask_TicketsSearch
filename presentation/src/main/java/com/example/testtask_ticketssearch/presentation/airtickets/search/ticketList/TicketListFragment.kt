package com.example.testtask_ticketssearch.presentation.airtickets.search.ticketList

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.testtask_ticketssearch.R
import com.example.testtask_ticketssearch.domain.model.TicketUi
import com.example.testtask_ticketssearch.presentation.AppActivity
import com.example.testtask_ticketssearch.presentation.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class TicketListFragment : Fragment() {

    companion object {
        const val SEARCH_PLACES_KEY = "com.example.testtask_ticketssearch.SEARCH_PLACES_KEY"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: TicketListViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as AppActivity).presentationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, viewModelFactory).get(TicketListViewModel::class.java)

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val uiState by viewModel.uiState.observeAsState()
                uiState?.let { state ->
                    Screen(
                        uiState = state,
                        searchDetails = arguments?.getString(SEARCH_PLACES_KEY),
                        onSearchBackClick = { onSearchBackClick() },
                        onTicketItemClickTest = { value -> onTicketItemClick(value) },
                        onTicketPriceClickTest = { value -> onTicketPriceClick(value) },
                        onTicketBadgeClickTest = { value -> onTicketBadgeClick(value) },
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadTicketList()
    }

    private fun onSearchBackClick() {
        findNavController().popBackStack()
    }

    private fun onTicketItemClick(model: TicketUi) {
        view?.let { fragment ->
            showMessage(fragment, "onTicketItemClick -> ${model.id}")
        }
    }

    private fun onTicketPriceClick(model: TicketUi) {
        view?.let { fragment ->
            showMessage(fragment, "onTicketPriceClick -> ${model.price}")
        }
    }

    private fun onTicketBadgeClick(model: TicketUi) {
        view?.let { fragment ->
            showMessage(fragment, "onTicketBadgeClick -> ${model.badgeText}")
        }
    }

    private fun showMessage(view: View, text: String) {
        Snackbar //TODO: correct color themes
            .make(view, text, Snackbar.LENGTH_SHORT)
            .show()
    }

}

@SuppressLint("PrivateResource")
@Composable
private fun Screen(
    modifier: Modifier = Modifier,
    searchDetails: String?,
    uiState: TicketListUiState,
    onSearchBackClick: () -> Unit,
    onTicketItemClickTest: (TicketUi) -> Unit,
    onTicketPriceClickTest: (TicketUi) -> Unit,
    onTicketBadgeClickTest: (TicketUi) -> Unit,
) {
    MaterialTheme {

        Box(
            modifier = modifier
                .padding(bottom = dimensionResource(com.google.android.material.R.dimen.design_bottom_navigation_height))
                .fillMaxSize(),
        ) {
            SearchDetails(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                details = searchDetails,
                onSearchBackClick = onSearchBackClick,
            )
            TicketsList(
                modifier = Modifier
                    .padding(start = 16.dp, top = 106.dp, end = 16.dp),
                new = uiState.tickets,
                onTicketItemClick = onTicketItemClickTest,
                onTicketPriceClick = onTicketPriceClickTest,
                onTicketBadgeClick = onTicketBadgeClickTest,
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
        }
    }
}

@Composable
private fun SearchDetails(
    modifier: Modifier = Modifier,
    details: String?,
    onSearchBackClick: () -> Unit,
) {
    val stubText = stringResource(R.string.text_stub)
    val detailsText = when (details.isNullOrEmpty()) {
        false -> details
        true -> stubText
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
                .clickable(onClick = onSearchBackClick),
            painter = painterResource(R.drawable.ic_arrow_left_24dp),
            contentDescription = null,
            tint = colorResource(R.color.blue),
        )
        Text(
            modifier = Modifier
                .padding(start = 32.dp, top = 8.dp, end = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            text = detailsText,
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
    Screen(
        onSearchBackClick = {},
        onTicketBadgeClickTest = {},
        onTicketPriceClickTest = {},
        searchDetails = "",
        onTicketItemClickTest = {},
        uiState = TicketListUiState(
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