package model

import com.example.praktam2_2417051012.R

object TravelSource {

    val dummyTravel = listOf(

        Travel(
            nama = "Pantai Kuta - Bali",
            deskripsi = "Pantai terkenal di Bali dengan pasir putih dan pemandangan sunset yang indah.",
            harga = "Rp 10.000 - Rp 25.000",
            imageRes = R.drawable.pantaikuta
        ),

        Travel(
            nama = "Gedung Sate - Bandung",
            deskripsi = "Bangunan ikonik di Bandung yang memiliki arsitektur unik dan museum sejarah.",
            harga = "Rp 5.000 - Rp 20.000",
            imageRes = R.drawable.bandung
        ),

        Travel(
            nama = "Gunung Bromo - Malang",
            deskripsi = "Destinasi wisata alam terkenal dengan pemandangan sunrise dan lautan pasir.",
            harga = "Rp 30.000 - Rp 50.000",
            imageRes = R.drawable.bromo
        )
    )
}