/*
 * Created by Mahesh Mathew Paul on 20/02/25, 2:25 pm
 * mahesh.paul@ust.com
 * Last modified 20/02/25, 11:47 am
 * Copyright (c) 2025.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.component.listitems

/**
 * Author: Jenson
 * Date Created: 10-02-2025
 * Last Modified: 17-02-2025
 */

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.heb.centralmarket.uicart.icons.chevronRight
import com.heb.centralmarket.uicart.icons.settingsGear
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICHeight
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.utils.rememberDebouncedClick

/** Section Component with right and left icons and a divider at the bottom
 * @param leftIcon The icon that need to be displayed before the section text. Default is 'null' for the no left icon.
 * @param leftIconTint The left icon tint that need to provided for the left Icon.
 * @param contentText The title or the text that need to be displayed in the section.
 * @param descriptionText The description or the text that need to be displayed below the title in the section.
 * @param rightIcon The icon at the right side of the text. This is a mandatory icon
 * @param rightIconTint The right icon tint that need to provided for the right icon. Default is 'null'
 * @param onClick The onClick action of the section component
 * @param dividerColor The color of divider that shows at the bottom of the section
 * @param showDivider This flag is used to decide whether the divider needs to be shown or hidden.
 * @param isDividerFromParentStart This is a flag for identifying the divider start position.
*         Default will be true and will start from parent. False flag will make the divider to start from below the title
 * @param shadowElevation The elevation for the section component. Default is null
 */
@Composable
fun SectionLevelFAQ(
    modifier: Modifier = Modifier,
    leftIcon: ImageVector? = null,
    leftIconTint: Color? = null,
    contentText: String,
    descriptionText: String? = null,
    rightIcon: ImageVector,
    rightIconTint: Color? = null,
    onClick: (() -> Unit)? = null,
    dividerColor: Color,
    showDivider: Boolean,
    isDividerFromParentStart: Boolean? = true,
    shadowElevation: Dp? = null,
    debounceDelayMillis: Long = 500L,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    // Keep the remember key stable by passing the onClick directly
    val debouncedOnClick = rememberDebouncedClick(onClick, debounceDelayMillis)

    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clipToBounds()
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
                    indication = null,
                    enabled = true,
                ) {
                    debouncedOnClick?.invoke()
                },
    ) {
        Row(
            modifier =
                Modifier
                    .wrapContentSize()
                    .padding(
                        horizontal = UICSpacing.spacing.spacing100,
                        vertical = UICSpacing.spacing.spacing100,
                    ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BoldTitleSectionContent(
                leftIcon = leftIcon,
                leftIconTint = leftIconTint,
                contentText = contentText,
                descriptionText = descriptionText,
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
fun BoldTitleSectionContent(
    modifier: Modifier = Modifier,
    leftIcon: ImageVector? = null,
    leftIconTint: Color? = null,
    contentText: String,
    descriptionText: String? = null,
    rightIcon: ImageVector,
    rightIconTint: Color? = null,
) {
    val density = LocalDensity.current
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        textViewPaddingStart =
            if (leftIcon != null) {
                Box(Modifier.sizeIn(maxHeight = ButtonDefaults.IconSize)) {
                    LeftIcon(leftIcon = leftIcon, tintColor = leftIconTint ?: Color.Unspecified, modifier = modifier)
                }
                UICSpacing.spacing.spacing100
            } else {
                2.dp
            }
        Column(modifier = Modifier.weight(1f)) {
            CoreBodyTextView(
                text = contentText,
                bodyVariant = BodyVariant.BODY_1,
                modifier =
                    modifier
                        .padding(
                            start = textViewPaddingStart,
                        )
                        .onGloballyPositioned { coordinates ->
                            textStartX = coordinates.positionInParent().x +
                                    with(density) {
                                        textViewPaddingStart.toPx()
                                    }
                        },
                isBold = true,
                textAlign = TextAlign.Start,
            )
            descriptionText?.let {
                CoreBodyTextView(
                    text = it,
                    bodyVariant = BodyVariant.BODY_2,
                    modifier =
                        modifier
                            .padding(
                                start = textViewPaddingStart,
                                top = UICSpacing.spacing.spacing025,
                            )
                            .onGloballyPositioned { coordinates ->
                                textStartX = coordinates.positionInParent().x +
                                        with(density) {
                                            textViewPaddingStart.toPx()
                                        }
                            },
                    color = UICTheme.colorScheme.txt.secondary,
                    textAlign = TextAlign.Start,
                )
            }
        }
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

@Preview
@Composable
fun BoldTitleSectionWithLeftIconPreview() {
    UICAppTheme {
        CoreBackground(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .height(UICHeight.height.large),
        ) {
            SectionLevelFAQ(
                modifier = Modifier,
                leftIcon = settingsGear(),
                leftIconTint = null,
                contentText = stringResource(id = R.string.section_terms_of_use),
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
