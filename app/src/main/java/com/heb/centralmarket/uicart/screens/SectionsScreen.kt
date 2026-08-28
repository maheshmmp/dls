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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.buttons.ServiceItemData
import com.heb.centralmarket.uicart.component.buttons.ServicesGrid
import com.heb.centralmarket.uicart.component.listitems.PreferenceRowItem
import com.heb.centralmarket.uicart.component.listitems.PreferenceRowItemShimmer
import com.heb.centralmarket.uicart.component.listitems.Section
import com.heb.centralmarket.uicart.component.listitems.SectionLevelFAQ
import com.heb.centralmarket.uicart.icons.accountAvatar
import com.heb.centralmarket.uicart.icons.chef
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.icons.chevronRight
import com.heb.centralmarket.uicart.icons.settingsGear
import com.heb.centralmarket.uicart.icons.shop
import com.heb.centralmarket.uicart.icons.truck
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

@Composable
fun SectionsScreen(
    navController: NavController,
    drawerState: DrawerState,
) {
    val serviceItemsEnabled =
        listOf(
            ServiceItemData(
                onClick = { /* Handle click */ },
                iconVector = chef(),
                text = "Account",
                enabled = true,
            ),
            ServiceItemData(
                onClick = { /* Handle click */ },
                iconVector = truck(),
                text = "Settings",
                enabled = true,
            ),
            ServiceItemData(
                onClick = { /* Handle click */ },
                iconVector = shop(),
                text = "Favorites",
                enabled = true,
            ),
            ServiceItemData(
                onClick = { /* Handle click */ },
                iconVector = shop(),
                text = "Order Ahead",
                enabled = true,
            ),
        )
    val serviceItemsDisabled =
        listOf(
            ServiceItemData(
                onClick = { /* Handle click */ },
                iconVector = chef(),
                text = "Account",
                enabled = false,
            ),
            ServiceItemData(
                onClick = { /* Handle click */ },
                iconVector = truck(),
                text = "Settings",
                enabled = false,
            ),
            ServiceItemData(
                onClick = { /* Handle click */ },
                iconVector = shop(),
                text = "Cooking School and Mandatory things",
                enabled = false,
            ),
        )
    Scaffold(
        topBar = {
            AppBar(
                title = R.string.sections,
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
    ) {
        CoreBackground {
            Column(
                modifier =
                    Modifier
                        .verticalScroll(rememberScrollState())
                        .wrapContentSize()
                        .padding(it)
                        .padding(horizontal = UICSpacing.spacing.spacing050),
                verticalArrangement = Arrangement.spacedBy(UICSpacing.spacing.spacing025),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.service_item),
                    modifier =
                        Modifier
                            .padding(
                                top = UICSpacing.spacing.spacing100,
                            ).align(Alignment.Start),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )
                Text(
                    text = stringResource(R.string.enabled),
                    modifier =
                        Modifier.padding(
                            vertical = UICSpacing.spacing.spacing100,
                        ),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )
                ServicesGrid(serviceItems = serviceItemsEnabled)
                Text(
                    text = stringResource(R.string.disabled),
                    modifier =
                        Modifier.padding(
                            vertical = UICSpacing.spacing.spacing100,
                        ),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )
                ServicesGrid(serviceItems = serviceItemsDisabled)
                Text(
                    text = stringResource(R.string.section_lvl1),
                    modifier =
                        Modifier
                            .padding(vertical = UICSpacing.spacing.spacing025)
                            .align(Alignment.Start),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )
                Section(
                    modifier = Modifier,
                    leftIcon = accountAvatar(),
                    leftIconTint = UICTheme.colorScheme.brandSecondary.core,
                    contentText = stringResource(id = R.string.section_accounts),
                    rightIcon = chevronRight(),
                    onClick = null,
                    dividerColor = UICTheme.colorScheme.structural.outlineLight,
                    isDividerFromParentStart = true,
                    showDivider = true,
                    shadowElevation = 0.dp,
                )
                Section(
                    modifier = Modifier,
                    contentText = stringResource(id = R.string.section_accounts),
                    rightIcon = chevronRight(),
                    onClick = null,
                    dividerColor = UICTheme.colorScheme.structural.outlineLight,
                    isDividerFromParentStart = false,
                    showDivider = true,
                    shadowElevation = 0.dp,
                )
                Section(
                    modifier = Modifier,
                    leftIcon = accountAvatar(),
                    leftIconTint = UICTheme.colorScheme.brandSecondary.core,
                    contentText = stringResource(id = R.string.section_accounts),
                    onClick = null,
                    dividerColor = UICTheme.colorScheme.structural.outlineLight,
                    isDividerFromParentStart = true,
                    showDivider = true,
                    shadowElevation = 0.dp,
                )
                Text(
                    text = stringResource(R.string.section_lvl2),
                    modifier =
                        Modifier
                            .padding(vertical = UICSpacing.spacing.spacing025)
                            .align(Alignment.Start),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )
                SectionLevelFAQ(
                    modifier = Modifier,
                    contentText = stringResource(id = R.string.section_accounts),
                    rightIcon = chevronRight(),
                    dividerColor = UICTheme.colorScheme.structural.outlineLight,
                    isDividerFromParentStart = true,
                    showDivider = true,
                    shadowElevation = 0.dp,
                )
                Text(
                    text = stringResource(R.string.section_lvlfaq),
                    modifier =
                        Modifier
                            .padding(vertical = UICSpacing.spacing.spacing025)
                            .align(Alignment.Start),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )
                SectionLevelFAQ(
                    modifier = Modifier.padding(top = UICSpacing.spacing.spacing025),
                    leftIcon = settingsGear(),
                    leftIconTint = null,
                    contentText = stringResource(id = R.string.section_terms_of_use),
                    descriptionText = stringResource(id = R.string.section_description),
                    rightIcon = chevronRight(),
                    onClick = null,
                    dividerColor = UICTheme.colorScheme.structural.outlineLight,
                    isDividerFromParentStart = true,
                    showDivider = true,
                    shadowElevation = 0.dp,
                )
                SectionLevelFAQ(
                    modifier = Modifier.padding(top = UICSpacing.spacing.spacing025),
                    leftIcon = settingsGear(),
                    leftIconTint = null,
                    contentText = stringResource(id = R.string.section_terms_of_use),
                    rightIcon = chevronRight(),
                    onClick = null,
                    dividerColor = UICTheme.colorScheme.structural.outlineLight,
                    isDividerFromParentStart = true,
                    showDivider = true,
                    shadowElevation = 0.dp,
                )
                SectionLevelFAQ(
                    modifier = Modifier.padding(top = UICSpacing.spacing.spacing025),
                    contentText = stringResource(id = R.string.section_terms_of_use),
                    descriptionText = stringResource(R.string.notifications_desc),
                    rightIcon = chevronRight(),
                    onClick = null,
                    dividerColor = UICTheme.colorScheme.structural.outlineLight,
                    isDividerFromParentStart = true,
                    showDivider = true,
                    shadowElevation = 0.dp,
                )
                Text(
                    text = stringResource(R.string.preference_row_item),
                    modifier =
                        Modifier
                            .padding(vertical = UICSpacing.spacing.spacing025)
                            .align(Alignment.Start),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )
                var isSubscribed by remember { mutableStateOf(false) }
                PreferenceRowItem(
                    isSubscribed = isSubscribed,
                    onSubscriptionChanged = { isSubscribed = it },
                    notificationTitle = stringResource(R.string.notifications_title),
                    notificationDescription = stringResource(R.string.notifications_desc),
                    showDivider = true,
                )
                PreferenceRowItem(
                    isSubscribed = isSubscribed,
                    onSubscriptionChanged = { isSubscribed = it },
                    notificationTitle = stringResource(R.string.notifications_title),
                    showDivider = true,
                )

                PreferenceRowItemShimmer(paddingValues = PaddingValues(), itemRows = 2)
            }
        }
    }
}
