package com.heb.centralmarket.uicart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.component.HeaderToggleSegmentedPillDefaults.PILL_BORDER_WIDTH
import com.heb.centralmarket.uicart.themesystem.UICShape
import com.heb.centralmarket.uicart.themesystem.UICTheme

/**
 * Draws the animated pill indicator for a flowing segmented control.
 *
 * @param modifier Modifier to be applied to the pill.
 * @param segmentWidth The width of each segment/tab in the control.
 * @param animatedOffset The animated horizontal offset for the pill's position.
 * @param pillColor The background color of the pill.
 * @param pillPadding The padding to apply inside the pill (default is 3.dp).
 * @param enabled Whether the pill is enabled (affects alpha/opacity).
 */
@Composable
fun HeaderToggleSegmentedPill(
    modifier: Modifier = Modifier,
    segmentWidth: Dp,
    animatedOffset: Dp,
    pillColor: Color,
    pillPadding: Dp,
    enabled: Boolean = true,
) {
    val pillWidth = segmentWidth - (pillPadding * 2)
    Box(
        modifier = modifier
            .offset(x = animatedOffset)
            .width(pillWidth)
            .fillMaxHeight()
            .padding(top = pillPadding, bottom = pillPadding)
            .alpha(if (enabled) 1f else 0.4f)
            .clip(UICShape.shapes.fullRoundCornerShape)
            .border(
                width = PILL_BORDER_WIDTH,
                color = UICTheme.colorScheme.brand.buttonPrimaryTonal.border,
                shape = UICShape.shapes.fullRoundCornerShape
            )
            .background(pillColor)
    )
}


// Default values for toggle item control appearance
private object HeaderToggleSegmentedPillDefaults {
    val PILL_BORDER_WIDTH = 1.dp
}