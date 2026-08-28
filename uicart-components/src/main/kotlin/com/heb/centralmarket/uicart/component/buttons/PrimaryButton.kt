/*
 * Created by Mahesh Mathew Paul on 11/02/25, 12:43 pm
 * mahesh.paul@ust.com
 * Last modified 11/02/25, 12:41 pm
 * Copyright (c) 2025.
 * All rights reserved.
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.CoreHeadingTextView
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.icons.plusIcon
import com.heb.centralmarket.uicart.icons.trashIcon
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICShape
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.utils.rememberDebouncedClick

/**
 * Enum class representing predefined button sizes.
 *
 * @property width The width of the button in dp.
 * @property height The height of the button in dp.
 */
enum class CoreButtonSize(
    val width: Int,
    val height: Int,
) {
    SMALL(width = 117, height = 34),
    MEDIUM(width = 132, height = 44),
    LARGE(width = 155, height = 48),
}

/**
 * Primary button with text and optional leading/trailing icons.
 *
 * This function extends [UICPrimaryButton] to include a text label
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
fun UICPrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonText: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    buttonSize: CoreButtonSize = CoreButtonSize.MEDIUM,
    isButtonFullWidth: Boolean = false,
    isLoading: Boolean = false,
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

    UICPrimaryButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        size = buttonSize,
        contentPadding = contentPadding,
        isButtonFullWidth = isButtonFullWidth,
    ) {
        UICPrimaryButtonContent(
            isLoading = isLoading,
            text = buttonText,
            headingLevel = headingLevel,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
        )
    }
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
private fun UICPrimaryButton(
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

    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = when {
            isPressed -> UICTheme.colorScheme.brand.buttonPrimary.hover
            isFocused -> UICTheme.colorScheme.brand.buttonPrimary.core
            isHovered -> UICTheme.colorScheme.brand.buttonPrimary.hover
            else -> UICTheme.colorScheme.brand.buttonPrimary.core
        },
        contentColor = UICTheme.colorScheme.brand.buttonPrimary.onCore,
        disabledContainerColor = UICTheme.colorScheme.brand.buttonPrimary.disabled,
        disabledContentColor = UICTheme.colorScheme.brand.buttonPrimary.onCore.copy(alpha = 0.38f),
    )

    val borderColor = when {
        isPressed || isFocused -> UICTheme.colorScheme.brand.buttonPrimary.focus
        isHovered -> UICTheme.colorScheme.brand.buttonPrimary.hover
        isDisabled -> Color.Transparent
        else -> UICTheme.colorScheme.brand.buttonPrimary.core
    }

    val borderWidth = CoreSecondaryButtonDefaults.OutlinedButtonBorderWidth  // Use consistent value

    val debouncedOnClick = rememberDebouncedClick(onClick, debounceDelayMillis)

    DisableRippleEffect {
        Button(
            onClick = debouncedOnClick ?: {},
            interactionSource = interactionSource,
            modifier =
                modifier
                    .then(if (isButtonFullWidth) Modifier.fillMaxWidth() else Modifier)
                    .border(
                        width = borderWidth,
                        color = borderColor,
                        shape = UICShape.shapes.fullRoundCornerShape,
                    )
                    .width(width = size.width.dp + CoreSecondaryButtonDefaults.OutlinedButtonBorderWidth)
                    .height(height = size.height.dp + CoreSecondaryButtonDefaults.OutlinedButtonBorderWidth),
            enabled = enabled,
            colors = buttonColors,
            contentPadding = contentPadding,
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
            content = content,
        )
    }
}

/**
 * Disables ripple effects within the composable content.
 *
 * @param content The composable content where ripple effects should be disabled.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisableRippleEffect(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalRippleConfiguration provides null) {
        content()
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
private fun UICPrimaryButtonContent(
    isLoading: Boolean = false,
    text: String,
    headingLevel: Int,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    if (isLoading) {
        CircularProgressIndicator(
            modifier = Modifier.size(20.dp),
            strokeWidth = 3.dp,
            color = UICTheme.colorScheme.brand.buttonPrimary.onCore,
        )
    } else {
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
                color = LocalContentColor.current,
            )
        }

        if (trailingIcon != null) {
            Box(Modifier.wrapContentSize()) {
                trailingIcon()
            }
        }
    }
}

@Preview
@Composable
fun UICPrimaryButtonPreview() {
    UICAppTheme {
        CoreBackground(modifier = Modifier.size(200.dp, 60.dp)) {
            UICPrimaryButton(
                onClick = {},
                buttonText =
                    stringResource(R.string.button).uppercase(),
                buttonSize = CoreButtonSize.MEDIUM,
                isButtonFullWidth = true
            )
        }
    }
}

@Preview
@Composable
fun UICPrimaryDisabledButtonPreview() {
    UICAppTheme {
        CoreBackground(modifier = Modifier.size(200.dp, 60.dp)) {
            UICPrimaryButton(
                onClick = {},
                buttonText =
                    stringResource(R.string.button).uppercase(),
                enabled = false,
            )
        }
    }
}

@Preview
@Composable
fun UICPrimaryButtonLeadingIconPreview() {
    UICAppTheme {
        CoreBackground(modifier = Modifier.size(200.dp, 60.dp)) {
            UICPrimaryButton(
                onClick = {},
                buttonText = stringResource(R.string.button).uppercase(),
                leadingIcon = {
                    Icon(
                        imageVector = trashIcon(),
                        contentDescription = null,
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = plusIcon(),
                        contentDescription = null,
                    )
                },
                buttonSize = CoreButtonSize.MEDIUM,
                enabled = true,
            )
        }
    }
}
