/*
 * Created by Mahesh Mathew Paul on 12/12/24, 10:50 am
 * mahesh.paul@ust.com
 * Last modified 12/12/24, 10:10 am
 * Copyright (c) 2024.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.themesystem.background

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * A class to model background color and tonal elevation values
 */
@Immutable
data class TintTheme(
    val iconTint: Color = Color.Unspecified,
)

/**
 * A composition local for [TintTheme].
 */
val LocalTintTheme = staticCompositionLocalOf { TintTheme() }
