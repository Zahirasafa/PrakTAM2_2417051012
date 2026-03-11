package model

import androidx.annotation.DrawableRes

data class Travel(
    val nama: String,
    val deskripsi: String,
    val harga: String,
    @DrawableRes val imageRes: Int
)