package com.example.testtask_ticketssearch.data.remote.offers

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable

data class PriceDto(
    @SerializedName("value") @SerialName("value") val value: Long,
)
