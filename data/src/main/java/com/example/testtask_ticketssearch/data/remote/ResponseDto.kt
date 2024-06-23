package com.example.testtask_ticketssearch.data.remote

import com.google.gson.annotations.SerializedName

data class ResponseDto(
    @SerializedName("tickets_offers") val ticketsOffers: List<TicketOfferDto>,
    @SerializedName("offers") val eventsOffers: List<EventOfferDto>,
)
