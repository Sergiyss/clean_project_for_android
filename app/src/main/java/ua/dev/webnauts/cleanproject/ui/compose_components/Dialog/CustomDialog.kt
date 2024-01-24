package ua.dev.webnauts.cleanproject.ui.compose_components.Dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider

/**
 * Реализация диалового окна
 * https://www.sinasamaki.com/custom-dialog-animation-in-jetpack-compose/
 * https://gist.github.com/sinasamaki/daa825d96235a18822177a2b1b323f49?ref=sinasamaki.com
 *
 * */
@Composable
fun CustomDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {

    var showAnimatedDialog by remember { mutableStateOf(false) }

    LaunchedEffect(showDialog) {
        if (showDialog) showAnimatedDialog = true
    }

    if (showAnimatedDialog) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            /**
             * Удаление системного фона
             * */
            (LocalView.current.parent as? DialogWindowProvider)?.window?.let { window ->
                window.setDimAmount(0f)
                window.setWindowAnimations(-1)
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                var animateIn by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) { animateIn = true }


//                val radius = if (animateIn && showDialog) {
//                    0.dp
//                } else {
//                    500.dp
//                }
//                val cornerRadius = animateDpAsState(targetValue = radius)

                /**
                 * Реализация фона.
                 * Кастом, если нужно добавить анимацию.
                 * */
                AnimatedVisibility(
                    visible = animateIn && showDialog,
                    enter = fadeIn(), //+ expandIn(expandFrom = Alignment.Center),
                    exit = fadeOut(), //+ shrinkOut(shrinkTowards = Alignment.Center)
                ) {
                    Box(
                        modifier = Modifier
                            .pointerInput(Unit) { detectTapGestures { onDismissRequest() } }
                            //.background(Color.Blue.copy(alpha = .16f), shape = RoundedCornerShape(cornerRadius.value))
                            .background(Color.Blue.copy(alpha = .36f))
                            .fillMaxSize()
//                            .animateContentSize(
//                                animationSpec = tween(
//                                    durationMillis = 500,
//                                    easing = FastOutSlowInEasing
//                                )
//                            )
                    )
                }
                AnimatedVisibility(
                    visible = animateIn && showDialog,
                    enter = fadeIn(spring(stiffness = Spring.StiffnessHigh)) + scaleIn(
                        initialScale = .8f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessMediumLow
                        )
                    ),
                    exit = slideOutVertically { it / 8 } + fadeOut() + scaleOut(targetScale = .95f)
                ) {
                    Box(
                        Modifier
                            .pointerInput(Unit) { detectTapGestures { } }
                            .shadow(8.dp, shape = RoundedCornerShape(16.dp))
                            .width(300.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(
                                MaterialTheme.colorScheme.surface,
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        content()
                    }

                    DisposableEffect(Unit) {
                        onDispose {
                            showAnimatedDialog = false
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ResetWarning(
    onDismissRequest: () -> Unit,
) {
    Column(Modifier.background(MaterialTheme.colorScheme.surface)) {

        var graphicVisible by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) { graphicVisible = true }

        AnimatedVisibility(
            visible = graphicVisible,
            enter = expandVertically(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                expandFrom = Alignment.CenterVertically,
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xE9EE8888),
                                Color(0xFFE4BD79),
                            )
                        )
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    imageVector = Icons.Rounded.Warning,
                    contentDescription = null,
                )
            }
        }

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(modifier = Modifier.height(8.dp))
            Text(
                text = "Reset Data",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
            Box(modifier = Modifier.height(8.dp))
            Text(text = "All your data will be permanently lost and phone will be listed for auction.")
        }

        Row(
            modifier = Modifier.height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onDismissRequest() }
                    .weight(1f)
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "CANCEL", fontWeight = FontWeight.Bold)
            }

            Box(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .width(2.dp)
                    .fillMaxHeight()
                    .background(
                        MaterialTheme.colorScheme.onSurface.copy(alpha = .08f),
                        shape = RoundedCornerShape(10.dp)
                    )
            )

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onDismissRequest() }
                    .weight(1f)
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "RESET", fontWeight = FontWeight.Bold, color = Color(0xFFFF332C))
            }
        }
    }
}