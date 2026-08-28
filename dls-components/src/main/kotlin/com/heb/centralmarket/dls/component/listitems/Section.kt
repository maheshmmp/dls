/*
 * Created by Mahesh Mathew Paul on 20/02/25, 2:25 pm
 * mahesh.paul@ust.com
 * Last modified 20/02/25, 9:58 am
 * Copyright (c) 2025.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.component.listitems

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.component.BodyVariant
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.CoreBodyTextView
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.icons.accountAvatar
import com.heb.centralmarket.uicart.icons.chevronRight
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICHeight
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.utils.rememberDebouncedClick

/**
 * Author: Ritu Varma G
 * Date Created: 27-01-2025
 * Last Modified: 27-01-2025
 */
var textStartX by mutableFloatStateOf(0f)
var textViewPaddingStart by mutableStateOf(0.dp)

/** Section Component with right and left icons and a divider at the bottom
 * @param leftIcon The icon that need to be displayed before the section text. Default is 'null' for the no left icon.
 * @param leftIconTint The left icon tint that need to provided for the left Icon.
 * @param contentText The title or the text that need to be displayed in the section.
 * @param rightIcon The icon at the right side of the text. This is a mandatory icon
 * @param rightIconTint The right icon tint that need to provided for the right icon. Default is 'null'
 * @param onClick The onClick action of the section component (debounced with 300ms delay to prevent rapid successive invocations)
 * @param dividerColor The color of divider that shows at the bottom of the section
 * @param showDivider This flag is used to decide whether the divider needs to be shown or hidden.
 * @param isDividerFromParentStart This is a flag for identifying the divider start position.
 *         Default will be true and will start from parent. False flag will make the divider to start from below the title
 * @param sectionHeight The height of the section component as a whole. Default will the 65px.
 * @param shadowElevation The elevation for the section component. Default is null
 * @param debounceDelayMillis The debounce delay in milliseconds. Default is 300ms
 */
@Composable
fun Section(
    modifier: Modifier = Modifier,
    leftIcon: ImageVector? = null,
    leftIconTint: Color? = null,
    contentText: String,
    rightIcon: ImageVector? = null,
    rightIconTint: Color? = null,
    onClick: (() -> Unit)? = null,
    dividerColor: Color,
    showDivider: Boolean,
    isDividerFromParentStart: Boolean? = true,
    sectionHeight: Dp? = UICHeight.height.large,
    shadowElevation: Dp? = null,
    debounceDelayMillis: Long = 500L,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // Use the shared debouncer utility to avoid coroutine races and ensure per-composition state
    // Pass the onClick directly so the remember key remains stable across recompositions
    val debouncedOnClick: (() -> Unit)? = rememberDebouncedClick(onClick, debounceDelayMillis)

    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .clipToBounds()
                .height(sectionHeight ?: UICHeight.height.large)
                .then(
                    if (shadowElevation != null) {
                        Modifier.shadow(shadowElevation)
                    } else {
                        Modifier
                    },
                )
                .background(
                    if (isPressed) {
                        UICTheme.colorScheme.structural.bgSecondary
                    } else {
                        UICTheme.colorScheme.structural.bgPrimary
                    },
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = null, // You can remove this if you want default ripple effect
                    enabled = true,
                ) {
                    debouncedOnClick?.invoke()
                },
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = UICSpacing.spacing.spacing100),
            verticalAlignment = Alignment.CenterVertically, // Center content vertically
        ) {
            SectionContent(
                leftIcon = leftIcon,
                leftIconTint = leftIconTint,
                contentText = contentText,
                rightIcon = rightIcon,
                rightIconTint = rightIconTint,
            )
        }
        if (showDivider) {
            SectionDivider(
                modifier =
                    if (isDividerFromParentStart == true) {
                        Modifier
                            .align(Alignment.BottomStart)
                    } else {
                        if (leftIcon != null) {
                            Modifier
                                .align(Alignment.BottomStart)
                                .graphicsLayer(translationX = textStartX)
                        } else {
                            Modifier
                                .align(Alignment.BottomStart)
                                .padding(start = UICSpacing.spacing.spacing100)
                        }
                    },
                dividerColor = dividerColor,
            )
        }
    }
}

@Composable
fun SectionContent(
    modifier: Modifier = Modifier,
    leftIcon: ImageVector? = null,
    leftIconTint: Color? = null,
    contentText: String,
    rightIcon: ImageVector? = null,
    rightIconTint: Color? = null,
) {
    val density = LocalDensity.current
    Row(
        modifier =
            Modifier
                .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leftIcon != null) {
            Box(Modifier.sizeIn(maxHeight = ButtonDefaults.IconSize)) {
                LeftIcon(
                    leftIcon = leftIcon,
                    tintColor = leftIconTint ?: Color.Unspecified,
                    modifier = modifier
                )
            }
            textViewPaddingStart = UICSpacing.spacing.spacing100
        } else {
            textViewPaddingStart = 2.dp
        }
        CoreBodyTextView(
            text = contentText,
            bodyVariant = BodyVariant.BODY_1,
            modifier =
                modifier
                    .weight(1f)
                    .padding(
                        start = textViewPaddingStart,
                    )
                    .onGloballyPositioned { coordinates ->
                        textStartX = coordinates.positionInParent().x +
                                with(density) {
                                    textViewPaddingStart.toPx()
                                }
                    },
            textAlign = TextAlign.Start,
        )
        Box {
            RightIcon(
                rightIcon = rightIcon,
                tintColor = rightIconTint ?: Color.Unspecified,
                modifier =
                    modifier.padding(
                        UICSpacing.spacing.spacing050,
                    ),
            )
        }
    }
}

@Composable
fun LeftIcon(
    leftIcon: ImageVector?,
    contentDescription: String? = null,
    tintColor: Color,
    modifier: Modifier,
) {
    if (leftIcon != null) {
        Icon(
            imageVector = leftIcon,
            contentDescription = contentDescription,
            tint = tintColor,
            modifier = modifier,
        )
    }
}

@Composable
fun RightIcon(
    rightIcon: ImageVector?,
    contentDescription: String? = null,
    tintColor: Color?,
    modifier: Modifier,
) {
    if (rightIcon != null) {
        Icon(
            imageVector = rightIcon,
            contentDescription = contentDescription,
            tint = tintColor ?: Color.Unspecified,
            modifier = modifier,
        )
    }
}

@Composable
fun SectionDivider(
    modifier: Modifier = Modifier,
    dividerColor: Color,
) {
    HorizontalDivider(
        modifier = modifier,
        color = dividerColor,
        thickness = UICHeight.height.dividerHeight,
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun SectionWithLeftIconPreview() {
    UICAppTheme {
        CoreBackground(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .height(UICHeight.height.large),
        ) {
            Section(
                modifier = Modifier,
                leftIcon = accountAvatar(),
                leftIconTint = UICTheme.colorScheme.brandSecondary.core,
                contentText = stringResource(id = R.string.section_title_account),
                rightIcon = chevronRight(),
                onClick = null,
                dividerColor = UICTheme.colorScheme.neutral.gray3,
                isDividerFromParentStart = true,
                showDivider = true,
                shadowElevation = 4.dp,
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun SectionWithoutLeftIconPreview() {
    UICAppTheme {
        CoreBackground(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .height(UICHeight.height.large),
        ) {
            Section(
                modifier = Modifier,
                contentText = stringResource(id = R.string.section_title_account),
                rightIcon = chevronRight(),
                onClick = null,
                dividerColor = UICTheme.colorScheme.neutral.gray3,
                showDivider = true,
                shadowElevation = 4.dp,
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun SectionWithDividerStartFromParentPreview() {
    UICAppTheme {
        CoreBackground(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .height(UICHeight.height.large),
        ) {
            Section(
                modifier = Modifier,
                leftIcon = accountAvatar(),
                leftIconTint = UICTheme.colorScheme.brandSecondary.core,
                contentText = stringResource(id = R.string.section_title_account),
                rightIcon = chevronRight(),
                onClick = null,
                dividerColor = UICTheme.colorScheme.neutral.gray3,
                isDividerFromParentStart = true,
                showDivider = true,
                shadowElevation = 4.dp,
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun SectionWithDividerStartFromTitlePreview() {
    UICAppTheme {
        CoreBackground(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .height(UICHeight.height.large),
        ) {
            Section(
                modifier = Modifier,
                leftIcon = accountAvatar(),
                leftIconTint = UICTheme.colorScheme.brandSecondary.core,
                contentText = stringResource(id = R.string.section_title_account),
                rightIcon = chevronRight(),
                onClick = null,
                dividerColor = UICTheme.colorScheme.neutral.gray3,
                isDividerFromParentStart = false,
                showDivider = true,
                shadowElevation = 4.dp,
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun SectionWithoutDividerPreview() {
    UICAppTheme {
        CoreBackground(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .height(UICHeight.height.large),
        ) {
            Section(
                modifier = Modifier,
                leftIcon = accountAvatar(),
                leftIconTint = UICTheme.colorScheme.brandSecondary.core,
                contentText = stringResource(id = R.string.section_title_account),
                rightIcon = chevronRight(),
                onClick = null,
                dividerColor = UICTheme.colorScheme.neutral.gray3,
                isDividerFromParentStart = false,
                showDivider = false,
                shadowElevation = 4.dp,
            )
        }
    }
}
