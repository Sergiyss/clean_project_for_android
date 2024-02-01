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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.dev.webnauts.cleanproject.navigation.AppRoutes
import ua.dev.webnauts.cleanproject.screen.base_creen.components.Questionnaire
import ua.dev.webnauts.cleanproject.screen.base_creen.components.RequirementsAnalysis
import ua.dev.webnauts.cleanproject.screen.base_creen.components.TitleQues
import ua.dev.webnauts.cleanproject.ui.theme.CleanProjectTheme
import javax.inject.Inject
@Composable
fun Screen4(appState: AppState){
    Box(Modifier.fillMaxSize(1f)) {
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

                TitleQues(
                    title = "В прошлую пятницу рынок узнал о снижении дивидендов акций банка Санкт-Петербург с 16.5% до 13,5%."
                )
                RequirementsAnalysis()
                Questionnaire(
                    title = "Вопрос 4/4",
                    subTitle = "Куда направится цена акций BSPB (Банк Санкт-Петербург) ?",
                    up={
                    appState.navController.navigate(AppRoutes.SCREEN5.route)
                }, down={
                    appState.navController.navigate(AppRoutes.SCREEN5.route)
                })
            }
        }
    }
}