/*
 * Created by Mahesh Mathew Paul on 12/12/24, 9:55 am
 * mahesh.paul@ust.com
 * Last modified 11/12/24, 7:56 pm
 * Copyright (c) 2024.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.themesystem

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.heb.centralmarket.uicart.components.R

val sourceSansProFamily =
    FontFamily(
        Font(R.font.source_sans_pro_regular, FontWeight.Normal),
        Font(R.font.source_sans_pro_bold, FontWeight.Bold),
        Font(R.font.source_sans_pro_black, FontWeight.Black),
    )

@Immutable
data class Typography(
    val heading: HeadingTextStyle,
    val title: TitleTextStyle,
    val subtitle: SubtitleTextStyle,
    val body: BodyTextStyle,
    val caption: TextStyleExtension,
    val footnote: FootnoteTextStyle,
)

@Immutable
data class HeadingTextStyle(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val h6: TextStyle,
)

@Immutable
data class TitleTextStyle(
    val t1: TextStyle,
    val t2: TextStyle,
)

@Immutable
data class SubtitleTextStyle(
    val subTitle: TextStyleExtension,
)

@Immutable
data class BodyTextStyle(
    val body1: TextStyleExtension,
    val body2: TextStyleExtension,
)

@Immutable
data class FootnoteTextStyle(
    val footnote: TextStyleBoldExtension,
)

@Immutable
data class TextStyleExtension(
    val regular: TextStyle,
    val strikeThrough: TextStyle,
    val bold: TextStyle,
    val boldStrikeThrough: TextStyle,
)

@Immutable
data class TextStyleBoldExtension(
    val regular: TextStyle,
    val bold: TextStyle,
)

val LocalTypography =
    staticCompositionLocalOf {
        Typography(
            heading =
                HeadingTextStyle(
                    h1 = TextStyle.Default,
                    h2 = TextStyle.Default,
                    h3 = TextStyle.Default,
                    h4 = TextStyle.Default,
                    h5 = TextStyle.Default,
                    h6 = TextStyle.Default,
                ),
            title =
                TitleTextStyle(
                    t1 = TextStyle.Default,
                    t2 = TextStyle.Default,
                ),
            subtitle =
                SubtitleTextStyle(
                    subTitle =
                        TextStyleExtension(
                            regular = TextStyle.Default,
                            strikeThrough = TextStyle.Default,
                            bold = TextStyle.Default,
                            boldStrikeThrough = TextStyle.Default,
                        ),
                ),
            body =
                BodyTextStyle(
                    body1 =
                        TextStyleExtension(
                            regular = TextStyle.Default,
                            strikeThrough = TextStyle.Default,
                            bold = TextStyle.Default,
                            boldStrikeThrough = TextStyle.Default,
                        ),
                    body2 =
                        TextStyleExtension(
                            regular = TextStyle.Default,
                            strikeThrough = TextStyle.Default,
                            bold = TextStyle.Default,
                            boldStrikeThrough = TextStyle.Default,
                        ),
                ),
            caption =
                TextStyleExtension(
                    regular = TextStyle.Default,
                    strikeThrough = TextStyle.Default,
                    bold = TextStyle.Default,
                    boldStrikeThrough = TextStyle.Default,
                ),
            footnote =
                FootnoteTextStyle(
                    footnote =
                        TextStyleBoldExtension(
                            regular = TextStyle.Default,
                            bold = TextStyle.Default,
                        ),
                ),
        )
    }

val typography =
    Typography(
        heading =
            HeadingTextStyle(
                h1 =
                    TextStyle(
                        fontFamily = sourceSansProFamily,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black,
                        lineHeight = 36.sp,
                        letterSpacing = 0.sp,
                    ),
                h2 =
                    TextStyle(
                        fontFamily = sourceSansProFamily,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black,
                        lineHeight = 28.sp,
                        letterSpacing = 0.sp,
                    ),
                h3 =
                    TextStyle(
                        fontFamily = sourceSansProFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black,
                        lineHeight = 24.sp,
                        letterSpacing = 0.sp,
                    ),
                h4 =
                    TextStyle(
                        fontFamily = sourceSansProFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black,
                        lineHeight = 20.sp,
                        letterSpacing = 0.sp,
                    ),
                h5 =
                    TextStyle(
                        fontFamily = sourceSansProFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        lineHeight = 18.sp,
                        letterSpacing = 0.sp,
                    ),
                h6 =
                    TextStyle(
                        fontFamily = sourceSansProFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        lineHeight = 16.sp,
                        letterSpacing = 0.sp,
                    ),
            ),
        title =
            TitleTextStyle(
                t1 =
                    TextStyle(
                        fontFamily = sourceSansProFamily,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 36.sp,
                        letterSpacing = 0.sp,
                    ),
                t2 =
                    TextStyle(
                        fontFamily = sourceSansProFamily,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 28.sp,
                        letterSpacing = 0.sp,
                    ),
            ),
        subtitle =
            SubtitleTextStyle(
                subTitle =
                    TextStyleExtension(
                        regular =
                            TextStyle(
                                fontFamily = sourceSansProFamily,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 24.sp,
                                letterSpacing = 0.sp,
                            ),
                        strikeThrough =
                            TextStyle(
                                fontFamily = sourceSansProFamily,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 24.sp,
                                letterSpacing = 0.sp,
                                textDecoration = TextDecoration.LineThrough,
                            ),
                        bold =
                            TextStyle(
                                fontFamily = sourceSansProFamily,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 24.sp,
                                letterSpacing = 0.sp,
                            ),
                        boldStrikeThrough =
                            TextStyle(
                                fontFamily = sourceSansProFamily,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 24.sp,
                                letterSpacing = 0.sp,
                                textDecoration = TextDecoration.LineThrough,
                            ),
                    ),
            ),
        body =
            BodyTextStyle(
                body1 =
                    TextStyleExtension(
                        regular =
                            TextStyle(
                                fontFamily = sourceSansProFamily,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 20.sp,
                                letterSpacing = 0.sp,
                            ),
                        strikeThrough =
                            TextStyle(
                                fontFamily = sourceSansProFamily,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 20.sp,
                                letterSpacing = 0.sp,
                                textDecoration = TextDecoration.LineThrough,
                            ),
                        bold =
                            TextStyle(
                                fontFamily = sourceSansProFamily,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 20.sp,
                                letterSpacing = 0.sp,
                            ),
                        boldStrikeThrough =
                            TextStyle(
                                fontFamily = sourceSansProFamily,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 20.sp,
                                letterSpacing = 0.sp,
                                textDecoration = TextDecoration.LineThrough,
                            ),
                    ),
                body2 =
                    TextStyleExtension(
                        regular =
                            TextStyle(
                                fontFamily = sourceSansProFamily,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 18.sp,
                                letterSpacing = 0.sp,
                            ),
                        strikeThrough =
                            TextStyle(
                                fontFamily = sourceSansProFamily,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 18.sp,
                                letterSpacing = 0.sp,
                                textDecoration = TextDecoration.LineThrough,
                            ),
                        bold =
                            TextStyle(
                                fontFamily = sourceSansProFamily,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 18.sp,
                                letterSpacing = 0.sp,
                            ),
                        boldStrikeThrough =
                            TextStyle(
                                fontFamily = sourceSansProFamily,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 18.sp,
                                letterSpacing = 0.sp,
                                textDecoration = TextDecoration.LineThrough,
                            ),
                    ),
            ),
        caption =
            TextStyleExtension(
                regular =
                    TextStyle(
                        fontFamily = sourceSansProFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 16.sp,
                        letterSpacing = 0.sp,
                    ),
                strikeThrough =
                    TextStyle(
                        fontFamily = sourceSansProFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 16.sp,
                        letterSpacing = 0.sp,
                        textDecoration = TextDecoration.LineThrough,
                    ),
                bold =
                    TextStyle(
                        fontFamily = sourceSansProFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 16.sp,
                        letterSpacing = 0.sp,
                    ),
                boldStrikeThrough =
                    TextStyle(
                        fontFamily = sourceSansProFamily,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 16.sp,
                        letterSpacing = 0.sp,
                        textDecoration = TextDecoration.LineThrough,
                    ),
            ),
        footnote =
            FootnoteTextStyle(
                footnote =
                    TextStyleBoldExtension(
                        regular =
                            TextStyle(
                                fontFamily = sourceSansProFamily,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Normal,
                                lineHeight = 15.sp,
                                letterSpacing = 0.sp,
                            ),
                        bold =
                            TextStyle(
                                fontFamily = sourceSansProFamily,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 15.sp,
                                letterSpacing = 0.sp,
                            ),
                    ),
            ),
    )
