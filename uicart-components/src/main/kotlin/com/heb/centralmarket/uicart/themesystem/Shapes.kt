/*
 * Created by Mahesh Mathew Paul on 12/12/24, 10:09 am
 * mahesh.paul@ust.com
 * Last modified 11/12/24, 10:09 am
 * Copyright (c) 2024.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.themesystem

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Immutable
data class Shape(
    val extraSmallRectangleShape: Shape,
    val smallRoundCornerShape: Shape,
    val mediumRoundCornerShape: Shape,
    val largeRoundCornerShape: Shape,
    val fullRoundCornerShape: Shape,
)

val LocalShape =
    staticCompositionLocalOf {
        Shape(
            extraSmallRectangleShape = RectangleShape,
            smallRoundCornerShape = CircleShape,
            mediumRoundCornerShape = RectangleShape,
            largeRoundCornerShape = RectangleShape,
            fullRoundCornerShape = RectangleShape,
        )
    }

val shape =
    Shape(
        extraSmallRectangleShape = RoundedCornerShape(CoreRadiusDefaults.RADIUS_ESM.dp),
        smallRoundCornerShape = RoundedCornerShape(CoreRadiusDefaults.RADIUS_SM.dp),
        mediumRoundCornerShape = RoundedCornerShape(CoreRadiusDefaults.RADIUS_MD.dp),
        largeRoundCornerShape = RoundedCornerShape(CoreRadiusDefaults.RADIUS_LG.dp),
        fullRoundCornerShape = RoundedCornerShape(CoreRadiusDefaults.RADIUS_FULL.dp),
    )
