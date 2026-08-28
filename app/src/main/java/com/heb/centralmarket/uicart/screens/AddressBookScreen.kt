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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.CoreBodyTextView
import com.heb.centralmarket.uicart.component.UICInputTextField
import com.heb.centralmarket.uicart.component.listitems.UICLocationDetailItemVariant
import com.heb.centralmarket.uicart.component.listitems.UICLocationDetails
import com.heb.centralmarket.uicart.component.listitems.UICLocationDetailsObj
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme

@Composable
fun LocationDetailsScreen(
    navController: NavController,
    drawerState: DrawerState,
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val variants =
        listOf(UICLocationDetailItemVariant.DELIVERY_ADDRESS, UICLocationDetailItemVariant.STORE_LOCATION)
    var isEditable by remember { mutableStateOf(false) }
    var isMultiple by remember { mutableStateOf(false) }
    var isSelected by remember { mutableStateOf(false) }
    var showDeliveryNotes by remember { mutableStateOf(false) }
    var deliveryNotes by remember { mutableStateOf("optional delivery notes go here") }
    var showLocationData by remember { mutableStateOf(false) }
    var isOrderAheadAvailable by remember { mutableStateOf(false) }
    var hasStoreInfo by remember { mutableStateOf(false) }

    val currentVariant = variants[selectedTab]
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
                horizontalAlignment = Alignment.Start,
            ) {
                Column(modifier = Modifier.padding(vertical = UICSpacing.spacing.spacing150)) {
                    UICLocationDetails(
                        isEditable = isEditable,
                        onEditClicked = {},
                        onDeleteClicked = {},
                        onSelected = { isSelected = it },
                        isSelected = isSelected,
                        showDivider = true,
                        uicLocationDetails =
                            UICLocationDetailsObj(
                                locationName = "Austin Westgate testing multiple lines for name here for wrapping",
                                locationAddressLineOne = "Location Address Line one",
                                locationAddressLineTwo = "",
                                locationAddressLineThree = "Location Address Line three which is long text",
                                deliveryNotes = deliveryNotes,
                                isOrderAheadAvailable = isOrderAheadAvailable,
                                locationData = if (showLocationData) "2.7mi" else null,
                                variant = currentVariant,
                                hasStoreInfo = hasStoreInfo,
                            ),
                    )
                    if (isMultiple) {
                        UICLocationDetails(
                            isEditable = isEditable,
                            onEditClicked = {},
                            onDeleteClicked = {},
                            onSelected = { isSelected = it },
                            isSelected = isSelected,
                            showDivider = true,
                            uicLocationDetails =
                                UICLocationDetailsObj(
                                    locationName = "Dallas Midway",
                                    locationAddressLineOne = "1234 Shady Ln",
                                    locationAddressLineTwo = "Austin, TX 78745",
                                    locationAddressLineThree = null,
                                    deliveryNotes = deliveryNotes,
                                    isOrderAheadAvailable = isOrderAheadAvailable,
                                    locationData = if (showLocationData) "2.7mi" else null,
                                    variant = currentVariant,
                                    hasStoreInfo = hasStoreInfo,
                                ),
                        )
                    }
                }
                Column(modifier = Modifier.padding(UICSpacing.spacing.spacing100)) {
                    PrimaryTabRow(selectedTabIndex = selectedTab) {
                        variants.forEachIndexed { index, variant ->
                            Tab(
                                selected = selectedTab == index,
                                onClick = { selectedTab = index },
                                text = {
                                    Text(
                                        text =
                                            when (variant) {
                                                UICLocationDetailItemVariant.DELIVERY_ADDRESS -> "Delivery Address"
                                                UICLocationDetailItemVariant.STORE_LOCATION -> "Store Location"
                                            },
                                    )
                                },
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        CoreBodyTextView("Multiple", color = UICTheme.colorScheme.txt.primary)
                        Switch(checked = isMultiple, onCheckedChange = { isMultiple = it })
                    }

                    if (currentVariant == UICLocationDetailItemVariant.DELIVERY_ADDRESS) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            CoreBodyTextView("Editable", color = UICTheme.colorScheme.txt.primary)
                            Switch(checked = isEditable, onCheckedChange = { isEditable = it })
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            CoreBodyTextView("Delivery Notes", color = UICTheme.colorScheme.txt.primary)
                            Switch(
                                checked = showDeliveryNotes,
                                onCheckedChange = { showDeliveryNotes = it },
                            )
                        }
                        if (showDeliveryNotes) {
                            UICInputTextField(
                                value = deliveryNotes,
                                onValueChange = { deliveryNotes = it },
                                label = "Delivery Notes",
                                enabled = true,
                                singleLine = false,
                                maxLines = 10,
                                isMultilineTextAreaNeeded = false,
                                isError = false,
                                errorText = null,
                                scaleFactor = 1f,
                                keyboardOptions =
                                    KeyboardOptions.Default.copy(
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Done,
                                    ),
                            )
                        }
                    } else {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            CoreBodyTextView("Show Location Data", color = UICTheme.colorScheme.txt.primary)

                            Switch(
                                checked = showLocationData,
                                onCheckedChange = { showLocationData = it },
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            CoreBodyTextView("Show Store Info", color = UICTheme.colorScheme.txt.primary)

                            Switch(
                                checked = hasStoreInfo,
                                onCheckedChange = { hasStoreInfo = it },
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            CoreBodyTextView("Order Ahead Available", color = UICTheme.colorScheme.txt.primary)
                            Switch(
                                checked = isOrderAheadAvailable,
                                onCheckedChange = { isOrderAheadAvailable = it },
                            )
                        }
                    }
                }
            }
        }
    }
}
