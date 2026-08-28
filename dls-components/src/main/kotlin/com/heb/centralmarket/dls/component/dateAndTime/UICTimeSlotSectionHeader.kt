/*
 *
 *  Created by Mahesh Paul on 1/31/26, 7:27 PM
 *  Copyright (c) 2026 . All rights reserved.
 *  Last modified 1/30/26, 7:32 PM
 *
 */

package com.heb.centralmarket.uicart.component.dateAndTime

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.heb.centralmarket.uicart.component.BodyVariant
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.CoreBodyTextView
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.icons.CoreIcon
import com.heb.centralmarket.uicart.icons.fastCart
import com.heb.centralmarket.uicart.icons.lightning
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme

/**
 * Visual style variant for a timeslot section header.
 *
 * @property NORMAL Default gray styling (Morning, Afternoon, Evening)
 * @property ASAP "Under 2 hours" — yellow/warning highlight treatment with lightning icon
 * @property EXPRESS "2-4 hours" — blue highlight treatment with fast cart icon
 */
enum class TimeSlotSectionHeaderStyle {
    NORMAL,
    ASAP,
    EXPRESS,
}

/**
 * A reusable section header for timeslot groupings.
 *
 * This composable renders a full-width header row with background color,
 * text color, and optional leading icon determined by the [style] parameter.
 *
 * ### Visual Variants
 * - [TimeSlotSectionHeaderStyle.ASAP]: yellow/warning background + ⚡ lightning icon
 * - [TimeSlotSectionHeaderStyle.EXPRESS]: blue/info background + 🛒 fast cart icon
 * - [TimeSlotSectionHeaderStyle.NORMAL]: neutral gray background, no icon
 *
 * ### Behavior
 * - Displays section title text (e.g., "Under 2 Hours", "Morning")
 * - Non-interactive (display only)
 * - Provides visual separation between timeslot groups
 *
 * @param title Section title text (e.g., "Under 2 Hours", "Morning")
 * @param modifier Modifier to be applied to the root container.
 * @param style The visual treatment style for this header.
 */
@Composable
fun UICTimeSlotSectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    style: TimeSlotSectionHeaderStyle = TimeSlotSectionHeaderStyle.NORMAL,
) {
    val backgroundColor: Color = when (style) {
        TimeSlotSectionHeaderStyle.ASAP -> UICTheme.colorScheme.brand.coupon.core
        TimeSlotSectionHeaderStyle.EXPRESS -> UICTheme.colorScheme.info.bg
        TimeSlotSectionHeaderStyle.NORMAL -> UICTheme.colorScheme.structural.bgSecondary
    }

    val textColor: Color = when (style) {
        TimeSlotSectionHeaderStyle.ASAP -> UICTheme.colorScheme.brand.coupon.onCore
        TimeSlotSectionHeaderStyle.EXPRESS -> UICTheme.colorScheme.info.onCore
        TimeSlotSectionHeaderStyle.NORMAL -> UICTheme.colorScheme.txt.primary
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = backgroundColor),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = UICSpacing.spacing.spacing050,
                    bottom = UICSpacing.spacing.spacing050,
                    start = UICSpacing.spacing.spacing100,
                    end = UICSpacing.spacing.spacing100,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val icon: ImageVector? = when (style) {
                TimeSlotSectionHeaderStyle.ASAP -> lightning()
                TimeSlotSectionHeaderStyle.EXPRESS -> fastCart()
                else -> null
            }

            if (icon != null) {
                CoreIcon(
                    imageVector = icon,
                    contentDescription = stringResource(R.string.time_slot_selector_header_icon_description),
                    tint = textColor,
                    size = UICSpacing.spacing.spacing100,
                )
                Spacer(modifier = Modifier.width(UICSpacing.spacing.spacing050))
            }

            CoreBodyTextView(
                text = title,
                textAlign = TextAlign.Start,
                isBold = true,
                bodyVariant = BodyVariant.BODY_1,
                color = textColor,
            )
        }
    }
}

@Preview
@Composable
fun UICTimeSlotSectionHeaderPreview() {
    UICAppTheme {
        CoreBackground {
            UICTimeSlotSectionHeader(
                title = "Morning",
                style = TimeSlotSectionHeaderStyle.NORMAL,
            )
            UICTimeSlotSectionHeader(
                title = "Under 2 Hours",
                style = TimeSlotSectionHeaderStyle.ASAP,
            )
            UICTimeSlotSectionHeader(
                title = "2-4 Hours",
                style = TimeSlotSectionHeaderStyle.EXPRESS,
            )
        }
    }
}
