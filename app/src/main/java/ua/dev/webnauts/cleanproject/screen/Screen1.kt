package ua.dev.webnauts.cleanproject.screen

import androidx.compose.runtime.Composable
import ua.dev.webnauts.cleanproject.AppState

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.dev.webnauts.cleanproject.navigation.AppRoutes
import ua.dev.webnauts.cleanproject.screen.base_creen.components.Questionnaire
import ua.dev.webnauts.cleanproject.screen.base_creen.components.RequirementsAnalysis
import ua.dev.webnauts.cleanproject.screen.base_creen.components.TitleQues
import ua.dev.webnauts.cleanproject.ui.theme.CleanProjectTheme
import javax.inject.Inject
@Composable
fun Screen1(appState: AppState){
    Box(Modifier.fillMaxSize(1f)) {
        var showAnalytics  by remember { mutableStateOf( false ) }
        val coroutineScope = rememberCoroutineScope()

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 20.dp)
                .fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {

                TitleQues()
                RequirementsAnalysis(showAnalytics)
                Questionnaire(up={
                    showAnalytics = true
                    coroutineScope.launch {
                        delay(4000)
                        appState.navController.navigate(AppRoutes.SCREEN2.route)
                    }
                }, down={
                    showAnalytics = true
                    coroutineScope.launch {
                        delay(4000)
                        appState.navController.navigate(AppRoutes.SCREEN2.route)
                    }
                })
            }
        }
    }
}