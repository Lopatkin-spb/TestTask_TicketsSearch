package com.example.testtask_ticketssearch.data.remote.offers

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketOfferDto(
    @SerializedName("id") @SerialName("id") val id: Long,
    @SerializedName("title") @SerialName("title") val title: String,
    @SerializedName("time_range") @SerialName("time_range") val timeRange: List<String>,
    @SerializedName("price") @SerialName("price") val price: PriceDto,
)
