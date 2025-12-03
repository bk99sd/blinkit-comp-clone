package com.example.blinkitclone.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.blinkitclone.ui.components.home.Header
import com.example.blinkitclone.ui.search.SearchBar

@Composable
fun AllScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // BestSeller component
        BestSeller()
    }
}

@Composable
fun WinterScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Winter Screen")
    }
}

@Composable
fun ElectronicsScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Electronics Screen")
    }
}

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    ALL("all", "All", Icons.Default.Info, "All"),
    WINTER("winter", "Winter", Icons.Default.Info, "Winter"),
    ELECTRONICS("electronics", "Electronics", Icons.Default.Info, "Electronics")
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination.route
    ) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.ALL -> AllScreen()
                    Destination.WINTER -> WinterScreen()
                    Destination.ELECTRONICS -> ElectronicsScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        val navController = rememberNavController()
        val startDestination = Destination.ALL
        var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 8.dp),
        ) {
            Header()
            Spacer(modifier = Modifier.height(12.dp))
            SearchBar(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                onSearch = { /*TODO*/ },
                query = "",
                onQueryChange = {},
                searchResults = emptyList(),
                onResultClick = {}
            )
            Spacer(modifier = Modifier.height(12.dp))
            PrimaryTabRow(
                selectedTabIndex = selectedDestination,
            ) {
                Destination.entries.forEachIndexed { index, destination ->
                    Tab(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination = index
                        },
                        text = {
                            Text(
                                text = destination.label,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                }
            }
            AppNavHost(navController, startDestination)
        }
    }
}
