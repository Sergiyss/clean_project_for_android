package ua.dev.webnauts.cleanproject.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.Graph
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.NavRoutes
import ua.dev.webnauts.cleanproject.screen.login.LoginViewModel

@Composable
fun HomeScreen(appState: AppState,
            viewModel : LoginViewModel = hiltViewModel()
) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Songs", "Artists", "Profiles")

    Scaffold(
       bottomBar = {
           NavigationBar {
               items.forEachIndexed { index, item ->
                   NavigationBarItem(
                       icon = { Icon(Icons.Filled.Favorite, contentDescription = item) },
                       label = { Text(item) },
                       selected = selectedItem == index,
                       onClick = { selectedItem = index }
                   )
               }
           }
       }
    ) {
        Column(Modifier.padding(it)) {
            ProductCard()
        }
    }

}