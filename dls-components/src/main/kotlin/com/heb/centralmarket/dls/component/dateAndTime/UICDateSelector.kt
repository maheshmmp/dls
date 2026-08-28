/*
 *
 *  Created by Mahesh Paul on 1/16/26, 10:02 AM
 *  Copyright (c) 2026 . All rights reserved.
 *  Last modified 1/16/26, 9:42 AM
 *
 */

package com.heb.centralmarket.uicart.component.dateAndTime

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.CoreCaptionTextView
import com.heb.centralmarket.uicart.component.CoreFootnoteTextView
import com.heb.centralmarket.uicart.component.CoreSubtitleTextView
import com.heb.centralmarket.uicart.component.buttons.DisableRippleEffect
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.icons.accountAvatar
import com.heb.centralmarket.uicart.shimmer
import com.heb.centralmarket.uicart.themesystem.ColorTokens
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICShape
import com.heb.centralmarket.uicart.themesystem.UICSize
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.utils.TestTags
import com.heb.centralmarket.uicart.utils.toTitleCase

/**
 * Displays a selectable date item with support for focus, hover, press states and an optional badge.
 *
 * This composable represents a single date slot in a date selector component.
 * It supports:
 * - Visual states based on availability and slot status
 * - Keyboard and pointer focus handling
 * - Hover and press interaction overlays
 * - Optional badge using Material3 [BadgedBox]
 *
 * The badge is rendered outside the clipped content area to avoid visual overlap
 * and corner clipping issues.
 *
 * @param dateItem Data model representing the date, availability and slot status.
 * @param modifier Modifier applied to the root container.
 * @param onClick Optional callback invoked when the date item is clicked.
 * @param showBadge Whether a badge should be shown on the top-right corner.
 */

@Composable
fun DateSelectorItem(
    dateItem: UICDateItem,
    modifier: Modifier,
    onClick: ((UICDateItem) -> Unit)? = null,
    showBadge: Boolean = false
){
    val colorTokens = UICTheme.colorScheme
    val colorMapper = remember(key1 = UICTheme.colorScheme) {
        DateSelectorItemColorMapper(colorTokens = colorTokens)
    }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val dateItemColors =
        colorMapper.mapColor(
            baseState = dateItem.dateAvailability,
            slotState = dateItem.dateSlotStatus,
        )

    DisableRippleEffect {
        BadgedBox(
            modifier =
                modifier,
            badge = {
                if (showBadge && dateItem.dateAvailability != DateSlotAvailability.LOADING) {
                    Badge(
                        modifier =
                            Modifier
                                .offset(
                                    x = -(DateSelectorItemDefaults.BADGE_OFFSET),
                                    y = DateSelectorItemDefaults.BADGE_OFFSET
                                )
                                .size(
                                    size = UICSize.size.s20
                                ),
                        containerColor = UICTheme.colorScheme.neutral.white
                    ) {
                        when {
                            dateItem.iconPainter != null -> {
                                Image(
                                    painter = dateItem.iconPainter,
                                    contentDescription = stringResource(R.string.date_selector_content_description),
                                    modifier = Modifier.size(size = UICSize.size.s12),
                                    contentScale = ContentScale.Fit
                                )
                            }

                            dateItem.iconVector != null -> {
                                Icon(
                                    imageVector = dateItem.iconVector,
                                    modifier = Modifier.size(size = UICSize.size.s12),
                                    contentDescription = stringResource(R.string.date_selector_content_description),
                                    tint = Color.Unspecified
                                )
                            }
                        }
                    }
                }
            },
            content = {
                FocusOutlineBox(
                    modifier = modifier,
                    isFocused = isFocused,
                    focusColor = dateItemColors.focusStateColor,
                    content = {
                        DateSelectorCard(
                            modifier = modifier,
                            dateItem = dateItem,
                            dateItemColors = dateItemColors,
                            interactionSource = interactionSource,
                            isPressed = isPressed,
                            isHovered = isHovered,
                            onClick = onClick
                        )
                    }
                )
            }
        )
    }
}

/**
 * A wrapper container that draws a focus outline around its content.
 *
 * This composable is used to visually indicate keyboard or accessibility focus
 * without clipping or interfering with the inner content layout.
 *
 * The outline is only visible when [isFocused] is true.
 *
 * @param isFocused Whether the focus outline should be visible.
 * @param focusColor Color of the focus outline.
 * @param modifier Modifier applied to the outline container.
 * @param content Content to be rendered inside the focus outline.
 */
@Composable
private fun FocusOutlineBox(
    isFocused: Boolean,
    focusColor: Color,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier =
            modifier
                .size(
                    width = DateSelectorItemDefaults.FOCUSED_BOX_MIN_WIDTH,
                    height = DateSelectorItemDefaults.FOCUSED_BOX_MIN_HEIGHT
                )
                .border(
                    width = DateSelectorItemDefaults.BUTTON_BORDER_WIDTH,
                    color = if (isFocused) focusColor else Color.Transparent,
                    shape = RoundedCornerShape(size = UICSpacing.spacing.spacing075)
                ),
        contentAlignment = Alignment.Center,
        content = content
    )
}

/**
 * The main interactive card representing the date content.
 *
 * This composable handles:
 * - Visual styling (background, border, shape)
 * - Hover, focus and click interactions
 * - Press and hover overlay rendering
 * - Display of day, date and slot pill
 *
 * It is clipped independently so that overlays and badges can be layered safely
 * without visual artifacts.
 *
 * @param modifier Modifier applied to the card container.
 * @param dateItem Data model representing the date information.
 * @param dateItemColors Color scheme mapped to the current state.
 * @param interactionSource Shared interaction source for press, hover and focus.
 * @param isPressed Whether the card is currently pressed.
 * @param isHovered Whether the card is currently hovered.
 * @param onClick Optional callback invoked when the card is clicked.
 */
@Composable
private fun DateSelectorCard(
    modifier: Modifier,
    dateItem: UICDateItem,
    dateItemColors: UICDateItemColorScheme,
    interactionSource: MutableInteractionSource,
    isPressed: Boolean,
    isHovered: Boolean,
    onClick: ((UICDateItem) -> Unit)?
) {
    Box(
        modifier =
            modifier
                .widthIn(min = UICSize.size.s60)
                .heightIn(min = UICSize.size.s63)
                .clip(shape = UICShape.shapes.mediumRoundCornerShape)
                .border(
                    width = DateSelectorItemDefaults.BUTTON_BORDER_WIDTH_DEFAULT,
                    color = dateItemColors.borderColor,
                    shape = UICShape.shapes.mediumRoundCornerShape
                )
                .hoverable(interactionSource = interactionSource)
                .focusable(interactionSource = interactionSource)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        onClick?.invoke(dateItem)
                    }
                )
                .then(
                    other = if (dateItem.dateAvailability == DateSlotAvailability.LOADING) {
                        modifier.shimmer()
                    } else {
                        modifier
                    }
                ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier =
                if (dateItem.dateAvailability != DateSlotAvailability.LOADING) {
                    Modifier
                        .background(dateItemColors.containerColor)
                        .clip(UICShape.shapes.mediumRoundCornerShape)
                } else
                    Modifier.clip(UICShape.shapes.mediumRoundCornerShape)
        ) {
            Column(
                modifier =
                    modifier
                        .padding(all = UICSpacing.spacing.spacing025),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CoreCaptionTextView(
                    text = dateItem.day,
                    isBold = true,
                    color = dateItemColors.dayTextColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.testTag(TestTags.TimeSlot.DATE_DAY)
                )
                CoreSubtitleTextView(
                    text = dateItem.date,
                    isBold = true,
                    color = dateItemColors.dateTextColor,
                    textAlign = TextAlign.Center
                )
                if (dateItem.dateAvailability != DateSlotAvailability.LOADING) {
                    DateSelectorPill(
                        modifier = modifier,
                        dateItem = dateItem,
                        dateItemColors = dateItemColors
                    )
                }
            }
            InteractionOverlay(
                isPressed = isPressed,
                isHovered = isHovered,
                pressColor = dateItemColors.pressStateColor.copy(alpha = 0.12f),
                hoverColor = dateItemColors.hoverStateColor.copy(alpha = 0.08f),
            )
        }
    }
}

/**
 * Displays the pill indicator for the date slot.
 *
 * The pill text and colors are derived from the slot status and availability.
 * Common use cases include showing:
 * - FREE
 * - SOLD OUT
 * - Custom amount text
 *
 * @param modifier Modifier applied to the pill container.
 * @param dateItem Data model representing the slot state.
 * @param dateItemColors Color scheme used to style the pill.
 */
@Composable
private fun DateSelectorPill(
    modifier: Modifier,
    dateItem: UICDateItem,
    dateItemColors: UICDateItemColorScheme
) {
    val pillText =
        when (dateItem.dateSlotStatus) {
            DateSlotStatus.FREE ->
                stringResource(R.string.date_selector_slot_free).toTitleCase()

            DateSlotStatus.SOLD_OUT ->
                stringResource(R.string.date_selector_slot_sold_out).toTitleCase()

            else -> dateItem.amount
        }

    Box(
        modifier =
            modifier
                .clip(
                    shape = RoundedCornerShape(
                        size = DateSelectorItemDefaults.PILL_CORNER_RADIUS_DEFAULT
                    )
                )
                .widthIn(min = DateSelectorItemDefaults.PILL_MIN_WIDTH)
                .height(height = UICSize.size.s15)
                .background(
                    color = dateItemColors.pillBgColor
                ),
        contentAlignment = Alignment.Center
    ) {
        CoreFootnoteTextView(
            text = pillText,
            isBold = true,
            color = dateItemColors.pillTextColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.testTag(TestTags.TimeSlot.DATE_PRICE)
        )
    }
}

/**
 * Draws a visual overlay on top of its parent to indicate hover or press states.
 *
 * This overlay is rendered above the content but within the card bounds,
 * respecting border insets to avoid covering outlines.
 *
 * @param isPressed Whether the parent component is pressed.
 * @param isHovered Whether the parent component is hovered.
 * @param pressColor Color used for the pressed state overlay.
 * @param hoverColor Color used for the hovered state overlay.
 */
@Composable
fun BoxScope.InteractionOverlay(
    isPressed: Boolean,
    isHovered: Boolean,
    pressColor: Color,
    hoverColor: Color,
) {
    val overlayColor =
        when {
            isPressed -> pressColor
            isHovered -> hoverColor
            else -> Color.Transparent
        }

    if (overlayColor != Color.Transparent) {
        Box(
            modifier =
                Modifier
                    .matchParentSize()
                    .padding(DateSelectorItemDefaults.BUTTON_BORDER_WIDTH_DEFAULT)
                    .background(overlayColor)

        )
    }
}

// -------------------------------
// Enums + Data models
// -------------------------------

enum class DateSlotAvailability {
    ACTIVE,
    INACTIVE,
    LOADING
}
enum class DateSlotStatus {
    NORMAL,
    FREE,
    SOLD_OUT
}
/**
 * Represents the resolved color scheme for a date selector item
 * across all interaction states.
 */
data class UICDateItem(
    val day: String,
    val date: String,
    val amount: String,
    val dateAvailability: DateSlotAvailability = DateSlotAvailability.INACTIVE,
    val dateSlotStatus: DateSlotStatus = DateSlotStatus.NORMAL,
    val iconVector: ImageVector? = null,
    val iconPainter: Painter? = null,
    val showTimeSlotDecoration: Boolean = false,
)

data class UICDateItemColorScheme(
    val containerColor: Color,
    val borderColor: Color,
    val dayTextColor: Color,
    val dateTextColor: Color,
    val pillBgColor: Color,
    val pillTextColor: Color,
    val pressStateColor: Color,
    val hoverStateColor: Color,
    val focusStateColor: Color,
)

/**
 * Maps availability and slot status to a resolved color scheme
 * used by [DateSelectorItem].
 */
class DateSelectorItemColorMapper(
    private val colorTokens: ColorTokens
){
    fun mapColor(
        baseState: DateSlotAvailability,
        slotState: DateSlotStatus,
    ): UICDateItemColorScheme{
        when(baseState){
            DateSlotAvailability.LOADING -> {
               return UICDateItemColorScheme(
                   containerColor = colorTokens.structural.bgSecondary,
                   borderColor = colorTokens.structural.outlineLight,
                   dayTextColor = colorTokens.txt.secondary,
                   dateTextColor = colorTokens.txt.secondary,
                   pillBgColor = colorTokens.txt.secondary,
                   pillTextColor = colorTokens.txt.secondary,
                   pressStateColor = colorTokens.txt.secondary,
                   hoverStateColor = colorTokens.txt.secondary,
                   focusStateColor = colorTokens.txt.secondary
               )
            }
            DateSlotAvailability.ACTIVE -> {
                when(slotState){
                    DateSlotStatus.NORMAL ->
                        return UICDateItemColorScheme(
                            containerColor = colorTokens.brand.buttonPrimaryTonal.core,
                            borderColor = colorTokens.brand.buttonPrimaryTonal.border,
                            dayTextColor = colorTokens.brand.buttonPrimaryTonal.onCore,
                            dateTextColor = colorTokens.brand.buttonPrimaryTonal.onCore,
                            pillBgColor = colorTokens.brand.buttonPrimary.core,
                            pillTextColor = colorTokens.brand.buttonPrimary.onCore,
                            pressStateColor = colorTokens.state.buttonPrimaryTonal.press,
                            hoverStateColor = colorTokens.state.buttonPrimaryTonal.hover,
                            focusStateColor = colorTokens.brand.buttonPrimaryTonal.focus
                        )
                    DateSlotStatus.FREE ->
                        return UICDateItemColorScheme(
                            containerColor = colorTokens.brand.buttonPrimaryTonal.core,
                            borderColor = colorTokens.brand.buttonPrimaryTonal.border,
                            dayTextColor = colorTokens.brand.buttonPrimaryTonal.onCore,
                            dateTextColor = colorTokens.brand.buttonPrimaryTonal.onCore,
                            pillBgColor = colorTokens.brand.buttonPrimary.core,
                            pillTextColor = colorTokens.brand.buttonPrimary.onCore,
                            pressStateColor = colorTokens.state.buttonPrimaryTonal.press,
                            hoverStateColor = colorTokens.state.buttonPrimaryTonal.hover,
                            focusStateColor = colorTokens.brand.buttonPrimaryTonal.focus
                    )
                    DateSlotStatus.SOLD_OUT ->
                        return UICDateItemColorScheme(
                            containerColor = colorTokens.brand.buttonPrimaryTonal.core,
                            borderColor = colorTokens.brand.buttonPrimaryTonal.border,
                            dayTextColor = colorTokens.brand.buttonPrimaryTonal.onCore,
                            dateTextColor = colorTokens.brand.buttonPrimaryTonal.onCore,
                            pillBgColor = colorTokens.structural.outlineDark,
                            pillTextColor = colorTokens.txt.primaryInverse,
                            pressStateColor = colorTokens.state.buttonPrimaryTonal.press,
                            hoverStateColor = colorTokens.state.buttonPrimaryTonal.hover,
                            focusStateColor = colorTokens.brand.buttonPrimaryTonal.focus
                        )
                }
            }
            DateSlotAvailability.INACTIVE -> {
                when(slotState){
                    DateSlotStatus.NORMAL ->
                        return UICDateItemColorScheme(
                            containerColor = colorTokens.structural.bgPrimary,
                            borderColor = colorTokens.structural.outlineLight,
                            dayTextColor = colorTokens.txt.primary,
                            dateTextColor = colorTokens.txt.primary,
                            pillBgColor = colorTokens.brand.buttonPrimaryTonal.core,
                            pillTextColor = colorTokens.brand.buttonPrimaryTonal.onCore,
                            pressStateColor = colorTokens.state.interactiveNeutral.press,
                            hoverStateColor = colorTokens.state.interactiveNeutral.hover,
                            focusStateColor = colorTokens.structural.outlineDark
                        )
                    DateSlotStatus.FREE ->
                        return UICDateItemColorScheme(
                            containerColor = colorTokens.structural.bgPrimary,
                            borderColor = colorTokens.structural.outlineLight,
                            dayTextColor = colorTokens.txt.primary,
                            dateTextColor = colorTokens.txt.primary,
                            pillBgColor = colorTokens.brand.buttonPrimary.core,
                            pillTextColor = colorTokens.brand.buttonPrimary.onCore,
                            pressStateColor = colorTokens.state.interactiveNeutral.press,
                            hoverStateColor = colorTokens.state.interactiveNeutral.hover,
                            focusStateColor = colorTokens.structural.outlineDark
                        )
                    DateSlotStatus.SOLD_OUT ->
                        return UICDateItemColorScheme(
                            containerColor = colorTokens.structural.bgSecondary,
                            borderColor = colorTokens.structural.outlineLight,
                            dayTextColor = colorTokens.brand.interactiveNeutral.onCore,
                            dateTextColor = colorTokens.brand.interactiveNeutral.onCore,
                            pillBgColor = colorTokens.structural.outlineDark,
                            pillTextColor = colorTokens.txt.primaryInverse,
                            pressStateColor = colorTokens.state.interactiveNeutral.press,
                            hoverStateColor = colorTokens.state.interactiveNeutral.hover,
                            focusStateColor = colorTokens.structural.outlineDark
                        )
                }
            }
        }
    }
}
private object DateSelectorItemDefaults {
    val BUTTON_BORDER_WIDTH = 2.dp
    val FOCUSED_BOX_MIN_HEIGHT = 69.dp
    val FOCUSED_BOX_MIN_WIDTH = 66.dp
    val BUTTON_BORDER_WIDTH_DEFAULT = 1.dp
    val PILL_CORNER_RADIUS_DEFAULT = 54.dp
    val PILL_MIN_WIDTH = 52.dp
    val BADGE_OFFSET = 4.dp
}

@Preview(
    name = "DateSelectorItem",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun PreviewUIDateSelectorItem() {
    UICAppTheme {
        CoreBackground {
            DateSelectorItem(
                dateItem = UICDateItem(
                    day = "Mon",
                    date = "01",
                    amount = "$4.98",
                    dateAvailability = DateSlotAvailability.ACTIVE,
                    dateSlotStatus = DateSlotStatus.FREE,
                    iconVector = accountAvatar()
                ),
                modifier = Modifier,
                onClick = { },
                showBadge = true
            )
        }
    }
}