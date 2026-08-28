/*
 * Created by Mahesh Mathew Paul on 28/01/25, 2:14 pm
 * mahesh.paul@ust.com
 * Last modified 28/01/25, 2:14 pm
 * Copyright (c) 2025.
 * All rights reserved.
 */

@file:OptIn(ExperimentalMaterial3Api::class)

package com.heb.centralmarket.uicart.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.heb.centralmarket.uicart.component.AppBarDefaults.HEADING_LEVEL
import com.heb.centralmarket.uicart.component.AppBarDefaults.MAX_LINES
import com.heb.centralmarket.uicart.component.buttons.CoreIconButton
import com.heb.centralmarket.uicart.component.buttons.IconButtonSize
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.icons.accountAvatar
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.utils.TestTags

/**
 * A simple top app bar with a centered title, navigation icon, and action icon.
 *
 * @param headerText The string resource ID for the title text.
 * @param navigationIcon The [ImageVector] for the navigation icon (e.g., back button).
 * @param actionIcon The [ImageVector] for the action icon (e.g., settings or search).
 * @param modifier The [Modifier] to be applied to the AppBar.
 * @param theme The theme of the AppBar, either [AppBarTheme.PRIMARY] or [AppBarTheme.SECONDARY].
 */
@Composable
fun SimpleTopAppBar(
    headerText: String,
    navigationIcon: @Composable (() -> Unit)? = null,
    actionIcon: @Composable (() -> Unit)? = null,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    theme: AppBarTheme = AppBarTheme.PRIMARY,
    windowInsets: WindowInsets = WindowInsets(left = 0, top = 0, right = 0, bottom = 0),
) {
    val backgroundColor =
        when (theme) {
            AppBarTheme.PRIMARY -> UICTheme.colorScheme.brand.primary.core
            AppBarTheme.SECONDARY -> UICTheme.colorScheme.structural.bgPrimary
        }

    val foregroundColor =
        when (theme) {
            AppBarTheme.PRIMARY -> UICTheme.colorScheme.brand.primary.onCore
            AppBarTheme.SECONDARY -> UICTheme.colorScheme.brand.primary.core
        }

    CenterAlignedTopAppBar(
        title = {
            CoreHeadingTextView(
                text = headerText.uppercase(),
                color = foregroundColor,
                headingLevel = HEADING_LEVEL,
                overflow = TextOverflow.Ellipsis,
                maxLines = MAX_LINES,
                modifier =
                    Modifier.semantics {
                        contentDescription = headerText
                        heading()
                    }.testTag(TestTags.TimeSlot.HEADER),
            )
        },
        navigationIcon = {
            if (navigationIcon != null) {
                Box(modifier = Modifier.padding(start = UICSpacing.spacing.spacing050)) {
                    navigationIcon()
                }
            }
        },
        actions = {
            if (actionIcon != null) {
                actionIcon()
            }
        },
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = backgroundColor,
                navigationIconContentColor = foregroundColor,
                titleContentColor = foregroundColor,
                actionIconContentColor = foregroundColor
            ),
        modifier =
            modifier
                .testTag("cm_toolbar"),
        windowInsets = windowInsets
    )
}

enum class AppBarTheme {
    PRIMARY,
    SECONDARY,
}

object AppBarDefaults {
    const val HEADING_LEVEL = 4
    const val MAX_LINES = 1
}

@Preview(showBackground = true)
@Composable
private fun SimpleTopAppBarPrimaryPreview() {
    UICAppTheme {
        SimpleTopAppBar(
            headerText = stringResource(R.string.topappbar),
            navigationIcon = {
                CoreIconButton(
                    onClick = { /* Handle click */ },
                    badgeCount = "2",
                    buttonSize = IconButtonSize.SMALL,
                    showBadge = true,
                    iconVector = chevronLeft(),
                    contentDescription = stringResource(R.string.preview_navigation_icon),
                )
            },
            actionIcon = {
                CoreIconButton(
                    onClick = { /* Handle click */ },
                    badgeCount = "2",
                    buttonSize = IconButtonSize.SMALL,
                    showBadge = true,
                    iconVector = accountAvatar(),
                    contentDescription = stringResource(R.string.preview_action_icon),
                )
            },
            theme = AppBarTheme.PRIMARY,
            modifier = Modifier.wrapContentSize(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SimpleTopAppBarSecondaryPreview() {
    UICAppTheme {
        SimpleTopAppBar(
            headerText = stringResource(R.string.topappbar),
            navigationIcon = {
                CoreIconButton(
                    onClick = { /* Handle click */ },
                    badgeCount = "2",
                    buttonSize = IconButtonSize.SMALL,
                    showBadge = true,
                    iconVector = chevronLeft(),
                    contentDescription = stringResource(R.string.preview_navigation_icon),
                )
            },
            actionIcon = {
                CoreIconButton(
                    onClick = { /* Handle click */ },
                    badgeCount = "2",
                    buttonSize = IconButtonSize.SMALL,
                    showBadge = true,
                    iconVector = chevronLeft(),
                    contentDescription = stringResource(R.string.preview_action_icon),
                )
            },
            theme = AppBarTheme.SECONDARY,
        )
    }
}
