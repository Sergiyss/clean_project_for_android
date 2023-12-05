@file:OptIn(ExperimentalAnimationApi::class)

package ua.dev.webnauts.cleanproject

import android.content.Context
import android.content.res.Resources
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ua.dev.webnauts.cleanproject.navigation.navs.navigateToHome
import ua.dev.webnauts.cleanproject.navigation.navs.navigateToProfile
import ua.dev.webnauts.cleanproject.navigation.navs.navigateToTab
import ua.dev.webnauts.cleanproject.navigation.Graph
import ua.dev.webnauts.cleanproject.navigation.NavRoutes
import ua.dev.webnauts.cleanproject.network.network_monitor.NetworkMonitor

//Где скрывать нижнюю навигацию
val routesWithoutBottomBar = listOf(
    NavRoutes.Profile,
    NavRoutes.Login
)


sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Error<T>(val data: T) : UiState<T>()
    data class Content<T>(val data: T) : UiState<T>()
    // Добавьте другие состояния по необходимости
}

@Stable
data class AppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val networkMonitor: NetworkMonitor,
    val resources: Resources,
    val context: Context,
) {
    @Composable
    fun showSnackBar(message: String, duration: SnackbarDuration = SnackbarDuration.Short) {

    }
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )


    fun navigateToTopLevelDestination(graph: Graph) {
        trace("Navigation: ${graph.graph}") {
            val topLevelNavOptions = navOptions {
                // Всплывающее окно к начальному пункту назначения графика
                // избегаем создания большого стека пунктов назначения
                // в заднем стеке, когда пользователи выбирают элементы
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Избегайте нескольких копий одного и того же места назначения, когда
                // повторный выбор того же элемента
                launchSingleTop = true
                // Восстанавливаем состояние при повторном выборе ранее выбранного элемента
                restoreState = true
            }

            when (graph) {
                Graph.Home -> navController.navigateToHome(topLevelNavOptions)
                Graph.TabTwo -> navController.navigateToTab(topLevelNavOptions)
                Graph.Profile -> navController.navigateToProfile(topLevelNavOptions)
                else ->  navController.navigateToHome(topLevelNavOptions)
            }
        }
    }

}

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    networkMonitor: NetworkMonitor,
    resources: Resources = LocalContext.current.resources,
    context: Context = LocalContext.current,
) = remember(scaffoldState, navController, coroutineScope, resources, context) {

    AppState(
        scaffoldState = scaffoldState,
        navController = navController,
        coroutineScope = coroutineScope,
        resources = resources,
        networkMonitor = networkMonitor,
        context = context
    )
}
