package ua.dev.webnauts.cleanproject.utils.animation.navanimation

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.ui.unit.IntOffset

fun enterTransitionVertically(initialOffsetY: Int, animationSpec: FiniteAnimationSpec<IntOffset>) =
    slideInVertically(initialOffsetY = { initialOffsetY }, animationSpec = animationSpec) + fadeIn()
fun enterTransitionVertically(initialOffsetY: Int, durationMillis: Int) =
    slideInVertically(initialOffsetY = { initialOffsetY }, animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing)) + fadeIn(animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing))

fun exitTransitionVertically(initialOffsetY: Int, animationSpec: FiniteAnimationSpec<IntOffset>) =
    slideOutVertically(targetOffsetY = { initialOffsetY }, animationSpec = animationSpec) + fadeOut()
fun exitTransitionVertically(initialOffsetY: Int, durationMillis: Int) =
    slideOutVertically(targetOffsetY = { initialOffsetY }, animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing)) + fadeOut(animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing))

fun popEnterTransitionVertically(initialOffsetY: Int, animationSpec: FiniteAnimationSpec<IntOffset>) =
    enterTransitionVertically(initialOffsetY, animationSpec)
fun popEnterTransitionVertically(initialOffsetY: Int, durationMillis: Int) =
    enterTransitionVertically(initialOffsetY, durationMillis)

fun popExitTransitionVertically(initialOffsetY: Int, animationSpec: FiniteAnimationSpec<IntOffset>) =
    exitTransitionVertically(initialOffsetY, animationSpec)
fun popExitTransitionVertically(initialOffsetY: Int, durationMillis: Int) =
    exitTransitionVertically(initialOffsetY, durationMillis)