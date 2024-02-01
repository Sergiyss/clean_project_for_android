package ua.dev.webnauts.cleanproject.screen.base_creen.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import ua.dev.webnauts.cleanproject.R

@Composable
fun BackgroundBox(
    content: @Composable() (BoxScope.() -> Unit)
){
    val infiniteTransition = rememberInfiniteTransition()
    val infiniteTransition2 = rememberInfiniteTransition()
    val sizeValue by infiniteTransition.animateFloat(
        initialValue = -20.dp.value,
        targetValue = 20.dp.value,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ), label = "loader"
    )

    val sizeValue2 by infiniteTransition2.animateFloat(
        initialValue = 0.dp.value,
        targetValue = 20.dp.value,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ), label = "loader"
    )


    Box(modifier = Modifier
        .fillMaxSize(1f)
        .background(color = Color.Black)
        .paint(
            // Replace with your image id
            painterResource(id = R.drawable.bacgraund),
            contentScale = ContentScale.FillBounds
        )
        ){

        content()

        Image(painter = painterResource(id = R.drawable.neon_1), contentDescription = "neon",
            modifier = Modifier.align(Alignment.TopStart).offset(y = -10.dp).alpha(.6f)
                .blur (radiusX = 60.dp, radiusY = 60.dp)
                .graphicsLayer(
                    scaleX = 1.2f,
                    scaleY = 1.2f,
                    rotationX = sizeValue
                )

                .zIndex(-1f)
        )

        Image(painter = painterResource(id = R.drawable.neon_2), contentDescription = "neon",
            modifier = Modifier.align(Alignment.BottomEnd).offset(y = 10.dp).alpha(.6f)
                .blur (radiusX = 60.dp, radiusY = 60.dp)
                .graphicsLayer(
                    scaleX = 1.4f,
                    scaleY = 1.4f,
                    rotationX = sizeValue2
                )

                .zIndex(-1f)
        )
    }
}