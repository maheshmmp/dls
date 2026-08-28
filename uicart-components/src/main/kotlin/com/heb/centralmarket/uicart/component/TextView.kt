/*
 *
 *  Created by Mahesh Paul on 2/17/26, 10:33 AM
 *  Copyright (c) 2026 . All rights reserved.
 *  Last modified 2/17/26, 9:57 AM
 *
 */

package com.heb.centralmarket.uicart.component

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography
import kotlin.math.pow

const val MAX_POWER = 1.3f
const val MIN_POWER = 0f

@Composable
private fun CoreTextView(
    text: String,
    typographyStyle: TextStyle,
    modifier: Modifier = Modifier,
    color: Color = UICTheme.colorScheme.txt.primary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    onClick: (() -> Unit)? = null,
    textAlign: TextAlign? = TextAlign.Start,
) {
    Text(
        text = text,
        style = typographyStyle,
        color = color,
        modifier = if (onClick != null) modifier.clickable { onClick() } else modifier,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
    )
}

@Composable
fun CoreHeadingTextView(
    text: String,
    headingLevel: Int,
    modifier: Modifier = Modifier,
    color: Color = UICTheme.colorScheme.txt.primary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign? = TextAlign.Start,
    isBold: Boolean? = null,
) {
    var typography =
        when (headingLevel) {
            1 -> UICTypography.typography.heading.h1
            2 -> UICTypography.typography.heading.h2
            3 -> UICTypography.typography.heading.h3
            4 -> UICTypography.typography.heading.h4
            5 -> UICTypography.typography.heading.h5
            else -> UICTypography.typography.heading.h6
        }

    if (isBold != null) {
        typography = typography.copy(
            fontWeight = if (isBold) FontWeight.Black else FontWeight.Normal
        )
    }

    CoreTextView(
        text = text,
        typographyStyle = typography,
        modifier = modifier,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
    )
}

@Composable
fun CoreBodyTextView(
    text: String,
    modifier: Modifier = Modifier,
    bodyVariant: Int = 1,
    maxLines: Int = Int.MAX_VALUE,
    color: Color = UICTheme.colorScheme.txt.primary,
    isBold: Boolean = false,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign? = TextAlign.Start,
) {
    var typography =
        when (bodyVariant) {
            1 -> UICTypography.typography.body.body1.regular
            else -> UICTypography.typography.body.body2.regular
        }

    if (isBold) {
        typography = typography.copy(fontWeight = FontWeight.Bold)
    }

    CoreTextView(
        text = text,
        typographyStyle = typography,
        modifier = modifier,
        color = color,
        overflow = overflow,
        textAlign = textAlign,
        maxLines = maxLines,
    )
}

@Composable
fun CoreTitleTextView(
    text: String,
    modifier: Modifier = Modifier,
    titleVariant: Int = 1,
    color: Color = UICTheme.colorScheme.txt.primary,
    textAlign: TextAlign? = TextAlign.Start,
) {
    val typography =
        when (titleVariant) {
            1 -> UICTypography.typography.title.t1
            else -> UICTypography.typography.title.t2
        }

    CoreTextView(
        text = text,
        typographyStyle = typography,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
    )
}

@Composable
fun CoreSubtitleTextView(
    text: String,
    modifier: Modifier = Modifier,
    isBold: Boolean = false,
    isStrikeThrough: Boolean = false,
    color: Color = UICTheme.colorScheme.txt.primary,
    textAlign: TextAlign? = TextAlign.Start,
) {
    val typography =
        UICTypography.typography.subtitle.subTitle.let {
            when {
                isBold && isStrikeThrough -> it.boldStrikeThrough
                isBold -> it.bold
                isStrikeThrough -> it.strikeThrough
                else -> it.regular
            }
        }

    CoreTextView(
        text = text,
        typographyStyle = typography,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
    )
}

@Composable
fun CoreCaptionTextView(
    text: String,
    modifier: Modifier = Modifier,
    isBold: Boolean = false,
    isStrikeThrough: Boolean = false,
    color: Color = UICTheme.colorScheme.txt.primary,
    textAlign: TextAlign? = TextAlign.Start,
) {
    val typography =
        UICTypography.typography.caption.let {
            when {
                isBold && isStrikeThrough -> it.boldStrikeThrough
                isBold -> it.bold
                isStrikeThrough -> it.strikeThrough
                else -> it.regular
            }
        }

    CoreTextView(
        text = text,
        typographyStyle = typography,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
    )
}

@Composable
fun CoreFootnoteTextView(
    text: String,
    modifier: Modifier = Modifier,
    isBold: Boolean = false,
    color: Color = UICTheme.colorScheme.txt.primary,
    textAlign: TextAlign? = TextAlign.Start,
) {
    val typography =
        if (isBold) {
            UICTypography.typography.footnote.footnote.bold
        } else {
            UICTypography.typography.footnote.footnote.regular
        }

    CoreTextView(
        text = text,
        typographyStyle = typography,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
    )
}

object BodyVariant {
    const val BODY_1 = 1
    const val BODY_2 = 2
}

object HeadingVariant {
    const val HEADING_1 = 1
    const val HEADING_2 = 2
    const val HEADING_3 = 3
    const val HEADING_4 = 4
    const val HEADING_5 = 5

    @Suppress("unused")
    const val HEADING_6 = 6
}

object TitleVariant {
    const val TITLE_1 = 1
    const val TITLE_2 = 2
}

@Preview(showBackground = true)
@Composable
fun PreviewCoreHeadingTextView() {
    CoreHeadingTextView(
        text = "Heading Text H1",
        headingLevel = HeadingVariant.HEADING_1,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCoreBodyTextView() {
    CoreBodyTextView(
        text = "Body Text B1",
        bodyVariant = BodyVariant.BODY_1,
        textAlign = TextAlign.Start,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCoreTitleTextView() {
    CoreTitleTextView(
        text = "Title Text T1",
        titleVariant = TitleVariant.TITLE_1,
        textAlign = TextAlign.Start,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCoreSubtitleTextViewRegular() {
    CoreSubtitleTextView(
        text = "Subtitle Regular",
        isBold = false,
        isStrikeThrough = false,
        textAlign = TextAlign.Start,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCoreSubtitleTextViewBold() {
    CoreSubtitleTextView(
        text = "Subtitle Bold",
        isBold = true,
        isStrikeThrough = false,
        textAlign = TextAlign.Start,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCoreSubtitleTextViewStrikeThrough() {
    CoreSubtitleTextView(
        text = "Subtitle StrikeThrough",
        isBold = false,
        isStrikeThrough = true,
        textAlign = TextAlign.Start,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCoreCaptionTextViewRegular() {
    CoreCaptionTextView(
        text = "Caption Regular",
        isBold = false,
        isStrikeThrough = false,
        textAlign = TextAlign.Start,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCoreCaptionTextViewBold() {
    CoreCaptionTextView(
        text = "Caption Bold",
        isBold = true,
        isStrikeThrough = false,
        textAlign = TextAlign.Start,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCoreCaptionTextViewStrikeThrough() {
    CoreCaptionTextView(
        text = "Caption StrikeThrough",
        isBold = false,
        isStrikeThrough = true,
        textAlign = TextAlign.Start,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCoreFootnoteTextViewRegular() {
    CoreFootnoteTextView(
        text = "Footnote Regular",
        isBold = false,
        textAlign = TextAlign.Start,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCoreFootnoteTextViewBold() {
    CoreFootnoteTextView(
        text = "Footnote Bold",
        isBold = true,
        textAlign = TextAlign.Start,
    )
}

@Suppress("unused")
@Composable
fun getFontScale(): Float = LocalDensity.current.fontScale

/**
 * Adjusts the font size based on the font scale and font boundaries.
 * The adjustment is applied only for SP fonts and uses a dynamic scaling multiplier.
 *
 * @param fontScale The scaling factor to apply to the font size (e.g., 1.0 for no scaling).
 * @param maxFont The font size at which the scaling effect is maximized.
 * @param minFont The font size below which scaling is not applied.
 * @return The adjusted font size based on the font scale and boundaries.
 */
@Suppress("unused")
fun TextUnit.adjustFontSize(
    fontScale: Float,
    maxFont: Float,
    minFont: Float,
): TextUnit = this * fontScaleAdjustMultiplier(fontScale, maxFont, minFont)

/**
 * Returns the multiplier for adjusting the font size based on the font scale and size limits.
 * A power curve is applied for larger fonts, and linear scaling is used for medium-sized fonts.
 *
 * @param fontScale The scaling factor to apply to the font size.
 * @param maxFont The font size at which the scaling effect is maximized.
 * @param minFont The font size below which scaling is not applied.
 * @return The multiplier that should be used to adjust the font size.
 */
fun TextUnit.fontScaleAdjustMultiplier(
    fontScale: Float,
    maxFont: Float,
    minFont: Float,
): Float {
    if (this.isSp) { // Only scale SP fonts
        // If the font scale is less than or equal to 1, no scaling is applied.
        if (fontScale <= 1f) return 1f

        return when {
            // If the font size is greater than or equal to the maximum font size, apply the max scaling effect.
            value >= maxFont -> {
                1 / fontScale.pow(MAX_POWER)
            }
            // For font sizes within the scaling range, use a linear slope adjustment.
            value >= minFont -> {
                val slope = (MIN_POWER - MAX_POWER) / (minFont - maxFont)
                val result = MAX_POWER + (value - maxFont) * slope
                1 / fontScale.pow(result)
            }
            // If the font is smaller than the minimum threshold, no scaling is applied.
            else -> {
                1f
            }
        }
    }
    return 1f // No scaling for non-SP fonts
}
