package ua.dev.webnauts.cleanproject.utils.animation.navanimation

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.ui.unit.IntSize

fun enterTransitionExpand(animationSpec: FiniteAnimationSpec<IntSize>) =
    expandIn(animationSpec = animationSpec) + fadeIn(animationSpec = tween(easing = LinearEasing))
fun enterTransitionExpand(durationMillis: Int) =
    expandIn(animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing)) + fadeIn(animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing))

fun exitTransitionExpand(animationSpec: FiniteAnimationSpec<IntSize>) =
    shrinkOut(animationSpec = animationSpec) + fadeOut(animationSpec = tween(easing = LinearEasing))
fun exitTransitionExpand(durationMillis: Int) =
    shrinkOut(animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing)) + fadeOut(animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing))

fun popEnterTransitionExpand(animationSpec: FiniteAnimationSpec<IntSize>) =
    enterTransitionExpand(animationSpec)
fun popEnterTransitionExpand(durationMillis: Int) =
    enterTransitionExpand(durationMillis)

fun popExitTransitionExpand(animationSpec: FiniteAnimationSpec<IntSize>) =
    exitTransitionExpand(animationSpec)
fun popExitTransitionExpand(durationMillis: Int) =
    exitTransitionExpand(durationMillis)