package ua.dev.webnauts.cleanproject.screen.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.ui.graphics.vector.ImageVector
import ua.dev.webnauts.cleanproject.navigation.Graph

data class NavigationData(
    val title : String,
    val icon : ImageVector,
    val route : Graph
)

val navigationData = listOf(
    NavigationData(
        title = "Home",
        icon = Icons.Filled.Home,
        route = Graph.Home
    ),
    NavigationData(
        title = "Articles",
        icon = Icons.Filled.Favorite,
        route = Graph.TabTwo
    ),

    NavigationData(
        title = "Profile",
        icon = Icons.Filled.PersonOutline,
        route = Graph.Profile
    )
)