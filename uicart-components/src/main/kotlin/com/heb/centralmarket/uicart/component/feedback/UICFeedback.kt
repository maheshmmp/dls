/*
 *
 *  Created by Mahesh Paul on 1/22/26, 8:32 PM
 *  Copyright (c) 2026 . All rights reserved.
 *  Last modified 1/22/26, 2:46 PM
 *
 */

package com.heb.centralmarket.uicart.component.feedback

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.heb.centralmarket.uicart.component.CoreHeadingTextView
import com.heb.centralmarket.uicart.component.HeadingVariant
import com.heb.centralmarket.uicart.component.buttons.IconButtonVariant
import com.heb.centralmarket.uicart.component.buttons.UICIconButton
import com.heb.centralmarket.uicart.component.buttons.UICIconButtonSize
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.themesystem.UICHeight
import com.heb.centralmarket.uicart.themesystem.UICShape
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme

@Composable
fun  UICFeedback(
    modifier: Modifier = Modifier,
    type: FeedbackType,
    message: String,
    description: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: ImageVector? = null,
    trailingIconOnClick: () -> Unit = {},
) {
    val bgColor = when (type) {
        FeedbackType.SUCCESS -> UICTheme.colorScheme.positive.bg
        FeedbackType.ERROR -> UICTheme.colorScheme.negative.bg
        FeedbackType.WARNING -> UICTheme.colorScheme.warning.bg
        FeedbackType.INFO -> UICTheme.colorScheme.info.bg
    }

    val closeDescription = when (type) {
        FeedbackType.SUCCESS -> stringResource(id = R.string.success_close_button_description)
        FeedbackType.ERROR -> stringResource(id = R.string.error_close_button_description)
        FeedbackType.WARNING -> stringResource(id = R.string.warning_close_button_description)
        FeedbackType.INFO -> stringResource(id = R.string.info_close_button_description)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(UICShape.shapes.smallRoundCornerShape)
            .background(bgColor)
            .defaultMinSize(minHeight = UICHeight.height.feedbackMinHeight)
            .padding(
                start = UICSpacing.spacing.spacing075, end = UICSpacing.spacing.spacing050,
                top = UICSpacing.spacing.spacing050, bottom = UICSpacing.spacing.spacing050
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (leadingIcon != null) {
            Box(modifier = Modifier
                .let {
                if (description != null) it.align(Alignment.Top)
                else it.align(Alignment.CenterVertically)
            },)
            {
                leadingIcon()
            }
        }

        Column(
            modifier = Modifier
                .weight(weight = FeedbackMessageConstants.WEIGHT)
                .padding(horizontal = UICSpacing.spacing.spacing050),
            verticalArrangement = Arrangement.Center,
        ) {
            CoreHeadingTextView(
                text = message,
                isBold = true,
                headingLevel = HeadingVariant.HEADING_4,
                color = UICTheme.colorScheme.txt.primary,
                textAlign = TextAlign.Start,
            )

            if (description != null) {
                CoreHeadingTextView(
                    modifier = Modifier.padding(top = UICSpacing.spacing.spacing025),
                    text = description,
                    isBold = false,
                    headingLevel = HeadingVariant.HEADING_5,
                    color = UICTheme.colorScheme.txt.primary,
                    textAlign = TextAlign.Start,
                )
            }
        }

        if (trailingIcon != null) {
            UICIconButton(
                onClick = trailingIconOnClick,
                buttonSize = UICIconButtonSize.SMALL,
                iconVector = trailingIcon,
                variant = IconButtonVariant.NEUTRAL,
                modifier = modifier
                    .let {
                        if (description != null) it.align(Alignment.Top)
                        else it.align(Alignment.CenterVertically)
                    },
                contentDescription = closeDescription
            )
        }
    }
}

enum class FeedbackType {
    SUCCESS, ERROR, WARNING, INFO
}

private object FeedbackMessageConstants {
     val WEIGHT = 1f
}
