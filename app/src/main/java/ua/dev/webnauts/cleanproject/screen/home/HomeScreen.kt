package ua.dev.webnauts.cleanproject.screen.home


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.R
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.NavRoutes
import ua.dev.webnauts.cleanproject.screen.home.components.navigationData
import ua.dev.webnauts.cleanproject.screen.login.LoginViewModel

@Composable
fun HomeScreen(
    appState: AppState,
    viewModel: LoginViewModel = hiltViewModel()
) {
    var animated by remember { mutableStateOf(false) }
    val rotation = remember { Animatable(initialValue = 0f) }
    val scale = remember { Animatable(initialValue = 1f) }
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Songs", "Artists", "Profiles")

    val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    LaunchedEffect(animated) {
        // if(animated == false ) return@LaunchedEffect
        rotation.animateTo(
            targetValue = if (animated) 180f else 0f,
            animationSpec = tween(durationMillis = 1000),
        )

        scale.animateTo(
            targetValue = if (animated) 3f else 1f,
            animationSpec = tween(durationMillis = 1000),
        )
    }

    println("<<<<< screen: 3-> ${currentDestination?.route}")

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Home Screen")

            LazyColumn(content = {
                items(100) {
                    Text(text = "Item $it", Modifier.padding(10.dp))
                }
            })
        }
    }

}

