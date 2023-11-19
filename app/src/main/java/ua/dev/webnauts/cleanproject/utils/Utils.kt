package ua.dev.webnauts.cleanproject.utils

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ua.dev.webnauts.cleanproject.AppState

fun String.ukToUaOrDefault(): String {
    return if (this == "uk") "ua" else this
}

fun String.uaToUkOrDefault(): String {
    return if (this == "ua") "uk" else this
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.createTransitionComposableArg(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
//        enterTransition = {
//            slideIntoContainer(
//                AnimatedContentTransitionScope.SlideDirection.Left,
//                animationSpec = tween(700)
//            )
//        },
//        exitTransition = {
//            slideOutOfContainer(
//                AnimatedContentTransitionScope.SlideDirection.Right,
//                animationSpec = tween(700)
//            )
//        },
        arguments = arguments

    ) {
        content(it)
    }
}