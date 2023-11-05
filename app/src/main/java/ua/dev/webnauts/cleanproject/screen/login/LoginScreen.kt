package ua.dev.webnauts.cleanproject.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.Graph
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.NavRoutes
import ua.dev.webnauts.cleanproject.ui.compose_ui.top_bars.DefaultTopBar

@Composable
fun LoginScreen(appState: AppState,
                viewModel : LoginViewModel = hiltViewModel()
) {

    val roundCorners = viewModel.saveCount.value
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Scaffold(
        topBar =
        {
            DefaultTopBar(
                title = "Login",
                onClick = { appState.navController.navigateUp() }
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(paddingValues = it)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(25.dp, Alignment.Top),
            ) {
                Text(text = "Login Screen", fontSize = 32.sp)

                TextField(value = login, onValueChange = {
                    login = it
                })

                TextField(value = password, onValueChange = {
                    password = it
                })
                Row(){
                    Button(onClick = {
                        appState.navController.navigate(Graph.Home.graph){
                            launchSingleTop = true
                        }
                    }) {
                        Text(text = "Login")
                    }
                    Button(onClick = {
                        appState.navController.navigate(NavRoutes.RegisterScreen().route){
                            launchSingleTop = true
                        }
                    }) {
                        Text(text = "Register")
                    }
                }

                Text(text = "Round corners: $roundCorners")
            }
        }
    }
}