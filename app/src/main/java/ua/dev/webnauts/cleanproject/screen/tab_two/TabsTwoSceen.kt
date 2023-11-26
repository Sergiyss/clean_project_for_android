package ua.dev.webnauts.cleanproject.screen.tab_two

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.ui.compose_ui.top_bars.DefaultTopBar
import ua.dev.webnauts.cleanproject.ui.theme.spacing

@Composable
fun TabsTwoScreen(
    appState: AppState,
) {
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