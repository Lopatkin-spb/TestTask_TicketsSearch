package com.example.testtask_ticketssearch.presentation.airtickets.search.ticketList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testtask_ticketssearch.R
import com.example.testtask_ticketssearch.domain.model.TicketUi


@Composable
internal fun TicketsList(
    modifier: Modifier = Modifier,
    new: List<TicketUi>,
    onEvent: (screen: TicketListUserEvent) -> Unit,
) {
    val stubText = stringResource(R.string.text_stub)

    LazyColumn(
        modifier = modifier
            .wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(
            items = new,
            key = { ticket -> ticket.id },
        ) { ticket ->

            val price = when (ticket.price.isNotEmpty()) {
                true -> stringResource(R.string.text_ticket_price, ticket.price)
                false -> stubText
            }
            val departureTime = when (ticket.departureTime.isNotEmpty()) {
                true -> ticket.departureTime
                false -> stubText
            }
            val departureAirport = when (ticket.departureAirport.isNotEmpty()) {
                true -> ticket.departureAirport
                false -> stubText
            }
            val arrivalTime = when (ticket.arrivalTime.isNotEmpty()) {
                true -> ticket.arrivalTime
                false -> stubText
            }
            val arrivalAirport = when (ticket.arrivalAirport.isNotEmpty()) {
                true -> ticket.arrivalAirport
                false -> stubText
            }
            val transfer = when (!ticket.hasTransfer) {
                true -> stringResource(R.string.text_travel_time_without_transfer)
                false -> stringResource(R.string.text_travel_time)
            }
            TicketItem(
                data = TicketUi(
                    id = ticket.id,
                    price = price,
                    departureTime = departureTime,
                    departureAirport = departureAirport,
                    arrivalTime = arrivalTime,
                    arrivalAirport = arrivalAirport,
                    isBadgeVisible = ticket.isBadgeVisible,
                    badgeText = ticket.badgeText,
                    hasTransfer = ticket.hasTransfer,
                    transfer = transfer,
                ),
                onEvent = onEvent,
            )
        }
    }
}

@Composable
private fun TicketItem(
    modifier: Modifier = Modifier,
    data: TicketUi,
    onEvent: (screen: TicketListUserEvent) -> Unit,
) {
    var paddingBackground = 0
    if (data.isBadgeVisible) paddingBackground = 8

    Box(
        modifier = modifier
            .wrapContentSize(),
    ) {
        Box(
            modifier = Modifier
                .padding(top = paddingBackground.dp)
                .background(color = colorResource(R.color.gray_unknown_1), shape = RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable(onClick = {
                    onEvent(TicketListUserEvent.CreateSnackbar("onTicketItemClick -> ${data.id}"))
                })
                .padding(bottom = 16.dp),
        ) {
            Text(
                text = data.price,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    .clickable(onClick = {
                        onEvent(TicketListUserEvent.CreateSnackbar("onTicketPriceClick -> ${data.price}"))
                    }),
                maxLines = 1,
                style = TextStyle(
                    color = colorResource(R.color.white),
                    fontSize = 22.sp,
                ),
            )
            DetailsBoard(
                modifier = Modifier
                    .padding(start = 16.dp, top = 58.dp),
                departureTime = data.departureTime,
                departureAirport = data.departureAirport,
                arrivalTime = data.arrivalTime,
                arrivalAirport = data.arrivalAirport,
            )
            Text(
                modifier = Modifier
                    .padding(start = 153.dp, top = 58.dp, end = 8.dp)
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                text = data.transfer,
                maxLines = 1,
                style = TextStyle(
                    color = colorResource(R.color.white),
                    fontSize = 14.sp,
                ),
            )
        }
        Badge(
            modifier = Modifier
                .clickable(onClick = {
                    onEvent(TicketListUserEvent.CreateSnackbar("onTicketBadgeClick -> ${data.badgeText}"))
                }),
            isVisible = data.isBadgeVisible,
            text = data.badgeText,
        )
    }
}


@Composable
private fun DetailsBoard(
    modifier: Modifier = Modifier,
    departureTime: String,
    departureAirport: String,
    arrivalTime: String,
    arrivalAirport: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .wrapContentSize(),
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(color = colorResource(R.color.red), shape = RoundedCornerShape(50.dp)),
        )
        Box(
            modifier = Modifier
                .padding(start = 8.dp)
                .wrapContentSize(),
        ) {
            Place(
                modifier = Modifier
                    .padding(start = 0.dp, top = 2.dp, bottom = 2.dp),
                time = departureTime,
                airport = departureAirport,
            )
            Box(
                modifier = Modifier
                    .padding(start = 40.dp, top = 10.dp)
                    .size(width = 10.dp, height = 1.dp)
                    .background(colorResource(R.color.gray_6)),
            )
            Place(
                modifier = Modifier
                    .padding(start = 54.dp, top = 2.dp, bottom = 2.dp),
                time = arrivalTime,
                airport = arrivalAirport,
            )
        }
    }
}


@Composable
private fun Place(
    modifier: Modifier = Modifier,
    time: String,
    airport: String,
) {
    Column(
        modifier = modifier
            .width(38.dp)
            .wrapContentHeight(),
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = time,
            maxLines = 1,
            style = TextStyle(
                color = colorResource(R.color.white),
                fontSize = 14.sp,
            ),
        )
        Spacer(
            modifier = Modifier
                .height(2.dp),
        )
        Text(
            text = airport,
            maxLines = 1,
            style = TextStyle(
                color = colorResource(R.color.gray_6),
                fontSize = 14.sp,
            ),
        )
    }
}

@Composable
private fun Badge(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    text: String,
) {
    if (isVisible) {
        Box(
            modifier = modifier
                .height(21.dp)
                .background(color = colorResource(R.color.blue), shape = RoundedCornerShape(50.dp))
                .padding(horizontal = 10.dp),
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .align(Alignment.Center),
                maxLines = 1,
                style = TextStyle(
                    color = colorResource(R.color.white),
                    fontSize = 14.sp,
                ),
            )
        }
    }
}