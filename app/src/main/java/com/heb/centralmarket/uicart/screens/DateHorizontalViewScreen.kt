/*
 *
 *  Created by Mahesh Paul on 2/4/26, 10:01 AM
 *  Copyright (c) 2026 . All rights reserved.
 *  Last modified 2/4/26, 10:00 AM
 *
 */

package com.heb.centralmarket.uicart.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.dateAndTime.DateSlotAvailability
import com.heb.centralmarket.uicart.component.dateAndTime.DateSlotStatus
import com.heb.centralmarket.uicart.component.dateAndTime.UICDateItem
import com.heb.centralmarket.uicart.component.dateAndTime.UICHorizontalDateSelectorShimmer
import com.heb.centralmarket.uicart.component.dateAndTime.UICHorizontalDateSelectorView
import com.heb.centralmarket.uicart.icons.accountAvatar
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.icons.infoIcon
import com.heb.centralmarket.uicart.icons.settingsGear
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateHorizontalViewScreen(
    navController: NavController,
    drawerState: DrawerState,
) {
     val staticDateItems = listOf(
        UICDateItem(
            day = "Today",
            date = "01",
            amount = "$4.98",
            dateAvailability = DateSlotAvailability.ACTIVE,
            dateSlotStatus = DateSlotStatus.NORMAL,
            iconVector = ImageVector.vectorResource(R.drawable.uic_ic_old_turkey)
        ),
        UICDateItem(
            day = "Tue",
            date = "02",
            amount = "$4.98",
            dateAvailability = DateSlotAvailability.INACTIVE,
            dateSlotStatus = DateSlotStatus.NORMAL,
            iconVector = accountAvatar()
        ),
        UICDateItem(
            day = "Wed",
            date = "03",
            amount = "$4.98",
            dateAvailability = DateSlotAvailability.INACTIVE,
            dateSlotStatus = DateSlotStatus.FREE,
            iconVector = settingsGear()
        ),
        UICDateItem(
            day = "Thu",
            date = "04",
            amount = "Sold Out",
            dateAvailability = DateSlotAvailability.ACTIVE,
            dateSlotStatus = DateSlotStatus.SOLD_OUT,
            iconVector = ImageVector.vectorResource(R.drawable.uic_ic_old_turkey)
        ),
        UICDateItem(
            day = "Fri",
            date = "05",
            amount = "Sold Out",
            dateAvailability = DateSlotAvailability.INACTIVE,
            dateSlotStatus = DateSlotStatus.SOLD_OUT,
            iconVector = infoIcon()
        ),
    )
    val baseDate = remember {
        LocalDate.now().withDayOfMonth(1)
    }
    var showBadge by remember { mutableStateOf(false) }
    var showShimmer by remember { mutableStateOf(false) }
    var itemCount by remember { mutableIntStateOf(5) }
    val fixedCount = staticDateItems.size
    val dateItems by remember(itemCount) {
        mutableStateOf(
            if (itemCount <= fixedCount) {
                staticDateItems.take(itemCount)
            } else {
                staticDateItems +
                        generateRandomDateItems(
                            count = itemCount - fixedCount,
                            baseDate = baseDate,
                            startOffset = fixedCount.toLong()
                        )
            }
        )
    }
    var selectedDateData by remember { mutableStateOf(value = staticDateItems[0]) }
    Scaffold(
        topBar = {
            AppBar(title = R.string.date_horizontal_scroll_view, drawerState = drawerState, navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = chevronLeft(),
                        contentDescription = stringResource(id = R.string.back),
                        tint = UICTheme.colorScheme.txt.primary,
                    )
                }
            }, actionIcon = {
                ThemeSwitcherAction()
                DarkModeSwitcherAction()
            })
        },
    ) { innerPadding ->
        CoreBackground {

                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(paddingValues = innerPadding)
                                .padding(horizontal = UICSpacing.spacing.spacing050),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(modifier = Modifier.height(height = UICSpacing.spacing.spacing100))

                        if (showShimmer){
                            UICHorizontalDateSelectorShimmer()
                        }
                        else {
                            UICHorizontalDateSelectorView(
                                modifier = Modifier,
                                title = "December",
                                dateItems = dateItems,
                                onDateSelected = { item ->
                                    selectedDateData = item
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(height = UICSpacing.spacing.spacing100))

                        Text(
                            text = "Selected Date : ${selectedDateData.day} ${selectedDateData.date} ",
                            style = UICTypography.typography.heading.h4,
                            color = UICTheme.colorScheme.txt.primary,
                        )

                        SwitchRow(
                            label = "Show Date Decoration",
                            checked = showBadge,
                            onCheckedChange = { showBadge = it },
                        )

                        SwitchRow(
                            label = "Show Shimmer",
                            checked = showShimmer,
                            onCheckedChange = { showShimmer = it },
                        )

                        DateItemCountSlider(
                            count = itemCount,
                            onCountChanged = { itemCount = it }
                        )
                    }


        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun generateRandomDateItems(
    count: Int,
    baseDate: LocalDate,
    startOffset: Long,
): List<UICDateItem> {


    return List(size = count) { index ->
        val currentDate = baseDate.plusDays( startOffset + index)

        val dayLabel =
            currentDate.dayOfWeek.getDisplayName(
                TextStyle.SHORT,
                Locale.getDefault()
            )

        val dateLabel =
            currentDate.dayOfMonth.toString().padStart(length = 2, padChar = '0')

        val randomStatus = DateSlotStatus.entries.toTypedArray().random()

        val availability =
            if (randomStatus == DateSlotStatus.SOLD_OUT)
                DateSlotAvailability.INACTIVE
            else
                DateSlotAvailability.ACTIVE

        val amount =
            when (randomStatus) {
                DateSlotStatus.FREE -> "FREE"
                DateSlotStatus.SOLD_OUT -> "Sold Out"
                DateSlotStatus.NORMAL -> "$4.98"
            }

        UICDateItem(
            day = dayLabel,
            date = dateLabel,
            amount = amount,
            dateAvailability = availability,
            dateSlotStatus = randomStatus,
        )
    }
}
@Composable
fun DateItemCountSlider(
    count: Int,
    onCountChanged: (Int) -> Unit,
) {
    Column(modifier = Modifier.padding(all = UICSpacing.spacing.spacing100)) {
        Text(
            text = stringResource(R.string.day_count_label, count),
            style =
                UICTypography.typography.body.body1.regular
                    .copy(color = UICTheme.colorScheme.txt.primary),
        )
        Slider(
            value = count.toFloat(),
            onValueChange = { onCountChanged(it.toInt()) },
            valueRange = 5f..31f,
            colors = SliderDefaults.colors(
                thumbColor = UICTheme.colorScheme.brand.primary.core,
                activeTrackColor = UICTheme.colorScheme.brand.primary.bg,
                inactiveTrackColor = UICTheme.colorScheme.structural.bgSecondary,
                activeTickColor = Color.Transparent,
                inactiveTickColor = Color.Transparent
            )
        )
    }
}
