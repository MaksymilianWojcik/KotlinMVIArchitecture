package com.example.kotlinmviarchitecture.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder { // thats how we create Singleton

    val retrofitBuilder: Retrofit.Builder by lazy {  // by lazy - only evert initialize it once, and after use that instance
        Retrofit.Builder()
            .baseUrl("https://open-api.xyz/")
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiService: ApiService by lazy {
        retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }

}