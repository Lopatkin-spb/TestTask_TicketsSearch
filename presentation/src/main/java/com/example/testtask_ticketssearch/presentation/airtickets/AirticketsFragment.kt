package com.example.testtask_ticketssearch.presentation.airtickets

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.testtask_ticketssearch.R
import com.example.testtask_ticketssearch.domain.model.EventOfferUi
import com.example.testtask_ticketssearch.domain.model.SearchPlace
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

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val uiState by viewModel.uiState.observeAsState()
                uiState?.let { state ->
                    Screen(
                        uiState = state,
                        onSearchDepartureChange = { text -> onSearchDepartureChange(text) },
                        onSearchArrivalClick = { text -> onSearchArrivalClick(text) },
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadEventsOffers()
        viewModel.getPlaceDeparture()
        viewModel.getPlaceArrival()
    }

    override fun onPause() {
        super.onPause()
        viewModel.savePlaceDeparture()
    }

    private fun onSearchDepartureChange(text: String) {
        viewModel.setNewPlaceDeparture(text)
    }

    private fun onSearchArrivalClick(text: String) {
        SearchDialog
            .newInstance(text)
            .show(requireActivity().supportFragmentManager, SearchDialog.TAG)
    }

}

@Composable
private fun Screen(
    modifier: Modifier = Modifier,
    uiState: AirticketsUiState,
    onSearchDepartureChange: (new: String) -> Unit,
    onSearchArrivalClick: (new: String) -> Unit,
) {
    MaterialTheme {

        Box(modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.padding(start = 94.dp, top = 60.dp, end = 94.dp).fillMaxWidth().wrapContentHeight(),
                text = stringResource(R.string.title_search),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = colorResource(R.color.gray_8), //TODO: set from theme
                    fontSize = 22.sp,
                )
            )
            AirticketsSearch(
                modifier = Modifier.padding(start = 15.dp, top = 148.dp, end = 15.dp),
                placeDeparture = uiState.placeDeparture,
                onPlaceDepartureChange = onSearchDepartureChange,
                onPlaceArrivalClick = onSearchArrivalClick,
            )
            Text(
                modifier = Modifier.padding(start = 15.dp, top = 305.dp, end = 15.dp).fillMaxWidth()
                    .wrapContentHeight(),
                text = stringResource(R.string.header_offers),
                maxLines = 1,
                style = TextStyle(
                    color = colorResource(R.color.white), //TODO: set from theme
                    fontSize = 22.sp,
                )
            )
            EventOfferList(
                modifier = Modifier.padding(start = 16.dp, top = 350.dp, end = 16.dp),
                new = uiState.eventsOffers,
            )
        }
    }
}

@Composable
private fun AirticketsSearch(
    modifier: Modifier = Modifier,
    placeDeparture: SearchPlace?,
    onPlaceDepartureChange: (new: String) -> Unit,
    onPlaceArrivalClick: (new: String) -> Unit,
) {

    Box(
        modifier.fillMaxWidth().wrapContentHeight()
            .background(colorResource(R.color.gray_3), shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
    ) {
        Box(
            Modifier.fillMaxWidth().wrapContentHeight()
                .background(colorResource(R.color.gray_4), shape = RoundedCornerShape(16.dp)),
        ) {
            Icon(
                modifier = Modifier.padding(vertical = 33.dp, horizontal = 8.dp).wrapContentSize(),
                painter = painterResource(R.drawable.ic_search_24dp),
                contentDescription = null,
                tint = colorResource(R.color.black),
            )
        }
        SearchBar(
            modifier = Modifier.padding(start = 49.dp, top = 16.dp, end = 16.dp).fillMaxWidth()
                .padding(vertical = 3.dp),
            hint = stringResource(R.string.action_place_departure),
            hintColor = colorResource(R.color.gray_6),
            colorCursor = colorResource(R.color.blue),
            textStarting = placeDeparture?.name,
            textStyle = TextStyle(
                color = colorResource(R.color.white),
                fontSize = 16.sp,
            ),
            onTextChange = onPlaceDepartureChange,
        )
        Box(
            modifier = Modifier.padding(start = 48.dp, end = 16.dp).fillMaxWidth().height(1.dp)
                .align(Alignment.Center)
                .background(colorResource(R.color.gray_5)),
        )
        Text(
            modifier = Modifier.padding(start = 49.dp, top = 53.dp, end = 16.dp)
                .fillMaxWidth().wrapContentHeight()
                .clickable(onClick = { onPlaceArrivalClick(placeDeparture?.name ?: "") }),
            text = stringResource(R.string.action_place_arrival),
            maxLines = 1,
            style = TextStyle(color = colorResource(R.color.gray_6), fontSize = 16.sp),
        )
    }
}

@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
    textStarting: String? = null,
    textStyle: TextStyle,
    hint: String,
    hintColor: Color,
    colorCursor: Color,
    onTextChange: (new: String) -> Unit = {},
) {

    val focusManager = LocalFocusManager.current
    var currentText by remember { mutableStateOf("") }
    if (textStarting != null) {
        currentText = textStarting
    }
    val customTextSelectionColors = TextSelectionColors(
        handleColor = colorCursor,
        backgroundColor = colorCursor,
    )

    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        BasicTextField(
            modifier = modifier,
            value = currentText,
            onValueChange = { newText ->
                currentText = newText
                onTextChange(newText)
            },
            singleLine = true,
            textStyle = textStyle,
            cursorBrush = SolidColor(colorCursor),
            decorationBox = { innerTextFieldHint ->
                if (currentText.isEmpty()) {
                    Text(
                        text = hint,
                        style = TextStyle(
                            color = hintColor,
                            fontSize = textStyle.fontSize,
                        ),
                    )
                }
                innerTextFieldHint()
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() },
            ),
        )
    }
}

@Composable
private fun EventOfferList(
    modifier: Modifier = Modifier,
    new: List<EventOfferUi>,
) {
    var eventsOffers by remember { mutableStateOf(emptyList<EventOfferUi>()) }
    eventsOffers = new
    val stubText = stringResource(R.string.text_stub)

    LazyRow(
        modifier = modifier.wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(67.dp),
    ) {
        items(
            items = eventsOffers,
            key = { offer -> offer.id },
        ) { offer ->

            val title = when (offer.title.isNotEmpty()) {
                true -> offer.title
                false -> stubText
            }
            val town = when (offer.town.isNotEmpty()) {
                true -> offer.town
                false -> stubText
            }
            val price = when (offer.price.isNotEmpty()) {
                true -> "от ${offer.price} ₽"
                false -> stubText
            }
            EventOfferItem(
                title = title,
                town = town,
                price = price,
                url = offer.url,
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun EventOfferItem(
    modifier: Modifier = Modifier,
    title: String,
    town: String,
    price: String,
    url: String?,
) {

    Column(
        modifier = modifier.wrapContentSize().height(213.16.dp),
    ) {
        GlideImage(
            modifier = Modifier.width(132.dp).height(133.16.dp)
                .background(colorResource(R.color.gray_4), shape = RoundedCornerShape(16.dp)),
            model = url,
            contentDescription = null,
        )
        Text(
            modifier = Modifier.padding(top = 7.dp).fillMaxWidth(),
            text = title,
            maxLines = 1,
            style = TextStyle(color = colorResource(R.color.white), fontSize = 16.sp),
        )
        Text(
            modifier = Modifier.padding(top = 5.dp).fillMaxWidth(),
            text = town,
            maxLines = 1,
            style = TextStyle(color = colorResource(R.color.white), fontSize = 14.sp),
        )
        Row(
            modifier = Modifier.wrapContentSize().padding(top = 5.dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_air_24dp),
                contentDescription = null,
                tint = colorResource(R.color.gray_6),
            )
            Text(
                modifier = Modifier.wrapContentSize().align(Alignment.CenterVertically),
                text = price,
                maxLines = 1,
                style = TextStyle(color = colorResource(R.color.white), fontSize = 14.sp),
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ScreenPreview() {
    Screen(
        onSearchArrivalClick = {},
        onSearchDepartureChange = {},
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

