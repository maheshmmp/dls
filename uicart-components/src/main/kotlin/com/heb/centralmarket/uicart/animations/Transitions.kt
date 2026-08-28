/*
 *
 *  Created by Mahesh Paul on 3/6/26, 3:43 PM
 *  Copyright (c) 2026 . All rights reserved.
 *  Last modified 3/6/26, 3:37 PM
 *
 */

package com.heb.centralmarket.uicart.animations

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

private const val ANIMATION_DURATION = 500

fun slideInFromRight(): EnterTransition {
    return slideInHorizontally(
        animationSpec = tween(ANIMATION_DURATION),
        initialOffsetX = { fullWidth -> fullWidth }
    )
}

fun slideInFromLeft(): EnterTransition {
    return slideInHorizontally(
        animationSpec = tween(ANIMATION_DURATION),
        initialOffsetX = { fullWidth -> -fullWidth }
    )
}

fun slideOutToRight(): ExitTransition {
    return slideOutHorizontally(
        animationSpec = tween(ANIMATION_DURATION),
        targetOffsetX = { fullWidth -> fullWidth }
    )
}

fun slideOutToLeft(): ExitTransition {
    return slideOutHorizontally(
        animationSpec = tween(ANIMATION_DURATION),
        targetOffsetX = { fullWidth -> -fullWidth }
    )
}

/**
 * A "hold in place" transition that keeps the screen visible and stationary
 * while another screen animates over it. Uses a very subtle fade to maintain
 * the animation duration without visual movement.
 */
fun holdInPlace(): ExitTransition {
    return fadeOut(
        animationSpec = tween(durationMillis = ANIMATION_DURATION),
        targetAlpha = 0.99f
    )
}

/**
 * A "hold in place" enter transition for when returning to a screen
 * that was held in place during exit.
 */
fun holdInPlaceEnter(): EnterTransition {
    return fadeIn(
        animationSpec = tween(durationMillis = ANIMATION_DURATION),
        initialAlpha = 0.99f
    )
}
