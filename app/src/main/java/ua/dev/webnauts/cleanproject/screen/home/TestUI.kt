package ua.dev.webnauts.cleanproject.screen.home

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDecay
import androidx.compose.animation.core.animateTo
import androidx.compose.animation.core.spring
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue


import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.MotionDurationScale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.dev.webnauts.cleanproject.R
import java.lang.Math.abs


@Composable
private fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

@Composable
fun ProductCard() {

    val customizationTranslationY = remember { Animatable(0f) }
    var customizationSize by remember { mutableStateOf<IntSize>(IntSize(0, 0)) }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    val lazyState = rememberLazyListState()
    val isDragged by lazyState.interactionSource.collectIsDraggedAsState()
    val isScrollingUp = lazyState.isScrollingUp()

    Box {
        // Изображение или видео на фоне
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    // Когда анимация работает, то мы сдвигаем изображение координировано с ней
                    translationY = if (customizationTranslationY.isRunning) {
                        customizationTranslationY.value - customizationSize.height.toFloat()
                    } else if (lazyState.firstVisibleItemIndex == 0) {
                        // Иначе картинка следует за ручным скроллом
                        -lazyState.firstVisibleItemScrollOffset.toFloat()
                    } else {
                        0f
                    }
                               },
            painter = painterResource(id = R.drawable.test_image),
            contentDescription = null
        )

        /*
         * Создали свой хендлер для обработки события, когда мы отпускаем палец
         * Основная идея – если двигаемся вниз и преодолели границу,
         * то делаем автоскролл до следующего элемента
         *
         * Иначе если скроллим наверх и видим первый элемент,
         * то возвращаемся на самый верх
         */
        LaunchedEffect(isDragged) {
            if (isDragged) return@LaunchedEffect
            if (lazyState.firstVisibleItemIndex != 0) return@LaunchedEffect

            if (lazyState.firstVisibleItemScrollOffset > 200 && !isScrollingUp) {
                lazyState.animateScrollToItem(1)
            } else if (isScrollingUp) {
                lazyState.animateScrollToItem(0)
            }
        }

        Surface(
            modifier = Modifier,
            color = Color.Transparent,
            content = {
                LazyColumn(
                    state = lazyState,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    item {

                        /*
                         * Контейнер для блока кастомизации.
                         * Включает в себя панель с ингредиентами и табы - группы ингредиентов
                         */
                        Column {
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .requiredHeight(580.dp)
                                    .graphicsLayer {
                                        // Вертикально сдвигаем панель кастомайза.
                                        // Получается эффект, как на схеме выше
                                        translationY = customizationTranslationY.value
                                    }
                                    .onPlaced {
                                        // Сразу после измерения нужно сохранить размер и скрыть кастомайз
                                        coroutineScope.launch {
                                            if (customizationSize == IntSize.Zero) {
                                                customizationSize = it.size
                                                customizationTranslationY.snapTo(customizationSize.height.toFloat())
                                            }
                                        }
                                    }
                                    .background(Color.Magenta),
                            )

                            // Контейнер с табами
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .requiredHeight(130.dp)
                                    .clickable {
                                        coroutineScope.launch {
                                            // На клик мы анимируем выдвигающийся кастомайз
                                            val target = if (customizationTranslationY.value == 0f) {
                                                customizationSize.height.toFloat()
                                            } else {
                                                0f
                                            }
                                            customizationTranslationY.animateTo(target)
                                        }
                                    }
                                    .background(Color.Cyan)
                            )
                        }
                    }

                    // Остальной контент
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .requiredHeight(230.dp)
                                .background(Color.Green)
                        ){
                            Text(text = "Hello3")
                            Text(text = "Hello3")
                            Text(text = "Hello3")
                            Text(text = "Hello3")
                        }
                    }

                    /* ... */
                }
            }
        )
    }
}