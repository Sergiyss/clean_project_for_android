package ua.dev.webnauts.cleanproject.screen.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.navigation.Graph
import ua.dev.webnauts.cleanproject.screen.login.LoginViewModel

@Composable
fun Welcome(appState: AppState,
            viewModel : LoginViewModel = hiltViewModel()
) {

    val isLogin by viewModel.isLogin

    LaunchedEffect(key1 = isLogin, block = {
        if(isLogin == null){
            viewModel.checkUser()
            return@LaunchedEffect
        }
        delay(1000)
        if(isLogin == true) {
            appState.navigateToTopLevelDestination(Graph.Home)
        }else{
            appState.navController.navigate(Graph.Login.graph) {
                launchSingleTop = true
            }
        }
    })



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