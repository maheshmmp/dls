/*
 * Created by Mahesh Mathew Paul on 11/02/25, 12:43 pm
 * mahesh.paul@ust.com
 * Last modified 11/02/25, 12:41 pm
 * Copyright (c) 2025.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.component.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.component.AppBarTheme
import com.heb.centralmarket.uicart.component.CoreCaptionTextView
import com.heb.centralmarket.uicart.icons.accountAvatar
import com.heb.centralmarket.uicart.themesystem.UICCornerRadius
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.utils.TestTags
import com.heb.centralmarket.uicart.utils.rememberDebouncedClick

/**
 * A customizable icon button component with an optional badge.
 *
 * @param onClick The callback invoked when the button is clicked.
 * @param modifier The modifier to be applied to the button.
 * @param enabled Determines if the button is enabled or disabled.
 * @param badgeCount Optional text displayed in the badge at the top-right corner.
 * @param theme The theme style of the button, defaulting to [AppBarTheme.SECONDARY].
 * @param buttonSize The size of the button, defaulting to [IconButtonSize.MEDIUM].
 * @param showBadge Determines if the badge should be displayed. Default is `true`.
 * @param iconVector A vector passed to render icon inside the button.
 * @param contentDescription Description used for accessibility.
 */
@Composable
fun CoreIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    badgeCount: String? = "",
    theme: AppBarTheme? = AppBarTheme.SECONDARY,
    buttonSize: IconButtonSize? = IconButtonSize.MEDIUM,
    showBadge: Boolean = true,
    iconVector: ImageVector? = null,
    contentDescription: String? = null,
    debounceDelayMillis: Long = 500L,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val borderColor =
        when {
            isPressed ->
                if (theme ==
                    AppBarTheme.PRIMARY
                ) {
                    UICTheme.colorScheme.structural.outlineLight
                } else {
                    UICTheme.colorScheme.brand.primary.focus
                }
            else ->
                if (theme ==
                    AppBarTheme.PRIMARY
                ) {
                    UICTheme.colorScheme.brand.primary.core
                } else {
                    UICTheme.colorScheme.neutral.transparent
                }
        }

    val containerColor =
        when {
            isPressed ->
                if (theme == AppBarTheme.PRIMARY) {
                    UICTheme.colorScheme.interactiveGray.onCore.copy(
                        alpha = CoreIconButtonConstants.PRESSED_CONTENT_ALPHA,
                    )
                } else {
                    UICTheme.colorScheme.brand.primary.bg
                }
            else -> if (theme == AppBarTheme.PRIMARY) {
                UICTheme.colorScheme.brand.primary.core
            } else {
                UICTheme.colorScheme.neutral.transparent
            }
        }

    val contentColor =
        when {
            isPressed ->
                if (theme ==
                    AppBarTheme.PRIMARY
                ) {
                    UICTheme.colorScheme.structural.outlineLight
                } else {
                    UICTheme.colorScheme.brand.primary.focus
                }
            else -> UICTheme.colorScheme.brand.buttonPrimary.onCore
        }

    val iconTint =
        if (enabled) {
            if (theme == AppBarTheme.PRIMARY) {
                UICTheme.colorScheme.txt.primaryInverse
            } else {
                UICTheme.colorScheme.brand.primary.core
            }
        } else {
            if (theme == AppBarTheme.PRIMARY) {
                UICTheme.colorScheme.txt.primaryInverse
                    .copy(alpha = CoreIconButtonConstants.DISABLED_ALPHA)
            } else {
                UICTheme.colorScheme.brand.primary.core
                    .copy(alpha = CoreIconButtonConstants.DISABLED_ALPHA)
            }
        }

    val debouncedOnClick = rememberDebouncedClick(onClick, debounceDelayMillis)

    DisableRippleEffect {
        if (buttonSize != null) {
            FilledIconButton(
                onClick = debouncedOnClick ?: {},
                interactionSource = interactionSource,
                enabled = enabled,
                modifier =
                    modifier
                        .size(size = buttonSize.buttonSize + CoreIconButtonConstants.BUTTON_BORDER_WIDTH)
                        .testTag(TestTags.AddressBook.BACK_BUTTON),
                colors =
                    IconButtonDefaults.iconButtonColors(
                        containerColor = containerColor,
                        contentColor = contentColor,
                    ),
                shape =
                    RoundedCornerShape(
                        topStart = UICCornerRadius.borderRadius.medium,
                        topEnd = UICCornerRadius.borderRadius.medium,
                        bottomStart = UICCornerRadius.borderRadius.medium,
                        bottomEnd = UICCornerRadius.borderRadius.medium,
                    ),
            ) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .border(
                                width = CoreIconButtonConstants.BUTTON_BORDER_WIDTH,
                                color = borderColor,
                                shape =
                                    RoundedCornerShape(
                                        topStart = UICCornerRadius.borderRadius.medium,
                                        topEnd = UICCornerRadius.borderRadius.medium,
                                        bottomStart = UICCornerRadius.borderRadius.medium,
                                        bottomEnd = UICCornerRadius.borderRadius.medium,
                                    ),
                            )
                            .alpha(alpha = if (enabled) 1f else CoreIconButtonConstants.DISABLED_ALPHA),
                ) {
                    // Render the icon with dynamic tint
                    iconVector?.let {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = it,
                                contentDescription = contentDescription,
                                tint = iconTint,
                                modifier = Modifier.size(buttonSize.iconSize),
                            )
                        }
                    }

                    // Badge/Text rendering
                    if (showBadge && !badgeCount.isNullOrEmpty()) {
                        Box(
                            modifier =
                                Modifier
                                    .padding(
                                        top = CoreIconButtonConstants.BUTTON_BORDER_WIDTH,
                                        end = CoreIconButtonConstants.BUTTON_BORDER_WIDTH,
                                    )
                                    .align(Alignment.TopEnd),
                        ) {
                            Box(
                                modifier =
                                    Modifier
                                        .size(CoreIconButtonConstants.BADGE_SIZE)
                                        .clip(CircleShape)
                                        .background(
                                            color = UICTheme.colorScheme.brand.coupon.core,
                                        )
                                        .border(
                                            width = CoreIconButtonConstants.BADGE_BORDER_WIDTH,
                                            color = UICTheme.colorScheme.neutral.white,
                                            shape = CircleShape,
                                        )
                                        .align(Alignment.TopEnd),
                                contentAlignment = Alignment.Center,
                            ) {
                                CoreCaptionTextView(
                                    text = badgeCount,
                                    isBold = true,
                                    color = UICTheme.colorScheme.brand.coupon.onCore,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

private object CoreIconButtonConstants {
    val BUTTON_BORDER_WIDTH = 2.dp
    val BADGE_BORDER_WIDTH = 1.dp
    val BADGE_SIZE = 16.dp
    const val DISABLED_ALPHA = 0.4f
    const val PRESSED_CONTENT_ALPHA = 0.3f
}

enum class IconButtonSize(
    val buttonSize: Dp,
    val iconSize: Dp,
) {
    SMALL(buttonSize = 32.dp, iconSize = 16.dp),
    MEDIUM(buttonSize = 36.dp, iconSize = 20.dp),
    LARGE(buttonSize = 40.dp, iconSize = 24.dp),
    EXTRA_LARGE(buttonSize = 48.dp, iconSize = 32.dp),
}

@Preview(name = "Small Icon Button", group = "Icon Buttons")
@Composable
fun PreviewSmallIconButton() {
    CoreIconButton(
        onClick = { /* Handle click */ },
        badgeCount = "2",
        buttonSize = IconButtonSize.SMALL,
        iconVector = accountAvatar(),
    )
}

@Preview(name = "Medium Icon Button", group = "Icon Buttons")
@Composable
fun PreviewMediumIconButton() {
    CoreIconButton(
        onClick = { /* Handle click */ },
        badgeCount = "5",
        buttonSize = IconButtonSize.MEDIUM,
        iconVector = accountAvatar(),
    )
}

@Preview(name = "Large Icon Button", group = "Icon Buttons")
@Composable
fun PreviewLargeIconButton() {
    CoreIconButton(
        onClick = { /* Handle click */ },
        badgeCount = "3",
        buttonSize = IconButtonSize.LARGE,
        iconVector = accountAvatar(),
    )
}

@Preview(name = "Extra Large Icon Button", group = "Icon Buttons")
@Composable
fun PreviewExtraLargeIconButton() {
    CoreIconButton(
        onClick = { /* Handle click */ },
        badgeCount = "7",
        buttonSize = IconButtonSize.EXTRA_LARGE,
        iconVector = accountAvatar(),
    )
}

@Preview(name = "Disabled Icon Button", group = "Icon Buttons")
@Composable
fun PreviewDisabledIconButton() {
    CoreIconButton(
        onClick = { /* Handle click */ },
        badgeCount = "0",
        enabled = false,
        buttonSize = IconButtonSize.MEDIUM,
        iconVector = accountAvatar(),
    )
}
