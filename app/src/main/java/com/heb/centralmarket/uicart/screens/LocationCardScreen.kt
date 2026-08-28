/*
 * *
 *  * Created by 160857 on 9/11/25, 6:29 PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  * Last modified 9/11/25, 5:17 PM
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.listitems.LocationCard
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

@Composable
fun LocationCardScreen(
    navController: NavController,
    drawerState: DrawerState,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = R.string.location_card,
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
                    text = stringResource(id = R.string.location_card),
                    modifier =
                        Modifier
                            .padding(
                                horizontal = UICSpacing.spacing.spacing050,
                                vertical = UICSpacing.spacing.spacing050,
                            ).align(Alignment.Start),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )

                LocationCard(
                    locationName = "Austin North Lamar",
                    addressLine1 = "4477 S Lamar Blvd",
                    addressLine2 = "Austin, TX 78745",
                    actionButtonText = stringResource(id = R.string.change_store),
                    onActionButtonClick = {},
                )

                LocationCard(
                    locationName = "Home Sweet Home - Cherthala, Alleppey, Kerala, India",
                    addressLine1 = "1234 S Lamar Blvd - Alleppey, Kerala, India",
                    addressLine2 = "Apt 1234",
                    addressLine3 = "Austin, TX 78701",
                    actionButtonText = stringResource(id = R.string.change_address),
                    onActionButtonClick = {},
                )
            }
        }
    }
}