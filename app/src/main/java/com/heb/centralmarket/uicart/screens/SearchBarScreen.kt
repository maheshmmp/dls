/*
 * *
 *  * Created by Mahesh Mathew Paul on 11/17/25, 11:27 AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  * Last modified 11/17/25, 11:27 AM
 *
 */

package com.heb.centralmarket.uicart.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.buttons.CoreIconButton
import com.heb.centralmarket.uicart.component.searchbar.UICSearchBar
import com.heb.centralmarket.uicart.icons.barcodeRead
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

@Composable
fun SearchBarScreen(
    navController: NavController,
    drawerState: DrawerState,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = R.string.address_book,
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
                        .verticalScroll(rememberScrollState())
                        .padding(paddingValues = paddingValues),
                verticalArrangement = Arrangement.spacedBy(space = UICSpacing.spacing.spacing025),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.search_bar),
                    modifier = Modifier
                        .padding(
                            horizontal = UICSpacing.spacing.spacing050,
                            vertical = UICSpacing.spacing.spacing050
                        )
                        .align(Alignment.Start),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )

                Box(modifier = Modifier.padding(all = UICSpacing.spacing.spacing100)) {
                    Column(verticalArrangement = Arrangement.spacedBy(space = UICSpacing.spacing.spacing050)) {
                        val context = LocalContext.current

                        var text2 by remember { mutableStateOf("") }
                        UICSearchBar(
                            query = text2,
                            onQueryChange = { text2 = it
                                if (text2.isNotEmpty()) {
                                    Toast.makeText(
                                        context,
                                        "$text2 searched",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            onSearchCleared = {
                                Toast.makeText(
                                    context,
                                    "Search cleared",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )

                        var text1 by remember { mutableStateOf("Wine") }
                        UICSearchBar(
                            query = text1,
                            onQueryChange = { text1 = it
                                if (text1.isNotEmpty()) {
                                    Toast.makeText(
                                        context,
                                        "$text1 searched",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            onSearchCleared = {
                                Toast.makeText(
                                    context,
                                    "Search cleared",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )

                        var text3 by remember { mutableStateOf("Bread") }
                        UICSearchBar(
                            query = text3,
                            onQueryChange = { text3 = it
                                if (text3.isNotEmpty()) {
                                    Toast.makeText(
                                        context,
                                        "$text3 searched",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            rightIcon = {
                                CoreIconButton(
                                    iconVector = barcodeRead(),
                                    contentDescription = stringResource(id = com.heb.centralmarket.uicart.components.R.string.barcode_read_icon_content_description),
                                    onClick = {
                                        Toast.makeText(
                                            context,
                                            "Custom button clicked!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                )
                            },
                            onSearchCleared = {
                                Toast.makeText(
                                    context,
                                    "Search cleared",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                    }
                }
            }
        }
    }
}