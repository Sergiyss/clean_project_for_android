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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ua.dev.webnauts.cleanproject.navigation.settings_navigation.NavRoutes
import ua.dev.webnauts.cleanproject.network.network_monitor.NetworkMonitor

//Где скрывать нижнюю навигацию
val routesWithoutBottomBar = listOf(
    NavRoutes.Profile,
    NavRoutes.Login
)


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
    fun ShowSnackBar(message: String, duration: SnackbarDuration = SnackbarDuration.Short) {
        /*TODO: snackbar content*/
    }

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )


//    val shouldShowBottomBar: Boolean
//        @Composable get() = navController
//            .currentBackStackEntryAsState().value?.destination?.route !in routesWithoutBottomBar

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
