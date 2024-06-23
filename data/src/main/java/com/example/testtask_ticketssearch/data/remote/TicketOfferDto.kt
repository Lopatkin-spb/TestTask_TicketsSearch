package com.example.testtask_ticketssearch.data.remote

import com.google.gson.annotations.SerializedName

data class TicketOfferDto(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("time_range") val timeRange: List<String>,
    @SerializedName("price") val price: PriceDto,
)
