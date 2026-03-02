package model

import com.example.praktam2_2417051012.R

object TravelSource {

    val dummyTravel = listOf(

        Travel(
            nama = "Pantai Kuta",
            lokasi = "Bali",
            deskripsi = "Pantai terkenal di Bali dengan pasir putih dan sunset yang indah.",
            harga = 20000,
            imageRes = R.drawable.pantaikuta
        ),

        Travel(
            nama = "Gedung Sate",
            lokasi = "Bandung",
            deskripsi = "Ikon kota Bandung dengan arsitektur khas dan spot foto menarik.",
            harga = 15000,
            imageRes = R.drawable.bandung
        ),

        Travel(
            nama = "Gunung Bromo",
            lokasi = "Malang",
            deskripsi = "Destinasi wisata alam dengan pemandangan sunrise yang menakjubkan.",
            harga = 40000,
            imageRes = R.drawable.bromo
        )
    )
}