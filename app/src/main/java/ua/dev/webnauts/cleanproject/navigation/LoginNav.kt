package ua.dev.webnauts.cleanproject.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.NavRoutes
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.Graph
import ua.dev.webnauts.cleanproject.screen.login.LoginScreen
import ua.dev.webnauts.cleanproject.screen.login.LoginViewModel
import ua.dev.webnauts.cleanproject.screen.login.RegistrationScreen
import ua.dev.webnauts.cleanproject.screen.welcome.Welcome

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.loginNav(appState: AppState, loginViewModel: LoginViewModel) {
    navigation(
        route = Graph.Login.graph,
        startDestination = NavRoutes.Login.route
    ) {
        composable(
            route = NavRoutes.Login.route,
        ){

            LoginScreen(appState = appState, loginViewModel)
        }

        composable(
            route = NavRoutes.RegisterScreen().route,
        ){

            RegistrationScreen(appState = appState, loginViewModel)
        }
    }
}