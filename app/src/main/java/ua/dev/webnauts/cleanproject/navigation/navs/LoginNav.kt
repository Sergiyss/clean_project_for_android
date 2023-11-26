package ua.dev.webnauts.cleanproject.navigation.navs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import ua.dev.webnauts.cleanproject.navigation.NavRoutes
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.navigation.Graph
import ua.dev.webnauts.cleanproject.screen.login.LoginScreen
import ua.dev.webnauts.cleanproject.screen.login.LoginViewModel
import ua.dev.webnauts.cleanproject.screen.login.RegistrationScreen
import ua.dev.webnauts.cleanproject.utils.createTransitionComposableArg

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.loginNav(appState: AppState, loginViewModel: LoginViewModel) {
    navigation(
        route = Graph.Login.graph,
        startDestination = NavRoutes.Login.route
    ) {
        createTransitionComposableArg(
            route = NavRoutes.Login.route,
        ){

            LoginScreen(appState = appState, loginViewModel)
        }

        createTransitionComposableArg(
            route = NavRoutes.RegisterScreen().route,
        ){

            RegistrationScreen(appState = appState, loginViewModel)
        }
    }
}