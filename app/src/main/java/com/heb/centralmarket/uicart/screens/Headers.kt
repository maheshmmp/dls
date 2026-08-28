/*
 * Created by Mahesh Mathew Paul on 12/03/25, 11:19 am
 * mahesh.paul@ust.com
 * Last modified 12/03/25, 11:19 am
 * Copyright (c) 2025.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.AppBarTheme
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.SimpleTopAppBar
import com.heb.centralmarket.uicart.component.buttons.CoreIconButton
import com.heb.centralmarket.uicart.component.buttons.IconButtonSize
import com.heb.centralmarket.uicart.icons.accountAvatar
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.icons.settingsGear
import com.heb.centralmarket.uicart.icons.trashIcon
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

@Composable
fun TopAppBarSection(
    navController: NavController,
    drawerState: DrawerState,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = R.string.topAppBar,
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
    ) { paddingValues ->
        CoreBackground {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(horizontal = UICSpacing.spacing.spacing050),
                verticalArrangement = Arrangement.Top,
            ) {
                val context = LocalContext.current

                Text(
                    text = stringResource(R.string.primary_topappbar),
                    Modifier
                        .padding(vertical = UICSpacing.spacing.spacing075)
                        .align(Alignment.Start),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )
                SimpleTopAppBar(
                    headerText = stringResource(R.string.header),
                    theme = AppBarTheme.PRIMARY,
                    navigationIcon = {
                        CoreIconButton(
                            onClick = { Toast.makeText(context, "Navigation button clicked!", Toast.LENGTH_SHORT).show() },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.MEDIUM,
                            theme = AppBarTheme.PRIMARY,
                            showBadge = true,
                            iconVector = accountAvatar(),
                            contentDescription = stringResource(R.string.back),
                        )
                    },
                    actionIcon = {
                        CoreIconButton(
                            onClick = { Toast.makeText(context, "Action button clicked!", Toast.LENGTH_SHORT).show() },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.MEDIUM,
                            theme = AppBarTheme.PRIMARY,
                            showBadge = true,
                            iconVector = trashIcon(),
                            contentDescription = stringResource(R.string.back),
                        )
                    },
                )
                Text(
                    text = stringResource(R.string.secondary_topappbar),
                    Modifier.padding(vertical = UICSpacing.spacing.spacing075),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )
                SimpleTopAppBar(
                    headerText = stringResource(R.string.back),
                    theme = AppBarTheme.SECONDARY,
                    navigationIcon = {
                        CoreIconButton(
                            onClick = { Toast.makeText(context, "Navigation button clicked!", Toast.LENGTH_SHORT).show() },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.MEDIUM,
                            theme = AppBarTheme.SECONDARY,
                            showBadge = true,
                            iconVector = accountAvatar(),
                            contentDescription = stringResource(R.string.back),
                        )
                    },
                    actionIcon = {
                        CoreIconButton(
                            onClick = { Toast.makeText(context, "Action button clicked!", Toast.LENGTH_SHORT).show() },
                            badgeCount = "2",
                            buttonSize = IconButtonSize.MEDIUM,
                            theme = AppBarTheme.SECONDARY,
                            showBadge = true,
                            iconVector = settingsGear(),
                            contentDescription = stringResource(R.string.settings),
                        )
                    },
                )
            }
        }
    }
}
