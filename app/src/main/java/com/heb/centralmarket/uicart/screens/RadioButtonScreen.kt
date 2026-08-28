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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.heb.centralmarket.uicart.component.buttons.UICRadioButton
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

@Composable
fun RadioButtonScreen(
    navController: NavController,
    drawerState: DrawerState,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = R.string.radio_button,
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
                        .padding(horizontal = UICSpacing.spacing.spacing100),
                verticalArrangement = Arrangement.spacedBy(UICSpacing.spacing.spacing100),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.enabled),
                    modifier = Modifier.padding(top = UICSpacing.spacing.spacing025),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )

                var isSelected by remember { mutableStateOf(true) }
                var isNotSelected by remember { mutableStateOf(false) }
                Row {
                    UICRadioButton(
                        selected = isSelected,
                        onClick = { isSelected = !isSelected },
                        enabled = true, contentDescription = stringResource(R.string.radio_button))
                    Spacer(Modifier.width(UICSpacing.spacing.spacing075))
                    UICRadioButton(selected = isNotSelected,
                        onClick = { isNotSelected = !isNotSelected },
                        enabled = true, contentDescription = stringResource(R.string.radio_button))
                }

                var isPressed by remember { mutableStateOf(true) }
                var isNotPressed by remember { mutableStateOf(false) }
                Text(
                    text = stringResource(R.string.pressed),
                    modifier = Modifier.padding(top = UICSpacing.spacing.spacing025),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )

                Row {
                    UICRadioButton(selected = isPressed,
                        onClick = { isPressed = !isPressed }, contentDescription = stringResource(R.string.radio_button))
                    Spacer(Modifier.width(UICSpacing.spacing.spacing075))
                    UICRadioButton(selected = isNotPressed,
                        onClick = { isNotPressed = !isNotPressed },enabled = true, contentDescription = stringResource(R.string.radio_button))
                }

                var isFocused by remember { mutableStateOf(true) }
                var isNotFocused by remember { mutableStateOf(false) }
                Text(
                    text = stringResource(R.string.focused),
                    modifier = Modifier.padding(top = UICSpacing.spacing.spacing025),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )

                Row {
                    UICRadioButton(selected = isFocused,
                        onClick = { isFocused = !isFocused }, contentDescription = stringResource(R.string.radio_button))
                    Spacer(Modifier.width(UICSpacing.spacing.spacing075))
                    UICRadioButton(selected = isNotFocused,
                        onClick = { isNotFocused = !isNotFocused }, enabled = true, contentDescription = stringResource(R.string.radio_button))
                }

                var isDisabled by remember { mutableStateOf(true) }
                var isNotDisabled by remember { mutableStateOf(false) }
                Text(
                    text = stringResource(R.string.disabled),
                    modifier = Modifier.padding(top = UICSpacing.spacing.spacing025),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )

                Row {
                    UICRadioButton(selected = isDisabled,
                        onClick = { }, enabled = false, contentDescription = stringResource(R.string.radio_button))
                    Spacer(Modifier.width(UICSpacing.spacing.spacing075))
                    UICRadioButton(selected = isNotDisabled,
                        onClick = { },enabled = false, contentDescription = stringResource(R.string.radio_button))
                }

                Text(
                    text = stringResource(R.string.single_select),
                    modifier = Modifier.padding(top = UICSpacing.spacing.spacing025),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )

                var isSingleSelected by remember { mutableStateOf(true) }
                Row {
                    UICRadioButton(
                        selected = isSingleSelected,
                        onClick = { isSingleSelected = !isSingleSelected },
                        enabled = true, contentDescription = stringResource(R.string.radio_button))
                    Spacer(Modifier.width(UICSpacing.spacing.spacing075))
                    UICRadioButton(selected = !isSingleSelected,
                        onClick = { isSingleSelected = !isSingleSelected },
                        enabled = true, contentDescription = stringResource(R.string.radio_button))
                }

            }
        }
    }
}
