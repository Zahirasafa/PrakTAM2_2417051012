package com.example.praktam2_2417051012

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.Travel
import model.TravelSource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                TravelPlannerScreen()
            }
        }
    }
}

@Composable
fun TravelPlannerScreen() {

    var selectedLocation by remember { mutableStateOf("Semua") }

    val lokasiList = listOf("Semua", "Bali", "Bandung", "Malang")

    val filteredList = if (selectedLocation == "Semua") {
        TravelSource.dummyTravel
    } else {
        TravelSource.dummyTravel.filter { it.lokasi == selectedLocation }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "TripNest - Travel Planner",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Pilih Lokasi:",
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            lokasiList.forEach { lokasi ->
                Button(
                    onClick = { selectedLocation = lokasi }
                ) {
                    Text(lokasi)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Rekomendasi Destinasi",
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(filteredList) { travel ->
                TravelItem(travel)
            }
        }
    }
}

@Composable
fun TravelItem(travel: Travel) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column(modifier = Modifier.padding(12.dp)) {

            Image(
                painter = painterResource(id = travel.imageRes),
                contentDescription = travel.nama,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = travel.nama,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Text(text = "Lokasi: ${travel.lokasi}")
            Text(text = travel.deskripsi)
            Text(text = "Harga Tiket: Rp ${travel.harga}")
        }
    }
}