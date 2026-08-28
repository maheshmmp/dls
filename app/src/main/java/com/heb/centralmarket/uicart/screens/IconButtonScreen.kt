/*
 * Created by Mahesh Mathew Paul on 11/03/25, 11:41 am
 * mahesh.paul@ust.com
 * Last modified 11/03/25, 11:41 am
 * Copyright (c) 2025.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.heb.centralmarket.uicart.component.AppBarTheme
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.buttons.CoreIconButton
import com.heb.centralmarket.uicart.component.buttons.IconButtonSize
import com.heb.centralmarket.uicart.icons.accountAvatar
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

@Composable
fun IconButtonScreen(
    navController: NavHostController,
    drawerState: DrawerState,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = R.string.icon_buttons,
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
                modifier =
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(it)
                        .padding(horizontal = UICSpacing.spacing.spacing050),
                verticalArrangement = Arrangement.spacedBy(UICSpacing.spacing.spacing025),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = stringResource(R.string.enabled),
                    modifier = Modifier.padding(top = UICSpacing.spacing.spacing025),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier =
                        Modifier
                            .padding(all = UICSpacing.spacing.spacing050),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier =
                            Modifier
                                .padding(all = UICSpacing.spacing.spacing050),
                    ) {
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.SMALL,
                            showBadge = true,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.MEDIUM,
                            showBadge = true,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.LARGE,
                            showBadge = true,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.EXTRA_LARGE,
                            showBadge = true,
                            iconVector = accountAvatar(),
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier =
                            Modifier
                                .padding(all = UICSpacing.spacing.spacing050),
                    ) {
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.SMALL,
                            showBadge = false,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.MEDIUM,
                            showBadge = false,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.LARGE,
                            showBadge = false,
                            iconVector = accountAvatar(),
                        )

                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.EXTRA_LARGE,
                            showBadge = false,
                            iconVector = accountAvatar(),
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier =
                            Modifier
                                .background(color = UICTheme.colorScheme.brand.primary.core)
                                .padding(all = UICSpacing.spacing.spacing050),
                    ) {
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.SMALL,
                            theme = AppBarTheme.PRIMARY,
                            showBadge = true,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.MEDIUM,
                            theme = AppBarTheme.PRIMARY,
                            showBadge = true,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.LARGE,
                            theme = AppBarTheme.PRIMARY,
                            showBadge = true,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.EXTRA_LARGE,
                            theme = AppBarTheme.PRIMARY,
                            showBadge = true,
                            iconVector = accountAvatar(),
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier =
                            Modifier
                                .background(color = UICTheme.colorScheme.brand.primary.core)
                                .padding(all = UICSpacing.spacing.spacing050),
                    ) {
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.SMALL,
                            theme = AppBarTheme.PRIMARY,
                            showBadge = false,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.MEDIUM,
                            theme = AppBarTheme.PRIMARY,
                            showBadge = false,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.LARGE,
                            theme = AppBarTheme.PRIMARY,
                            showBadge = false,
                            iconVector = accountAvatar(),
                        )

                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.EXTRA_LARGE,
                            theme = AppBarTheme.PRIMARY,
                            showBadge = false,
                            iconVector = accountAvatar(),
                        )
                    }
                }

                Text(
                    text = stringResource(R.string.disabled),
                    modifier = Modifier.padding(top = UICSpacing.spacing.spacing025),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier =
                        Modifier
                            .padding(all = UICSpacing.spacing.spacing050),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier =
                            Modifier
                                .padding(all = UICSpacing.spacing.spacing050),
                    ) {
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.SMALL,
                            enabled = false,
                            showBadge = true,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "9",
                            enabled = false,
                            showBadge = true,
                            buttonSize = IconButtonSize.MEDIUM,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "10",
                            enabled = false,
                            showBadge = true,
                            buttonSize = IconButtonSize.LARGE,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "12",
                            enabled = false,
                            showBadge = true,
                            buttonSize = IconButtonSize.EXTRA_LARGE,
                            iconVector = accountAvatar(),
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier =
                            Modifier
                                .padding(all = UICSpacing.spacing.spacing050),
                    ) {
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.SMALL,
                            enabled = false,
                            showBadge = false,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "9",
                            enabled = false,
                            showBadge = false,
                            buttonSize = IconButtonSize.MEDIUM,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "10",
                            enabled = false,
                            showBadge = false,
                            buttonSize = IconButtonSize.LARGE,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "12",
                            enabled = false,
                            showBadge = false,
                            buttonSize = IconButtonSize.EXTRA_LARGE,
                            iconVector = accountAvatar(),
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier =
                            Modifier
                                .background(color = UICTheme.colorScheme.brand.primary.core)
                                .padding(all = UICSpacing.spacing.spacing050),
                    ) {
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.SMALL,
                            theme = AppBarTheme.PRIMARY,
                            enabled = false,
                            showBadge = true,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.MEDIUM,
                            theme = AppBarTheme.PRIMARY,
                            enabled = false,
                            showBadge = true,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.LARGE,
                            theme = AppBarTheme.PRIMARY,
                            enabled = false,
                            showBadge = true,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.EXTRA_LARGE,
                            theme = AppBarTheme.PRIMARY,
                            enabled = false,
                            showBadge = true,
                            iconVector = accountAvatar(),
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier =
                            Modifier
                                .background(color = UICTheme.colorScheme.brand.primary.core)
                                .padding(all = UICSpacing.spacing.spacing050),
                    ) {
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.SMALL,
                            theme = AppBarTheme.PRIMARY,
                            enabled = false,
                            showBadge = false,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.MEDIUM,
                            theme = AppBarTheme.PRIMARY,
                            enabled = false,
                            showBadge = false,
                            iconVector = accountAvatar(),
                        )
                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.LARGE,
                            theme = AppBarTheme.PRIMARY,
                            enabled = false,
                            showBadge = false,
                            iconVector = accountAvatar(),
                        )

                        CoreIconButton(
                            onClick = { /* Handle click */ },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.EXTRA_LARGE,
                            theme = AppBarTheme.PRIMARY,
                            enabled = false,
                            showBadge = false,
                            iconVector = accountAvatar(),
                        )
                    }
                }
            }
        }
    }
}
