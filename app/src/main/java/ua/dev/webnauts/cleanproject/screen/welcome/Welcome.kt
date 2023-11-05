package ua.dev.webnauts.cleanproject.screen.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.Graph
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.NavRoutes
import ua.dev.webnauts.cleanproject.screen.login.LoginViewModel

@Composable
fun Welcome(appState: AppState,
            viewModel : LoginViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        delay(2000)
        appState.navController.navigate(Graph.Login.graph){
            launchSingleTop = true
        }
    }


    Box(
        modifier = Modifier.fillMaxSize(1f),
        contentAlignment = Alignment.Center,
    ) {
        Column {

            viewModel.setRoundCorners(23)

            Text(text = "Welcome Screen")
//            Button(onClick = {
//                appState.navController.navigate(Graph.Login.graph)
//            }) {
//                Text(text = "Go to Registration")
//            }
        }
    }
}