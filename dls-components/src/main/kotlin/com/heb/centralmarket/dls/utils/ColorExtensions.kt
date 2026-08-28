package com.heb.centralmarket.uicart.utils

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt

/**
 * Safely parses a hex color string (e.g. "#FF5733") into a [Color].
 * Returns null if the string is not a valid color.
 */
fun String.parseHexColor(): Color =
    try {
        Color(this.trim().toColorInt())
    } catch (_: Exception) {
        Color.White
    }
