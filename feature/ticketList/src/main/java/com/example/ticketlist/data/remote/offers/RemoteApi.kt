package com.example.ticketlist.data.remote.offers

import retrofit2.Retrofit
import retrofit2.http.GET


interface OffersApi {

    /**
     * Получить список билетов.
     */
    @GET("v3/c0464573-5a13-45c9-89f8-717436748b69")
    suspend fun getTickets(): ResponseDto

}

internal fun registerOffersApi(provider: Retrofit) : OffersApi {
    return provider.create(OffersApi::class.java)
}