/*
 *
 *  Created by Mahesh Paul on 1/21/26, 8:29 PM
 *  Copyright (c) 2026 . All rights reserved.
 *  Last modified 1/21/26, 7:55 PM
 *
 */

package com.heb.centralmarket.uicart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.component.AppBarDefaults.HEADING_LEVEL
import com.heb.centralmarket.uicart.component.AppBarDefaults.MAX_LINES
import com.heb.centralmarket.uicart.component.UICHeaderFulfilmentDefaults.OVERLAP_OFFSET
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.utils.TestTags

/**
 * UICFulfilmentAppBar combines the FulfilmentAppBar and AppBarToggleControl into a single composable.
 *
 * This component displays a top app bar with a centered title and navigation icon,
 * and overlays a segmented control (toggle) below the app bar, maintaining the same UI placement as before.
 *
 * @param modifier Modifier to be applied to the control.
 * @param headerText The text to display as the app bar title.
 * @param items List of tab labels to display in the segmented control.
 * @param selectedIndex The index of the currently selected tab.
 * @param onItemSelected Callback invoked when a tab is selected, with the selected index.
 * @param enabled Whether the segmented control is enabled for interaction.
 * @param appBarTheme The color theme for the app bar (primary or secondary).
 * @param navigationIcon The [androidx.compose.ui.graphics.vector.ImageVector] for the navigation icon (e.g., back button).
 * @param actionIcon The [androidx.compose.ui.graphics.vector.ImageVector] for the action icon (e.g., settings or search).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UICHeaderFulfilment(
    modifier: Modifier = Modifier,
    headerText: String,
    items: List<String>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    enabled: Boolean = true,
    appBarTheme: AppBarTheme = AppBarTheme.PRIMARY,
    navigationIcon: @Composable (() -> Unit)? = null,
    actionIcon: @Composable (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(UICTheme.colorScheme.brand.primary.core)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            val backgroundColor =
                when (appBarTheme) {
                    AppBarTheme.PRIMARY -> UICTheme.colorScheme.brand.primary.core
                    AppBarTheme.SECONDARY -> UICTheme.colorScheme.structural.bgPrimary
                }

            val foregroundColor =
                when (appBarTheme) {
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
                            }.testTag(TestTags.Fulfillment.HEADER),
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
                    // TODO: wire actionIcon if/when needed
                    // Keeping behavior unchanged for now.
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = backgroundColor,
                        navigationIconContentColor = foregroundColor,
                        titleContentColor = foregroundColor,
                        actionIconContentColor = foregroundColor,
                    ),
                windowInsets = WindowInsets(0)
            )
            Spacer(modifier = Modifier.height(UICSpacing.spacing.spacing100))
        }

        // Overlapping segmented control as part of topBar
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = UICSpacing.spacing.spacing100)
                .align(Alignment.BottomCenter)
                .offset(y = OVERLAP_OFFSET),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeaderToggleControl(
                items = items,
                selectedIndex = selectedIndex,
                onItemSelected = onItemSelected,
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
            )
        }
    }
}


// Default values for toggle item control appearance
private object UICHeaderFulfilmentDefaults {
    val OVERLAP_OFFSET = (+21).dp
}