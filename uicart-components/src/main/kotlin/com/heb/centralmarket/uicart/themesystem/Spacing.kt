/*
 * Created by Mahesh Mathew Paul on 12/12/24, 12:01 pm
 * mahesh.paul@ust.com
 * Last modified 12/12/24, 12:01 pm
 * Copyright (c) 2024.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.themesystem

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Specifies amount of spacing that should be used through the application in a non-graphic
 * library specific amount.
 */
data object SpacingDefaults {
    internal const val SPACING_000 = 0
    internal const val SPACING_025 = 4
    internal const val SPACING_050 = 8
    internal const val SPACING_075 = 12
    internal const val SPACING_100 = 16
    internal const val SPACING_125 = 20
    internal const val SPACING_150 = 24
    internal const val SPACING_200 = 32
    internal const val SPACING_250 = 48
    internal const val SPACING_300 = 64
    internal const val DEFAULT = SPACING_025
}

data class Spacing(
    val default: Dp,
    val spacing000: Dp,
    val spacing025: Dp,
    val spacing050: Dp,
    val spacing075: Dp,
    val spacing100: Dp,
    val spacing125: Dp,
    val spacing150: Dp,
    val spacing200: Dp,
    val spacing250: Dp,
    val spacing300: Dp,
)

val LocalSpacing =
    compositionLocalOf {
        Spacing(
            default = SpacingDefaults.DEFAULT.dp,
            spacing000 = SpacingDefaults.DEFAULT.dp,
            spacing025 = SpacingDefaults.DEFAULT.dp,
            spacing050 = SpacingDefaults.DEFAULT.dp,
            spacing075 = SpacingDefaults.DEFAULT.dp,
            spacing100 = SpacingDefaults.DEFAULT.dp,
            spacing125 = SpacingDefaults.DEFAULT.dp,
            spacing150 = SpacingDefaults.DEFAULT.dp,
            spacing200 = SpacingDefaults.DEFAULT.dp,
            spacing250 = SpacingDefaults.DEFAULT.dp,
            spacing300 = SpacingDefaults.DEFAULT.dp,
        )
    }

val spacing =
    Spacing(
        default = SpacingDefaults.SPACING_025.dp,
        spacing000 = SpacingDefaults.SPACING_000.dp,
        spacing025 = SpacingDefaults.SPACING_025.dp,
        spacing050 = SpacingDefaults.SPACING_050.dp,
        spacing075 = SpacingDefaults.SPACING_075.dp,
        spacing100 = SpacingDefaults.SPACING_100.dp,
        spacing125 = SpacingDefaults.SPACING_125.dp,
        spacing150 = SpacingDefaults.SPACING_150.dp,
        spacing200 = SpacingDefaults.SPACING_200.dp,
        spacing250 = SpacingDefaults.SPACING_250.dp,
        spacing300 = SpacingDefaults.SPACING_300.dp,
    )
