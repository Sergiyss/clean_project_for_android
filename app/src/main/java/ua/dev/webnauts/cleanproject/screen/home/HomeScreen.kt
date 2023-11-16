package ua.dev.webnauts.cleanproject.screen.home


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ua.dev.webnauts.cleanproject.AppState
import ua.dev.webnauts.cleanproject.R
import ua.dev.webnauts.cleanproject.screen.login.LoginViewModel

@Composable
fun HomeScreen(appState: AppState,
            viewModel : LoginViewModel = hiltViewModel()
) {
    var animated by remember { mutableStateOf(false) }
    val rotation = remember { Animatable(initialValue = 0f) }
    val scale = remember { Animatable(initialValue = 1f) }

    LaunchedEffect(animated) {
       // if(animated == false ) return@LaunchedEffect
        rotation.animateTo(
            targetValue = if (animated) 180f else 0f,
            animationSpec = tween(durationMillis = 1000),
        )

        scale.animateTo(
            targetValue = if (animated) 3f else 1f,
            animationSpec = tween(durationMillis = 1000),
        )
    }

    Surface(modifier = Modifier.fillMaxSize(),
        color = Color(0xFFE5E5E5)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Home Screen")

            Box(modifier = Modifier
                .graphicsLayer(
                    rotationY = rotation.value,
                )
                .clickable {
                    animated = !animated
                }
                .height(150.dp)
                .width(150.dp)
                .border(1.dp, Color.Black)) {
                if(rotation.value < 150f) {
                    Image(
                        modifier = Modifier
                            .graphicsLayer(
                                scaleX = scale.value,
                                scaleY = scale.value,
                            )
                            .size(150.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.test_image),
                        contentDescription = "")
                }else{
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "12 + ${rotation.value}",
                        fontSize = 14.sp
                    )
                }

            }
        }
    }

}

