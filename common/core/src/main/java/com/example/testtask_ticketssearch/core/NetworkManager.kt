package com.example.testtask_ticketssearch.core

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

interface NetworkManager {

    fun getProvider(): Retrofit

}


internal class NetworkRetrofit : NetworkManager {

    override fun getProvider(): Retrofit {
        return getNetworkProvider()
    }

    private val URL = "https://run.mocky.io/"

    private fun getNetworkProvider(): Retrofit {
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

}