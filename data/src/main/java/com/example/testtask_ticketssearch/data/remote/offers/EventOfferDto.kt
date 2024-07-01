package com.example.testtask_ticketssearch.data.remote.offers

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class EventOfferDto(
    @SerializedName("id") @SerialName("id") val id: Long,
    @SerializedName("title") @SerialName("title") val title: String,
    @SerializedName("town") @SerialName("town") val town: String,
    @SerializedName("price") @SerialName("price") val price: PriceDto,
)
