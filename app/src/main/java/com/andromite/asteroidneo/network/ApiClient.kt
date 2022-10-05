package com.andromite.asteroidneo.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {

    private lateinit var retrofit: Retrofit
    private fun loadRetrofit(): Retrofit {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            retrofit = Retrofit.Builder()
//                .baseUrl(Constants.baseUrl)
                .baseUrl("https://api.nasa.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit
    }

    val apiClient : Apis by lazy {
        loadRetrofit().create(Apis::class.java)
    }


}