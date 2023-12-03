package ua.dev.webnauts.cleanproject.utils

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ua.dev.webnauts.cleanproject.AppState

fun String.ukToUaOrDefault(): String {
    return if (this == "uk") "ua" else this
}

fun String.uaToUkOrDefault(): String {
    return if (this == "ua") "uk" else this
}

/**
 * Переход по навигации со следующем очищением стека
 * */
fun NavController.navigateAndClean(route: String) {
    navigate(route = route) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
    }
}
/**
 * Переход по навигации, уже включен в себя [launchSingleTop] true
 * */
fun NavController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }

fun NavGraphBuilder.createTransitionComposableArg(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        },
        arguments = arguments

    ) {
        content(it)
    }
}