package com.example.praktam2_2417051012.network

import com.example.praktam2_2417051012.model.Travel
import retrofit2.http.GET

interface ApiService {
    @GET("travel_wisata.json")
    suspend fun getTravels(): List<Travel>
}