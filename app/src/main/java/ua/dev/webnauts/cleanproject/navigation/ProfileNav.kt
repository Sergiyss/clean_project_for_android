package ua.dev.webnauts.cleanproject.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.NavRoutes
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.Graph
import ua.dev.webnauts.cleanproject.screen.home.HomeScreen
import ua.dev.webnauts.cleanproject.screen.profile.ProfileScreen
import ua.dev.webnauts.cleanproject.utils.createTransitionComposableArg

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    this.navigate(Graph.Profile.graph, navOptions)
}
fun NavGraphBuilder.profileNav(appState: AppState) {
    navigation(
        route = Graph.Profile.graph,
        startDestination = NavRoutes.Profile.route
    ) {
        createTransitionComposableArg(
            route = NavRoutes.Profile.route,
        ){
            ProfileScreen(appState = appState)
        }
    }
}