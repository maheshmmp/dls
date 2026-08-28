package com.heb.centralmarket.uicart.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import com.heb.centralmarket.uicart.component.BodyVariant
import com.heb.centralmarket.uicart.themesystem.UICTypography

@Composable
fun Int.toTextStyle(): TextStyle {
    return when (this) {
        BodyVariant.BODY_1 -> UICTypography.typography.body.body1.regular
        BodyVariant.BODY_2 -> UICTypography.typography.body.body2.regular
        else -> UICTypography.typography.body.body1.regular
    }
}

@Composable
fun TextStyle.toSpanStyle(): SpanStyle = SpanStyle(
    color = this.color,
    fontSize = this.fontSize,
    fontWeight = this.fontWeight,
    fontStyle = this.fontStyle,
    fontFamily = this.fontFamily,
    letterSpacing = this.letterSpacing,
    baselineShift = this.baselineShift
)
fun String.toTitleCase(): String =
    split(" ", "_", "-")
        .filter { it.isNotBlank() }
        .joinToString(" ") {
            it.lowercase().replaceFirstChar { ch -> ch.uppercase() }
        }

