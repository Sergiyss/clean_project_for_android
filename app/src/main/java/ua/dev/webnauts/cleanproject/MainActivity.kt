package ua.dev.webnauts.cleanproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import ua.dev.webnauts.cleanproject.navigation.Graph
import ua.dev.webnauts.cleanproject.navigation.Navigation
import ua.dev.webnauts.cleanproject.network.network_monitor.NetworkMonitor
import ua.dev.webnauts.cleanproject.screen.base_creen.components.BackgroundBox
import ua.dev.webnauts.cleanproject.ui.theme.CleanProjectTheme
import javax.inject.Inject
/***
 *  Проект с одним модулем...
 *
 *  Для быстрого старта, где уже есть всё необходимое, чтобы начать программировать
 *  и накидывать дизайн, не отвлекаясь на подключение либ и настройки проекта, навигации
 * */


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            var startScreen  by remember { mutableStateOf<String?>( null ) }
            val animationState = remember { MutableTransitionState(false) }

            CleanProjectTheme {
                BackgroundBox{
                    RootScreen(networkMonitor = networkMonitor, startDestination = Graph.Home.graph)
                }
            }
        }
    }
}

/***
 * Стартовая точка для [rootNavigation]
 * Здесь будут всё основные экраны. Отделил по той причине, что хочу [WelcomeScreen]
 * вынести из навигации вверх. Во многих моментах она будет только мешать.
 * */
@Composable
fun RootScreen(networkMonitor: NetworkMonitor, startDestination : String){
    val appState  = rememberAppState(networkMonitor = networkMonitor,)
    var selectedItem by remember { mutableStateOf(0) }


    appState.showSnackBar("hi", duration = androidx.compose.material.SnackbarDuration.Short)


    val currentDestination =  appState.currentDestination



            /**
             * @param startDestination - start destination for navigation
             * @param token - token for user,
             * @param startDestination start Screen
             * */
            Navigation(
                appState = appState,
                startDestination = startDestination
            )



}