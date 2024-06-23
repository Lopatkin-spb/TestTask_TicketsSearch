package com.example.testtask_ticketssearch.data.remote

import com.example.testtask_ticketssearch.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

}


private const val URL = "https://run.mocky.io/"


private fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(URL)
        .client(getModifiedOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
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

object NetworkProvider {
    val registerApi: OffersApi by lazy {
        getRetrofit().create(OffersApi::class.java)
    }
}