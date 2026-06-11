package com.example.praktam2_2417051012.data.model

data class TravelUser(
    val username: String,
    val displayName: String,
    val bio: String,
    val visitedPlaces: List<String>,
    val followersCount: Int,
    val followingCount: Int,
    val avatarUrl: String,
    val isFollowing: Boolean = false
)