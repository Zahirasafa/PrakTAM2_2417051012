package com.example.praktam2_2417051012.data.model

import com.google.gson.annotations.SerializedName

data class Travel(
    @SerializedName("nama")
    val nama: String,

    @SerializedName("deskripsi")
    val deskripsi: String,

    @SerializedName("harga")
    val harga: String,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("province")
    val province: String,

    @SerializedName("city")
    val city: String,

    @SerializedName("rating")
    val rating: Double,

    @SerializedName("review_count")
    val reviewCount: Int,

    @SerializedName("maps_query")
    val mapsQuery: String
)