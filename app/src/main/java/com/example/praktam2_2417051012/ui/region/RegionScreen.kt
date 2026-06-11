package com.example.praktam2_2417051012.ui.region

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.praktam2_2417051012.data.model.Travel
import com.example.praktam2_2417051012.data.repository.TravelRepository
import com.example.praktam2_2417051012.ui.components.TravelCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegionScreen(
    navController: NavHostController,
    regionName: String
) {
    var travels by remember { mutableStateOf<List<Travel>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(regionName) {
        scope.launch {
            isLoading = true
            travels = TravelRepository().getTravels()
            isLoading = false
        }
    }

    // Filter travels by region/province keyword
    val filteredTravels = remember(travels, regionName) {
        travels.filter { travel ->
            travel.province.contains(regionName, true) || 
            travel.city.contains(regionName, true)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = "Wisata di $regionName", 
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {}
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (filteredTravels.isEmpty()) {
                Text(
                    text = "Tidak ada destinasi ditemukan di wilayah $regionName",
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 14.sp
                )
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1), // vertical feed is cleaner for travel cards
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredTravels) { travel ->
                        TravelCard(
                            travel = travel,
                            onClick = {
                                navController.navigate("detail/${travel.nama}")
                            }
                        )
                    }
                }
            }
        }
    }
}
