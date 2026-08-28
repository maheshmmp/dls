/*
 * *
 *  * Created by Mahesh Mathew Paul on 10/10/25, 3:47 PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  * Last modified 10/10/25, 2:34 PM
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
import com.heb.centralmarket.uicart.component.buttons.UICTertiaryButton
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.icons.plusIcon
import com.heb.centralmarket.uicart.icons.trashIcon
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

@Composable
fun TertiaryButtonScreen(
    navController: NavHostController,
    drawerState: DrawerState,
) {
    var isFillWidthEnabled by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            AppBar(
                title = R.string.buttons_tertiary,
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
                        }
                    )
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
                verticalArrangement = Arrangement.spacedBy(UICSpacing.spacing.spacing050),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TertiaryButtons(isFillMaxWidth = isFillWidthEnabled)
            }
        }
    }
}

@Composable
fun TertiaryButtons(isFillMaxWidth: Boolean) {
    Text(
        text = stringResource(R.string.large_variant),
        modifier = Modifier.padding(top = UICSpacing.spacing.spacing025),
        style = UICTypography.typography.caption.bold,
        color = UICTheme.colorScheme.txt.primary,
    )
    UICTertiaryButton(
        onClick = {},
        buttonText = stringResource(R.string.button).uppercase(),
        leadingIcon = {
            Icon(
                imageVector = trashIcon(),
                contentDescription = "Delete",
                tint = UICTheme.colorScheme.brand.interactive.core
            )
        },
        trailingIcon = {
            Icon(
                imageVector = plusIcon(),
                contentDescription = "Add",
                tint = UICTheme.colorScheme.brand.interactive.core
            )
        },
        buttonSize = CoreButtonSize.LARGE,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICTertiaryButton(
        onClick = {},
        enabled = true,
        buttonText = stringResource(R.string.button).uppercase(),
        leadingIcon = {
            Icon(
                imageVector = trashIcon(),
                contentDescription = "Delete",
                tint = UICTheme.colorScheme.brand.interactive.core
            )
        },
        buttonSize = CoreButtonSize.LARGE,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICTertiaryButton(
        onClick = {},
        enabled = true,
        buttonText = stringResource(R.string.button).uppercase(),
        trailingIcon = {
            Icon(
                imageVector = plusIcon(),
                contentDescription = "Delete",
                tint = UICTheme.colorScheme.brand.interactive.core
            )
        },
        buttonSize = CoreButtonSize.LARGE,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICTertiaryButton(
        onClick = {},
        enabled = true,
        buttonSize = CoreButtonSize.LARGE,
        isButtonFullWidth = isFillMaxWidth,
        buttonText = stringResource(R.string.button).uppercase(),
    )
    UICTertiaryButton(
        onClick = {},
        enabled = false,
        buttonText = stringResource(R.string.button).uppercase(),
        buttonSize = CoreButtonSize.LARGE,
        isButtonFullWidth = isFillMaxWidth,
    )

    Text(
        text = stringResource(R.string.medium_variant),
        Modifier.padding(top = UICSpacing.spacing.spacing075),
        style = UICTypography.typography.caption.bold,
        color = UICTheme.colorScheme.txt.primary,
    )
    UICTertiaryButton(
        onClick = {},
        enabled = true,
        buttonText = stringResource(R.string.button).uppercase(),
        leadingIcon = {
            Icon(
                imageVector = trashIcon(),
                contentDescription = "Delete",
            )
        },
        trailingIcon = {
            Icon(
                imageVector = plusIcon(),
                contentDescription = "Add",
            )
        },
        buttonSize = CoreButtonSize.MEDIUM,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICTertiaryButton(
        onClick = {},
        enabled = true,
        buttonText = stringResource(R.string.button).uppercase(),
        leadingIcon = {
            Icon(
                imageVector = trashIcon(),
                contentDescription = "Delete",
            )
        },
        buttonSize = CoreButtonSize.MEDIUM,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICTertiaryButton(
        onClick = {},
        enabled = true,
        buttonText = stringResource(R.string.button).uppercase(),
        trailingIcon = {
            Icon(
                imageVector = plusIcon(),
                contentDescription = "Delete",
            )
        },
        buttonSize = CoreButtonSize.MEDIUM,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICTertiaryButton(
        onClick = {},
        enabled = true,
        buttonText = stringResource(R.string.button).uppercase(),
        buttonSize = CoreButtonSize.MEDIUM,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICTertiaryButton(
        onClick = {},
        enabled = false,
        buttonText = stringResource(R.string.button).uppercase(),
        buttonSize = CoreButtonSize.MEDIUM,
        isButtonFullWidth = isFillMaxWidth,
    )


    Text(
        text = stringResource(R.string.small_variant),
        Modifier.padding(top = UICSpacing.spacing.spacing075),
        style = UICTypography.typography.caption.bold,
        color = UICTheme.colorScheme.txt.primary,
    )
    UICTertiaryButton(
        onClick = {},
        enabled = true,
        buttonText = stringResource(R.string.button).uppercase(),
        leadingIcon = {
            Icon(
                imageVector = trashIcon(),
                contentDescription = "Delete",
            )
        },
        trailingIcon = {
            Icon(
                imageVector = plusIcon(),
                contentDescription = "Add",
            )
        },
        buttonSize = CoreButtonSize.SMALL,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICTertiaryButton(
        onClick = {},
        enabled = true,
        buttonText = stringResource(R.string.button).uppercase(),
        leadingIcon = {
            Icon(
                imageVector = trashIcon(),
                contentDescription = "Delete",
            )
        },
        buttonSize = CoreButtonSize.SMALL,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICTertiaryButton(
        onClick = {},
        enabled = true,
        buttonText = stringResource(R.string.button).uppercase(),
        trailingIcon = {
            Icon(
                imageVector = plusIcon(),
                contentDescription = "Delete",
            )
        },
        buttonSize = CoreButtonSize.SMALL,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICTertiaryButton(
        onClick = {},
        enabled = true,
        buttonText = stringResource(R.string.button).uppercase(),
        buttonSize = CoreButtonSize.SMALL,
        isButtonFullWidth = isFillMaxWidth,
    )
    UICTertiaryButton(
        onClick = {},
        enabled = false,
        buttonText = stringResource(R.string.button).uppercase(),
        buttonSize = CoreButtonSize.SMALL,
        isButtonFullWidth = isFillMaxWidth,
    )
}
