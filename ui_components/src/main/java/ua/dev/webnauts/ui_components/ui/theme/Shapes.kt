package ua.dev.webnauts.ui_components.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

data class CustomShapes(
    /**0dp*/
    val zero: RoundedCornerShape = RoundedCornerShape(0.dp),
    /**10dp*/
    val small: RoundedCornerShape = RoundedCornerShape(10.dp),
    /**15dp*/
    val medium: RoundedCornerShape = RoundedCornerShape(15.dp),
    /**18dp*/
    val large: RoundedCornerShape = RoundedCornerShape(18.dp),
    /**25%*/
    val quarter: RoundedCornerShape = RoundedCornerShape(25),
    /**50%*/
    val half: RoundedCornerShape = RoundedCornerShape(50)
)

val LocalShapes = compositionLocalOf { CustomShapes() }
val MaterialTheme.customShapes: CustomShapes
    @Composable
    @ReadOnlyComposable
    get() = LocalShapes.current