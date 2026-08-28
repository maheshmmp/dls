package com.heb.centralmarket.uicart.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier

fun Modifier.clickableWithoutRipple(
    interactionSource: MutableInteractionSource,
    enabled: Boolean = true,
    onClick: () -> Unit,
): Modifier {
    return this.then(
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = null, // no ripple
            enabled = enabled,
            onClick = onClick
        )
    )
}
