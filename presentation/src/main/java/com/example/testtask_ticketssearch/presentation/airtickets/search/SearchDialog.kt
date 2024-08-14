package com.example.testtask_ticketssearch.presentation.airtickets.search

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.testtask_ticketssearch.R
import com.example.testtask_ticketssearch.domain.model.TicketOfferUi
import com.example.testtask_ticketssearch.presentation.AppActivity
import com.example.testtask_ticketssearch.presentation.NavigationEvent
import com.example.testtask_ticketssearch.presentation.ViewModelFactory
import com.example.testtask_ticketssearch.presentation.airtickets.SearchField
import javax.inject.Inject

//class SearchDialog : BottomSheetDialogFragment() {
//
//    companion object {
//        const val TAG = "com.example.testtask_ticketssearch.SearchDialog"
//        private const val EXTRA_PLACE_DEPARTURE = "com.example.testtask_ticketssearch.EXTRA_PLACE_DEPARTURE"
//        fun newInstance(placeDeparture: String? = null): SearchDialog {
//            val dialog = SearchDialog()
//            val args = Bundle().apply {
//                placeDeparture?.let { data -> putString(EXTRA_PLACE_DEPARTURE, data) }
//            }
//            dialog.arguments = args
//            return dialog
//        }
//    }
//
//    @Inject
//    lateinit var viewModelFactory: ViewModelFactory
//    private lateinit var viewModel: SearchViewModel
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        (activity as AppActivity).presentationComponent.inject(this)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
//
//        viewModel.handle(SearchUserEvent.OnSearchDepartureChange(arguments?.getString(EXTRA_PLACE_DEPARTURE)))
//
//        return ComposeView(requireContext()).apply {
//            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
//            setContent {
//                val uiState by viewModel.uiState.observeAsState()
//
//                uiState?.let { state ->
//                    Screen(
//                        uiState = state,
//                        onEvent = { event -> viewModel.handle(event) },
//                    )
//                }
//            }
//        }
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // This all shit - setup full screen
//        val defaultBottomSheetLayout: FrameLayout? =
//            dialog?.findViewById(com.google.android.material.R.id.design_bottom_sheet)
//
//        defaultBottomSheetLayout?.let { layout ->
//            layout.layoutParams?.height = ViewGroup.LayoutParams.MATCH_PARENT
//
//            val bottomSheetBehavior = BottomSheetBehavior.from(layout)
//            bottomSheetBehavior.also { behavior ->
//                behavior.peekHeight = resources.displayMetrics.heightPixels
//                behavior.state = BottomSheetBehavior.STATE_EXPANDED
//            }
//        }
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//        uiStateListener()
//        viewModel.getTicketsOffers()
//    }
//
//    private fun uiStateListener() {
//        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
//            if (uiState.navigateBack) {
//                dismiss()
//                viewModel.handle(SearchUserEvent.NavigationFinished)
//            } else if (uiState.navigateTo) {
//                dismiss()
//                val places = "${uiState.searchDeparture}-${uiState.searchArrival}"
////                findNavController().navigate(
////                    R.id.action_navigate_from_airtickets_to_ticket_list,
////                    bundleOf(Pair(TicketListFragment.SEARCH_PLACES_KEY, places))
////                )
//                viewModel.handle(SearchUserEvent.NavigationFinished)
//            }
//        }
//    }
//
//}


@Stable
class SearchDaggerContainer {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
}

@Composable
internal fun SearchSheet(
    placeDeparture: String? = "",
    context: Context = LocalContext.current,
    container: SearchDaggerContainer = remember {
        SearchDaggerContainer().also { container ->
            (context as AppActivity).presentationComponent.inject(container)
        }
    },
    viewModel: SearchViewModel = viewModel(factory = container.viewModelFactory),
    navigateToTicketListScreen: (String) -> Unit = {},
    onHide: () -> Unit,
    onNavigationEvent: (app: NavigationEvent) -> Unit,
) {
    viewModel.handle(SearchUserEvent.OnSearchDepartureChange(placeDeparture))
    viewModel.getTicketsOffers()
    val uiState by viewModel.uiState.observeAsState()

    uiState?.let { state ->
        Screen(
            uiState = state,
            onEvent = { event -> viewModel.handle(event) },
        )
        if (state.navigateBack) {
//                dismiss()
            onHide()
            viewModel.handle(SearchUserEvent.NavigationFinished)
        } else if (state.navigateTo) {
//                dismiss()
            val places = "${state.searchDeparture}-${state.searchArrival}"
//            navigateToTicketListScreen(places)
            onNavigationEvent(NavigationEvent.ToTicketList(places))
            viewModel.handle(SearchUserEvent.NavigationFinished)
        }
    }
}


@Composable
private fun Screen(
    modifier: Modifier = Modifier,
    uiState: SearchUiState,
    onEvent: (new: SearchUserEvent) -> Unit,
) {
    MaterialTheme {

        Box(
            modifier = modifier
                .fillMaxSize()
                .background(
                    color = colorResource(R.color.gray_2),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                ),
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(width = 38.dp, height = 5.dp)
                    .background(color = colorResource(R.color.gray_5), shape = RoundedCornerShape(10.dp))
                    .align(Alignment.TopCenter),
            )
            SearchSection(
                modifier = Modifier
                    .padding(start = 16.dp, top = 45.dp, end = 16.dp),
                uiState = uiState,
                onEvent = onEvent,
            )
            ChipsSection(
                modifier = Modifier
                    .padding(top = 188.dp),
                uiState = uiState,
                onEvent = onEvent,
            )
            HintsSection(
                modifier = Modifier
                    .padding(top = 165.dp),
                uiState = uiState,
                onEvent = onEvent,
            )
            RecommendationPlacesSection(
                modifier = Modifier
                    .padding(start = 16.dp, top = 285.dp, end = 16.dp),
                uiState = uiState,
                onEvent = onEvent,
            )
            TicketsOffersSection(
                modifier = Modifier
                    .padding(start = 16.dp, top = 233.dp, end = 16.dp),
                uiState = uiState,
                onEvent = onEvent,
            )
            ButtonNavigateToShowAllTickets(
                modifier = Modifier
                    .padding(start = 16.dp, top = 544.dp, end = 16.dp),
                uiState = uiState,
                onEvent = onEvent,
            )
            ButtonSubscriptionToPrice(
                modifier = Modifier
                    .padding(start = 16.dp, top = 610.dp, end = 16.dp),
                uiState = uiState,
                onEvent = onEvent,
            )
            Snackbar(
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                text = uiState.message,
                textAction = uiState.messageAction,
                onShowed = { onEvent(SearchUserEvent.MessageShowed) },
                onAction = { onEvent(SearchUserEvent.NavigateBack) },
            )
        }

    }
}

@Composable
private fun SearchSection(
    modifier: Modifier = Modifier,
    uiState: SearchUiState,
    onEvent: (new: SearchUserEvent) -> Unit,
) {
    val textDeparture = when (uiState.searchDeparture.isNotEmpty()) {
        true -> uiState.searchDeparture
        false -> stringResource(R.string.text_stub)
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp)
            .background(color = colorResource(R.color.gray_3), shape = RoundedCornerShape(16.dp)),
    ) {
        Icon(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp),
            painter = painterResource(R.drawable.ic_plane_24dp),
            contentDescription = null,
            tint = colorResource(R.color.gray_5),
        )
        Text(
            modifier = Modifier
                .padding(start = 8.dp, top = 16.dp)
                .padding(horizontal = 16.dp)
                .padding(horizontal = 24.dp)
                .padding(end = 20.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 3.dp),
            text = textDeparture,
            maxLines = 1,
            style = TextStyle(
                color = colorResource(R.color.white),
                fontSize = 16.sp,
            ),
        )
        AnimatedVisibilityIcon(
            modifier = Modifier
                .padding(top = 18.dp, end = 16.dp)
                .align(Alignment.TopEnd)
                .padding(end = 16.dp)
                .clickable(onClick = { onEvent(SearchUserEvent.OnSearchPlaceChange) }),
            visible = uiState.isArrivalCompleted,
            painter = painterResource(R.drawable.ic_change_24dp),
            contentDescription = null,
            tint = colorResource(R.color.white),
        )
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(height = 1.dp)
                .background(color = colorResource(R.color.gray_4), shape = RoundedCornerShape(10.dp))
                .align(Alignment.Center),
        )
        Icon(
            modifier = Modifier
                .padding(start = 16.dp, top = 56.dp),
            painter = painterResource(R.drawable.ic_search_24dp),
            contentDescription = null,
            tint = colorResource(R.color.white),
        )
        SearchField(
            modifier = Modifier
                .padding(start = 8.dp, top = 56.dp)
                .padding(horizontal = 16.dp)
                .padding(horizontal = 24.dp)
                .padding(end = 20.dp)
                .fillMaxWidth()
                .padding(vertical = 3.dp),
            hint = stringResource(R.string.action_place_arrival),
            hintColor = colorResource(R.color.gray_6),
            colorCursor = colorResource(R.color.blue),
            textStarting = uiState.searchArrival,
            textStyle = TextStyle(
                color = colorResource(R.color.white),
                fontSize = 16.sp,
            ),
            onTextChange = { text -> onEvent(SearchUserEvent.OnSearchArrivalChange(text)) },
            onKeyboardDone = { onEvent(SearchUserEvent.OnSearchDone) },
        )
        Icon(
            modifier = Modifier
                .padding(top = 56.dp, end = 16.dp)
                .align(Alignment.TopEnd)
                .padding(end = 16.dp)
                .clickable(onClick = { onEvent(SearchUserEvent.OnSearchArrivalChange("")) }),
            painter = painterResource(R.drawable.ic_close_24dp),
            contentDescription = null,
            tint = colorResource(R.color.gray_6),
        )
    }
}

@Composable
private fun ChipsSection(
    modifier: Modifier = Modifier,
    uiState: SearchUiState,
    onEvent: (new: SearchUserEvent) -> Unit,
) {
    val visibleState = remember { mutableStateOf(false) }
    visibleState.value = uiState.isArrivalCompleted

    AnimatedVisibility(
        modifier = Modifier,
        visible = visibleState.value,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Row(
            modifier = modifier
                .wrapContentSize()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(width = 89.dp, height = 33.dp)
                    .background(color = colorResource(R.color.gray_3), shape = RoundedCornerShape(50.dp))
                    .clickable(onClick = { onEvent(SearchUserEvent.CreateSnackbar(text = "TODO: action")) }),
            ) {
                Icon(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .align(Alignment.CenterStart),
                    painter = painterResource(R.drawable.ic_plus_16dp),
                    contentDescription = null,
                    tint = colorResource(R.color.white),
                )
                Text(
                    modifier = Modifier
                        .padding(start = 27.dp)
                        .align(Alignment.CenterStart),
                    text = stringResource(R.string.action_back),
                    maxLines = 1,
                    style = TextStyle(
                        color = colorResource(R.color.white),
                        fontSize = 14.sp,
                    ),
                )
            }
            Box(
                modifier = Modifier
                    .size(width = 89.dp, height = 33.dp)
                    .background(color = colorResource(R.color.gray_3), shape = RoundedCornerShape(50.dp))
                    .clickable(onClick = { onEvent(SearchUserEvent.CreateSnackbar(text = "TODO: action")) }),
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = stringResource(R.string.action_departure),
                    maxLines = 1,
                    style = TextStyle(
                        color = colorResource(R.color.white),
                        fontSize = 14.sp,
                    ),
                )
            }
            Box(
                modifier = Modifier
                    .size(width = 104.dp, height = 33.dp)
                    .background(color = colorResource(R.color.gray_3), shape = RoundedCornerShape(50.dp))
                    .clickable(onClick = { onEvent(SearchUserEvent.CreateSnackbar(text = "TODO: action")) }),
            ) {
                Icon(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterStart),
                    painter = painterResource(R.drawable.ic_person_16dp),
                    contentDescription = null,
                    tint = colorResource(R.color.gray_5),
                )
                Text(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .align(Alignment.CenterEnd),
                    text = stringResource(R.string.action_passenger_class),
                    maxLines = 1,
                    style = TextStyle(
                        color = colorResource(R.color.white),
                        fontSize = 14.sp,
                    ),
                )
            }
            Box(
                modifier = Modifier
                    .size(width = 107.dp, height = 33.dp)
                    .background(color = colorResource(R.color.gray_3), shape = RoundedCornerShape(50.dp))
                    .clickable(onClick = { onEvent(SearchUserEvent.CreateSnackbar(text = "TODO: action")) }),
            ) {
                Icon(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterStart),
                    painter = painterResource(R.drawable.ic_filter_16dp),
                    contentDescription = null,
                    tint = colorResource(R.color.white),
                )
                Text(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .align(Alignment.CenterEnd),
                    text = stringResource(R.string.action_filter),
                    maxLines = 1,
                    style = TextStyle(
                        color = colorResource(R.color.white),
                        fontSize = 14.sp,
                    ),
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}


@Composable
private fun AnimatedVisibilityIcon(
    modifier: Modifier = Modifier,
    visible: Boolean,
    painter: Painter,
    contentDescription: String?,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
) {
    val visibleState = remember { mutableStateOf(visible) }
    visibleState.value = visible

    AnimatedVisibility(
        modifier = modifier,
        visible = visibleState.value,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
            tint = tint,
        )
    }
}

@Composable
private fun HintsSection(
    modifier: Modifier = Modifier,
    uiState: SearchUiState,
    onEvent: (new: SearchUserEvent) -> Unit,
) {
    val textAnywhere = stringResource(R.string.text_anywhere)
    val visibleState = remember { mutableStateOf(true) }
    visibleState.value = !uiState.isArrivalCompleted

    AnimatedVisibility(
        modifier = Modifier,
        visible = visibleState.value,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Row(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            HintView(
                color = colorResource(R.color.light_green),
                painter = painterResource(R.drawable.ic_route_24dp),
                text = stringResource(R.string.text_difficult_route),
                onClick = { onEvent(SearchUserEvent.CreateSnackbar(text = "Stub", action = "На главный экран")) },
            )
            HintView(
                color = colorResource(R.color.blue),
                painter = painterResource(R.drawable.ic_ball_24dp),
                text = textAnywhere,
                onClick = { onEvent(SearchUserEvent.OnSearchArrivalChange(text = textAnywhere, done = true)) },
            )
            HintView(
                color = colorResource(R.color.dark_blue),
                painter = painterResource(R.drawable.ic_calendar_24dp),
                text = stringResource(R.string.text_weekend),
                onClick = { onEvent(SearchUserEvent.CreateSnackbar(text = "Stub", action = "На главный экран")) },
            )
            HintView(
                color = colorResource(R.color.red),
                painter = painterResource(R.drawable.ic_fire_24dp),
                text = stringResource(R.string.text_hot_tickets),
                onClick = { onEvent(SearchUserEvent.CreateSnackbar(text = "Stub", action = "На главный экран")) },
            )
        }
    }
}

@Composable
private fun HintView(
    modifier: Modifier = Modifier,
    color: Color,
    painter: Painter,
    text: String,
    onClick: () -> Unit = {},
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(color = color, shape = RoundedCornerShape(8.dp))
                .clickable(onClick = onClick),
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.Center),
                painter = painter,
                contentDescription = null,
                tint = colorResource(R.color.white),
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 5.dp)
                .padding(vertical = 3.dp)
                .width(80.dp),
            text = text,
            style = TextStyle(
                color = colorResource(R.color.white),
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
            ),
        )
    }
}

@Composable
private fun RecommendationPlacesSection(
    modifier: Modifier = Modifier,
    uiState: SearchUiState,
    onEvent: (new: SearchUserEvent) -> Unit,
) {
    val town1 = stringResource(R.string.text_town_1)
    val town2 = stringResource(R.string.text_town_2)
    val town3 = stringResource(R.string.text_town_3)
    val visibleState = remember { mutableStateOf(true) }
    visibleState.value = !uiState.isArrivalCompleted

    AnimatedVisibility(
        modifier = Modifier,
        visible = visibleState.value,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = colorResource(R.color.gray_3), shape = RoundedCornerShape(16.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            RecommendationPlaceView(
                text = town1,
                url = "file:///android_asset/town_1.png",
                onClick = { onEvent(SearchUserEvent.OnSearchArrivalChange(text = town1, done = true)) },
            )
            RecommendationPlaceView(
                text = town2,
                url = "file:///android_asset/town_2.png",
                onClick = { onEvent(SearchUserEvent.OnSearchArrivalChange(text = town2, done = true)) },
            )
            RecommendationPlaceView(
                text = town3,
                url = "file:///android_asset/town_3.png",
                onClick = { onEvent(SearchUserEvent.OnSearchArrivalChange(text = town3, done = true)) },
            )
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun RecommendationPlaceView(
    modifier: Modifier = Modifier,
    text: String,
    url: String?,
    onClick: () -> Unit = {},
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Box(
            modifier = Modifier
                .padding(top = 8.dp)
                .wrapContentSize()
                .clickable(onClick = onClick),
        ) {
            GlideImage(
                modifier = Modifier
                    .size(width = 40.dp, height = 40.dp)
                    .background(colorResource(R.color.blue), shape = RoundedCornerShape(8.dp)),
                model = url,
                contentDescription = null,
            )
            Column(
                modifier = Modifier
                    .padding(start = 48.dp),
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 3.dp),
                    text = text,
                    maxLines = 1,
                    style = TextStyle(
                        color = colorResource(R.color.white),
                        fontSize = 16.sp,
                    ),
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    text = stringResource(R.string.text_popular_destination),
                    maxLines = 1,
                    style = TextStyle(
                        color = colorResource(R.color.gray_5),
                        fontSize = 14.sp,
                    ),
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(colorResource(R.color.gray_4)),
        )
    }
}

@Composable
private fun TicketsOffersSection(
    modifier: Modifier = Modifier,
    uiState: SearchUiState,
    onEvent: (new: SearchUserEvent) -> Unit,
) {
    val visibleState = remember { mutableStateOf(false) }
    visibleState.value = uiState.isArrivalCompleted

    AnimatedVisibility(
        modifier = Modifier,
        visible = visibleState.value,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = colorResource(R.color.gray_unknown_1), shape = RoundedCornerShape(16.dp))
                .padding(16.dp),
        ) {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(vertical = 3.dp),
                text = stringResource(R.string.header_search_tickets_offers),
                maxLines = 1,
                style = TextStyle(
                    color = colorResource(R.color.white),
                    fontSize = 20.sp,
                ),
            )
            uiState.ticketsOffers[0]?.let { offer ->
                TicketOfferView(
                    color = colorResource(R.color.red),
                    new = offer,
                )
            }
            uiState.ticketsOffers[1]?.let { offer ->
                TicketOfferView(
                    color = colorResource(R.color.blue),
                    new = offer,
                )
            }
            uiState.ticketsOffers[2]?.let { offer ->
                TicketOfferView(
                    color = colorResource(R.color.white),
                    new = offer,
                )
            }
        }
    }
}

@Composable
private fun TicketOfferView(
    modifier: Modifier = Modifier,
    color: Color,
    new: TicketOfferUi? = null,
) {
    val stubText = stringResource(R.string.text_stub)
    val title = when (new?.title.isNullOrEmpty()) {
        false -> new?.title
        true -> stubText
    } ?: stubText
    val price = when (new?.price.isNullOrEmpty()) {
        false -> new?.price
        true -> stubText
    } ?: stubText
    val timeFlights = when (new?.timeFlights.isNullOrEmpty()) {
        false -> new?.timeFlights
        true -> stubText
    } ?: stubText

    Box(
        modifier = modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Box(
            modifier = Modifier
                .padding(top = 8.dp)
                .size(24.dp)
                .background(color = color, shape = CircleShape),
        )
        Column(
            modifier = Modifier
                .padding(start = 32.dp, top = 8.dp, end = 60.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 2.dp),
                text = title,
                maxLines = 1,
                style = TextStyle(
                    color = colorResource(R.color.white),
                    fontSize = 14.sp,
                ),
            )
            Text(
                modifier = Modifier,
                text = timeFlights,
                maxLines = 1,
                style = TextStyle(
                    color = colorResource(R.color.white),
                    fontSize = 14.sp,
                ),
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 8.dp, end = 14.dp)
                .padding(vertical = 2.dp)
                .align(Alignment.TopEnd),
            text = price,
            maxLines = 1,
            style = TextStyle(
                color = colorResource(R.color.blue),
                fontSize = 14.sp,
            ),
        )
        Icon(
            modifier = Modifier
                .padding(top = 10.dp)
                .align(Alignment.TopEnd),
            painter = painterResource(R.drawable.ic_arrow_right_16dp),
            contentDescription = null,
            tint = colorResource(R.color.blue),
        )
        Box(
            modifier = Modifier
                .padding(top = 56.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(colorResource(R.color.gray_4)),
        )
    }
}

@Composable
private fun ButtonNavigateToShowAllTickets(
    modifier: Modifier = Modifier,
    uiState: SearchUiState,
    onEvent: (new: SearchUserEvent) -> Unit,
) {
    val visibleState = remember { mutableStateOf(false) }
    visibleState.value = uiState.isArrivalCompleted

    AnimatedVisibility(
        modifier = Modifier,
        visible = visibleState.value,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(color = colorResource(R.color.blue), shape = RoundedCornerShape(8.dp))
                .clickable(onClick = { onEvent(SearchUserEvent.NavigateTo) })
                .wrapContentHeight(),
            text = stringResource(R.string.action_navigate_to_show_all_tickets),
            maxLines = 1,
            style = TextStyle(
                color = colorResource(R.color.white),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
            ),
        )
    }
}

@Composable
private fun ButtonSubscriptionToPrice(
    modifier: Modifier = Modifier,
    uiState: SearchUiState,
    onEvent: (new: SearchUserEvent) -> Unit,
) {
    val visibleState = remember { mutableStateOf(false) }
    visibleState.value = uiState.isArrivalCompleted

    AnimatedVisibility(
        modifier = Modifier,
        visible = visibleState.value,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(51.dp)
                .background(color = colorResource(R.color.gray_3), shape = RoundedCornerShape(8.dp))
                .clickable(onClick = { onEvent(SearchUserEvent.CreateSnackbar("TODO: action with switcher")) }),
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterStart),
                painter = painterResource(R.drawable.ic_bell_24dp),
                contentDescription = null,
                tint = colorResource(R.color.blue),
            )
            Text(
                modifier = Modifier
                    .padding(start = 47.dp)
                    .wrapContentSize()
                    .align(Alignment.CenterStart),
                text = stringResource(R.string.action_subscription_to_price),
                maxLines = 1,
                style = TextStyle(
                    color = colorResource(R.color.white),
                    fontSize = 16.sp,
                ),
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ScreenPreview() {
    Screen(
        uiState = SearchUiState(
            isArrivalCompleted = true,
            searchDeparture = "hrhgfhgfht",
            ticketsOffers = listOf(
                TicketOfferUi(
                    id = 1,
                    title = "Уральские пельмени",
                    timeFlights = "07:00 09:10 10:00 11:00 12:00 13:00 12:00",
                    price = "34.666",
                ),
                TicketOfferUi(
                    id = 2,
                    title = "Уральские пельмени",
                    timeFlights = "07:00 09:10 10:00 11:00 12:00 13:00 12:00",
                    price = "",
                ),
                TicketOfferUi(
                    id = 3,
                    title = "",
                    timeFlights = "",
                    price = "34.666",
                ),
            ),
        ),
        onEvent = {},
    )
}