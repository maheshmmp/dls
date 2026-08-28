package com.heb.centralmarket.uicart.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.dateAndTime.TimeSlotSectionHeaderStyle
import com.heb.centralmarket.uicart.component.dateAndTime.TimeSlotSelectorStyle
import com.heb.centralmarket.uicart.component.dateAndTime.UICTimeSlotSectionHeader
import com.heb.centralmarket.uicart.component.dateAndTime.UICTimeSlotSelector
import com.heb.centralmarket.uicart.component.dateAndTime.UICTimeSlotShimmer
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

private object TimeSlotIds {
    const val ASAP_1 = "asap_1"
    const val ASAP_2 = "asap_2"
    const val EXPRESS_1 = "express_1"
    const val EXPRESS_2 = "express_2"
    const val NORMAL_1 = "normal_1"
    const val NORMAL_2 = "normal_2"
}

@Composable
fun TimeSlotSelectorScreen(
    navController: NavHostController,
    drawerState: DrawerState,
) {
    var selectedId by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            AppBar(
                title = R.string.time_slot_selector_title,
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
    ) { innerPadding ->
        CoreBackground {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState())
                    .padding(paddingValues = innerPadding),
                horizontalAlignment = Alignment.Start,
            ) {


                Spacer(modifier = Modifier.height(UICSpacing.spacing.spacing025))


                // ==================== ASAP group ====================
                Text(
                    text = stringResource(R.string.asap_timeslot),
                    modifier = Modifier.padding(all = UICSpacing.spacing.spacing050),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )
                UICTimeSlotSectionHeader(
                    title = "Under 2 Hours",
                    style = TimeSlotSectionHeaderStyle.ASAP,
                    modifier = Modifier.fillMaxWidth(),
                )
                UICTimeSlotSelector(
                    modifier = Modifier.fillMaxWidth(),
                    timeText = "11:00am - 12:30pm",
                    valueText = "FREE",
                    slotStyle = TimeSlotSelectorStyle.ASAP,
                    isSelected = selectedId == TimeSlotIds.ASAP_1,
                    onSelected = { selectedId = TimeSlotIds.ASAP_1 },
                )
                UICTimeSlotSelector(
                    modifier = Modifier.fillMaxWidth(),
                    timeText = "12:30pm - 02:00pm",
                    valueText = "FREE",
                    slotStyle = TimeSlotSelectorStyle.ASAP,
                    isSelected = selectedId == TimeSlotIds.ASAP_2,
                    onSelected = { selectedId = TimeSlotIds.ASAP_2 },
                )

                // ==================== EXPRESS group ====================
                Text(
                    text = stringResource(R.string.express_timeslot),
                    modifier = Modifier.padding(all = UICSpacing.spacing.spacing050),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )
                UICTimeSlotSectionHeader(
                    title = "2-4 Hours",
                    style = TimeSlotSectionHeaderStyle.EXPRESS,
                    modifier = Modifier.fillMaxWidth(),
                )
                UICTimeSlotSelector(
                    modifier = Modifier.fillMaxWidth(),
                    timeText = "02:00pm - 04:00pm",
                    valueText = "$2.99",
                    slotStyle = TimeSlotSelectorStyle.EXPRESS,
                    isSelected = selectedId == TimeSlotIds.EXPRESS_1,
                    onSelected = { selectedId = TimeSlotIds.EXPRESS_1 },
                )
                UICTimeSlotSelector(
                    modifier = Modifier.fillMaxWidth(),
                    timeText = "04:00pm - 06:00pm",
                    valueText = "$2.99",
                    slotStyle = TimeSlotSelectorStyle.EXPRESS,
                    isSelected = selectedId == TimeSlotIds.EXPRESS_2,
                    onSelected = { selectedId = TimeSlotIds.EXPRESS_2 },
                )

                // ==================== NORMAL group ====================
                // MEDIUM variant section
                Text(
                    text = stringResource(R.string.normal_timeslot),
                    modifier = Modifier.padding(all = UICSpacing.spacing.spacing050),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )
                UICTimeSlotSectionHeader(
                    title = "Morning",
                    style = TimeSlotSectionHeaderStyle.NORMAL,
                    modifier = Modifier.fillMaxWidth(),
                )
                UICTimeSlotSelector(
                    modifier = Modifier.fillMaxWidth(),
                    timeText = "08:00am - 09:30am",
                    valueText = "$4.98",
                    slotStyle = TimeSlotSelectorStyle.NORMAL,
                    isSelected = selectedId == TimeSlotIds.NORMAL_1,
                    onSelected = { selectedId = TimeSlotIds.NORMAL_1 },
                )
                UICTimeSlotSelector(
                    modifier = Modifier.fillMaxWidth(),
                    timeText = "09:30am - 11:00am",
                    valueText = "$3.99",
                    slotStyle = TimeSlotSelectorStyle.NORMAL,
                    isSelected = selectedId == TimeSlotIds.NORMAL_2,
                    onSelected = { selectedId = TimeSlotIds.NORMAL_2 },
                )


                // ==================== Shimmer ====================
                Text(
                    text = stringResource(R.string.shimmer_timeslot),
                    modifier = Modifier
                        .padding(all = UICSpacing.spacing.spacing050),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )
                UICTimeSlotShimmer()
            }
        }
    }
}

@Preview(name = "Time Slot Selector Screen")
@Composable
fun PreviewTimeSelectorView() {
    TimeSlotSelectorScreen(
        NavHostController(LocalContext.current),
        rememberDrawerState(initialValue = DrawerValue.Closed),
    )
}
