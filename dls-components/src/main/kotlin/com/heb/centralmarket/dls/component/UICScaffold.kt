/*
 *
 *  Created by Mahesh Paul on 12/18/25, 5:48 PM
 *  Copyright (c) 2025 . All rights reserved.
 *  Last modified 12/18/25, 9:13 AM
 *  
 */

package com.heb.centralmarket.uicart.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.heb.centralmarket.uicart.component.buttons.CoreIconButton
import com.heb.centralmarket.uicart.component.buttons.IconButtonSize
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.icons.backArrow
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.utils.TestTags

/**
 * Composable for the My Account screen content.
 * This screen is wrapped in a shared [UICScaffold] that provides a top app bar
 * and consistent layout styling. Displays a list of account-related options and navigates
 * to other screens such as FAQ, About, etc.
 */
@Composable
fun UICScaffold(
    headerText: String,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    topBarWindowInsets: WindowInsets = WindowInsets(left = 0, top = 0, right = 0, bottom = 0),
    content: @Composable (PaddingValues) -> Unit,
) {
    UICScaffold(
        topBar = {
            SimpleTopAppBar(
                headerText = headerText,
                navigationIcon = {
                    CoreIconButton(
                        onClick = onNavigateBack,
                        buttonSize = IconButtonSize.SMALL,
                        theme = AppBarTheme.PRIMARY,
                        showBadge = true,
                        iconVector = backArrow(),
                        contentDescription = stringResource(id = R.string.preview_navigation_icon),
                        modifier = Modifier.testTag(TestTags.TimeSlot.BACK_BUTTON)
                    )
                },
                theme = AppBarTheme.PRIMARY,
                windowInsets = topBarWindowInsets,
            )
        },
        modifier = modifier,
        contentWindowInsets = WindowInsets(left = 0, top = 0, right = 0, bottom = 0),
        content = content
    )
}

@Composable
fun UICScaffold(
    topBar: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    contentWindowInsets: WindowInsets = WindowInsets(left = 0, top = 0, right = 0, bottom = 0),
    content: @Composable (PaddingValues) -> Unit,
) {
    UICAppTheme {
        Scaffold(
            topBar = topBar,
            modifier = modifier,
            contentWindowInsets = contentWindowInsets
        ) { paddingValues ->
            content(paddingValues)
        }
    }
}
