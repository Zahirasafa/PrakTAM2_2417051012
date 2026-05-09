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
    val imageUrl: String
)