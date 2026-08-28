package com.heb.centralmarket.uicart.themesystem

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Author: Ritu Varma G
 * Date Created: 10-03-2025
 * Last Modified: 10-03-2025
 */
data object CoreRadiusDefaults {
    internal const val RADIUS_DEFAULT = 0
    internal const val RADIUS_ESM = 2
    internal const val RADIUS_SM = 4
    internal const val RADIUS_MD = 8
    internal const val RADIUS_LG = 16
    internal const val RADIUS_FULL = 9999
}

data class CoreBorderRadius(
    val default: Dp,
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val full: Dp,
)

val LocalCoreBorderRadius =
    compositionLocalOf {
        CoreBorderRadius(
            default = CoreRadiusDefaults.RADIUS_DEFAULT.dp,
            small = CoreRadiusDefaults.RADIUS_SM.dp,
            medium = CoreRadiusDefaults.RADIUS_MD.dp,
            large = CoreRadiusDefaults.RADIUS_LG.dp,
            full = CoreRadiusDefaults.RADIUS_FULL.dp,
        )
    }

val coreBorderRadius =
    CoreBorderRadius(
        default = CoreRadiusDefaults.RADIUS_DEFAULT.dp,
        small = CoreRadiusDefaults.RADIUS_SM.dp,
        medium = CoreRadiusDefaults.RADIUS_MD.dp,
        large = CoreRadiusDefaults.RADIUS_LG.dp,
        full = CoreRadiusDefaults.RADIUS_FULL.dp,
    )
