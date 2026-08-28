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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.CoreHeadingTextView
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.icons.downArrow
import com.heb.centralmarket.uicart.icons.plusIcon
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICShape
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.utils.TestTags
import com.heb.centralmarket.uicart.utils.rememberDebouncedClick

enum class UICLinkButtonStyle(
    val compact: Boolean,
    val bold: Boolean,
    val allCaps: Boolean,
    val iconSize: Dp,
    val iconGap: Dp,
) {
    REGULAR_BOLD_ALL_CAPS(
        compact = false,
        bold = true,
        allCaps = true,
        iconSize = 16.dp,
        iconGap = 8.dp
    ),
    REGULAR_BOLD(
        compact = false,
        bold = true,
        allCaps = false,
        iconSize = 16.dp,
        iconGap = 8.dp
    ),
    REGULAR(
        compact = false,
        bold = false,
        allCaps = false,
        iconSize = 16.dp,
        iconGap = 8.dp
    ),
    COMPACT_BOLD_ALL_CAPS(
        compact = true,
        bold = true,
        allCaps = true,
        iconSize = 12.dp,
        iconGap = 4.dp

    ),
    COMPACT_BOLD(
        compact = true,
        bold = true,
        allCaps = false,
        iconSize = 12.dp,
        iconGap = 4.dp
    ),
    COMPACT(
        compact = true,
        bold = false,
        allCaps = false,
        iconSize = 12.dp,
        iconGap = 4.dp
    )
}

@Composable
fun UICLinkButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonText: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    buttonStyle: UICLinkButtonStyle,
    isButtonFullWidth: Boolean = false,
) {
    val headingLevel = if (buttonStyle.compact) 5 else 4

    val contentPadding = PaddingValues(
                start = UICSpacing.spacing.spacing050,
                end = UICSpacing.spacing.spacing050,
                top = UICSpacing.spacing.spacing050,
                bottom = UICSpacing.spacing.spacing050
            )

    UICLinkButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = contentPadding,
        isButtonFullWidth = isButtonFullWidth,
    ) {isPressed ->
        UICLinkButtonContent(
            text = buttonText,
            headingLevel = headingLevel,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            buttonEnabled = enabled,
            buttonStyle = buttonStyle,
            isPressed = isPressed
        )
    }
}

@Composable
fun UICLinkButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    isButtonFullWidth: Boolean,
    content: @Composable RowScope.(isPressed: Boolean) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isDisabled = !enabled
    val isFocused by interactionSource.collectIsFocusedAsState()

    val buttonColors = ButtonDefaults.buttonColors(
        containerColor =
            when {
                isPressed -> UICTheme.colorScheme.brand.interactive.bg
                isDisabled -> Color.Transparent
                else -> UICTheme.colorScheme.structural.bgPrimary
            },
        contentColor = when {
            isPressed -> UICTheme.colorScheme.brand.interactive.hover
            isHovered -> UICTheme.colorScheme.brand.interactive.hover
            else -> UICTheme.colorScheme.brand.interactive.core
        },
        disabledContainerColor = UICTheme.colorScheme.brand.interactive.onCore,
        disabledContentColor = UICTheme.colorScheme.brand.interactive.disabled,
    )

    val borderColor =
        if (isFocused) UICTheme.colorScheme.brand.interactive.focus
        else Color.Transparent

    // Use shared debounced click utility
    val debouncedOnClick = rememberDebouncedClick(onClick, 500L)

    DisableRippleEffect {
        Box(
            modifier = modifier.border(
                width = UICLinkButtonDefaults.OutlinedButtonBorderWidth,
                color = borderColor,
                shape = UICShape.shapes.fullRoundCornerShape,
            ),
        ) {
            OutlinedButton(
                onClick = { debouncedOnClick?.invoke() ?: Unit },
                interactionSource = interactionSource,
                modifier = if (isButtonFullWidth) Modifier.fillMaxWidth() else Modifier,
                enabled = enabled,
                colors = buttonColors,
                border = BorderStroke(
                    width = UICLinkButtonDefaults.OutlinedButtonBorderWidth,
                    color = Color.Transparent
                ),
                contentPadding = contentPadding,
                shape = UICShape.shapes.fullRoundCornerShape,
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp,
                    hoveredElevation = 0.dp,
                    focusedElevation = 0.dp
                )// No elevations required
            )
            {
                content(isPressed)
            }
        }
    }
}

@Composable
private fun UICLinkButtonContent(
    text: String,
    headingLevel: Int,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    buttonEnabled: Boolean,
    buttonStyle: UICLinkButtonStyle,
    isPressed: Boolean = false,
) {
    val internalVerticalPadding = if (buttonStyle.compact) 3.dp else 2.dp

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(buttonStyle.iconGap),
    ) {
        if (leadingIcon != null) {
            Box(Modifier.size(buttonStyle.iconSize)) { leadingIcon() }
        }
        CoreHeadingTextView(
            text = if (buttonStyle.allCaps) text.uppercase() else text,
            isBold = buttonStyle.bold,
            headingLevel = headingLevel,
            color = when {
                !buttonEnabled -> UICTheme.colorScheme.brand.interactive.disabled
                isPressed -> UICTheme.colorScheme.brand.interactive.hover
                else -> UICTheme.colorScheme.brand.interactive.core
            },
            modifier = Modifier
                .padding(vertical = internalVerticalPadding)
                .testTag(TestTags.AddressBook.ADD_NEW_ADDRESS_TEXT),
        )

        if (trailingIcon != null) {
            Box(Modifier.size(buttonStyle.iconSize)) { trailingIcon() }
        }
    }
}

object UICLinkButtonDefaults {
    val OutlinedButtonBorderWidth = 2.dp
}


@Preview
@Composable
fun UICTertiaryButtonWithCompact() {
    UICAppTheme {
        CoreBackground(modifier = Modifier.size(200.dp, 60.dp)) {
            UICLinkButton(
                onClick = {},
                buttonText = stringResource(R.string.button),
                leadingIcon = {
                    Icon(
                        imageVector = plusIcon(),
                        contentDescription = null,
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = downArrow(),
                        contentDescription = null,
                    )
                },
                buttonStyle = UICLinkButtonStyle.COMPACT,
                isButtonFullWidth = true,
                enabled = true,
            )
        }
    }
}

@Preview
@Composable
fun UICTertiaryButtonWithCompactBold() {
    UICAppTheme {
        CoreBackground(modifier = Modifier.size(200.dp, 60.dp)) {
            UICLinkButton(
                onClick = {},
                buttonText = stringResource(R.string.button),
                leadingIcon = {
                    Icon(
                        imageVector = plusIcon(),
                        contentDescription = null,
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = downArrow(),
                        contentDescription = null,
                    )
                },
                buttonStyle = UICLinkButtonStyle.COMPACT_BOLD,
                isButtonFullWidth = true,
                enabled = false,
            )
        }
    }
}

@Preview
@Composable
fun UICTertiaryButtonWithCompactCaps() {
    UICAppTheme {
        CoreBackground(modifier = Modifier.size(200.dp, 60.dp)) {
            UICLinkButton(
                onClick = {},
                buttonText = stringResource(R.string.button),
                leadingIcon = {
                    Icon(
                        imageVector = plusIcon(),
                        contentDescription = null,
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = downArrow(),
                        contentDescription = null,
                    )
                },
                buttonStyle = UICLinkButtonStyle.COMPACT_BOLD_ALL_CAPS,
                isButtonFullWidth = true,
                enabled = false,
            )
        }
    }
}

@Preview
@Composable
fun UICTertiaryButtonWithNotCompact() {
    UICAppTheme {
        CoreBackground(modifier = Modifier.size(200.dp, 60.dp)) {
            UICLinkButton(
                onClick = {},
                buttonText = stringResource(R.string.button),
                leadingIcon = {
                    Icon(
                        imageVector = plusIcon(),
                        contentDescription = null,
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = downArrow(),
                        contentDescription = null,
                    )
                },
                buttonStyle = UICLinkButtonStyle.REGULAR,
                isButtonFullWidth = true,
                enabled = false,
            )
        }
    }
}

@Preview
@Composable
fun UICTertiaryButtonWithNotCompactBold() {
    UICAppTheme {
        CoreBackground(modifier = Modifier.size(200.dp, 60.dp)) {
            UICLinkButton(
                onClick = {},
                buttonText = stringResource(R.string.button),
                leadingIcon = {
                    Icon(
                        imageVector = plusIcon(),
                        contentDescription = null,
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = downArrow(),
                        contentDescription = null,
                    )
                },
                buttonStyle = UICLinkButtonStyle.REGULAR_BOLD,
                isButtonFullWidth = true,
                enabled = false,
            )
        }
    }
}

@Preview
@Composable
fun UICTertiaryButtonWithNotCompactCaps() {
    UICAppTheme {
        CoreBackground(modifier = Modifier.size(200.dp, 60.dp)) {
            UICLinkButton(
                onClick = {},
                buttonText = stringResource(R.string.button),
                leadingIcon = {
                    Icon(
                        imageVector = plusIcon(),
                        contentDescription = null,
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = downArrow(),
                        contentDescription = null,
                    )
                },
                buttonStyle = UICLinkButtonStyle.REGULAR_BOLD_ALL_CAPS,
                isButtonFullWidth = true,
                enabled = false,
            )
        }
    }
}
