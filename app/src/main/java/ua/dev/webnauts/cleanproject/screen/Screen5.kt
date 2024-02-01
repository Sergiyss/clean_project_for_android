package ua.dev.webnauts.cleanproject.screen

import androidx.compose.runtime.Composable
import ua.dev.webnauts.cleanproject.AppState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.dev.webnauts.cleanproject.navigation.AppRoutes
import ua.dev.webnauts.cleanproject.screen.base_creen.components.SuccessScreen
@Composable
fun Screen5(appState: AppState){
    Box(Modifier.fillMaxSize(1f)) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 20.dp)
                .fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            SuccessScreen{
                appState.navController.navigate(AppRoutes.SCREEN6.route)
            }
        }
    }
}