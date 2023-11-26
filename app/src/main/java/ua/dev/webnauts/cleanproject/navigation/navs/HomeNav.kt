package ua.dev.webnauts.cleanproject.navigation.navs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import ua.dev.webnauts.cleanproject.navigation.NavRoutes
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.navigation.Graph
import ua.dev.webnauts.cleanproject.screen.home.HomeScreen
import ua.dev.webnauts.cleanproject.utils.createTransitionComposableArg



fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(Graph.Home.graph, navOptions)
}

fun NavGraphBuilder.homeNav(appState: AppState) {
    navigation(
        route = Graph.Home.graph,
        startDestination = NavRoutes.Home().route
    ) {
        createTransitionComposableArg(
            route = NavRoutes.Home().route,
        ){
            HomeScreen(appState = appState)
        }
    }
}