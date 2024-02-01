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
fun Screen3(appState: AppState){
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
                    title = "Компании Аэрофлот подписали соглашение на поставку 339 самолетов в связи с работами по импортозамещению за 2024 год."
                )
                RequirementsAnalysis()
                Questionnaire(
                    title = "Вопрос 3/4",
                    subTitle = "Как отреагируют акции AFLT (Аэрофлот)?",
                    up={
                    appState.navController.navigate(AppRoutes.SCREEN4.route)
                }, down={
                    appState.navController.navigate(AppRoutes.SCREEN4.route)
                })
            }
        }
    }
}