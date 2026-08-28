package com.heb.centralmarket.uicart.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import com.heb.centralmarket.uicart.themesystem.UICShape
import com.heb.centralmarket.uicart.themesystem.UICTheme
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.component.ToggleItemDefaults.TOGGLE_ITEM_PADDING
import com.heb.centralmarket.uicart.themesystem.ColorTokens
import com.heb.centralmarket.uicart.utils.TestTags

/**
 * AppBarToggleItem displays a single toggle item for the segmented control in the app bar.
 * Handles selection, focus, pressed, and animated color transitions.
 *
 * @param toggleItem The current toggle item (label, index, selection, status, enabled).
 * @param onItemSelected Callback when this item is selected (provides the index).
 * @param selectedTextColor Color for selected text.
 * @param unselectedTextColor Color for unselected text.
 * @param enabled Whether this toggle item is enabled for interaction.
 */
@Composable
fun HeaderToggleItem(
    toggleItem: UICToggleItem,
    onItemSelected: (Int) -> Unit,
    selectedTextColor: Color,
    unselectedTextColor: Color,
    enabled: Boolean = true,
) {
    val colorMapper = AppBarToggleItemColorMapper(colorTokens = UICTheme.colorScheme)
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val toggleItemColors =
        colorMapper.mapColor(
            toggleStatus = toggleItem.toggleStatus
        )

    val isSelected = toggleItem.toggleItemIndex == toggleItem.toggleItemSelectedIndex
    toggleItem.toggleStatus = if (isSelected) ToggleStatus.ACTIVE else ToggleStatus.INACTIVE

    val textColor by animateColorAsState(
        targetValue = if (isSelected) selectedTextColor else unselectedTextColor,
        animationSpec = tween(200)
    )
    Box(
        modifier = Modifier
            .then(
                // Apply padding if pressed or focused or hovered
                other = if (isPressed || isHovered) Modifier.padding(TOGGLE_ITEM_PADDING)
                else if (isFocused) Modifier.padding(1.dp)
                else Modifier
            )
            .fillMaxHeight()
            .fillMaxWidth()
            .clip(UICShape.shapes.fullRoundCornerShape)
            .background(
                color =
                    if (isPressed) toggleItemColors.pressStateColor
                    else if (isHovered) toggleItemColors.hoverStateColor
                    else Color.Transparent
            )
            .alpha(if (enabled) 1f else 0.4f)
            .then(
                if (enabled) {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { onItemSelected(toggleItem.toggleItemIndex) }
                } else Modifier
            )
            .then(
                if (isFocused) {
                    Modifier.border(
                        width = ToggleItemDefaults.TOGGLE_ITEM_BORDER_WIDTH,
                        color = UICTheme.colorScheme.brand.buttonPrimaryTonal.focus,
                        shape = UICShape.shapes.fullRoundCornerShape
                    )
                } else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(
            value = LocalDensity provides
                    Density(
                        density = LocalDensity.current.density,
                        fontScale = minOf(a = LocalDensity.current.fontScale, b = 1.0f),
                    ),
        ) {
            CoreBodyTextView(
                text = toggleItem.toggleItemLabel,
                bodyVariant = BodyVariant.BODY_2,
                isBold = true,
                textAlign = TextAlign.Center,
                color = textColor,
                modifier = Modifier
                    .testTag(TestTags.Fulfillment.fulfillmentType(toggleItem.toggleItemLabel))
            )
        }
    }
}

/**
 * Maps toggle status to color scheme for the AppBarToggleItem.
 *
 * @param colorTokens The color tokens from the theme system.
 */
class AppBarToggleItemColorMapper(
    private val colorTokens: ColorTokens
) {
    /**
     * Returns the color scheme for the given toggle status.
     *
     * @param toggleStatus The current status (ACTIVE/INACTIVE).
     * @return UICAppBarToggleColorScheme for the toggle item.
     */
    fun mapColor(
        toggleStatus: ToggleStatus,
    ): UICAppBarToggleColorScheme {
        when (toggleStatus) {
            ToggleStatus.ACTIVE -> {
                return UICAppBarToggleColorScheme(
                    containerColor = colorTokens.structural.elevated,
                    borderColor = colorTokens.brand.buttonPrimaryTonal.border,
                    pillBgColor = colorTokens.brand.buttonPrimary.core,
                    pillTextColor = colorTokens.brand.buttonPrimary.onCore,
                    pressStateColor = colorTokens.state.interactiveNeutral.press,
                    hoverStateColor = colorTokens.state.interactiveNeutral.hover,
                    focusStateColor = colorTokens.state.interactiveNeutral.hover
                )
            }

            ToggleStatus.INACTIVE -> {
                return UICAppBarToggleColorScheme(
                    containerColor = colorTokens.structural.elevated,
                    borderColor = colorTokens.brand.buttonPrimaryTonal.border,
                    pillBgColor = colorTokens.brand.buttonPrimary.core,
                    pillTextColor = colorTokens.brand.buttonPrimary.onCore,
                    pressStateColor = colorTokens.state.interactiveNeutral.press,
                    hoverStateColor = colorTokens.state.interactiveNeutral.hover,
                    focusStateColor = colorTokens.state.interactiveNeutral.hover
                )
            }
        }
    }
}

/**
 * Data class representing the color scheme for an AppBar toggle item.
 */
data class UICAppBarToggleColorScheme(
    val containerColor: Color,
    val borderColor: Color,
    val pillBgColor: Color,
    val pillTextColor: Color,
    val pressStateColor: Color,
    val hoverStateColor: Color,
    val focusStateColor: Color,
)

/**
 * Enum representing the toggle status (active/inactive).
 */
enum class ToggleStatus {
    ACTIVE,
    INACTIVE
}

/**
 * Data class representing a toggle item for the segmented control.
 *
 * @param toggleItemLabel The label for the toggle item.
 * @param toggleItemIndex The index of this item.
 * @param toggleItemSelectedIndex The index of the currently selected item.
 * @param toggleStatus The current status (active/inactive).
 * @param enabled Whether this item is enabled.
 */
data class UICToggleItem(
    val toggleItemLabel: String,
    val toggleItemIndex: Int,
    val toggleItemSelectedIndex: Int,
    var toggleStatus: ToggleStatus = ToggleStatus.INACTIVE,
    var enabled: Boolean = true,
)

// Default values for toggle item appearance
private object ToggleItemDefaults {
    val TOGGLE_ITEM_BORDER_WIDTH = 2.dp
    val TOGGLE_ITEM_PADDING = 4.dp
}