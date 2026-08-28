/*
 * *
 *  * Created by Mahesh Mathew Paul on 10/10/25, 3:46 PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  * Last modified 10/10/25, 2:40 PM
 *
 */

package com.heb.centralmarket.uicart.component.buttons

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.component.CoreHeadingTextView
import com.heb.centralmarket.uicart.themesystem.UICShape
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.utils.rememberDebouncedClick

/**
 * Primary button with text and optional leading/trailing icons.
 *
 * This function extends [UICLinkButton] to include a text label
 * with configurable heading size based on button dimensions.
 *
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier Modifier to apply to the button.
 * @param enabled Controls whether the button is enabled.
 * @param buttonText The text displayed inside the button.
 * @param leadingIcon Optional leading icon before the text.
 * @param trailingIcon Optional trailing icon after the text.
 * @param buttonSize The size of the button (SMALL, MEDIUM, LARGE).
 * @param isButtonFullWidth Whether the button should expand to the full width of its parent.
 *
 */
@Composable
fun UICTertiaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonText: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    buttonSize: CoreButtonSize = CoreButtonSize.MEDIUM,
    isButtonFullWidth: Boolean = false,
) {
    val headingLevel =
        when (buttonSize) {
            CoreButtonSize.SMALL -> 5
            CoreButtonSize.MEDIUM -> 4
            CoreButtonSize.LARGE -> 3
        }

    val contentPadding =
        when (buttonSize) {
            CoreButtonSize.SMALL -> {
                PaddingValues(
                    start = UICSpacing.spacing.spacing050,
                    end = UICSpacing.spacing.spacing050,
                )
            }

            CoreButtonSize.MEDIUM -> {
                PaddingValues(
                    start = UICSpacing.spacing.spacing075,
                    end = UICSpacing.spacing.spacing075,
                )
            }

            CoreButtonSize.LARGE -> {
                PaddingValues(
                    start = UICSpacing.spacing.spacing100,
                    end = UICSpacing.spacing.spacing100,
                )
            }
        }

    UICTertiaryButtonInternal(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        size = buttonSize,
        contentPadding = contentPadding,
        isButtonFullWidth = isButtonFullWidth,
        content = {
            TertiaryButtonContent(
                text = buttonText,
                headingLevel = headingLevel,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                buttonEnabled = enabled
            )
        }
    )
}

/**
 * Abstracted Primary button with a customizable content slot.
 *
 * This function wraps Material 3's [Button] and provides additional styling
 * and interaction handling. It is a private function used internally.
 *
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier Modifier to apply to the button.
 * @param enabled Controls whether the button is enabled.
 * @param contentPadding Internal padding between the container and its content.
 * @param size The button size, which determines height and width.
 * @param content The composable content inside the button.
 * @param isButtonFullWidth Whether the button should expand to the full width of its parent.
 */
@Composable
private fun UICTertiaryButtonInternal(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    size: CoreButtonSize = CoreButtonSize.MEDIUM,
    isButtonFullWidth: Boolean = false,
    debounceDelayMillis: Long = 500L,
    content: @Composable RowScope.() -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isDisabled = !enabled

    val buttonColors =
        ButtonDefaults.buttonColors(
            containerColor =
                when {
                    isPressed -> UICTheme.colorScheme.brand.interactive.bg
                    isFocused -> Color.Transparent
                    isHovered -> UICTheme.colorScheme.brand.interactive.bg
                    enabled -> Color.Transparent // Enabled state
                    else -> Color.Transparent // Disabled state
                },
            contentColor =
                when {
                    isPressed || isFocused || isHovered || enabled -> UICTheme.colorScheme.brand.interactive.core
                    else -> UICTheme.colorScheme.brand.interactive.core.copy(alpha = 0.4F) // Disabled state
                },
            disabledContainerColor = Color.Transparent,
            disabledContentColor =  UICTheme.colorScheme.brand.interactive.core.copy(alpha = 0.4F),
        )

    val borderColor =
        when {
            isPressed ->
                UICTheme.colorScheme.brand.interactive.focus

            isFocused -> UICTheme.colorScheme.brand.interactive.focus
            isDisabled -> Color.Transparent // Added due to improper color rendering
            else -> Color.Transparent
        }

    val debouncedOnClick = rememberDebouncedClick(onClick, debounceDelayMillis)

    DisableRippleEffect {
        Button(
            onClick = debouncedOnClick ?: {},
            interactionSource = interactionSource,
            modifier = modifier
                .then(if (isButtonFullWidth) Modifier.fillMaxWidth() else Modifier)
                .border(
                    width = CoreSecondaryButtonDefaults.OutlinedButtonBorderWidth,
                    color = borderColor,
                    shape = UICShape.shapes.fullRoundCornerShape
                )
                .padding(all = CoreSecondaryButtonDefaults.OutlineBorderPadding)
                .widthIn(min = size.width.dp + CoreSecondaryButtonDefaults.OutlinedButtonBorderWidth)
                .height(height = size.height.dp + CoreSecondaryButtonDefaults.OutlinedButtonBorderWidth),
            enabled = enabled,
            colors = buttonColors,
            contentPadding = contentPadding,
            shape = UICShape.shapes.fullRoundCornerShape,
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp,
                hoveredElevation = 0.dp, // Set hovered elevation to 0.dp
                focusedElevation = 0.dp
            ),
            content = content,
        )
    }
}

/**
 * Internal component for rendering button content.
 *
 * This function arranges the text label and optional icons within the button.
 *
 * @param text The text displayed inside the button.
 * @param headingLevel The text size level based on the button size.
 * @param leadingIcon Optional leading icon before the text.
 * @param trailingIcon Optional trailing icon after the text.
 */
@Composable
private fun TertiaryButtonContent(
    text: String,
    headingLevel: Int,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    buttonEnabled: Boolean,
) {
    if (leadingIcon != null) {
        Box(Modifier.wrapContentSize()) {
            leadingIcon()
        }
    }
    Box(
        Modifier
            .padding(
                start =
                    if (leadingIcon != null) {
                        UICSpacing.spacing.spacing050
                    } else {
                        UICSpacing.spacing.spacing000
                    },
                end =
                    if (trailingIcon != null) {
                        UICSpacing.spacing.spacing050
                    } else {
                        UICSpacing.spacing.spacing000
                    },
            ),
    ) {
        CoreHeadingTextView(
            text = text.uppercase(),
            headingLevel = headingLevel,
            color = if (buttonEnabled) UICTheme.colorScheme.brand.interactive.core else UICTheme.colorScheme.brand.interactive.core.copy(
                alpha = 0.4F
            ),
        )
    }
    if (trailingIcon != null) {
        Box(Modifier.wrapContentSize()) {
            trailingIcon()
        }
    }
}