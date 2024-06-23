package com.example.testtask_ticketssearch.data.remote

import com.google.gson.annotations.SerializedName

data class PriceDto(
    @SerializedName("value") val value: Long,
)
