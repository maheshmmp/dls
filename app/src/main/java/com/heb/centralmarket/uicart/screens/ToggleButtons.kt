/*
 * Created by Mahesh Mathew Paul on 12/03/25, 11:19 am
 * mahesh.paul@ust.com
 * Last modified 12/03/25, 11:19 am
 * Copyright (c) 2025.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.buttons.UICToggleButton
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

@Composable
fun ToggleButtons(
    navController: NavController,
    drawerState: DrawerState,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = R.string.toggle_button,
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
                        .padding(it)
                        .padding(horizontal = UICSpacing.spacing.spacing050),
                verticalArrangement = Arrangement.spacedBy(UICSpacing.spacing.spacing025),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.toggle_button_on),
                    modifier = Modifier.padding(top = UICSpacing.spacing.spacing075),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )

                var isToggled by remember { mutableStateOf(true) }
                UICToggleButton(
                    toggleCheck = isToggled,
                    toggleDescription = stringResource(R.string.toggle_button_on),
                    onToggleChange = { newValue ->
                        isToggled = newValue
                    },
                    modifier = Modifier,
                )

                Text(
                    text = stringResource(R.string.toggle_button_off),
                    modifier = Modifier.padding(top = UICSpacing.spacing.spacing075),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )

                var isToggled2 by remember { mutableStateOf(false) }
                UICToggleButton(
                    toggleCheck = isToggled2,
                    toggleDescription = stringResource(R.string.toggle_button_off),
                    onToggleChange = { newValue ->
                        isToggled2 = newValue
                    },
                    modifier = Modifier,
                )
            }
        }
    }
}
