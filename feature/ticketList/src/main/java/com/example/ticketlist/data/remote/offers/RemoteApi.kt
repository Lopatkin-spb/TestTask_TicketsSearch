package com.example.ticketlist.data.remote.offers

import com.example.ticketlist.BuildConfig
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