package com.example.triptrack_ai.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object ApiClient {
    private const val BASE_URL = "http://10.65.171.54/"

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create()) // Important for plain text responses
            .addConverterFactory(GsonConverterFactory.create())    // For JSON responses
            .build()

        retrofit.create(ApiService::class.java)
    }
}
