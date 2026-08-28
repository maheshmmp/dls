/*
 * *
 *  * Created by Mahesh Mathew Paul on 10/30/25, 1:28 PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  * Last modified 10/30/25, 1:25 PM
 *  
 */

package com.heb.centralmarket.uicart.component.buttons

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.heb.centralmarket.uicart.icons.chef
import com.heb.centralmarket.uicart.icons.shop
import com.heb.centralmarket.uicart.icons.truck
import com.heb.centralmarket.uicart.themesystem.UICSpacing

/**
 * A grid layout that displays service items in rows of 3
 *
 * @param serviceItems List of service items to display
 */
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ServicesGrid(
    serviceItems: List<com.heb.centralmarket.uicart.component.buttons.ServiceItemData>,
) {
    val spacing = UICSpacing.spacing.spacing100
    val columns = 3

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val totalSpacing = spacing * (columns - 1)
        val itemWidth = (maxWidth - totalSpacing) / columns

        val groupedItems = serviceItems.chunked(size = columns)

        Column(
            verticalArrangement = Arrangement.spacedBy(space = spacing),
        ) {
            groupedItems.forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(space = spacing),
                ) {
                    rowItems.forEach { item ->
                        _root_ide_package_.com.heb.centralmarket.uicart.component.buttons.ServicesItem(
                            onClick = item.onClick,
                            iconVector = item.iconVector,
                            text = item.text,
                            enabled = item.enabled,
                            contentDescription = item.contentDescription,
                            modifier = Modifier.width(itemWidth)
                        )
                    }
                    // ✅ Fill remaining columns if not multiple of 3
                    repeat(times = columns - rowItems.size) {
                        Spacer(modifier = Modifier.width(itemWidth))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ServicesGridEnabledPreview() {
    val serviceItemsEnabled =
        listOf(
            _root_ide_package_.com.heb.centralmarket.uicart.component.buttons.ServiceItemData(
                onClick = { /* Handle click */ },
                iconVector = chef(),
                text = "Account",
                enabled = true
            ),
            _root_ide_package_.com.heb.centralmarket.uicart.component.buttons.ServiceItemData(
                onClick = { /* Handle click */ },
                iconVector = truck(),
                text = "Settings",
                enabled = true,
            ),

            _root_ide_package_.com.heb.centralmarket.uicart.component.buttons.ServiceItemData(
                onClick = { /* Handle click */ },
                iconVector = shop(),
                text = "Favorites",
                enabled = true,
            ),
            _root_ide_package_.com.heb.centralmarket.uicart.component.buttons.ServiceItemData(
                onClick = { /* Handle click */ },
                iconVector = shop(),
                text = "Order Ahead",
                enabled = true,
            ),
        )
    ServicesGrid(serviceItems = serviceItemsEnabled)
}

@Preview(showBackground = true)
@Composable
fun ServicesGridDisabledPreview() {
    val serviceItemsDisabled =
        listOf(
            _root_ide_package_.com.heb.centralmarket.uicart.component.buttons.ServiceItemData(
                onClick = { /* Handle click */ },
                iconVector = chef(),
                text = "Account",
                enabled = false
            ),
            _root_ide_package_.com.heb.centralmarket.uicart.component.buttons.ServiceItemData(
                onClick = { /* Handle click */ },
                iconVector = truck(),
                text = "Settings",
                enabled = false,
            ),

            _root_ide_package_.com.heb.centralmarket.uicart.component.buttons.ServiceItemData(
                onClick = { /* Handle click */ },
                iconVector = shop(),
                text = "Cooking School and Mandatory things",
                enabled = false,
            ),
        )
    ServicesGrid(serviceItems = serviceItemsDisabled)
}
