package ua.dev.webnauts.cleanproject.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.NavRoutes
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.Graph
import ua.dev.webnauts.cleanproject.screen.login.LoginViewModel
import ua.dev.webnauts.cleanproject.screen.welcome.Welcome
import ua.dev.webnauts.cleanproject.utils.createTransitionComposableArg

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.welcomeNav(appState: AppState, loginViewModel: LoginViewModel) {
    navigation(
        route = Graph.Welcome.graph,
        startDestination = NavRoutes.Welcome.route
    ) {
        createTransitionComposableArg(
            route = NavRoutes.Welcome.route,
        ){
            Welcome(appState = appState, loginViewModel)
        }
    }
}