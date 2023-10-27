package aci.personal.piggybank.ui.composables.common.atoms.charts

import aci.personal.piggybank.ui.theme.Pink3f
import aci.personal.piggybank.ui.theme.Pink8f
import aci.personal.piggybank.ui.theme.spacing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class BarChartDirection {
    TOP, BOTTOM, LEFT, RIGHT
}

@Composable
fun BarChart(
    modifier: Modifier,
    containerSize: Dp,
    data: Map<String, Float>,
    unit: String,
    barDirection: BarChartDirection = BarChartDirection.LEFT,
    barSpacing: Float = 0.0F,
    labelTextSize: TextUnit = 10.sp,
    basePadding: Dp = MaterialTheme.spacing.extraSmall,
    animDuration: Int = 1000
) {
    val maxData = data.values.maxOrNull() ?: 1f

    val colors = listOf(
        Pink8f,
        Pink3f
    )

    var animationPlayed by remember { mutableStateOf(false) }

    val animateSize by animateFloatAsState(
        targetValue = if (animationPlayed) containerSize.value * 1f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        ),
        label = ""
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(modifier = modifier.width(animateSize.dp)) {
        Canvas(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
            val barThickness = size.height / data.values.size

            data.values.forEachIndexed { index, value ->
                val barLength = size.width * (value / maxData)

                val barRect = calcRectangle(
                    index = index,
                    size = size,
                    barLength = barLength,
                    barThickness = barThickness,
                    barDirection = barDirection,
                    barSpacing = barSpacing
                )

                // Dibuja la barra
                drawRect(
                    color = colors[index],
                    topLeft = barRect.topLeft,
                    size = barRect.size
                )

                val labelText = "$value $unit"

                val labelSize = labelTextSize.toPx()
                val paint = android.graphics.Paint()
                paint.textSize = labelSize

                val labelWidth = paint.measureText(labelText) + basePadding.toPx()
                val labelHeight = labelSize

                val labelPos = calcLabelPosition(
                    size = size,
                    barThickness = barThickness,
                    rectangle = barRect,
                    direction = barDirection,
                    labelWidth = labelWidth,
                    labelHeigth = labelHeight
                )

                // Agrega etiquetas o leyendas
                drawContext.canvas.nativeCanvas.drawText(
                    labelText,
                    labelPos.x,
                    labelPos.y,
                    Paint().asFrameworkPaint().apply {
                        color = Color.Black.toArgb()
                        textSize = labelSize
                    }
                )
            }
        }
    }
}

private fun calcRectangle(
    index: Int,
    size: androidx.compose.ui.geometry.Size,
    barLength: Float,
    barThickness: Float,
    barDirection: BarChartDirection,
    barSpacing: Float
): Rect {
    val spacing = if (index == 0) 0.0F else barSpacing

    when (barDirection) {
        BarChartDirection.LEFT -> {
            return Rect(
                left = size.width - barLength,
                top = index * barThickness + spacing,
                right = size.width,
                bottom = index * barThickness + spacing + barThickness
            )
        }
        BarChartDirection.TOP -> {
            return Rect(
                left = index * barThickness + spacing,
                top = size.height - barLength,
                right = index * barThickness + barThickness,
                bottom = size.height
            )
        }
        BarChartDirection.RIGHT -> {
            return Rect(
                left = 0.0F,
                top = index * barThickness + spacing,
                right = size.width - barLength,
                bottom = index * barThickness + spacing + barThickness
            )
        }
        BarChartDirection.BOTTOM -> {
            return Rect(
                left = index * barThickness + spacing,
                top = 0.0F,
                right = index * barThickness + spacing + barThickness,
                bottom = barLength
            )
        }
    }
}

private data class LabelPosition(
    val x: Float,
    val y: Float
)

private fun calcLabelPosition(
    size: androidx.compose.ui.geometry.Size,
    barThickness: Float,
    rectangle: Rect,
    direction: BarChartDirection,
    labelWidth: Float,
    labelHeigth: Float
): LabelPosition {
    when (direction) {
        BarChartDirection.LEFT -> {
            val barBase = rectangle.topRight.y

            return LabelPosition(
                x = size.width - labelWidth,
                y = barBase + barThickness / 2 + labelHeigth / 2
            )
        }
        BarChartDirection.TOP -> {
            val barBase = rectangle.bottomLeft.x

            return LabelPosition(
                x = barBase + barThickness / 2 - 20,
                y = size.height - 4
            )
        }
        BarChartDirection.RIGHT -> {
            val barBase = rectangle.topLeft.y

            return LabelPosition(
                x = 4F,
                y = barBase + barThickness / 2 - 20
            )
        }
        BarChartDirection.BOTTOM -> {
            val barBase = rectangle.bottomRight.x

            return LabelPosition(
                x = barBase + barThickness / 2 - 20,
                y = 4F
            )
        }
    }
}
