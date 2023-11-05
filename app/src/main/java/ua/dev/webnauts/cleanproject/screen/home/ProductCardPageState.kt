package ua.dev.webnauts.cleanproject.screen.home

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import java.lang.Math.abs
import java.util.Collections.max
import kotlin.math.max

enum class CustomizationValue {
    Closed,
    Open,
    Initial,
    ;
}

private const val DUMP_VELOCITY_THRESHOLD = 0.2f
private const val ANIMATION_SPRING_STIFFNESS = 600f

@Composable
fun rememberProductCardPageState(
     initialItemIndex: Int = 0,
): ProductCardPageState = rememberSaveable(saver = ProductCardPageState.Saver) {
    ProductCardPageState(initialItemIndex)
}

@Stable
class ProductCardPageState(
   val initialItemIndex: Int = 0,
) {

    private var customizeCurrentValue by mutableStateOf(CustomizationValue.Initial)

    internal val lazyListState = LazyListState(firstVisibleItemIndex = initialItemIndex)

    /**
     * [Animatable], через который происходит изменение раскрытие блока с кастомайзом
     * Для взаимодействия с ним, используйте [closeCustomize] и [openCustomize]
     */
    private val customizeAnimatable = Animatable(0f)

    /**
     * Величина кастомайза
     * Также является максимальным сдвигом для состояния, чтобы кастомайз был закрыт
     */
    val customizeSize: Float
        get() = customizePanelSize - customizeTitleSize

    /**
     * Размер контейнера кастомайза => ингредиенты + надпись
     */
    var customizePanelSize by mutableStateOf(0f)

    /**
     * Полный размер кастомайза => панель с ингредиентами + список категорий
     * Нужен для того, чтобы знать, сколько места занимает полностью кастомайз в списке
     */
    var customizeFullSize by mutableStateOf(0f)

    /**
     * Размер заголовка, находящегося в контейнере кастомайза
     * Нужен для того, чтобы задвинутый кастомайз немного
     * выдвинуть на размер заголовка, чтобы он (заголово) был виден
     */
    var customizeTitleSize by mutableStateOf(0f)

    /**
     * Величина сдвига кастомайза
     * - (0) – кастомайз открыт
     * - (customizeSize) – кастомайз закрыт
     */
    val customizeTranslationY: Float
        get() = customizeAnimatable.value

    /**
     * Величина сдвига фонового видео
     * -  (0) – кастомайз закрыт, фоновое видео видно полностью
     * - (-customizeSize) – кастомайз открыт, фоновое видео синхронно с ним сдвинуто наверх
     */
    val customizeTranslationImageY: Float
        get() = customizeTranslationY - customizeSize

    /**
     *
     */
    val customizeScrollOffset: Int
        get() = lazyListState.firstVisibleItemScrollOffset

    /**
     * Степень скролла относительно кастомайза в карточке
     * - (0) – скролл находится в самой верхней точке
     * - (1) – мы полностью проскроллили кастомайз
     */
    val customizeScrollFraction: Float
        get() {
            if (lazyListState.firstVisibleItemIndex > 0) {
                return 1f
            }

            if (customizeFullSize == 0f) {
                return 0f
            }

            return customizeScrollOffset / customizeFullSize
        }

    /**
     * Степень "открытости" кастомайза.
     * Показывает, насколько он выдвинут и виден пользователю
     * P.S Это не означает, что он готов общаться и заводить друзей
     *
     * - (0) – кастомайз закрыт, видим только торчащий заголовок
     * - (1) – кастомайз полностью открыт
     */
    val customizeOpenFraction: Float
        get() {
            if (customizeSize == 0f) {
                return 0f
            }

            return abs(customizeTranslationImageY) / customizeSize
        }

    /**
     * [Float], который является максимумом между [customizeOpenFraction] и [customizeScrollFraction]
     * Это нужно, потому что некоторые Composable одинаково
     * реагируют как на скролл, так и на открытие кастомайза
     */
    val customizeFractionCombined: Float
        get() = max(customizeOpenFraction, customizeScrollFraction)

    /**
     * Открыть кастомайз
     *
     * - [animated] – закрыть анимированно или нет
     */
    suspend fun openCustomize(animated: Boolean = true) {
        customizeCurrentValue = CustomizationValue.Open
        if (animated) {
            customizeAnimatable.animateTo(0f)
        } else {
            customizeAnimatable.snapTo(0f)
        }
    }

    private suspend fun closeCustomize(
        animated: Boolean = true,
    ) {
        customizeCurrentValue = CustomizationValue.Closed

        if (animated) {
            customizeAnimatable.animateTo(customizeSize)
        } else {
            customizeAnimatable.snapTo(customizeSize)
        }
    }

    companion object {

        val Saver: Saver<ProductCardPageState, *> = listSaver(
            save = {
                listOf<Any>(
                    it.lazyListState.firstVisibleItemIndex,
                )
            },
            restore = {
                ProductCardPageState(
                    initialItemIndex = it[0] as Int,
                )
            }
        )
    }
}