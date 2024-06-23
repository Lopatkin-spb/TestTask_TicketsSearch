package com.example.testtask_ticketssearch.data.remote

import com.google.gson.annotations.SerializedName

data class EventOfferDto(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("town") val town: String,
    @SerializedName("price") val price: PriceDto,
)
