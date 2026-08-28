/*
 *
 *  Created by Mahesh Paul on 1/15/26, 11:30 AM
 *  Copyright (c) 2026 . All rights reserved.
 *  Last modified 1/15/26, 11:24 AM
 *
 */

package com.heb.centralmarket.uicart.component.dateAndTime

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.component.BodyVariant
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.CoreBodyTextView
import com.heb.centralmarket.uicart.component.buttons.UICRadioButton
import com.heb.centralmarket.uicart.component.listitems.ShimmerBox
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICHeight
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.utils.TestTags

/**
 * Visual style variant for a timeslot row.
 *
 * @property NORMAL Default styling (white background)
 * @property ASAP "Under 2 hours" — yellow/warning highlight treatment
 * @property EXPRESS "2-4 hours" — blue/coupon highlight treatment
 */
enum class TimeSlotSelectorStyle {
    NORMAL,
    ASAP,
    EXPRESS,
}

/**
 * A reusable time-slot row component with selectable radio indicator.
 *
 * This composable is **stateless** and follows **state hoisting**.
 * It does not manage its own selection state. Instead, it exposes
 * a selection callback which the caller must handle and provide
 * the updated [isSelected] value on recomposition.
 *
 * The view supports visual style variants via [slotStyle]:
 * - [TimeSlotSelectorStyle.ASAP]: yellow/warning background
 * - [TimeSlotSelectorStyle.EXPRESS]: blue/coupon background
 * - [TimeSlotSelectorStyle.NORMAL]: standard white background
 *
 * ### Behavior
 * - Displays a time range on the left
 * - Displays a price/value on the right
 * - Shows a radio button indicating selection state
 * - Draws a bottom divider for visual separation
 *
 * @param modifier Modifier to be applied to the root container.
 * @param timeText Primary text representing the time range.
 * @param valueText Secondary text representing the price or value.
 * @param slotStyle The visual treatment style for this slot row.
 * @param isExpressDelivery Deprecated — use [slotStyle] instead. Kept for backward compatibility.
 * @param isSelected Whether this time slot is currently selected.
 * @param onSelected Callback invoked when the radio button is clicked.
 */
@Composable
fun UICTimeSlotSelector(
    modifier: Modifier,
    timeText: String,
    valueText: String,
    slotStyle: TimeSlotSelectorStyle = TimeSlotSelectorStyle.NORMAL,
    isExpressDelivery: Boolean = false,
    isSelected: Boolean,
    onSelected: (() -> Unit?),
) {
    val interactions = remember { MutableInteractionSource() }
    val isPressed by interactions.collectIsPressedAsState()

    val borderColor = UICTheme.colorScheme.structural.bgSecondary
    val strokeWidth = UICHeight.height.dividerHeight

    // Resolve effective style: slotStyle takes precedence; fall back to legacy boolean
    val effectiveStyle = when {
        slotStyle != TimeSlotSelectorStyle.NORMAL -> slotStyle
        isExpressDelivery -> TimeSlotSelectorStyle.EXPRESS
        else -> TimeSlotSelectorStyle.NORMAL
    }

    val containerColor =
        when {
            isPressed -> UICTheme.colorScheme.structural.bgSecondary
            effectiveStyle == TimeSlotSelectorStyle.ASAP -> UICTheme.colorScheme.brand.coupon.bg
            effectiveStyle == TimeSlotSelectorStyle.EXPRESS -> UICTheme.colorScheme.info.bgLight
            else -> UICTheme.colorScheme.structural.bgPrimary
        }

    val contentColor = when (effectiveStyle) {
        TimeSlotSelectorStyle.ASAP -> UICTheme.colorScheme.brand.coupon.onBg
        TimeSlotSelectorStyle.EXPRESS -> UICTheme.colorScheme.info.onBgLight
        TimeSlotSelectorStyle.NORMAL -> UICTheme.colorScheme.txt.primary
    }

    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .heightIn(min = UICHeight.height.timeSelectorMinHeight)
                .background(color = containerColor)
                .indication(interactionSource = interactions, indication = null)
                .clickable(
                    interactionSource = interactions,
                    indication = null,
                    onClick = { onSelected() },
                )
                .drawBehind {
                    drawLine(
                        color = borderColor,
                        start = Offset(x = 0f, y = size.height),
                        end = Offset(x = size.width, y = size.height),
                        strokeWidth = strokeWidth.toPx()
                    )
                }
                .padding(
                    start = UICSpacing.spacing.spacing100,
                    end = UICSpacing.spacing.spacing050
                )
                .testTag(TestTags.TimeSlot.TIMESLOT_ROW),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CoreBodyTextView(
            text = timeText,
            bodyVariant = BodyVariant.BODY_1,
            modifier =
                Modifier
                    .weight(
                        weight = 1f
                    )
                    .testTag(TestTags.TimeSlot.TIMESLOT_TIME),
            color = contentColor,
            isBold = false,
            textAlign = TextAlign.Start
        )

        Spacer(
            modifier =
                Modifier
                    .width(width = UICSpacing.spacing.spacing100)
        )

        CoreBodyTextView(
            text = valueText,
            bodyVariant = BodyVariant.BODY_1,
            color = contentColor,
            isBold = true,
            textAlign = TextAlign.End,
            modifier = Modifier.testTag(TestTags.TimeSlot.TIMESLOT_PRICE)
        )

        UICRadioButton(
            selected = isSelected,
            onClick = { onSelected() },
            contentDescription = stringResource(R.string.time_slot_selector_radio_button_description),
            modifier = Modifier.testTag(TestTags.TimeSlot.TIMESLOT_RADIO_BUTTON),
        )
    }
}

/**
 * Displays a shimmer placeholder for the Time Slot Selector row.
 *
 * This composable is used as a loading state replacement for
 * [UICTimeSlotSelector] while the actual time slot data is being fetched.
 *
 * ## Visual Structure
 * - Full-width row with minimum height matching the selector item
 * - Two shimmer blocks representing:
 *   1. Time range text
 *   2. Value / price text
 * - Bottom divider to maintain visual consistency with the loaded state
 *
 * ## Usage
 * Use this composable inside lists or screens when the time slot data
 * is not yet available and a skeleton UI is required.
 *
 *
 * ## Notes
 * - Uses theme tokens for spacing, height, and colors
 * - Does not handle any user interaction
 * - Intended to be visually interchangeable with the loaded state
 */
@Composable
fun UICTimeSlotShimmer() {
    val borderColor = UICTheme.colorScheme.structural.bgSecondary
    val strokeWidth = UICHeight.height.dividerHeight
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .heightIn(min = UICHeight.height.timeSelectorMinHeight)
                .background(
                    color = UICTheme.colorScheme.structural.bgPrimary
                )
                .drawBehind {
                    drawLine(
                        color = borderColor,
                        start = Offset(x = 0f, y = size.height),
                        end = Offset(x = size.width, y = size.height),
                        strokeWidth = strokeWidth.toPx()
                    )
                }
                .padding(
                    PaddingValues(
                        horizontal = UICSpacing.spacing.spacing100,
                        vertical = UICSpacing.spacing.spacing150
                    )
                ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {
        ShimmerBox(
            modifier =
                Modifier
                    .width(width = TimeSlotDefaults.TIME_TEXT_SHIMMER_WIDTH)
                    .fillMaxWidth(fraction = 0.2f)
                    .height(height = UICHeight.height.shimmerRowItemHeight),
        )

        ShimmerBox(
            modifier =
                Modifier
                    .width(width = TimeSlotDefaults.VALUE_TEXT_SHIMMER_WIDTH)
                    .fillMaxWidth(fraction = 0.2f)
                    .height(height = UICHeight.height.shimmerRowItemHeight),
        )
    }
}

private object TimeSlotDefaults {
    val TIME_TEXT_SHIMMER_WIDTH = 124.dp
    val VALUE_TEXT_SHIMMER_WIDTH = 60.dp
}

@Preview
@Composable
fun UICTimeSlotSelectorPreview() {
    UICAppTheme {
        CoreBackground {
            Column{
                UICTimeSlotSelector(
                    modifier = Modifier,
                    timeText = "08:00am - 09:30am",
                    valueText = "$4.98",
                    isExpressDelivery = false,
                    isSelected = false,
                    onSelected = {  },
                )

                Spacer(modifier = Modifier.height(50.dp))

                UICTimeSlotSelector(
                    modifier = Modifier,
                    timeText = "08:00am - 09:30am",
                    valueText = "$4.98",
                    isExpressDelivery = true,
                    isSelected = false,
                    onSelected = {  },
                )
                Spacer(modifier = Modifier.height(50.dp))

                UICTimeSlotShimmer()
            }
        }
    }
}
