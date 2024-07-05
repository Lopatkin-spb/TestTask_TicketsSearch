package com.example.testtask_ticketssearch.data.remote.offers

import com.example.testtask_ticketssearch.data.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET


interface OffersApi {

    /**
     * Получить предложения стоимости билетов на разные события.
     */
    @GET("v3/ad9a46ba-276c-4a81-88a6-c068e51cce3a")
    suspend fun getEventsOffers(): ResponseDto

    /**
     * Получить подробные рекомендации билетов по событию.
     */
    @GET("v3/38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a")
    suspend fun getTicketsOffers(): ResponseDto

    /**
     * Получить список билетов.
     */
    @GET("v3/c0464573-5a13-45c9-89f8-717436748b69")
    suspend fun getTickets(): ResponseDto

}


private const val URL = "https://run.mocky.io/"


private fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(URL)
        .client(getModifiedOkHttpClient())
        .addConverterFactory(getModifiedJsonConverterFactory())
        .build()
}

private fun getModifiedOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(getLoggingInterceptor()) //Must last (bottom) after all interceptors for intercept all data
        .build()
}

private fun getLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()

    if (BuildConfig.DEBUG) {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    } else {
        interceptor.level = HttpLoggingInterceptor.Level.NONE
    }

    return interceptor
}

private fun getModifiedJsonConverterFactory(): Converter.Factory {
    val jsonModified = Json { ignoreUnknownKeys = true }
    return jsonModified.asConverterFactory("application/json; charset=UTF8".toMediaType())
}

object NetworkProvider {
    val registerApi: OffersApi by lazy {
        getRetrofit().create(OffersApi::class.java)
    }
}