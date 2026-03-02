package model

import androidx.annotation.DrawableRes

data class Travel(
    val nama: String,
    val lokasi: String,
    val deskripsi: String,
    val harga: Int,
    @DrawableRes val imageRes: Int
)