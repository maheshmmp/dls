/*
 *
 *  Created by 160857 on 11/24/25, 7:29 PM
 *  Copyright (c) 2025 . All rights reserved.
 *  Last modified 11/24/25, 7:29 PM
 *
 */

package com.heb.centralmarket.uicart.component.bottomButtonContainer

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.component.CoreCaptionTextView
import com.heb.centralmarket.uicart.component.buttons.CoreButtonSize
import com.heb.centralmarket.uicart.component.buttons.UICPrimaryButton
import com.heb.centralmarket.uicart.component.buttons.UICSecondaryButton
import com.heb.centralmarket.uicart.component.buttons.UICTertiaryButton
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme

/**
 * A reusable bottom container for displaying one or two action buttons, optionally accompanied by
 * a caption or helper text.
 *
 * This composable is typically used at the bottom of a screen for primary actions such as
 * "Continue", "Submit", or "Cancel". It supports both horizontal and vertical layouts.
 *
 * ### Layout behavior:
 * - Wrapped inside a [Surface] with a subtle shadow and white background.
 * - Buttons can be arranged either horizontally (side by side) or vertically (stacked).
 * - Optionally displays caption or helper text below the buttons.
 *
 * @param modifier Modifier to apply to the container layout.
 * @param contentOrientation Defines whether the buttons are arranged horizontally or vertically. Defaults to [Orientation.Horizontal].
 * @param primaryButton Composable function representing the primary action button. Typically styled as the main or emphasized action.
 * @param secondaryButton Optional composable function representing the secondary action button. Displayed next to or below the primary button
 *                        depending on orientation.
 * @param captionText Optional composable function for displaying helper or descriptive text below the buttons.
 * @param isComponentFilled This param is used to make the container TRANSPARENT or filled with WHITE background and SHADOW. Default is White
 */
@Composable
fun UICBottomButtonsContainer(
    modifier: Modifier,
    contentOrientation: Orientation = Orientation.Horizontal,
    primaryButton: @Composable () -> Unit,
    secondaryButton: (@Composable () -> Unit)? = null,
    captionText: (@Composable () -> Unit)? = null,
    isComponentFilled: Boolean? = true
) {
    val baseModifier =
        modifier
            .fillMaxWidth()
            .wrapContentHeight()

    val containerModifier = if (isComponentFilled == true) {
        baseModifier
            .dropShadow(
                shape = RectangleShape,
                shadow = Shadow(
                    radius = UICSpacing.spacing.spacing025,
                    color = UICTheme.colorScheme.shadow.default,
                    alpha = 1f,
                    offset = DpOffset(y = MinusTwoDp, x = UICSpacing.spacing.spacing000)
                )
            )
    }
    else{
        baseModifier
    }

    Surface(
        modifier = containerModifier,
        color = if (isComponentFilled == true) UICTheme.colorScheme.structural.bgPrimary else Color.Transparent,
    ) {
        Column(
            modifier = Modifier
                .padding(all = UICSpacing.spacing.spacing100)
                .fillMaxWidth()
        ) {
            when (contentOrientation) {
                Orientation.Vertical -> {
                    Column {
                        Box(modifier = modifier.fillMaxWidth()) {
                            primaryButton()
                        }
                        secondaryButton?.let {
                            Box(modifier = modifier.fillMaxWidth()) {
                                it.invoke()
                            }
                        }
                    }
                }

                Orientation.Horizontal -> {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(UICSpacing.spacing.spacing100)
                    ) {
                        secondaryButton?.let {
                            Box(modifier = Modifier.weight(1f)) {
                                it.invoke()
                            }
                        }

                        Box(modifier = modifier.weight(1f)) {
                            primaryButton()
                        }
                    }
                }
            }
            captionText?.let {
                Spacer(modifier = Modifier.height(UICSpacing.spacing.spacing050))
                it.invoke()
            }
        }
    }
}

private val MinusTwoDp = (-2).dp


//Previews
@Preview(showBackground = true)
@Composable
fun FilledContainerPrimaryOnlyPreview() {
    UICAppTheme {
        UICBottomButtonsContainer(
            contentOrientation = Orientation.Horizontal,
            primaryButton = {
                UICPrimaryButton(
                    onClick = {},
                    buttonText = stringResource(R.string.bottom_button_container_primary_button).uppercase(),
                    buttonSize = CoreButtonSize.LARGE,
                    modifier = Modifier.fillMaxWidth(),
                    isButtonFullWidth = true,
                )
            },
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilledContainerWithoutDescriptionHorizontalPreview() {
    UICAppTheme {
        UICBottomButtonsContainer(
            contentOrientation = Orientation.Horizontal,
            primaryButton = {
                UICPrimaryButton(
                    onClick = {},
                    buttonText = stringResource(R.string.bottom_button_container_primary_button).uppercase(),
                    buttonSize = CoreButtonSize.LARGE,
                    modifier = Modifier.fillMaxWidth(),
                    isButtonFullWidth = true,
                )
            },
            secondaryButton = {
                UICSecondaryButton(
                    onClick = {},
                    buttonText = stringResource(R.string.bottom_button_container_secondary_button).uppercase(),
                    buttonSize = CoreButtonSize.LARGE,
                    modifier = Modifier.fillMaxWidth(),
                    isButtonFullWidth = true,
                )
            },
            modifier = Modifier,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilledContainerWithPrimaryTertiaryPreview() {
    UICAppTheme {
        UICBottomButtonsContainer(
            contentOrientation = Orientation.Vertical,
            primaryButton = {
                UICPrimaryButton(
                    onClick = {},
                    buttonText = stringResource(R.string.bottom_button_container_primary_button).uppercase(),
                    buttonSize = CoreButtonSize.LARGE,
                    modifier = Modifier.fillMaxWidth(),
                    isButtonFullWidth = true,
                )
            },
            secondaryButton = {
                UICTertiaryButton(
                    onClick = {},
                    buttonText = stringResource(R.string.bottom_button_container_secondary_button1).uppercase(),
                    buttonSize = CoreButtonSize.LARGE,
                    modifier = Modifier.fillMaxWidth(),
                    isButtonFullWidth = true,
                )
            },
            modifier = Modifier
        )
    }
}
@Preview(showBackground = true)
@Composable
fun FilledContainerVerticalPreview() {
    UICAppTheme {
        UICBottomButtonsContainer(
            contentOrientation = Orientation.Vertical,
            primaryButton = {
                UICPrimaryButton(
                    onClick = {},
                    buttonText = stringResource(R.string.bottom_button_container_primary_button).uppercase(),
                    buttonSize = CoreButtonSize.LARGE,
                    modifier = Modifier.fillMaxWidth(),
                    isButtonFullWidth = true,
                )
            },
            secondaryButton = {
                UICTertiaryButton(
                    onClick = {},
                    buttonText = stringResource(R.string.bottom_button_container_secondary_button1).uppercase(),
                    buttonSize = CoreButtonSize.LARGE,
                    modifier = Modifier.fillMaxWidth(),
                    isButtonFullWidth = true,
                )
            },
            captionText = {
                CoreCaptionTextView(
                    text = stringResource(R.string.bottom_button_container_description),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
            },
            modifier = Modifier
        )
    }
}
@Preview(showBackground = true)
@Composable
fun FilledContainerWithoutDescriptionPreview() {
    UICAppTheme {
        UICBottomButtonsContainer(
            contentOrientation = Orientation.Vertical,
            primaryButton = {
                UICPrimaryButton(
                    onClick = {},
                    buttonText = stringResource(R.string.bottom_button_container_primary_button).uppercase(),
                    buttonSize = CoreButtonSize.LARGE,
                    modifier = Modifier.fillMaxWidth(),
                    isButtonFullWidth = true,
                )
            },
            captionText = {
                CoreCaptionTextView(
                    text = stringResource(R.string.bottom_button_container_description),
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )
            },
            modifier = Modifier
        )
    }
}
@Preview(showBackground = true)
@Composable
fun TransparentContainerPrimaryOnlyPreview() {
    UICAppTheme {
        UICBottomButtonsContainer(
            contentOrientation = Orientation.Horizontal,
            primaryButton = {
                UICPrimaryButton(
                    onClick = {},
                    buttonText = stringResource(R.string.bottom_button_container_primary_button).uppercase(),
                    buttonSize = CoreButtonSize.LARGE,
                    modifier = Modifier.fillMaxWidth(),
                    isButtonFullWidth = true,
                )
            },
            modifier = Modifier,
            isComponentFilled = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TransparentContainerWithoutDescriptionHorizontalPreview() {
    UICAppTheme {
        UICBottomButtonsContainer(
            contentOrientation = Orientation.Horizontal,
            primaryButton = {
                UICPrimaryButton(
                    onClick = {},
                    buttonText = stringResource(R.string.bottom_button_container_primary_button).uppercase(),
                    buttonSize = CoreButtonSize.LARGE,
                    modifier = Modifier.fillMaxWidth(),
                    isButtonFullWidth = true,
                )
            },
            secondaryButton = {
                UICSecondaryButton(
                    onClick = {},
                    buttonText = stringResource(R.string.bottom_button_container_secondary_button).uppercase(),
                    buttonSize = CoreButtonSize.LARGE,
                    modifier = Modifier.fillMaxWidth(),
                    isButtonFullWidth = true,
                )
            },
            modifier = Modifier,
            isComponentFilled = false
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TransparentContainerWithPrimaryTertiaryPreview() {
    UICAppTheme {
        UICBottomButtonsContainer(
            contentOrientation = Orientation.Vertical,
            primaryButton = {
                UICPrimaryButton(
                    onClick = {},
                    buttonText = stringResource(R.string.bottom_button_container_primary_button).uppercase(),
                    buttonSize = CoreButtonSize.LARGE,
                    modifier = Modifier.fillMaxWidth(),
                    isButtonFullWidth = true,
                )
            },
            secondaryButton = {
                UICTertiaryButton(
                    onClick = {},
                    buttonText = stringResource(R.string.bottom_button_container_secondary_button1).uppercase(),
                    buttonSize = CoreButtonSize.LARGE,
                    modifier = Modifier.fillMaxWidth(),
                    isButtonFullWidth = true,
                )
            },
            modifier = Modifier,
            isComponentFilled = false
        )
    }
}
@Preview(showBackground = true)
@Composable
fun TransparentContainerVerticalPreview() {
    UICAppTheme {
        UICBottomButtonsContainer(
            contentOrientation = Orientation.Vertical,
            primaryButton = {
                UICPrimaryButton(
                    onClick = {},
                    buttonText = stringResource(R.string.bottom_button_container_primary_button).uppercase(),
                    buttonSize = CoreButtonSize.LARGE,
                    modifier = Modifier.fillMaxWidth(),
                    isButtonFullWidth = true,
                )
            },
            secondaryButton = {
                UICTertiaryButton(
                    onClick = {},
                    buttonText = stringResource(R.string.bottom_button_container_secondary_button1).uppercase(),
                    buttonSize = CoreButtonSize.LARGE,
                    modifier = Modifier.fillMaxWidth(),
                    isButtonFullWidth = true,
                )
            },
            captionText = {
                CoreCaptionTextView(
                    text = stringResource(R.string.bottom_button_container_description),
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )
            },
            modifier = Modifier,
            isComponentFilled = false
        )
    }
}
@Preview(showBackground = true)
@Composable
fun TransparentContainerWithoutDescriptionPreview() {
    UICAppTheme {
        UICBottomButtonsContainer(
            contentOrientation = Orientation.Vertical,
            primaryButton = {
                UICPrimaryButton(
                    onClick = {},
                    buttonText = stringResource(R.string.bottom_button_container_primary_button).uppercase(),
                    buttonSize = CoreButtonSize.LARGE,
                    modifier = Modifier.fillMaxWidth(),
                    isButtonFullWidth = true,
                )
            },
            captionText = {
                CoreCaptionTextView(
                    text = stringResource(R.string.bottom_button_container_description),
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )
            },
            modifier = Modifier,
            isComponentFilled = false
        )
    }
}