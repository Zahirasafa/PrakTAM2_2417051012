package com.example.praktam2_2417051012.data.repository

import com.example.praktam2_2417051012.data.api.RetrofitClient
import com.example.praktam2_2417051012.data.model.Travel

class TravelRepository {
    suspend fun getTravels(): List<Travel> {
        return try {
            RetrofitClient.instance.getTravels()
        } catch (e: Exception) {
            emptyList()
        }
    }
}