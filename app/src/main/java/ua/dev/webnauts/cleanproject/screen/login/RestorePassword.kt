package ua.dev.webnauts.cleanproject.screen.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ua.dev.webnauts.cleanproject.ui.compose_ui.top_bars.DefaultTopBar


@Composable
fun RestorePassword(navController: NavController) {
    Scaffold(
        topBar =
        {
            DefaultTopBar(
                title = "Restore Password",
                onClick = { navController.navigateUp() }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(paddingValues = it)
                .fillMaxSize(1f),
        ) {
            Text(text = "Restore Password")
        }
    }
}