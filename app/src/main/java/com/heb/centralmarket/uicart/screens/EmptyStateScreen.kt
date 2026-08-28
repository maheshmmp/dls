/*
 *
 *  Created by Mahesh Paul on 1/13/26, 6:50 PM
 *  Copyright (c) 2026 . All rights reserved.
 *  Last modified 1/13/26, 10:14 AM
 *
 */

package com.heb.centralmarket.uicart.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.EmptyListPlaceHolder
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

@Composable
fun EmptyStateScreen(
    navController: NavHostController,
    drawerState: DrawerState
) {
    // Scaffold provides structure with a top app bar and screen body.
    Scaffold(
        topBar = {
            // App bar with title and navigation (back) icon
            AppBar(
                title = R.string.empty_states,
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
        // Core Background wrapper that will include App theme styling
        CoreBackground {
            Column(
                modifier =
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(it)
                    .padding(horizontal = UICSpacing.spacing.spacing050),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = stringResource(R.string.empty_cart),
                    modifier = Modifier
                        .padding(
                            top = UICSpacing.spacing.spacing100
                        )
                        .align(Alignment.Start),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )

                Spacer(modifier = Modifier.height(UICSpacing.spacing.spacing100))

                EmptyListPlaceHolder(
                    imageModel = R.drawable.uic_ic_empty_order_ahead,
                    contentDesc = stringResource(R.string.section_description),
                    titleText = stringResource(com.heb.centralmarket.uicart.components.R.string.empty_cart_lengthy_title_text_demo),
                    bodyText = stringResource(com.heb.centralmarket.uicart.components.R.string.empty_cart_lengthy_description_text_demo),
                    modifier = Modifier.fillMaxHeight()
                )
            }
        }

    }
}

