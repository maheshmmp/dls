/*
 *
 *  Created by Mahesh Paul on 1/15/26, 11:30 AM
 *  Copyright (c) 2026 . All rights reserved.
 *  Last modified 1/15/26, 9:44 AM
 *
 */

package com.heb.centralmarket.uicart.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.heb.centralmarket.uicart.MainNavOption
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.CoreBodyTextView
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme

@Composable
fun HomeScreen(
    navController: NavHostController,
    drawerState: DrawerState,
) {
    Scaffold(
        topBar = {
            AppBar(title = R.string.home_title, drawerState = drawerState, actionIcon = {
            })
        },
    ) { paddingValues ->
        UICAppTheme {
            Surface {
                val categories =
                    listOf(
                        R.string.actions,
                        R.string.buttons_chips_tabs,
                        R.string.input,
                        R.string.topAppBar,
                        R.string.pages,
                        R.string.contentful_banners,
                        R.string.circular_loading_indicator,
                        R.string.webview_title,
                        R.string.empty_states,
                        R.string.mapSnapshot,
                        R.string.bottom_components,
                        R.string.feedback,
                        R.string.date_selector,
                        R.string.time_slot_selector,
                    )
                CoreBackground {
                    LazyColumn(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                    ) {
                        itemsIndexed(categories) { index, category ->
                            ListItem(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            when (category) {
                                                R.string.buttons_chips_tabs ->
                                                    navController.navigate(MainNavOption.ButtonsChipsTabsScreen.name)

                                                R.string.topAppBar ->
                                                    navController.navigate(MainNavOption.HeadersScreen.name)

                                                R.string.input ->
                                                    navController.navigate(MainNavOption.InputCategoryScreen.name)

                                                R.string.pages ->
                                                    navController.navigate(MainNavOption.PagesCategoryScreen.name)

                                                R.string.actions ->
                                                    navController.navigate(MainNavOption.ActionsScreen.name)

                                                R.string.contentful_banners ->
                                                    navController.navigate(MainNavOption.ContentfulBannersScreen.name)

                                                R.string.circular_loading_indicator ->
                                                    navController.navigate(MainNavOption.LoadingIndicator.name)

                                                R.string.webview_title ->
                                                    navController.navigate(MainNavOption.WebView.name)

                                                R.string.empty_states ->
                                                    navController.navigate(MainNavOption.EmptyStates.name)

                                                R.string.mapSnapshot ->
                                                    navController.navigate(MainNavOption.SnapShotMap.name)

                                                R.string.bottom_components ->
                                                    navController.navigate(MainNavOption.BottomComponents.name)

                                                R.string.feedback ->
                                                    navController.navigate(MainNavOption.FeedbackScreen.name)

                                                R.string.date_selector ->
                                                    navController.navigate(MainNavOption.DateSelectorHorizontalScreen.name)

                                                R.string.time_slot_selector ->
                                                    navController.navigate(MainNavOption.TimeSlotSelectorScreen.name)
                                            }
                                        },
                                headlineContent = {
                                    CoreBodyTextView(
                                        text =
                                            stringResource(
                                                id = category,
                                            ),
                                        isBold = true,
                                        textAlign = TextAlign.Start,
                                    )
                                },
                                shadowElevation = UICSpacing.spacing.spacing050,
                                colors =
                                    ListItemColors(
                                        containerColor = UICTheme.colorScheme.structural.bgPrimary,
                                        headlineColor = UICTheme.colorScheme.structural.bgPrimary,
                                        leadingIconColor = UICTheme.colorScheme.structural.bgPrimary,
                                        overlineColor = UICTheme.colorScheme.structural.bgPrimary,
                                        supportingTextColor = UICTheme.colorScheme.structural.bgPrimary,
                                        trailingIconColor = UICTheme.colorScheme.structural.bgPrimary,
                                        disabledHeadlineColor = UICTheme.colorScheme.structural.bgPrimary,
                                        disabledLeadingIconColor = UICTheme.colorScheme.structural.bgPrimary,
                                        disabledTrailingIconColor = UICTheme.colorScheme.structural.bgPrimary,
                                    ),
                            )
                            // Add divider after each item except the last one
                            if (index < categories.size - 1) {
                                HorizontalDivider(
                                    color = UICTheme.colorScheme.structural.outlineLight,
                                    thickness = 1.dp,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
