package ua.dev.webnauts.cleanproject.navigation.settings_navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.navigation.animation.AnimatedNavHost
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.R
import ua.dev.webnauts.cleanproject.navigation.loginNav
import ua.dev.webnauts.cleanproject.navigation.welcomeNav
import ua.dev.webnauts.cleanproject.screen.login.LoginViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(appState : AppState, startDestination : String, token : String, loginViewModel: LoginViewModel) {

    val localDensity = LocalDensity.current
    val configuration = LocalConfiguration.current
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val width = with(localDensity) { configuration.screenWidthDp.dp.toPx().toInt() }

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



    AnimatedNavHost(
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
    }

}