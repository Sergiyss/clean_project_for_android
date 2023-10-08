package ua.dev.webnauts.cleanproject.utils.animation.navanimation

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.unit.IntOffset

fun enterTransitionHorizontally(initialOffsetX: Int, animationSpec: FiniteAnimationSpec<IntOffset>) =
    slideInHorizontally(initialOffsetX = { initialOffsetX }, animationSpec = animationSpec) + fadeIn(animationSpec = tween(easing = LinearEasing))
fun enterTransitionHorizontally(initialOffsetX: Int, durationMillis: Int) =
    slideInHorizontally(initialOffsetX = { initialOffsetX }, animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing)) + fadeIn(animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing))

fun exitTransitionHorizontally(initialOffsetX: Int, animationSpec: FiniteAnimationSpec<IntOffset>) =
    slideOutHorizontally(targetOffsetX = { initialOffsetX }, animationSpec = animationSpec) + fadeOut(animationSpec = tween(easing = LinearEasing))
fun exitTransitionHorizontally(initialOffsetX: Int, durationMillis: Int) =
    slideOutHorizontally(targetOffsetX = { initialOffsetX }, animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing)) + fadeOut(animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing))

fun popEnterTransitionHorizontally(initialOffsetX: Int, animationSpec: FiniteAnimationSpec<IntOffset>) =
    enterTransitionHorizontally(initialOffsetX, animationSpec)
fun popEnterTransitionHorizontally(initialOffsetX: Int, durationMillis: Int) =
    enterTransitionHorizontally(initialOffsetX, durationMillis)

fun popExitTransitionHorizontally(initialOffsetX: Int, animationSpec: FiniteAnimationSpec<IntOffset>) =
    exitTransitionHorizontally(initialOffsetX, animationSpec)
fun popExitTransitionHorizontally(initialOffsetX: Int, durationMillis: Int) =
    exitTransitionHorizontally(initialOffsetX, durationMillis)