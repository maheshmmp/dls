/*
 * Created by Mahesh Mathew Paul on 11/03/25, 11:41 am
 * mahesh.paul@ust.com
 * Last modified 10/14/25, 04:25 pm
 * Copyright (c) 2025.
 * All rights reserved.
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.buttons.CoreButtonSize
import com.heb.centralmarket.uicart.component.buttons.UICPrimaryButton
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.icons.plusIcon
import com.heb.centralmarket.uicart.icons.trashIcon
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

@Composable
fun PrimaryButtonScreen(
    navController: NavHostController,
    drawerState: DrawerState,
) {
    var isFillWidthEnabled by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            AppBar(
                title = R.string.buttons_primary,
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
                    ChangerSwitchLayout(
                        displayText = stringResource(R.string.preview_full_width),
                        onCheckedChange = { enabled ->
                            isFillWidthEnabled = enabled
                        },
                    )
                    ThemeSwitcherAction()
                    DarkModeSwitcherAction()
                }
            )
        },
    ) { innerPadding ->
        CoreBackground {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(state = rememberScrollState())
                        .padding(paddingValues = innerPadding)
                        .padding(horizontal = UICSpacing.spacing.spacing050),
                verticalArrangement = Arrangement.spacedBy(UICSpacing.spacing.spacing050),
                horizontalAlignment = Alignment.CenterHorizontally, // aligned like TertiaryButtonScreen
            ) {
                PrimaryButtons(isFillMaxWidth = isFillWidthEnabled)
            }
        }
    }
}

@Composable
fun PrimaryButtons(isFillMaxWidth: Boolean) {
    // LARGE variant section
    Text(
        text = stringResource(R.string.large_variant),
        modifier = Modifier.padding(top = UICSpacing.spacing.spacing025),
        style = UICTypography.typography.caption.bold,
        color = UICTheme.colorScheme.txt.primary,
    )
    UICPrimaryButton(
        onClick = {},
        buttonText = stringResource(R.string.button).uppercase(),
        leadingIcon = { Icon(imageVector = trashIcon(), contentDescription = "Delete") },
        trailingIcon = { Icon(imageVector = plusIcon(), contentDescription = "Add") },
        buttonSize = CoreButtonSize.LARGE,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICPrimaryButton(
        onClick = {},
        buttonText = stringResource(R.string.button).uppercase(),
        leadingIcon = { Icon(imageVector = trashIcon(), contentDescription = "Delete") },
        buttonSize = CoreButtonSize.LARGE,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICPrimaryButton(
        onClick = {},
        buttonText = stringResource(R.string.button).uppercase(),
        trailingIcon = { Icon(imageVector = plusIcon(), contentDescription = "Add") },
        buttonSize = CoreButtonSize.LARGE,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICPrimaryButton(
        onClick = {},
        buttonText = stringResource(R.string.button).uppercase(),
        buttonSize = CoreButtonSize.LARGE,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICPrimaryButton(
        onClick = {},
        enabled = false,
        buttonText = stringResource(R.string.button).uppercase(),
        buttonSize = CoreButtonSize.LARGE,
        isButtonFullWidth = isFillMaxWidth,
    )

    // MEDIUM variant section
    Text(
        text = stringResource(R.string.medium_variant),
        modifier = Modifier.padding(top = UICSpacing.spacing.spacing075),
        style = UICTypography.typography.caption.bold,
        color = UICTheme.colorScheme.txt.primary,
    )
    UICPrimaryButton(
        onClick = {},
        buttonText = stringResource(R.string.button).uppercase(),
        leadingIcon = { Icon(imageVector = trashIcon(), contentDescription = "Delete") },
        trailingIcon = { Icon(imageVector = plusIcon(), contentDescription = "Add") },
        buttonSize = CoreButtonSize.MEDIUM,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICPrimaryButton(
        onClick = {},
        buttonText = stringResource(R.string.button).uppercase(),
        leadingIcon = { Icon(imageVector = trashIcon(), contentDescription = "Delete") },
        buttonSize = CoreButtonSize.MEDIUM,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICPrimaryButton(
        onClick = {},
        buttonText = stringResource(R.string.button).uppercase(),
        trailingIcon = { Icon(imageVector = plusIcon(), contentDescription = "Add") },
        buttonSize = CoreButtonSize.MEDIUM,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICPrimaryButton(
        onClick = {},
        buttonText = stringResource(R.string.button).uppercase(),
        buttonSize = CoreButtonSize.MEDIUM,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICPrimaryButton(
        onClick = {},
        enabled = false,
        buttonText = stringResource(R.string.button).uppercase(),
        buttonSize = CoreButtonSize.MEDIUM,
        isButtonFullWidth = isFillMaxWidth,
    )

    // SMALL variant section
    Text(
        text = stringResource(R.string.small_variant),
        modifier = Modifier.padding(top = UICSpacing.spacing.spacing075),
        style = UICTypography.typography.caption.bold,
        color = UICTheme.colorScheme.txt.primary,
    )
    UICPrimaryButton(
        onClick = {},
        buttonText = stringResource(R.string.button).uppercase(),
        leadingIcon = { Icon(imageVector = trashIcon(), contentDescription = "Delete") },
        trailingIcon = { Icon(imageVector = plusIcon(), contentDescription = "Add") },
        buttonSize = CoreButtonSize.SMALL,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICPrimaryButton(
        onClick = {},
        buttonText = stringResource(R.string.button).uppercase(),
        leadingIcon = { Icon(imageVector = trashIcon(), contentDescription = "Delete") },
        buttonSize = CoreButtonSize.SMALL,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICPrimaryButton(
        onClick = {},
        buttonText = stringResource(R.string.button).uppercase(),
        trailingIcon = { Icon(imageVector = plusIcon(), contentDescription = "Add") },
        buttonSize = CoreButtonSize.SMALL,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICPrimaryButton(
        onClick = {},
        buttonText = stringResource(R.string.button).uppercase(),
        buttonSize = CoreButtonSize.SMALL,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICPrimaryButton(
        onClick = {},
        enabled = false,
        buttonText = stringResource(R.string.button).uppercase(),
        buttonSize = CoreButtonSize.SMALL,
        isButtonFullWidth = isFillMaxWidth,
    )
}
