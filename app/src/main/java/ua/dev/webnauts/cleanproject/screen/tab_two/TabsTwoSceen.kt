package ua.dev.webnauts.cleanproject.screen.tab_two

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.navigation.NavRoutes
import ua.dev.webnauts.cleanproject.network.ktor.NetworkResponse
import ua.dev.webnauts.cleanproject.state.collectUiState
import ua.dev.webnauts.cleanproject.state.collectWithLifecycle
import ua.dev.webnauts.cleanproject.ui.compose_ui.top_bars.DefaultTopBar
import ua.dev.webnauts.cleanproject.ui.theme.spacing
import ua.dev.webnauts.cleanproject.utils.navigateSingleTopTo

@Composable
fun TabsTwoScreen(
    appState: AppState,
    tabsTwoViewModel: TabsTwoViewModel = hiltViewModel(),
    lifecycle: LifecycleOwner = LocalLifecycleOwner.current
) {
    var tst by remember { mutableStateOf("") }
    val uiState by tabsTwoViewModel.collectWithLifecycle()
    val emailCharacterCount by tabsTwoViewModel.emailCharacterCountFlow.collectAsState(initial = 0)

    var uiTestState by tabsTwoViewModel.testUiState

    LaunchedEffect(key1 = Unit, block = {
        ///tabsTwoViewModel.flowTest()
        tabsTwoViewModel.testUpdateUiState()
    })

    LaunchedEffect(key1 = Unit) {
        tabsTwoViewModel.collectEvent(lifecycle) { event ->
            when (event) {
                TabsTwoViewModel.Event.GoToHome -> {
                    appState.navController.navigateSingleTopTo(NavRoutes.Home().route)
                }
            }
        }
    }
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Tab Screen",
                onClick = { appState.navController.navigateUp() }
            )
        }) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {

            when (val response = uiTestState) {
                is NetworkResponse.Success -> {
                    Text(response.data.test)
                    Button(onClick = { tabsTwoViewModel.testUpdateUiState(34) }) {
                        Text("OK-+")
                    }
                }

                is NetworkResponse.Error -> {
                    Column {

                        Text(response.message)

                        Button(onClick = { uiTestState = null }) {
                            Text("OK-+")
                        }
                    }
                }

                else -> {
                    //Error
                }
            }

            // Здесь вы можете использовать emailCharacterCount в UI вашего Compose-экрана
            // Например:
            Text("Character count: $emailCharacterCount")
            if (uiState.isLoading) {
                CircularProgressIndicator(strokeWidth = 20.dp)
            }
            if (uiState.error) {
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = uiState.errorMessage
                )
            }

            TextField(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                value = uiState.login,
                onValueChange = tabsTwoViewModel::onLoginChange,
                enabled = uiState.isLoading.not(),
            )

            TextField(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                value = uiState.password,
                onValueChange = tabsTwoViewModel::onPasswordChange,
                enabled = uiState.isLoading.not(),
            )


            Button(onClick = tabsTwoViewModel::onLoginClick) {
                Text("Login")
            }
        }
    }
}