/*
 * *
 *  * Created by Mahesh Mathew Paul on 10/30/25, 1:28 PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  * Last modified 10/30/25, 1:25 PM
 *
 */

package com.heb.centralmarket.uicart.component.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.component.BodyVariant
import com.heb.centralmarket.uicart.component.CoreBodyTextView
import com.heb.centralmarket.uicart.themesystem.UICCornerRadius
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.utils.rememberDebouncedClick

/**
 * Adaptive version of ServicesItem that scales its minimum height based on system font scale
 * to ensure two lines of text remain visible when accessibility font size is increased.
 *
 * @param onClick Callback when the button is clicked (debounced with 300ms delay to prevent rapid successive invocations)
 * @param modifier The modifier to apply to the button
 * @param enabled Whether the button is enabled
 * @param iconVector The icon to display
 * @param contentDescription Accessibility description for the icon
 * @param text The text label below the icon
 * @param baseMinHeight The base minimum height before scaling
 * @param maxLines Maximum lines for the text
 * @param debounceDelayMillis The debounce delay in milliseconds. Default is 300ms
 */
@Composable
fun ServicesItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    iconVector: ImageVector? = null,
    contentDescription: String? = null,
    text: String,
    baseMinHeight: Dp = ServiceItemConstants.MIN_HEIGHT,
    maxLines: Int = ServiceItemConstants.MAX_LINES,
    debounceDelayMillis: Long = 1000L,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    // Use shared debounced click utility
    val debouncedOnClick = rememberDebouncedClick(onClick, debounceDelayMillis)

    // Scale baseline height by system font scale (capped) so two lines fit.
    val fontScale = LocalDensity.current.fontScale.coerceAtLeast(minimumValue = 1f)
        .coerceAtMost(maximumValue = 1.6f)
    val scaledMinHeight = baseMinHeight * fontScale

    val borderColor = when {
        isPressed -> UICTheme.colorScheme.brand.primary.core
        isFocused || isHovered -> UICTheme.colorScheme.brand.primary.focus
        else -> UICTheme.colorScheme.structural.outlineLight
    }
    val containerColor =
        if (isPressed) UICTheme.colorScheme.brand.primary.bg else UICTheme.colorScheme.structural.bgPrimary
    val contentColor =
        if (isPressed) UICTheme.colorScheme.brand.primary.focus else UICTheme.colorScheme.brand.buttonPrimary.onCore
    val iconTint = UICTheme.colorScheme.brand.primary.core
    val iconBackgroundColor =
        if (isPressed) UICTheme.colorScheme.brand.primary.onCore else UICTheme.colorScheme.brand.primary.bg
    val borderWidth =
        if (isFocused || isHovered) ServiceItemConstants.BUTTON_BORDER_WIDTH else ServiceItemConstants.BUTTON_BORDER_WIDTH_DEFAULT

    _root_ide_package_.com.heb.centralmarket.uicart.component.buttons.DisableRippleEffect {
        FilledIconButton(
            onClick = debouncedOnClick ?: {},
            interactionSource = interactionSource,
            enabled = enabled,
            modifier = modifier
                .fillMaxWidth()
                .border(
                    width = borderWidth,
                    color = borderColor,
                    shape = RoundedCornerShape(UICCornerRadius.borderRadius.medium),
                )
                .alpha(if (enabled) 1f else ServiceItemConstants.DISABLED_ALPHA)
                .heightIn(min = scaledMinHeight)
                .fillMaxHeight(),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = containerColor,
                contentColor = contentColor,
            ),
            shape = RoundedCornerShape(UICCornerRadius.borderRadius.medium),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = UICSpacing.spacing.spacing050),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier.wrapContentSize(),
                    contentAlignment = Alignment.TopCenter,
                ) {
                    iconVector?.let {
                        Box(
                            modifier = Modifier
                                .size(size = ServiceItemConstants.ICON_SIZE)
                                .clip(CircleShape)
                                .background(iconBackgroundColor),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = it,
                                contentDescription = contentDescription,
                                tint = iconTint,
                                modifier = Modifier.size(UICSpacing.spacing.spacing100),
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = UICSpacing.spacing.spacing050),
                    contentAlignment = Alignment.Center,
                ) {
                    CoreBodyTextView(
                        text = text,
                        textAlign = TextAlign.Center,
                        isBold = true,
                        bodyVariant = BodyVariant.BODY_2,
                        maxLines = maxLines,
                        overflow = TextOverflow.Visible,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = UICSpacing.spacing.spacing050),
                    )
                }
            }
        }
    }
}

private object ServiceItemConstants {
    val BUTTON_BORDER_WIDTH = 2.dp
    val ICON_SIZE = 32.dp
    const val DISABLED_ALPHA = 0.4f
    val MIN_HEIGHT = 92.dp
    val BUTTON_BORDER_WIDTH_DEFAULT = 1.dp
    const val MAX_LINES = 2
}

data class ServiceItemData(
    val onClick: () -> Unit,
    val iconVector: ImageVector,
    val text: String = "Item",
    val enabled: Boolean = true,
    val contentDescription: String? = null,
)