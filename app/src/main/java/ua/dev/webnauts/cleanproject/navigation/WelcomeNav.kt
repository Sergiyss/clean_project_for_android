package ua.dev.webnauts.cleanproject.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.NavRoutes
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.Graph
import ua.dev.webnauts.cleanproject.screen.login.LoginViewModel
import ua.dev.webnauts.cleanproject.screen.welcome.Welcome

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.welcomeNav(appState: AppState, loginViewModel: LoginViewModel) {
    navigation(
        route = Graph.Welcome.graph,
        startDestination = NavRoutes.Welcome.route
    ) {
        composable(
            route = NavRoutes.Welcome.route,
        ){

            Welcome(appState = appState, loginViewModel)
        }
    }
}