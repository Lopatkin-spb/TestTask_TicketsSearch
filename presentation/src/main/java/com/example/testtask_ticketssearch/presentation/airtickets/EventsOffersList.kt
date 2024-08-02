package com.example.testtask_ticketssearch.presentation.airtickets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.testtask_ticketssearch.R
import com.example.testtask_ticketssearch.domain.model.EventOfferUi


@Composable
internal fun EventsOffersSection(
    modifier: Modifier = Modifier,
    new: List<EventOfferUi>,
) {
    val stubText = stringResource(R.string.text_stub)

    LazyRow(
        modifier = modifier
            .wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(67.dp),
    ) {
        items(
            items = new,
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
                new = EventOfferUi(
                    id = offer.id,
                    title = title,
                    town = town,
                    price = price,
                    url = offer.url,
                )
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun EventOfferItem(
    modifier: Modifier = Modifier,
    new: EventOfferUi,
) {

    Column(
        modifier = modifier
            .wrapContentSize()
            .height(213.16.dp),
    ) {
        GlideImage(
            modifier = Modifier
                .width(132.dp)
                .height(133.16.dp)
                .background(color = colorResource(R.color.gray_4), shape = RoundedCornerShape(16.dp)),
            model = new.url,
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .padding(top = 7.dp)
                .fillMaxWidth(),
            text = new.title,
            maxLines = 1,
            style = TextStyle(
                color = colorResource(R.color.white),
                fontSize = 16.sp,
            ),
        )
        Text(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(),
            text = new.town,
            maxLines = 1,
            style = TextStyle(
                color = colorResource(R.color.white),
                fontSize = 14.sp,
            ),
        )
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 5.dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_air_24dp),
                contentDescription = null,
                tint = colorResource(R.color.gray_6),
            )
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterVertically),
                text = new.price,
                maxLines = 1,
                style = TextStyle(
                    color = colorResource(R.color.white),
                    fontSize = 14.sp,
                ),
            )
        }
    }
}