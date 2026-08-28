/*
 * Created by Mahesh Mathew Paul on 12/12/24, 12:21 pm
 * mahesh.paul@ust.com
 * Last modified 12/12/24, 12:18 pm
 * Copyright (c) 2024.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.BodyVariant
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.CoreBodyTextView
import com.heb.centralmarket.uicart.component.CoreHeadingTextView
import com.heb.centralmarket.uicart.component.HeadingVariant
import com.heb.centralmarket.uicart.previews.AllScreenPreview
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICSystemThemePreference
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICThemeSystem
import com.heb.centralmarket.uicart.themesystem.UICartThemeSystem
import com.heb.centralmarket.uicart.themesystem.next

@Composable
fun SettingsScreen(drawerState: DrawerState) {
    Scaffold(
        topBar = {
            AppBar(
                title = R.string.settings_title,
                drawerState = drawerState,
            )
        },
    ) {
        CoreBackground {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(it)
                        .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                ThemeSwitcher()

                DarkModeSwitcher()
            }
        }
    }
}

@Composable
fun ThemeSwitcherAction() {
    var expanded by remember { mutableStateOf(false) }
    val themeOptions =
        listOf(
            UICThemeSystem.CentralMarket,
            UICThemeSystem.JoeVs,
            UICThemeSystem.MiTienda,
        )
    val themeNames = listOf("Central Market", "Joe V's", "Mi Tienda")
    val currentTheme by UICartThemeSystem.themeUpdates.collectAsState()
    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(
                painter =
                    painterResource(
                        id =
                            when (currentTheme) {
                                UICThemeSystem.CentralMarket -> R.drawable.ic_cm_appbar
                                UICThemeSystem.JoeVs -> R.drawable.ic_jv_appbar
                                UICThemeSystem.MiTienda -> R.drawable.ic_mi_tienda_appbar
                            },
                    ),
                modifier = Modifier.padding(all = UICSpacing.spacing.spacing050),
                contentDescription = "Switch Theme",
                tint = UICTheme.colorScheme.txt.primary,
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            themeOptions.forEachIndexed { index, theme ->
                DropdownMenuItem(
                    text = { Text(themeNames[index]) },
                    onClick = {
                        UICartThemeSystem.setThemeSystem(theme)
                        expanded = false
                    },
                )
            }
        }
    }
}

@Composable
fun DarkModeSwitcherAction() {
    val currentDarkMode by UICartThemeSystem.darkModeUpdates.collectAsState()
    val iconRes =
        when (currentDarkMode) {
            UICSystemThemePreference.Dark -> R.drawable.ic_light_mode
            UICSystemThemePreference.Light,
            UICSystemThemePreference.System,
                -> R.drawable.ic_dark_mode
        }

    IconButton(onClick = {
        UICartThemeSystem.setDarkMode(currentDarkMode.next())
    }) {
        Icon(
            painter =
                painterResource(
                    id = iconRes,
                ),
            contentDescription = "Cycle dark mode: ${currentDarkMode.name}",
            tint = UICTheme.colorScheme.txt.primary,
        )
    }
}

@Composable
fun ThemeSwitcher() {
    val currentTheme by UICartThemeSystem.themeUpdates.collectAsState()
    val isCentralMarket = currentTheme == UICThemeSystem.CentralMarket

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors =
            CardDefaults.cardColors(
                containerColor = UICTheme.colorScheme.structural.bgSecondary,
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CoreHeadingTextView(
                text = "Theme Selection",
                headingLevel = HeadingVariant.HEADING_4,
                color = UICTheme.colorScheme.txt.primary,
            )

            Spacer(modifier = Modifier.height(UICSpacing.spacing.spacing050))

            CoreBodyTextView(
                text = "Current: ${if (isCentralMarket) "Central Market" else "Joe V's"}",
                bodyVariant = BodyVariant.BODY_2,
                color = UICTheme.colorScheme.txt.secondary,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Switch(
                    checked = isCentralMarket,
                    onCheckedChange = { checked ->
                        val newTheme =
                            if (checked) UICThemeSystem.CentralMarket else UICThemeSystem.JoeVs
                        UICartThemeSystem.setThemeSystem(newTheme)
                    },
                    thumbContent = {
                        if (isCentralMarket) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Central Market Selected",
                                modifier = Modifier.size(SwitchDefaults.IconSize),
                            )
                        }
                    },
                    colors =
                        SwitchDefaults.colors(
                            checkedThumbColor = UICTheme.colorScheme.brand.primary.focus,
                            uncheckedThumbColor = UICTheme.colorScheme.brand.primary.focus,
                            checkedTrackColor = UICTheme.colorScheme.brand.interactive.core,
                            uncheckedTrackColor = UICTheme.colorScheme.brand.interactive.core,
                        ),
                )
            }
        }
    }
}

@Composable
fun DarkModeSwitcher() {
    val currentDarkMode by UICartThemeSystem.darkModeUpdates.collectAsState()
    val options =
        listOf(
            UICSystemThemePreference.System,
            UICSystemThemePreference.Dark,
            UICSystemThemePreference.Light,
        )

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors =
            CardDefaults.cardColors(
                containerColor = UICTheme.colorScheme.structural.bgSecondary,
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CoreHeadingTextView(
                text = "Dark Mode",
                headingLevel = HeadingVariant.HEADING_4,
                color = UICTheme.colorScheme.txt.primary,
            )

            Spacer(modifier = Modifier.height(UICSpacing.spacing.spacing050))

            CoreBodyTextView(
                text =
                    when (currentDarkMode) {
                        UICSystemThemePreference.Dark -> "Dark mode enabled"
                        UICSystemThemePreference.Light -> "Light mode enabled"
                        UICSystemThemePreference.System -> "Following system setting"
                    },
                bodyVariant = BodyVariant.BODY_2,
                color = UICTheme.colorScheme.txt.secondary,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                options.forEach { option ->
                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clickable { UICartThemeSystem.setDarkMode(option) },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        RadioButton(
                            selected = currentDarkMode == option,
                            onClick = { UICartThemeSystem.setDarkMode(option) },
                        )

                        Text(
                            text =
                                when (option) {
                                    UICSystemThemePreference.System -> "System"
                                    UICSystemThemePreference.Dark -> "Dark"
                                    UICSystemThemePreference.Light -> "Light"
                                },
                            color = UICTheme.colorScheme.txt.primary,
                        )
                    }
                }
            }
        }
    }
}

@AllScreenPreview
@Composable
fun SettingsScreenPreview() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    UICAppTheme {
        SettingsScreen(drawerState)
    }
}
