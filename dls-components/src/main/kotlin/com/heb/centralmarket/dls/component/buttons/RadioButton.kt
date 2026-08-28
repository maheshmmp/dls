package com.heb.centralmarket.uicart.component.buttons

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.buttons.UICRadioButtonConstants.RADIO_BUTTON_FOCUS_SIZE
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.utils.clickableWithoutRipple
import com.heb.centralmarket.uicart.utils.rememberDebouncedClick

/**
 * UICRadioButton radio button with different states.
 * with configurable heading size based on button dimensions.
 * @param selected The radio button selected state.
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier Modifier to apply to the button.
 * @param enabled Controls whether the button is enabled.
 * @param size The size of the radio button touch area.
 * @param contentDescription Content description for voice over.
 *
 */
@Composable
fun UICRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: Dp = UICRadioButtonConstants.RADIO_PRESS_SIZE,
    contentDescription: String,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val disabled = !enabled

    // Border color changes
    val borderColor by animateColorAsState(
        targetValue = when {
            disabled -> if(selected) UICTheme.colorScheme.brand.interactive.core.copy(alpha = 0.15f) else UICTheme.colorScheme.structural.outlineDark.copy(alpha = 0.4f)
            isFocused || enabled -> if (selected) UICTheme.colorScheme.brand.interactive.core else UICTheme.colorScheme.structural.outlineDark
            else -> UICTheme.colorScheme.structural.outlineDark
        },
        label = "RadioBorderColor"
    )

    // Fill color inside the ring
    val fillColor by animateColorAsState(
        targetValue = when {
            isPressed && selected-> UICTheme.colorScheme.brand.interactive.bg
            isPressed && !selected -> UICTheme.colorScheme.structural.bgSecondary
            else -> Color.Transparent
        },
        label = "RadioFillColor"
    )

    // Dot color (inner filled circle)
    val dotColor by animateColorAsState(
        targetValue = when {
            disabled -> UICTheme.colorScheme.brand.interactive.core.copy(alpha = 0.4f)
            selected -> UICTheme.colorScheme.brand.interactive.core
            else -> UICTheme.colorScheme.structural.bgPrimary
        },
        label = "RadioDotColor"
    )

    // Press ring / ripple background
    val pressRingColor by animateColorAsState(
        targetValue = when {
            isPressed && selected -> UICTheme.colorScheme.brand.interactive.bg
            isPressed && !selected -> UICTheme.colorScheme.structural.bgSecondary
            else -> Color.Transparent
        },
        label = "PressRingColor"
    )

    // Focus ring / ripple background
    val focusOuterRingColor by animateColorAsState(
        targetValue = when {
            isFocused && selected -> UICTheme.colorScheme.brand.interactive.bg
            isFocused && !selected -> UICTheme.colorScheme.structural.outlineLight
            else -> Color.Transparent
        },
        label = "FocusRingColor"
    )

    // Parent Radio button holder
    val debouncedOnClick = rememberDebouncedClick(onClick, 500L)

    Box(
        modifier = modifier
            .size(size = size)
            .clickableWithoutRipple(
                interactionSource = interactionSource,
                enabled = enabled,
                onClick = debouncedOnClick ?: {}
            ),
        contentAlignment = Alignment.Center
    ) {


        val animatedFocusSize by animateDpAsState(
            targetValue = if (isFocused) RADIO_BUTTON_FOCUS_SIZE else size,
            label = "FocusSizeAnim"
        )

        // Outer ring (focus/press visual)
        Box(
            modifier = Modifier
                .size(animatedFocusSize)
                .clip(CircleShape)
                .background(pressRingColor)
                .border(
                    width = UICRadioButtonConstants.RADIO_BUTTON_BORDER_WIDTH,
                    color = focusOuterRingColor,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {

            // Main radio button body
            Box(
                modifier = Modifier
                    .size(size = UICRadioButtonConstants.RADIO_BUTTON_SIZE)
                    .clip(CircleShape)
                    .background(fillColor)
                    .border(
                        width = if (selected) UICRadioButtonConstants.RADIO_BUTTON_BORDER_WIDTH else UICRadioButtonConstants.RADIO_BUTTON_BORDER_UNSELECTED_WIDTH,
                        color = borderColor,
                        shape = CircleShape
                    )
                    .alpha(alpha = if (enabled) 1f else UICRadioButtonConstants.DISABLED_ALPHA)
                    .semantics { this.contentDescription = contentDescription },
                contentAlignment = Alignment.Center
            ) {
                if (selected) {
                    Box(
                        modifier = Modifier
                            .size(size = UICRadioButtonConstants.RADIO_BUTTON_SELECTION_SIZE)
                            .clip(CircleShape)
                            .background(dotColor)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun UICRadioButtonPreview() {
    CoreBackground {
        Surface {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    UICRadioButton(selected = true, onClick = {}, contentDescription = "")
                    UICRadioButton(selected = false, onClick = {}, contentDescription = "")
                }
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    UICRadioButton(selected = true, enabled = false, onClick = {}, contentDescription = "")
                    UICRadioButton(selected = false, enabled = false, onClick = {}, contentDescription = "")
                }
            }
        }
    }
}

private object UICRadioButtonConstants {
    val RADIO_BUTTON_BORDER_WIDTH = 1.75.dp
    val RADIO_BUTTON_BORDER_UNSELECTED_WIDTH = 1.25.dp
    val RADIO_BUTTON_SIZE = 20.dp
    val RADIO_BUTTON_FOCUS_SIZE = 28.dp
    val RADIO_PRESS_SIZE = 36.dp
    val RADIO_BUTTON_SELECTION_SIZE = 12.dp
    const val DISABLED_ALPHA = 0.4f
}