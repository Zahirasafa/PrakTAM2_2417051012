package com.example.praktam2_2417051012.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL =
        "https://gist.githubusercontent.com/Zahirasafa/63f765b8015b65694b378bc0240ff750/raw/90f7671a1d7d18b6bfe71cd2667406b7d53fff10/"

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}