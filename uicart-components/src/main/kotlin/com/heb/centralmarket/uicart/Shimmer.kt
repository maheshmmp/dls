package com.heb.centralmarket.uicart

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.themesystem.UICTheme
import kotlin.math.cos
import kotlin.math.sin

/**
 * Enhanced Shimmer modifier with better performance and smoother animation
 */
@Composable
fun Modifier.shimmer(
    isVisible: Boolean = true,
    colors: List<Color> = listOf(
        UICTheme.colorScheme.structural.bgSecondary,
        UICTheme.colorScheme.structural.bgPrimary,
        UICTheme.colorScheme.structural.bgSecondary,
    ),
    animationSpec: InfiniteRepeatableSpec<Float> = infiniteRepeatable(
        animation = tween(durationMillis = 2400, easing = LinearEasing), // Faster shimmer animation
        repeatMode = RepeatMode.Restart
    ),
    shape: Shape = RoundedCornerShape(4.dp),
    angleOfAxisY: Float = 0f,
): Modifier = composed {
    if (!isVisible) return@composed this

    val transition = rememberInfiniteTransition(label = "shimmer_transition")
    val progress by transition.animateFloat(
        initialValue = -1f,
        targetValue = 2f,
        animationSpec = animationSpec,
        label = "shimmer_translate"
    )

    val angleRad = Math.toRadians(angleOfAxisY.toDouble())
    val dx = cos(angleRad).toFloat()
    val dy = sin(angleRad).toFloat()

    val start = Offset(x = 300f * progress * dx, y = 300f * progress * dy)
    val end = Offset(x = start.x + 60f * dx, y = start.y + 60f * dy) // Narrow shimmer width

    val brush = Brush.linearGradient(
        colors = colors,
        start = start,
        end = end,
        tileMode = TileMode.Clamp
    )

    this
        .clip(shape)
        .background(brush)
}