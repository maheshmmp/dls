/*
 *
 *  Created by Mahesh Paul on 1/13/26, 7:48 PM
 *  Copyright (c) 2026 . All rights reserved.
 *  Last modified 1/13/26, 7:39 PM
 *
 */

package com.heb.centralmarket.uicart.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.feedback.FeedbackType
import com.heb.centralmarket.uicart.component.feedback.UICFeedback
import com.heb.centralmarket.uicart.icons.CoreIcon
import com.heb.centralmarket.uicart.icons.cautionIcon
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.icons.errorVector
import com.heb.centralmarket.uicart.icons.infoMessage
import com.heb.centralmarket.uicart.icons.successVector
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

@Composable
fun FeedbackScreen(
    navController: NavHostController,
    drawerState: DrawerState,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = R.string.feedback,
                drawerState = drawerState,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = chevronLeft(),
                            contentDescription = stringResource(id = R.string.back),
                            tint = UICTheme.colorScheme.txt.primary,
                        )
                    }
                },
                actionIcon = {
                    ThemeSwitcherAction()
                    DarkModeSwitcherAction()
                }
            )
        },
    ) {
        CoreBackground {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(it)
                    .padding(horizontal = UICSpacing.spacing.spacing050),
                verticalArrangement = Arrangement.spacedBy(UICSpacing.spacing.spacing050),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = stringResource(id = R.string.feedback_message1),
                    modifier = Modifier
                        .padding(
                            top = UICSpacing.spacing.spacing100
                        )
                        .align(Alignment.Start),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )

                UICFeedback(
                    type = FeedbackType.SUCCESS,
                    message = stringResource(id = R.string.success_message),
                    leadingIcon = {
                        CoreIcon(
                            imageVector = successVector(),
                            contentDescription = stringResource(id = R.string.success_icon_description),
                            tint = UICTheme.colorScheme.positive.core,
                            size = UICSpacing.spacing.spacing125
                        )
                    }
                )

                UICFeedback(
                    type = FeedbackType.ERROR,
                    message = stringResource(id = R.string.feedback_error_message),
                    leadingIcon = {
                        CoreIcon(
                            imageVector = errorVector(),
                            contentDescription = stringResource(id = R.string.error_icon_description),
                            tint = UICTheme.colorScheme.negative.core,
                            size = UICSpacing.spacing.spacing125
                        )
                    }
                )

                UICFeedback(
                    type = FeedbackType.WARNING,
                    message = stringResource(id = R.string.warning_message),
                    leadingIcon = {
                        CoreIcon(
                            imageVector = cautionIcon(),
                            contentDescription = stringResource(id = R.string.warning_icon_description),
                            tint = UICTheme.colorScheme.warning.core,
                            size = UICSpacing.spacing.spacing125
                        )
                    }
                )

                UICFeedback(
                    type = FeedbackType.INFO,
                    message = stringResource(id = R.string.info_message),
                    leadingIcon = {
                        CoreIcon(
                            imageVector = infoMessage(),
                            contentDescription = stringResource(id = R.string.info_icon_description),
                            tint = UICTheme.colorScheme.info.core,
                            size = UICSpacing.spacing.spacing125
                        )
                    }
                )

                UICFeedback(
                    type = FeedbackType.SUCCESS,
                    message = stringResource(id = R.string.success_message),
                    description = stringResource(id = R.string.section_description),
                    leadingIcon = {
                        CoreIcon(
                            imageVector = successVector(),
                            contentDescription = stringResource(id = R.string.success_icon_description),
                            tint = UICTheme.colorScheme.positive.core,
                            size = UICSpacing.spacing.spacing125
                        )
                    },
                    trailingIcon = infoMessage()
                )

                Text(
                    text = stringResource(id = R.string.feedback_message2),
                    modifier = Modifier
                        .padding(
                            top = UICSpacing.spacing.spacing100
                        )
                        .align(Alignment.Start),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )

                UICFeedback(
                    type = FeedbackType.SUCCESS,
                    message = stringResource(R.string.feedback_success_message),
                    description = stringResource(R.string.feedback_success_description),
                    leadingIcon = {
                        CoreIcon(
                            imageVector = successVector(),
                            contentDescription = stringResource(id = R.string.success_icon_description),
                            tint = UICTheme.colorScheme.positive.core,
                            size = UICSpacing.spacing.spacing125
                        )
                    },
                    trailingIcon = infoMessage()
                )

                UICFeedback(
                    type = FeedbackType.ERROR,
                    message = stringResource(R.string.feedback_error_message_1),
                    leadingIcon = {
                        CoreIcon(
                            imageVector = errorVector(),
                            contentDescription = stringResource(id = R.string.error_icon_description),
                            tint = UICTheme.colorScheme.negative.core,
                            size = UICSpacing.spacing.spacing125
                        )
                    },
                )

                UICFeedback(
                    type = FeedbackType.WARNING,
                    message = stringResource(R.string.feedback_warning_message),
                    leadingIcon = {
                        CoreIcon(
                            imageVector = cautionIcon(),
                            contentDescription = stringResource(id = R.string.warning_icon_description),
                            tint = UICTheme.colorScheme.warning.core,
                            size = UICSpacing.spacing.spacing125
                        )
                    },
                )

                UICFeedback(
                    type = FeedbackType.INFO,
                    message = stringResource(R.string.feedback_info_message),
                    leadingIcon = {
                        CoreIcon(
                            imageVector = infoMessage(),
                            contentDescription = stringResource(id = R.string.info_icon_description),
                            tint = UICTheme.colorScheme.info.core,
                            size = UICSpacing.spacing.spacing125
                        )
                    },
                    trailingIcon = infoMessage()
                )

                UICFeedback(
                    type = FeedbackType.SUCCESS,
                    message = stringResource(R.string.feedback_success_message),
                    description = stringResource(R.string.feedback_success_description),
                    leadingIcon = {
                        CoreIcon(
                            imageVector = successVector(),
                            contentDescription = stringResource(id = R.string.success_icon_description),
                            tint = UICTheme.colorScheme.positive.core,
                            size = UICSpacing.spacing.spacing125
                        )
                    },
                )
            }
        }
    }
}
