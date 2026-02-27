package com.example.praktam2_2417051012

import model.Travel
import model.TravelSource
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import com.example.praktam2_2417051012.ui.theme.PrakTAM2_2417051012Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrakTAM2_2417051012Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Greeting(innerPadding: PaddingValues) {
    val travel = TravelSource.dummyTravel[0]

    Column(modifier = Modifier.fillMaxSize().padding(all = 30.dp)) {
        Image(
            painter = painterResource(id = travel.ImageRes),
            contentDescription = travel.nama,
            modifier = Modifier.size(200.dp),
            contentScale = Crop
        )
        Text(text = "Nama: ${travel.nama}")
        Text(text = "Deskripsi: ${travel.deskripsi}")
        Text(text= "Harga: ${travel.harga}")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PrakTAM2_2417051012Theme {
        Greeting(PaddingValues(0.dp))
    }
}