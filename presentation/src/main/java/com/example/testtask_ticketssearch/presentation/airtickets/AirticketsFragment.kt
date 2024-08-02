package com.example.testtask_ticketssearch.presentation.airtickets

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testtask_ticketssearch.R
import com.example.testtask_ticketssearch.domain.model.EventOfferUi
import com.example.testtask_ticketssearch.presentation.AppActivity
import com.example.testtask_ticketssearch.presentation.ViewModelFactory
import com.example.testtask_ticketssearch.presentation.airtickets.search.SearchDialog
import javax.inject.Inject

class AirticketsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: AirticketsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as AppActivity).presentationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this, viewModelFactory).get(AirticketsViewModel::class.java)
        viewModel.handle(AirticketsUserEvent.OnScreenOpen)

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val uiState by viewModel.uiState.observeAsState()

                uiState?.let { state ->
                    Screen(
                        uiState = state,
                        onEvent = { event -> viewModel.handle(event) },
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        navigationListener()
    }

    override fun onPause() {
        super.onPause()
        viewModel.savePlaceDeparture()
    }

    private fun navigationListener() {
        viewModel.uiNavigation.observe(viewLifecycleOwner) { navigation ->
            navigation.toSearchDialog?.let { args ->
                SearchDialog
                    .newInstance(args)
                    .show(requireActivity().supportFragmentManager, SearchDialog.TAG)
                viewModel.handle(AirticketsUserEvent.OnNavigationFinish)
            }
        }
    }

}

@Composable
private fun Screen(
    modifier: Modifier = Modifier,
    uiState: AirticketsUiState,
    onEvent: (new: AirticketsUserEvent) -> Unit,
) {

    MaterialTheme {
        Box(
            modifier = modifier
                .fillMaxSize(),
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 94.dp, top = 60.dp, end = 94.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = stringResource(R.string.title_search),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = colorResource(R.color.gray_8), //TODO: set from theme
                    fontSize = 22.sp,
                ),
            )
            SearchSection(
                modifier = Modifier
                    .padding(start = 15.dp, top = 148.dp, end = 15.dp),
                uiState = uiState,
                onEvent = onEvent,
            )
            Text(
                modifier = Modifier
                    .padding(start = 15.dp, top = 305.dp, end = 15.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = stringResource(R.string.header_offers),
                maxLines = 1,
                style = TextStyle(
                    color = colorResource(R.color.white), //TODO: set from theme
                    fontSize = 22.sp,
                ),
            )
            EventsOffersSection(
                modifier = Modifier
                    .padding(start = 16.dp, top = 350.dp, end = 16.dp),
                new = uiState.eventsOffers,
            )
        }
    }
}

@Composable
private fun SearchSection(
    modifier: Modifier = Modifier,
    uiState: AirticketsUiState,
    onEvent: (new: AirticketsUserEvent) -> Unit,
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = colorResource(R.color.gray_3), shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = colorResource(R.color.gray_4), shape = RoundedCornerShape(16.dp)),
        ) {
            Icon(
                modifier = Modifier
                    .padding(vertical = 33.dp, horizontal = 8.dp)
                    .wrapContentSize(),
                painter = painterResource(R.drawable.ic_search_24dp),
                contentDescription = null,
                tint = colorResource(R.color.black),
            )
        }
        SearchField(
            modifier = Modifier
                .padding(start = 49.dp, top = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .padding(vertical = 3.dp),
            hint = stringResource(R.string.action_place_departure),
            hintColor = colorResource(R.color.gray_6),
            colorCursor = colorResource(R.color.blue),
            textStarting = uiState.placeDeparture?.name,
            textStyle = TextStyle(
                color = colorResource(R.color.white),
                fontSize = 16.sp,
            ),
            onTextChange = { text -> onEvent(AirticketsUserEvent.OnSearchDepartureChange(text)) },
        )
        Box(
            modifier = Modifier
                .padding(start = 48.dp, end = 16.dp)
                .fillMaxWidth()
                .height(1.dp)
                .align(Alignment.Center)
                .background(colorResource(R.color.gray_5)),
        )
        Text(
            modifier = Modifier
                .padding(start = 49.dp, top = 53.dp, end = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable(onClick = { onEvent(AirticketsUserEvent.OnNavigationStart(Destination.SEARCH_DIALOG)) }),
            text = stringResource(R.string.action_place_arrival),
            maxLines = 1,
            style = TextStyle(
                color = colorResource(R.color.gray_6),
                fontSize = 16.sp,
            ),
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ScreenPreview() {
    Screen(
        onEvent = {},
        uiState = AirticketsUiState(
            eventsOffers = listOf(
                EventOfferUi(
                    id = 1,
                    title = "title",
                    town = "town",
                    price = "",
                    url = null,
                ),
                EventOfferUi(
                    id = 2,
                    title = "title",
                    town = "",
                    price = "00999",
                    url = null,
                ),
                EventOfferUi(
                    id = 3,
                    title = "",
                    town = "",
                    price = "",
                    url = null,
                ),
            )
        ),
    )
}

