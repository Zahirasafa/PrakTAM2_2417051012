package com.example.praktam2_2417051012.data.repository

import com.example.praktam2_2417051012.data.api.RetrofitClient
import com.example.praktam2_2417051012.data.model.Travel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TravelRepository {
    companion object {
        private val _favorites = MutableStateFlow<Set<String>>(emptySet())
        val favorites: StateFlow<Set<String>> = _favorites.asStateFlow()

        fun toggleFavorite(travelName: String) {
            _favorites.update { current ->
                if (current.contains(travelName)) current - travelName else current + travelName
            }
        }
    }

    suspend fun getTravels(): List<Travel> {
        return try {
            RetrofitClient.instance.getTravels()
        } catch (e: Exception) {
            emptyList()
        }
    }
}
