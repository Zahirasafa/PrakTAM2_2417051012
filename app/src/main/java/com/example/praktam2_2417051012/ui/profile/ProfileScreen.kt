package com.example.praktam2_2417051012.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.praktam2_2417051012.data.model.Travel
import com.example.praktam2_2417051012.data.model.TravelUser
import com.example.praktam2_2417051012.data.repository.TravelRepository
import com.example.praktam2_2417051012.data.repository.UserRepository

@Composable
fun StatItem(count: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(horizontal = 12.dp)) {
        Text(text = count, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text(text = label, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun VisitedPlaceCard(travel: Travel, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth().height(130.dp).clickable { onClick() }
    ) {
        Box {
            AsyncImage(
                model = travel.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.4f)))
            Text(
                text = travel.nama,
                color = Color.White,
                modifier = Modifier.align(Alignment.BottomStart).padding(12.dp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onDetailClick: (String) -> Unit,
    username: String? = null,
    onBackClick: (() -> Unit)? = null,
    onUserClick: (String) -> Unit = {}
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    var travels by remember { mutableStateOf<List<Travel>>(emptyList()) }
    val users by UserRepository.users.collectAsState()
    val favorites by TravelRepository.favorites.collectAsState()

    val displayUser = remember(username, users) {
        if (username == null) {
            users.find { it.username == "rara_travel" }
        } else {
            users.find { it.username == username }
        }
    }

    LaunchedEffect(Unit) {
        travels = TravelRepository().getTravels()
    }

    val visitedTravels = remember(travels, displayUser) {
        travels.filter { travel ->
            displayUser?.visitedPlaces?.any { it.equals(travel.nama, true) } == true
        }
    }

    val favoriteTravels = remember(travels, favorites) {
        travels.filter { favorites.contains(it.nama) }
    }

    Scaffold(
        topBar = {
            if (onBackClick != null) {
                TopAppBar(
                    title = { Text(displayUser?.displayName ?: "Profil") },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                        }
                    }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Initial Circle instead of Photo
                Surface(
                    modifier = Modifier.size(80.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = (displayUser?.displayName ?: "?").take(1),
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                
                Text(text = displayUser?.displayName ?: "Unknown", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = "@${displayUser?.username ?: "unknown"}", color = MaterialTheme.colorScheme.secondary)
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = displayUser?.bio ?: "",
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )

                Spacer(modifier = Modifier.height(20.dp))
                
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    StatItem(visitedTravels.size.toString(), "Wisata")
                    StatItem(displayUser?.followersCount?.toString() ?: "0", "Followers")
                    StatItem(displayUser?.followingCount?.toString() ?: "0", "Following")
                }

                if (username != null && username != "rara_travel") {
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { UserRepository.toggleFollow(username) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (displayUser?.isFollowing == true) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.secondary,
                            contentColor = if (displayUser?.isFollowing == true) MaterialTheme.colorScheme.onSurfaceVariant else Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(if (displayUser?.isFollowing == true) "Batal Ikuti" else "Ikuti")
                    }
                }
            }

            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.secondary,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            ) {
                Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }) {
                    Column(Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.Bookmark, null)
                        Text("Dikunjungi", fontSize = 12.sp)
                    }
                }
                if (username == null || username == "rara_travel") {
                    Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }) {
                        Column(Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Default.Favorite, null, tint = Color.Red)
                            Text("Favorit", fontSize = 12.sp)
                        }
                    }
                }
                Tab(selected = selectedTab == 2, onClick = { selectedTab = 2 }) {
                    Column(Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.People, null)
                        Text("Teman", fontSize = 12.sp)
                    }
                }
            }

            Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                when (selectedTab) {
                    0 -> {
                        if (visitedTravels.isEmpty()) {
                            Text("Belum ada riwayat wisata.", Modifier.align(Alignment.Center))
                        } else {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                items(visitedTravels) { travel ->
                                    VisitedPlaceCard(travel) { onDetailClick(travel.nama) }
                                }
                            }
                        }
                    }
                    1 -> {
                        if (favoriteTravels.isEmpty()) {
                            Text("Belum ada wisata favorit.", Modifier.align(Alignment.Center))
                        } else {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                items(favoriteTravels) { travel ->
                                    VisitedPlaceCard(travel) { onDetailClick(travel.nama) }
                                }
                            }
                        }
                    }
                    2 -> {
                        LazyColumn {
                            items(users.filter { it.username != displayUser?.username }) { user ->
                                ListItem(
                                    headlineContent = { Text(user.displayName, fontWeight = FontWeight.Bold) },
                                    supportingContent = { Text("@${user.username}") },
                                    leadingContent = { 
                                         Surface(Modifier.size(40.dp), shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.primaryContainer) {
                                             Box(contentAlignment = Alignment.Center) {
                                                 Text(user.displayName.take(1))
                                             }
                                         }
                                    },
                                    modifier = Modifier.clickable { 
                                        onUserClick(user.username)
                                    }
                                )
                                HorizontalDivider()
                            }
                        }
                    }
                }
            }
        }
    }
}
