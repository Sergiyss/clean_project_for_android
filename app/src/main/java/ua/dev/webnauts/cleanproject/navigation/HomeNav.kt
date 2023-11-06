package ua.dev.webnauts.cleanproject.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.NavRoutes
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.Graph
import ua.dev.webnauts.cleanproject.screen.home.HomeScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeNav(appState: AppState) {
    navigation(
        route = Graph.Home.graph,
        startDestination = NavRoutes.Home().route
    ) {
        composable(
            route = NavRoutes.Home().route,
        ){
            HomeScreen(appState = appState)
        }
    }
}