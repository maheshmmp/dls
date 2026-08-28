/*
 * Created by Mahesh Mathew Paul on 12/12/24, 12:01 pm
 * mahesh.paul@ust.com
 * Last modified 12/12/24, 12:01 pm
 * Copyright (c) 2024.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.themesystem

import android.icu.number.IntegerWidth
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Specifies amount of spacing that should be used through the application in a non-graphic
 * library specific amount.
 */
data object HeightDefaults {
    internal const val SMALL_APPBAR_HEIGHT = 56
    internal const val LARGE_APPBAR_HEIGHT = 64
    internal const val ACTION_BAR_HEIGHT = 56
    internal const val SECTION_HEIGHT = 65
    internal const val DEFAULT = LARGE_APPBAR_HEIGHT
    internal const val DEFAULT_DIVIDER_HEIGHT = 1
    internal const val DEFAULT_CIRCULAR_LOADING_HEIGHT = 100
    internal const val SHIMMER_ROW_ITEM_HEIGHT = 16
    internal const val DRAG_HANDLE_HEIGHT = 40
    internal const val TERTIARY_BUTTON_HEIGHT = 48
    internal const val IMAGE_HOLDER_HEIGHT = 200
    internal const val EMPTY_STATE_IMAGE_HOLDER_HEIGHT = 120
    internal const val SEARCH_BAR_HEIGHT = 44
    internal const val SHIMMER_TITLE_HEIGHT = 24
    internal const val SHIMMER_TITLE_WIDTH = 195
    internal const val FEEDBACK_MIN_HEIGHT = 42
    internal const val FULFILMENT_TOGGLE_CONTROL_HEIGHT = 42
    internal const val TIME_SELECTOR_MIN_HEIGHT = 68
}

data class Height(
    val default: Dp,
    val small: Dp,
    val large: Dp,
    val divider: Dp,
    val circularLoading: Dp,
    val actionBarHeight: Dp,
    val sectionDefaultHeight: Dp,
    val dividerHeight: Dp,
    val shimmerRowItemHeight: Dp,
    val dragHandle: Dp,
    val tertiaryButtonHeight: Dp,
    val imageHolderHeight: Dp,
    val emptyStateImageHolderHeight: Dp,
    val searchBarHeight: Dp,
    val feedbackMinHeight: Dp,
    val timeSelectorMinHeight: Dp,
    val fulfilmentToggleControlHeight: Dp,
    val shimmerTitleHeight: Dp,
    val shimmerTitleWidth: Dp
)

val LocalHeight =
    compositionLocalOf {
        Height(
            default = HeightDefaults.DEFAULT.dp,
            small = HeightDefaults.SMALL_APPBAR_HEIGHT.dp,
            large = HeightDefaults.LARGE_APPBAR_HEIGHT.dp,
            divider = HeightDefaults.DEFAULT_DIVIDER_HEIGHT.dp,
            circularLoading = HeightDefaults.DEFAULT_CIRCULAR_LOADING_HEIGHT.dp,
            actionBarHeight = HeightDefaults.LARGE_APPBAR_HEIGHT.dp,
            sectionDefaultHeight = HeightDefaults.SECTION_HEIGHT.dp,
            dividerHeight = HeightDefaults.DEFAULT_DIVIDER_HEIGHT.dp,
            shimmerRowItemHeight = HeightDefaults.SHIMMER_ROW_ITEM_HEIGHT.dp,
            dragHandle = HeightDefaults.DRAG_HANDLE_HEIGHT.dp,
            tertiaryButtonHeight = HeightDefaults.TERTIARY_BUTTON_HEIGHT.dp,
            imageHolderHeight = HeightDefaults.IMAGE_HOLDER_HEIGHT.dp,
            emptyStateImageHolderHeight = HeightDefaults.EMPTY_STATE_IMAGE_HOLDER_HEIGHT.dp,
            searchBarHeight = HeightDefaults.IMAGE_HOLDER_HEIGHT.dp,
            shimmerTitleHeight = HeightDefaults.SHIMMER_TITLE_HEIGHT.dp,
            shimmerTitleWidth = HeightDefaults.SHIMMER_TITLE_WIDTH.dp,
            feedbackMinHeight = HeightDefaults.FEEDBACK_MIN_HEIGHT.dp,
            timeSelectorMinHeight = HeightDefaults.TIME_SELECTOR_MIN_HEIGHT.dp,
            fulfilmentToggleControlHeight = HeightDefaults.FULFILMENT_TOGGLE_CONTROL_HEIGHT.dp,
        )
    }

val height =
    Height(
        default = HeightDefaults.LARGE_APPBAR_HEIGHT.dp,
        small = HeightDefaults.SMALL_APPBAR_HEIGHT.dp,
        large = HeightDefaults.LARGE_APPBAR_HEIGHT.dp,
        divider = HeightDefaults.DEFAULT_DIVIDER_HEIGHT.dp,
        circularLoading = HeightDefaults.DEFAULT_CIRCULAR_LOADING_HEIGHT.dp,
        actionBarHeight = HeightDefaults.ACTION_BAR_HEIGHT.dp,
        sectionDefaultHeight = HeightDefaults.SECTION_HEIGHT.dp,
        dividerHeight = HeightDefaults.DEFAULT_DIVIDER_HEIGHT.dp,
        shimmerRowItemHeight = HeightDefaults.SHIMMER_ROW_ITEM_HEIGHT.dp,
        dragHandle = HeightDefaults.DRAG_HANDLE_HEIGHT.dp,
        tertiaryButtonHeight = HeightDefaults.TERTIARY_BUTTON_HEIGHT.dp,
        imageHolderHeight = HeightDefaults.IMAGE_HOLDER_HEIGHT.dp,
        emptyStateImageHolderHeight = HeightDefaults.EMPTY_STATE_IMAGE_HOLDER_HEIGHT.dp,
        searchBarHeight = HeightDefaults.SEARCH_BAR_HEIGHT.dp,
        shimmerTitleHeight = HeightDefaults.SHIMMER_TITLE_HEIGHT.dp,
        shimmerTitleWidth = HeightDefaults.SHIMMER_TITLE_WIDTH.dp,
        feedbackMinHeight = HeightDefaults.FEEDBACK_MIN_HEIGHT.dp,
        fulfilmentToggleControlHeight = HeightDefaults.FULFILMENT_TOGGLE_CONTROL_HEIGHT.dp,
        timeSelectorMinHeight = HeightDefaults.TIME_SELECTOR_MIN_HEIGHT.dp,
    )
