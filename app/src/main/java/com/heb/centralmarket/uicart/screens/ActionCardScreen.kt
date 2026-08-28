/*
 *
 *  Created by Mahesh Paul on 1/15/26, 11:30 AM
 *  Copyright (c) 2026 . All rights reserved.
 *  Last modified 1/15/26, 9:45 AM
 *
 */

package com.heb.centralmarket.uicart.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
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
import com.heb.centralmarket.uicart.component.listitems.fullfillment.ActionCard
import com.heb.centralmarket.uicart.icons.check
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.icons.chevronRight
import com.heb.centralmarket.uicart.icons.clock
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

@Composable
fun ActionCardScreen(
    navController: NavController,
    drawerState: DrawerState,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = R.string.action_card_title,
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
                },
            )
        },
    ) { paddingValues ->
        CoreBackground {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(paddingValues = paddingValues),
                verticalArrangement = Arrangement.spacedBy(space = UICSpacing.spacing.spacing025),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = "Action card",
                    modifier =
                        Modifier
                            .padding(
                                top = UICSpacing.spacing.spacing125,
                                start = UICSpacing.spacing.spacing050
                            )
                            .fillMaxWidth(),
                    style = UICTypography.typography.heading.h5,
                    color = UICTheme.colorScheme.txt.primary,
                )

                val options = listOf("Pickup", "Delivery")
                var selectedOption by remember { mutableStateOf(options[0]) }

                if (selectedOption == "Pickup") {
                    ActionCard(
                        title = "Select a pickup time",
                        description = "Available today as soon as 11:30am",
                        modifier = Modifier.padding(all = UICSpacing.spacing.spacing100),
                        leftIcon = clock(),
                        leftIconContentDescription = stringResource(id = R.string.clock_icon_content_description),
                        leftIconTint = UICTheme.colorScheme.txt.primary,
                        rightIcon = chevronRight(),
                        rightIconContentDescription = stringResource(id = com.heb.centralmarket.uicart.components.R.string.chevron_right_icon_content_description),
                        rightIconTint = UICTheme.colorScheme.brand.interactive.core,
                        onActionCardClick = {}
                    )

                    ActionCard(
                        title = "Pickup today, 11:30-12:00pm",
                        description = "Reservation expires in 59 min",
                        modifier = Modifier.padding(all = UICSpacing.spacing.spacing100),
                        leftIcon = check(),
                        leftIconContentDescription = stringResource(id = R.string.check_icon_content_description),
                        leftIconTint = UICTheme.colorScheme.brand.interactive.core,
                        rightIcon = chevronRight(),
                        rightIconContentDescription = stringResource(id = com.heb.centralmarket.uicart.components.R.string.chevron_right_icon_content_description),
                        rightIconTint = UICTheme.colorScheme.brand.interactive.core,
                        onActionCardClick = {}
                    )
                } else if (selectedOption == "Delivery") {
                    ActionCard(
                        title = "Select a delivery time",
                        description = "Available today as soon as 11:30am",
                        modifier = Modifier.padding(all = UICSpacing.spacing.spacing100),
                        leftIcon = clock(),
                        leftIconContentDescription = stringResource(id = R.string.clock_icon_content_description),
                        leftIconTint = UICTheme.colorScheme.txt.primary,
                        rightIcon = chevronRight(),
                        rightIconContentDescription = stringResource(id = com.heb.centralmarket.uicart.components.R.string.chevron_right_icon_content_description),
                        rightIconTint = UICTheme.colorScheme.brand.interactive.core,
                        onActionCardClick = {}
                    )

                    ActionCard(
                        title = "Delivery today, 11:30-12:00pm",
                        description = "Reservation expires in 59 min",
                        modifier = Modifier.padding(all = UICSpacing.spacing.spacing100),
                        leftIcon = check(),
                        leftIconContentDescription = stringResource(id = R.string.check_icon_content_description),
                        leftIconTint = UICTheme.colorScheme.brand.interactive.core,
                        rightIcon = chevronRight(),
                        rightIconContentDescription = stringResource(id = com.heb.centralmarket.uicart.components.R.string.chevron_right_icon_content_description),
                        rightIconTint = UICTheme.colorScheme.brand.interactive.core,
                        onActionCardClick = {}
                    )
                }
                Text(
                    text = "Variants :",
                    modifier =
                        Modifier
                            .padding(
                                horizontal = UICSpacing.spacing.spacing050,
                                vertical = UICSpacing.spacing.spacing050,
                            )
                            .align(Alignment.Start),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )

                FlowRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    options.forEach { option ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = UICSpacing.spacing.spacing025)
                        ) {
                            RadioButton(
                                selected = (option == selectedOption),
                                onClick = { selectedOption = option }
                            )
                            Text(
                                text = option,
                                style = UICTypography.typography.caption.bold,
                                color = UICTheme.colorScheme.txt.primary,
                                modifier = Modifier.padding(end = UICSpacing.spacing.spacing025)
                            )
                        }
                    }
                }
            }
        }
    }
}