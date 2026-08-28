/*
 *
 *  Created by Mahesh Paul on 1/22/26, 8:32 PM
 *  Copyright (c) 2026 . All rights reserved.
 *  Last modified 1/22/26, 7:47 PM
 *
 */

package com.heb.centralmarket.uicart.themesystem

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Global size scale (2dp → 34dp in steps of 2).
 */
data object SizeDefaults {
    internal const val S2 = 2
    internal const val S4 = 4
    internal const val S6 = 6
    internal const val S8 = 8
    internal const val S10 = 10
    internal const val S12 = 12
    internal const val S14 = 14
    internal const val S15 = 15
    internal const val S16 = 16
    internal const val S18 = 18
    internal const val S20 = 20
    internal const val S22 = 22
    internal const val S24 = 24
    internal const val S26 = 26
    internal const val S28 = 28
    internal const val S30 = 30
    internal const val S32 = 32
    internal const val S34 = 34
    internal const val S60 = 60
    internal const val S63 = 63

    internal const val S64 = 64
}

data class Sizes(
    val s2: Dp,
    val s4: Dp,
    val s6: Dp,
    val s8: Dp,
    val s10: Dp,
    val s12: Dp,
    val s14: Dp,
    val s15: Dp,
    val s16: Dp,
    val s18: Dp,
    val s20: Dp,
    val s22: Dp,
    val s24: Dp,
    val s26: Dp,
    val s28: Dp,
    val s30: Dp,
    val s32: Dp,
    val s34: Dp,
    val s60: Dp,
    val s63: Dp,
    val s64: Dp,
)

val LocalSizes = compositionLocalOf {
    Sizes(
        s2 = SizeDefaults.S2.dp,
        s4 = SizeDefaults.S4.dp,
        s6 = SizeDefaults.S6.dp,
        s8 = SizeDefaults.S8.dp,
        s10 = SizeDefaults.S10.dp,
        s12 = SizeDefaults.S12.dp,
        s15 = SizeDefaults.S15.dp,
        s14 = SizeDefaults.S14.dp,
        s16 = SizeDefaults.S16.dp,
        s18 = SizeDefaults.S18.dp,
        s20 = SizeDefaults.S20.dp,
        s22 = SizeDefaults.S22.dp,
        s24 = SizeDefaults.S24.dp,
        s26 = SizeDefaults.S26.dp,
        s28 = SizeDefaults.S28.dp,
        s30 = SizeDefaults.S30.dp,
        s32 = SizeDefaults.S32.dp,
        s34 = SizeDefaults.S34.dp,
        s60 = SizeDefaults.S60.dp,
        s63 = SizeDefaults.S63.dp,
        s64 = SizeDefaults.S64.dp,
    )
}

val sizes =
    Sizes(
        s2 = SizeDefaults.S2.dp,
        s4 = SizeDefaults.S4.dp,
        s6 = SizeDefaults.S6.dp,
        s8 = SizeDefaults.S8.dp,
        s10 = SizeDefaults.S10.dp,
        s12 = SizeDefaults.S12.dp,
        s14 = SizeDefaults.S14.dp,
        s15 = SizeDefaults.S15.dp,
        s16 = SizeDefaults.S16.dp,
        s18 = SizeDefaults.S18.dp,
        s20 = SizeDefaults.S20.dp,
        s22 = SizeDefaults.S22.dp,
        s24 = SizeDefaults.S24.dp,
        s26 = SizeDefaults.S26.dp,
        s28 = SizeDefaults.S28.dp,
        s30 = SizeDefaults.S30.dp,
        s32 = SizeDefaults.S32.dp,
        s34 = SizeDefaults.S34.dp,
        s60 = SizeDefaults.S60.dp,
        s63 = SizeDefaults.S63.dp,
        s64 = SizeDefaults.S64.dp,
    )

