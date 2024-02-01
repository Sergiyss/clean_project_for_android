package ua.dev.webnauts.cleanproject.screen.base_creen.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import  androidx.compose.ui.geometry.Size
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.core.graphics.drawable.toBitmap
import com.conena.nanokt.android.content.toPx
import ua.dev.webnauts.cleanproject.R
import java.lang.Float.max
import java.lang.Float.min
import kotlin.math.roundToInt
import kotlin.random.Random

@Preview
@Composable
fun RequirementsAnalysis(showAnalytics: Boolean = false) {


    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        // Rounded container with a grid
        Box(
            modifier = Modifier
                .border(2.dp, color = Color.White, shape = RoundedCornerShape(18.dp))
                .fillMaxWidth(1f)
                .height(280.dp)
                .clip(RoundedCornerShape(15.dp)),
            contentAlignment = Alignment.Center
        ) {
            Grid(R.drawable.point, showAnalytics = showAnalytics)
        }
    }
}


data class Point(val x: Float, val y: Float, val point: Boolean? = null, val icon: Int? = null)

@Composable
fun Grid(imageResId: Int, showAnalytics: Boolean) {
    val values = listOf(
        Point(0f, 12f),
        Point(3f, 10f),
        Point(3f, 8f),
        Point(4f, 6f),
        Point(5f, 10f),
        Point(6f, 9f),
        Point(7f, 6f),
        Point(8f, 7f),
        Point(13f, 0f),
    )

    val context = LocalContext.current
    val local = LocalDensity.current.density


    val imageDrawable = remember(imageResId) {
        context.resources.getDrawable(imageResId, null)
    }

    val bitmap = remember(imageDrawable) {
        imageDrawable.toBitmap(
            width = (30 * local).roundToInt(),
            height = (30 * local).roundToInt()
        )
    }.asImageBitmap()


//    val values = (14 downTo 0).mapIndexed { index, it ->
//        val min = max(it.toFloat() -4f, 0f)
//        val p = Point(
//            x = index.toFloat(),
//            y = min + Random.nextFloat() * (it.toFloat() - min)
//        )
//        println("x - > $p")
//
//        p
//    }

    val middleIndex = values.size / 2
    val highestValueInMiddle =
        values.subList(middleIndex, middleIndex + 1).maxByOrNull { it.y }?.y ?: 0f

    val highestValueInMiddleX =
        values.subList(middleIndex, middleIndex + 1).maxByOrNull { it.x }?.x ?: 0f



    println("Самое высокое значение из середины массива: $highestValueInMiddleX -- $highestValueInMiddle")


    // find max and min value of X, we will need that later
    val minXValue = 0f
    val maxXValue = 14f

    // find max and min value of Y, we will need that later
    val minYValue = 0f
    val maxYValue = 14f

    // Animatable to control the progress of the animation
    val progress = remember { Animatable(0f) }

    LaunchedEffect(showAnalytics) {
        if(!showAnalytics) return@LaunchedEffect
        progress.animateTo(1f, animationSpec = tween(1000)) // adjust the duration as needed
    }

    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(280.dp)
            .drawBehind {
                // draw grid
                val rows = 14
                val cols = 14

                val cellWidth = size.width / cols
                val cellHeight = size.height / rows

                // Draw horizontal lines
                for (i in 1 until rows) {
                    drawLine(
                        color = Color.White.copy(alpha = .6f),
                        strokeWidth = 2.dp.toPx(),
                        start = Offset(x = 0f, y = i * cellHeight),
                        end = Offset(x = size.width, y = i * cellHeight)
                    )
                }

                // Draw vertical lines
                for (i in 1 until cols) {
                    drawLine(
                        color = Color.White.copy(alpha = .6f),
                        strokeWidth = 2.dp.toPx(),
                        start = Offset(x = i * cellWidth, y = 0f),
                        end = Offset(x = i * cellWidth, y = size.height)
                    )
                }
                if (showAnalytics) {
                    // Calculate the current progress based on the animation
                    val currentProgress = progress.value

                    val pixelPoints = values.map {
                        val x = it.x.mapValueToDifferentRange(
                            inMin = minXValue,
                            inMax = maxXValue,
                            outMin = 0f,
                            outMax = size.width
                        ) * currentProgress

                        val y = it.y.mapValueToDifferentRange(
                            inMin = minYValue,
                            inMax = maxYValue,
                            outMin = size.height,
                            outMax = 0f
                        ) * currentProgress

                        Point(x, y)
                    }

                    val path = Path()
                    // Fill the path based on the current progress
                    pixelPoints
                        .take((pixelPoints.size * currentProgress).toInt())
                        .forEachIndexed { index, point ->
                            if (index == 0) {
                                path.moveTo(point.x, point.y)
                            } else {
                                path.lineTo(point.x, point.y)
                            }
                        }


                    drawPath(
                        path,
                        color = Color(0xFFA6310C),
                        style = Stroke(width = 16f)
                    )


                    // Draw circle at Point(5f, 10f)
                    val circleX = highestValueInMiddleX.mapValueToDifferentRange(
                        minXValue,
                        maxXValue,
                        0f,
                        size.width
                    ) * currentProgress
                    val circleY = highestValueInMiddle.mapValueToDifferentRange(
                        minYValue,
                        maxYValue,
                        size.height,
                        0f
                    ) * currentProgress

                    // Check if the animation is complete before displaying the point and image
                    if (currentProgress == 1f) {
                        // Draw circle at the desired point

                        drawCircle(
                            color = Color.Green,
                            center = Offset(circleX, circleY),
                            radius = 8.dp.toPx()
                        )


                        drawCircle(
                            color = Color.White,
                            center = Offset(circleX, circleY),
                            radius = 8.dp.toPx() + 2.dp.toPx(), // Adjust the border width as needed
                            style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round),
                            alpha = 1f
                        )

                        drawImage(
                            image = bitmap,
                            alpha = 1f,
                            topLeft = Offset(
                                circleX - bitmap.width / 2f,
                                (circleY - bitmap.height / 2f) - 100f
                            )
                        )
                    }


                }
            }
    )
}


fun Float.mapValueToDifferentRange(
    inMin: Float,
    inMax: Float,
    outMin: Float,
    outMax: Float
) = (this - inMin) * (outMax - outMin) / (inMax - inMin) + outMin