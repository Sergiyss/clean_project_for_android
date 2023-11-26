package ua.dev.webnauts.cleanproject.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.R
import ua.dev.webnauts.cleanproject.navigation.navs.homeNav
import ua.dev.webnauts.cleanproject.navigation.navs.loginNav
import ua.dev.webnauts.cleanproject.navigation.navs.profileNav
import ua.dev.webnauts.cleanproject.navigation.navs.tabNav
import ua.dev.webnauts.cleanproject.navigation.navs.welcomeNav
import ua.dev.webnauts.cleanproject.screen.login.LoginViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(appState : AppState, loginViewModel: LoginViewModel) {


    val isOffline by appState.isOffline.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    // If user is not connected to the internet show a snack bar to inform them.
    val notConnectedMessage = stringResource(R.string.not_connected)

    LaunchedEffect(isOffline) {
        if (isOffline) {
            snackbarHostState.showSnackbar(
                message = notConnectedMessage,
                duration = SnackbarDuration.Indefinite,
            )
        }
    }

    NavHost(
        navController = appState.navController,
        startDestination = Graph.Welcome.graph,
        route = NavRoutes.Welcome.route
    ) {
        welcomeNav(
            appState = appState,
            loginViewModel = loginViewModel
        )
        loginNav(
            appState = appState,
            loginViewModel = loginViewModel
        )
        homeNav(
            appState = appState,
        )
        profileNav(
            appState = appState,
        )

        tabNav(appState = appState,)
    }

}