/*
 *
 *  Created by Mahesh Paul on 1/30/26, 3:31 PM
 *  Copyright (c) 2026 . All rights reserved.
 *  Last modified 1/30/26, 3:15 PM
 *
 */

package com.heb.centralmarket.uicart.component.dateAndTime

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.CoreHeadingTextView
import com.heb.centralmarket.uicart.component.HeadingVariant
import com.heb.centralmarket.uicart.component.buttons.DisableRippleEffect
import com.heb.centralmarket.uicart.component.listitems.ShimmerBox
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.icons.accountAvatar
import com.heb.centralmarket.uicart.icons.settingsGear
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICHeight
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.utils.TestTags

/**
 * A single date item view used inside horizontal date selectors.
 *
 * This composable renders a date item with day, date, and an optional pill showing amount or status.
 * It also supports interaction states (pressed, focused, hovered) and an optional badge decoration.
 *
 * The colors are mapped dynamically using [DateSelectorItemColorMapper] based on the current [UICTheme]
 * and the item's [DateSlotAvailability] and [DateSlotStatus]. Interaction states are reflected in
 * background and border colors.
 *
 * @param dateItems The [UICDateItem] containing the day, date, amount, availability, status, and optional icon.
 * @param modifier [Modifier] for styling, layout, and interaction.
 * @param onDateSelected Optional lambda invoked when the item is clicked. Returns the [dateItems].
 */
@Composable
fun UICHorizontalDateSelectorView(
    modifier: Modifier,
    title: String,
    dateItems: List<UICDateItem>,
    showDivider: Boolean? = true,
    onDateSelected: (UICDateItem) -> Unit,
    listState: LazyListState = rememberLazyListState(),
) {
    DisableRippleEffect {
        Column(
            modifier =
                modifier
                    .fillMaxWidth()
        ) {
            CoreHeadingTextView(
                text = title.uppercase(),
                headingLevel = HeadingVariant.HEADING_3,
                modifier = modifier
                    .wrapContentWidth()
                    .align(
                        alignment = Alignment.CenterHorizontally
                    )
                    .fillMaxWidth()
                    .padding(vertical = UICSpacing.spacing.spacing050),
                textAlign = TextAlign.Center,
                maxLines = 1,
                isBold = true
            )

            if (dateItems.size > 1) {
                LazyRow(
                    modifier =
                        modifier
                            .fillMaxWidth()
                            .testTag(TestTags.TimeSlot.DATE_CONTAINER),
                    contentPadding = PaddingValues(
                        horizontal = UICSpacing.spacing.spacing050,
                        vertical = UICSpacing.spacing.spacing050
                    ),
                    horizontalArrangement = Arrangement.spacedBy(space = UICSpacing.spacing.spacing050),
                    state = listState
                ) {
                    items(count = dateItems.size) { index ->
                        DateSelectorItem(
                            dateItem = dateItems[index],
                            modifier = modifier.testTag(TestTags.TimeSlot.DATE_ROW),
                            onClick = { onDateSelected.invoke(dateItems[index]) },
                            showBadge = dateItems[index].showTimeSlotDecoration,
                        )
                    }
                }
            }
            if (showDivider == true) {
                HorizontalDivider(
                    color = UICTheme.colorScheme.structural.outlineLight,
                    thickness = UICHeight.height.dividerHeight,
                )
            }
        }
    }
}

/**
 * Displays a shimmer placeholder UI for a horizontal date selector while data is loading.
 *
 * This composable mimics the structure of [UICHorizontalDateSelectorView] by rendering:
 * - A shimmer title placeholder
 * - A horizontally scrolling list of shimmer date items
 * - An optional bottom divider
 *
 * Internally, it uses a [UICDateItem] configured with
 * [DateSlotAvailability.LOADING] to ensure all child date items render
 * their shimmer states consistently.
 *
 * @param itemCount
 * Number of shimmer date items to display in the horizontal list.
 * If null, [DateSelectorShimmerDefaults.DEFAULT_ITEM_COUNT] is used.
 *
 * @param modifier
 * Modifier applied to the root container of the shimmer layout,
 * allowing external control of size, padding, and positioning.
 *
 * @param showDivider
 * Controls whether a horizontal divider is displayed below the shimmer list.
 * This is useful for maintaining layout parity with the loaded state.
 */
@Composable
fun UICHorizontalDateSelectorShimmer(
    modifier: Modifier = Modifier,
    itemCount: Int? = DateSelectorShimmerDefaults.DEFAULT_ITEM_COUNT,
    showDivider: Boolean = true,
) {
    val safeItemCount =
        (itemCount ?: DateSelectorShimmerDefaults.DEFAULT_ITEM_COUNT).coerceAtLeast(1)

    val dateItemObj =
        UICDateItem(
            day = "",
            date = "",
            amount = "",
            dateAvailability = DateSlotAvailability.LOADING,
            dateSlotStatus = DateSlotStatus.SOLD_OUT
        )

    Column(
        modifier =
            modifier.fillMaxWidth()
    ) {
        ShimmerBox(
            modifier =
                modifier
                    .padding(vertical = UICSpacing.spacing.spacing050)
                    .width(width = UICHeight.height.shimmerTitleWidth)
                    .fillMaxWidth(fraction = 0.2f)
                    .height(height = UICHeight.height.shimmerTitleHeight)
                    .align(alignment = Alignment.CenterHorizontally),
        )
        LazyRow(
            modifier =
                Modifier
                    .fillMaxWidth(),
            contentPadding = PaddingValues(
                horizontal = UICSpacing.spacing.spacing100,
                vertical = UICSpacing.spacing.spacing050
            ),
            horizontalArrangement = Arrangement.spacedBy(space = UICSpacing.spacing.spacing075)
        ) {
            items(count = safeItemCount) {
                DateSelectorItem(
                    dateItem = dateItemObj,
                    modifier = Modifier
                )
            }
        }
        if (showDivider) {
            HorizontalDivider(
                color = UICTheme.colorScheme.structural.outlineLight,
                thickness = UICHeight.height.dividerHeight,
            )
        }
    }
}

private object DateSelectorShimmerDefaults {
    const val DEFAULT_ITEM_COUNT = 5
}

@Preview(
    name = "DateSelectorView",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun PreviewUIDateSelectorView() {
    UICAppTheme {
        CoreBackground {
            UICHorizontalDateSelectorView(
                title = "Month",
                dateItems = listOf(
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
                        iconVector = ImageVector.vectorResource(R.drawable.uic_ic_pen)
                    ),
                    UICDateItem(
                        day = "Wed",
                        date = "03",
                        amount = "$4.98",
                        dateAvailability = DateSlotAvailability.INACTIVE,
                        dateSlotStatus = DateSlotStatus.FREE,
                        iconVector = accountAvatar()
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
                        iconVector = settingsGear()
                    ),
                    UICDateItem(
                        day = "",
                        date = "",
                        amount = "",
                        dateAvailability = DateSlotAvailability.LOADING,
                        dateSlotStatus = DateSlotStatus.SOLD_OUT,
                        iconVector = ImageVector.vectorResource(R.drawable.uic_ic_old_turkey)
                    )
                ),
                modifier = Modifier,
                showDivider = true,
                onDateSelected = { }
            )
        }
    }
}

@Preview(
    name = "DateSelectorView",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun PreviewUIDateSelectorShimmer() {
    UICAppTheme {
        CoreBackground {
            UICHorizontalDateSelectorShimmer()
        }
    }
}