/*
 * Created by Ritu Varma G
 * varma.ritu@ust.com
 * Date Created: 12-03-2025
 * Last Modified: 12-03-2025
 * Copyright (c) 2025.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.component.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
 * Secondary button with customizable text and optional leading/trailing icons.
 *
 * This button provides flexibility in styling and layout, including different sizes,
 * optional icons, and an adjustable width setting.
 *
 * @param onClick Callback invoked when the button is clicked.
 * @param modifier Modifier to apply to the button.
 * @param enabled Controls whether the button is enabled.
 * @param buttonText The text displayed inside the button.
 * @param leadingIcon Optional composable function for a leading icon before the text.
 * @param trailingIcon Optional composable function for a trailing icon after the text.
 * @param buttonSize The size of the button (SMALL, MEDIUM, LARGE).
 * @param isButtonFullWidth Whether the button should expand to the full width of its parent.
 */
@Composable
fun UICSecondaryButton(
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
    var contentPadding: PaddingValues

    when (buttonSize) {
        CoreButtonSize.SMALL -> {
            contentPadding =
                PaddingValues(
                    start = UICSpacing.spacing.spacing050,
                    end = UICSpacing.spacing.spacing050,
                )
        }

        CoreButtonSize.MEDIUM -> {
            contentPadding =
                PaddingValues(
                    start = UICSpacing.spacing.spacing075,
                    end = UICSpacing.spacing.spacing075,
                )
        }

        CoreButtonSize.LARGE -> {
            contentPadding =
                PaddingValues(
                    start = UICSpacing.spacing.spacing100,
                    end = UICSpacing.spacing.spacing100,
                )
        }
    }

    UICSecondaryButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = contentPadding,
        size = buttonSize,
        isButtonFullWidth = isButtonFullWidth,
    ) {
        UICPrimaryButtonContent(
            text = buttonText,
            headingLevel = headingLevel,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            buttonEnabled = enabled,
            isLoading = isLoading,
        )
    }
}

/**
 * Internal component for rendering button content.
 *
 * This function arranges the text label and optional icons within the button.
 *
 * @param onClick The click listener of the button implementation.
 * @param enabled The flag used to make the button enable/disable.
 * @param contentPadding Padding values for the contents in the button.
 * @param size size of the button small / medium, and Large.
 * @param isButtonFullWidth The boolean flag used to update the wrapping based on the full width enable/disable status..
 * @param content The composable content for the button.
 */
@Composable
fun UICSecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    size: CoreButtonSize,
    isButtonFullWidth: Boolean,
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
                    isFocused -> UICTheme.colorScheme.structural.bgPrimary
                    isHovered -> UICTheme.colorScheme.brand.interactive.bg
                    else -> UICTheme.colorScheme.structural.bgPrimary
                },
            contentColor = UICTheme.colorScheme.brand.interactive.core,
            disabledContainerColor = UICTheme.colorScheme.brand.interactive.onCore,
            disabledContentColor = UICTheme.colorScheme.brand.interactive.disabled,
        )

    val borderColor =
        when {
            isPressed -> UICTheme.colorScheme.brand.interactive.focus
            isFocused -> UICTheme.colorScheme.brand.interactive.core
            isDisabled -> Color.Transparent
            else -> Color.Transparent
        }

    val debouncedOnClick = rememberDebouncedClick(onClick, debounceDelayMillis)

    DisableRippleEffect {
        OutlinedButton(
            onClick = debouncedOnClick ?: {},
            interactionSource = interactionSource,
            modifier =
                modifier
                    .then(if (isButtonFullWidth) Modifier.fillMaxWidth() else Modifier)
                    .border(
                        width = CoreSecondaryButtonDefaults.OutlinedButtonBorderWidth,
                        color = borderColor,
                        shape = UICShape.shapes.fullRoundCornerShape,
                    )
                    .width(width = size.width.dp + CoreSecondaryButtonDefaults.OutlinedButtonBorderWidth)
                    .height(height = size.height.dp + CoreSecondaryButtonDefaults.OutlinedButtonBorderWidth),
            enabled = enabled,
            colors = buttonColors,
            border =
                BorderStroke(
                    width = CoreSecondaryButtonDefaults.DefaultButtonBorderWidth,
                    color =
                        if (enabled) {
                            UICTheme.colorScheme.brand.interactive.core
                        } else {
                            UICTheme.colorScheme.brand.interactive.disabled
                        },
                ),
            contentPadding = contentPadding,
            shape = UICShape.shapes.fullRoundCornerShape,
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
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
 * @param buttonEnabled The flag used to handle the enabled disabled status of the button.
 */
@Composable
private fun UICPrimaryButtonContent(
    text: String,
    headingLevel: Int,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    buttonEnabled: Boolean,
    isLoading: Boolean = false,
) {
    if (isLoading) {
        CircularProgressIndicator(
            modifier = Modifier.size(20.dp),
            strokeWidth = 3.dp,
            color = UICTheme.colorScheme.brand.interactive.core,
        )
    } else {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            if (leadingIcon != null) {
                Box(
                    Modifier.wrapContentSize(),
                ) {
                    leadingIcon()
                }
            }

            Box(
                modifier =
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
                contentAlignment = Alignment.Center,
            ) {
                CoreHeadingTextView(
                    text = text.uppercase(),
                    headingLevel = headingLevel,
                    color =
                        if (buttonEnabled) {
                            UICTheme.colorScheme.brand.interactive.core
                        } else {
                            UICTheme.colorScheme.brand.interactive.disabled
                        },
                    textAlign = TextAlign.Center,
                )
            }

            if (trailingIcon != null) {
                Box(
                    Modifier
                        .wrapContentSize(),
                ) {
                    trailingIcon()
                }
            }
        }
    }
}

/**
 * Default values for CorePrimaryButton styling.
 */
object CoreSecondaryButtonDefaults {
    val OutlinedButtonBorderWidth = 2.dp // Default border width for outlined buttons.
    val DefaultButtonBorderWidth = 1.dp // Default border width.
    val OutlineBorderPadding = 2.dp // Padding for the outline border.
}

@Preview
@Composable
fun UICSecondaryButtonPreview() {
    UICAppTheme {
        CoreBackground(modifier = Modifier.size(200.dp, 60.dp)) {
            UICSecondaryButton(
                onClick = {},
                buttonText =
                    stringResource(R.string.button).uppercase(),
                buttonSize = CoreButtonSize.SMALL,
                isButtonFullWidth = false,
            )
        }
    }
}

@Preview
@Composable
fun UICSecondaryDisabledButtonPreview() {
    UICAppTheme {
        CoreBackground(modifier = Modifier.size(200.dp, 60.dp)) {
            UICSecondaryButton(
                onClick = {},
                buttonText =
                    stringResource(R.string.button).uppercase(),
                enabled = true,
                isButtonFullWidth = false,
            )
        }
    }
}

@Preview
@Composable
fun UICSecondaryButtonLeadingIconPreview() {
    UICAppTheme {
        CoreBackground(modifier = Modifier.size(200.dp, 60.dp)) {
            UICSecondaryButton(
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
                buttonSize = CoreButtonSize.LARGE,
                isButtonFullWidth = false,
                enabled = true,
            )
        }
    }
}

@Preview
@Composable
fun UICSecondaryButtonWithOutIconPreview() {
    UICAppTheme {
        CoreBackground(modifier = Modifier.size(200.dp, 60.dp)) {
            UICSecondaryButton(
                onClick = {},
                buttonText = stringResource(R.string.button).uppercase(),
                buttonSize = CoreButtonSize.SMALL,
                isButtonFullWidth = true,
                enabled = true,
            )
        }
    }
}

@Preview
@Composable
fun UICSecondaryMediumButtonWithIconFullWidthPreview() {
    UICAppTheme {
        CoreBackground(modifier = Modifier.size(200.dp, 60.dp)) {
            UICSecondaryButton(
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
                isButtonFullWidth = true,
                enabled = true,
            )
        }
    }
}

@Preview
@Composable
fun UICSecondaryLargeButtonWithIconFullWidthPreview() {
    UICAppTheme {
        CoreBackground(modifier = Modifier.size(200.dp, 60.dp)) {
            UICSecondaryButton(
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
                buttonSize = CoreButtonSize.LARGE,
                isButtonFullWidth = true,
                enabled = true,
            )
        }
    }
}
