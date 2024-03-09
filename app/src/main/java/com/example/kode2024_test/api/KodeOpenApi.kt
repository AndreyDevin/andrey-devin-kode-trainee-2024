package com.example.kode2024_test.api

import com.example.kode2024_test.api.models.ResponseBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface KodeOpenApi {
    @GET("users")
    suspend fun getResponse(): ResponseBody

    companion object {
        private const val BASE_URL = "https://stoplight.io/mocks/kode-api/trainee-test/331141861/"

        fun create(): KodeOpenApi {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(KodeOpenApi::class.java)
        }
    }
}