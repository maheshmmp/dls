package com.heb.centralmarket.uicart.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.component.ToggleItemControlDefaults.PILL_PADDING
import com.heb.centralmarket.uicart.component.ToggleItemControlDefaults.TOGGLE_ELEVATION
import com.heb.centralmarket.uicart.themesystem.UICHeight
import com.heb.centralmarket.uicart.themesystem.UICShape
import com.heb.centralmarket.uicart.themesystem.UICTheme

/**
 * A flowing segmented control composable with animated pill indicator.
 *
 * @param items List of tab labels to display.
 * @param selectedIndex The index of the currently selected tab.
 * @param onItemSelected Callback invoked when a tab is selected, with the selected index.
 * @param modifier Modifier to be applied to the control.
 * @param pillColor Color of the animated pill indicator.
 * @param backgroundColor Background color of the segmented control.
 * @param selectedTextColor Text color for the selected tab.
 * @param unselectedTextColor Text color for unselected tabs.
 * @param enabled Whether the control is enabled for interaction.
 */
@Composable
fun HeaderToggleControl(
    items: List<String>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    pillColor: Color = UICTheme.colorScheme.brand.buttonPrimaryTonal.core,
    backgroundColor: Color = UICTheme.colorScheme.structural.elevated,
    selectedTextColor: Color = UICTheme.colorScheme.brand.buttonPrimaryTonal.onCore,
    unselectedTextColor: Color = UICTheme.colorScheme.txt.primary,
    enabled: Boolean,
) {
    BoxWithConstraints(
        modifier = modifier
            .height(UICHeight.height.fulfilmentToggleControlHeight)
            .shadow(
                elevation = TOGGLE_ELEVATION,
                shape = UICShape.shapes.fullRoundCornerShape,
                spotColor = Color(0xFF000000),
                ambientColor = Color(0x0A000000)
            )
            .clip(UICShape.shapes.fullRoundCornerShape)
            .background(backgroundColor)
    ) {
        val segmentWidth = maxWidth / items.size
        // targetOffset is left edge of the segment; add pillPadding to inset the pill
        val targetOffsetBase = segmentWidth * selectedIndex
        val pillTargetOffset = targetOffsetBase + PILL_PADDING

        // Sliding pill offset animation (animatedOffset already includes the pillPadding)
        val animatedOffset by animateDpAsState(
            targetValue = pillTargetOffset,
            animationSpec = spring(
                stiffness = Spring.StiffnessMediumLow,
                dampingRatio = Spring.DampingRatioNoBouncy
            )
        )

        // Draw the pill
        HeaderToggleSegmentedPill(
            segmentWidth = segmentWidth,
            animatedOffset = animatedOffset,
            pillColor = pillColor,
            pillPadding = PILL_PADDING,
            enabled = enabled
        )

        // Foreground tappable items
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clip(UICShape.shapes.fullRoundCornerShape)
        ) {
            items.forEachIndexed { index, label ->
                Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    val toggleItem = UICToggleItem(
                        toggleItemLabel = label,
                        toggleItemIndex = index,
                        toggleItemSelectedIndex = selectedIndex,
                    )
                    HeaderToggleItem(
                        toggleItem = toggleItem,
                        onItemSelected = onItemSelected,
                        selectedTextColor = selectedTextColor,
                        unselectedTextColor = unselectedTextColor,
                        enabled = enabled
                    )
                }
            }
        }
    }
}

// Default values for toggle item control appearance
private object ToggleItemControlDefaults {
    val PILL_PADDING = 4.dp
    val TOGGLE_ELEVATION = 3.dp
}
