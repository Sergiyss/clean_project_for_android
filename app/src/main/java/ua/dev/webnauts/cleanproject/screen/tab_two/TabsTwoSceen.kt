package ua.dev.webnauts.cleanproject.screen.tab_two

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.navigation.NavRoutes
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

    val uiState by tabsTwoViewModel.collectWithLifecycle()

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

        }
    }
}