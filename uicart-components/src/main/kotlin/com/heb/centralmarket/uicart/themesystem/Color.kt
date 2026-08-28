/*
 *
 *  Created by Mahesh Paul on 1/22/26, 8:32 PM
 *  Copyright (c) 2026 . All rights reserved.
 *  Last modified 1/22/26, 8:18 PM
 *
 */

package com.heb.centralmarket.uicart.themesystem

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ColorTokens(
    val txt: Txt,
    val brand: Brand,
    val brandSecondary: BrandSecondary,
    val interactiveGray: InteractiveNeutral,
    val positive: Positive,
    val negative: Negative,
    val warning: Warning,
    val info: Info,
    val structural: Structural,
    val neutral: Neutral,
    val overlay: Overlay,
    val shadow: Shadow,
    val state: State,
    val splash: Splash
) {
    @Immutable
    data class Txt(
        val primary: Color,
        val primaryInverse: Color,
        val secondary: Color,
        val secondaryInverse: Color,
    )

    @Immutable
    data class Brand(
        val primary: Primary,
        val buttonPrimary: ButtonPrimary,
        val buttonPrimaryTonal: ButtonPrimaryTonal,
        val interactive: Interactive,
        val interactiveNeutral: InteractiveNeutral,
        val incrementer: Incrementer,
        val coupon: Coupon,
    ) {
        @Immutable
        data class Primary(
            val core: Color,
            val onCore: Color,
            val hover: Color,
            val focus: Color,
            val bg: Color,
            val disabled: Color,
        )

        @Immutable
        data class ButtonPrimary(
            val core: Color,
            val hover: Color,
            val focus: Color,
            val onCore: Color,
            val bg: Color,
            val disabled: Color,
        )
        @Immutable
        data class ButtonPrimaryTonal(
            val core: Color,
            val hover: Color,
            val press: Color,
            val focus: Color,
            val onCore: Color,
            val border: Color,
        )

        @Immutable
        data class Interactive(
            val core: Color,
            val onCore: Color,
            val press: Color,
            val focus: Color,
            val bg: Color,
            val disabled: Color,
            val hover: Color,
        )
        @Immutable
        data class InteractiveNeutral(
            val onCore: Color,
            val press: Color,
            val hover: Color,
        )

        @Immutable
        data class Incrementer(
            val core: Color,
            val onCore: Color,
            val coreDisabled: Color,
            val onCoreDisabled: Color,
        )

        @Immutable
        data class Coupon(
            val bg: Color,
            val onBg: Color,
            val core: Color,
            val onCore: Color,
        )
    }

    @Immutable
    data class BrandSecondary(
        val core: Color,
        val onCore: Color,
        val hover: Color,
        val focus: Color,
        val coreBg: Color,
        val coreDisabled: Color,
        val icon: Color,
    )

    @Immutable
    data class InteractiveNeutral(
        val core: Color,
        val onCore: Color,
    )

    @Immutable
    data class Positive(
        val core: Color,
        val onCore: Color,
        val bg: Color,
    )

    @Immutable
    data class Negative(
        val core: Color,
        val onCore: Color,
        val bg: Color,
    )

    @Immutable
    data class Warning(
        val core: Color,
        val onCore: Color,
        val bg: Color,
    )

    @Immutable
    data class Info(
        val core: Color,
        val onCore: Color,
        val bg: Color,
        val bgLight: Color,
        val onBgLight: Color,
    )

    @Immutable
    data class Structural(
        val bgPrimary: Color,
        val bgSecondary: Color,
        val elevated: Color,
        val outlineLight: Color,
        val outlineDark: Color,
        val systemThemeColor: Color
    )

    @Immutable
    data class Neutral(
        val white: Color,
        val gray4: Color,
        val gray3: Color,
        val gray2: Color,
        val gray1: Color,
        val black: Color,
        val disabled: Color,
        val transparent: Color,
    )

    @Immutable
    data class Overlay(
        val dark40: Color,
    )

    @Immutable
    data class Shadow(
        val default: Color,
        val light: Color,
        val dark: Color,
    )

    @Immutable
    data class State(
        val interactiveNeutral: InteractiveNeutral,
        val buttonPrimaryTonal: ButtonPrimaryTonal
    ){
        @Immutable
        data class InteractiveNeutral(
            val press: Color,
            val hover: Color,
        )
        @Immutable
        data class ButtonPrimaryTonal(
            val press: Color,
            val hover: Color,
        )
    }

    @Immutable
    data class Splash(
        val bg: Color,
    )
}

val LocalColorScheme =
    staticCompositionLocalOf {
        ColorTokens(
            txt =
                ColorTokens.Txt(
                    primary = Color.Unspecified,
                    primaryInverse = Color.Unspecified,
                    secondary = Color.Unspecified,
                    secondaryInverse = Color.Unspecified,
                ),
            brand =
                ColorTokens.Brand(
                    primary =
                        ColorTokens.Brand.Primary(
                            core = Color.Unspecified,
                            onCore = Color.Unspecified,
                            hover = Color.Unspecified,
                            focus = Color.Unspecified,
                            bg = Color.Unspecified,
                            disabled = Color.Unspecified,
                        ),
                    buttonPrimary =
                        ColorTokens.Brand.ButtonPrimary(
                            core = Color.Unspecified,
                            hover = Color.Unspecified,
                            focus = Color.Unspecified,
                            onCore = Color.Unspecified,
                            bg = Color.Unspecified,
                            disabled = Color.Unspecified,
                        ),
                    buttonPrimaryTonal =
                        ColorTokens.Brand.ButtonPrimaryTonal(
                            core = Color.Unspecified,
                            hover = Color.Unspecified,
                            press = Color.Unspecified,
                            focus = Color.Unspecified,
                            onCore = Color.Unspecified,
                            border = Color.Unspecified,
                        ),
                    interactive =
                        ColorTokens.Brand.Interactive(
                            core = Color.Unspecified,
                            onCore = Color.Unspecified,
                            press = Color.Unspecified,
                            focus = Color.Unspecified,
                            bg = Color.Unspecified,
                            disabled = Color.Unspecified,
                            hover = Color.Unspecified,
                        ),
                    incrementer =
                        ColorTokens.Brand.Incrementer(
                            core = Color.Unspecified,
                            onCore = Color.Unspecified,
                            onCoreDisabled = Color.Unspecified,
                            coreDisabled = Color.Unspecified,
                        ),
                    interactiveNeutral =
                        ColorTokens.Brand.InteractiveNeutral(
                            onCore = Color.Unspecified,
                            press = Color.Unspecified,
                            hover = Color.Unspecified
                        ),
                    coupon =
                        ColorTokens.Brand.Coupon(
                            bg = Color.Unspecified,
                            onBg = Color.Unspecified,
                            core = Color.Unspecified,
                            onCore = Color.Unspecified,
                        ),
                ),
            brandSecondary =
                ColorTokens.BrandSecondary(
                    core = Color.Unspecified,
                    onCore = Color.Unspecified,
                    hover = Color.Unspecified,
                    focus = Color.Unspecified,
                    coreBg = Color.Unspecified,
                    coreDisabled = Color.Unspecified,
                    icon = Color.Unspecified,
                ),
            interactiveGray =
                ColorTokens.InteractiveNeutral(
                    core = Color.Unspecified,
                    onCore = Color.Unspecified,
                ),
            positive =
                ColorTokens.Positive(
                    core = Color.Unspecified,
                    onCore = Color.Unspecified,
                    bg = Color.Unspecified,
                ),
            negative =
                ColorTokens.Negative(
                    core = Color.Unspecified,
                    onCore = Color.Unspecified,
                    bg = Color.Unspecified,
                ),
            warning =
                ColorTokens.Warning(
                    core = Color.Unspecified,
                    onCore = Color.Unspecified,
                    bg = Color.Unspecified,
                ),
            info =
                ColorTokens.Info(
                    core = Color.Unspecified,
                    onCore = Color.Unspecified,
                    bg = Color.Unspecified,
                    bgLight = Color.Unspecified,
                    onBgLight = Color.Unspecified,
                ),
            structural =
                ColorTokens.Structural(
                    bgPrimary = Color.Unspecified,
                    bgSecondary = Color.Unspecified,
                    elevated = Color.Unspecified,
                    outlineLight = Color.Unspecified,
                    outlineDark = Color.Unspecified,
                    systemThemeColor = Color.Unspecified
                ),
            neutral =
                ColorTokens.Neutral(
                    white = Color.Unspecified,
                    gray4 = Color.Unspecified,
                    gray3 = Color.Unspecified,
                    gray2 = Color.Unspecified,
                    gray1 = Color.Unspecified,
                    black = Color.Unspecified,
                    disabled = Color.Unspecified,
                    transparent = Color.Transparent
                ),
            overlay =
                ColorTokens.Overlay(
                    dark40 = Color.Unspecified,
                ),
            shadow =
                ColorTokens.Shadow(
                    default = Color.Unspecified,
                    light = Color.Unspecified,
                    dark = Color.Unspecified
                ),
            state =
                ColorTokens.State(
                    interactiveNeutral =
                        ColorTokens.State.InteractiveNeutral(
                            press = Color.Unspecified,
                            hover = Color.Unspecified
                        ),
                    buttonPrimaryTonal =
                        ColorTokens.State.ButtonPrimaryTonal(
                            press = Color.Unspecified,
                            hover = Color.Unspecified
                        )
                ),
            splash = ColorTokens.Splash(
                bg = Color.Unspecified
            )
        )
    }

@Immutable
data class ExtendedColorTokens(
    val berry: Color,
    val berryHighlight: Color,
    val berryTint: Color,
    val bluefin: Color,
    val bluefinHighlight: Color,
    val bluefinTint: Color,
    val greenHighlight: Color,
    val mustard: Color,
    val malbec: Color,
    val malbecHighlight: Color,
    val mustardHighlight: Color,
    val oldGreen: Color,
    val olive: Color,
    val oliveHighlight: Color,
    val ribEye: Color,
    val ribEyeHighlight: Color,
    val warmWhite: Color,
    val systemBlack: Color,
    val blue: Color,
    val blueHighlight: Color,
    val green: Color,
    val orange: Color,
    val orangeHighlight: Color,
    val red: Color,
    val persimmon: Color
)

val LocalExtendedColorScheme =
    staticCompositionLocalOf {
        ExtendedColorTokens(
            berry = Color.Unspecified,
            berryHighlight = Color.Unspecified,
            berryTint = Color.Unspecified,
            bluefin = Color.Unspecified,
            bluefinHighlight = Color.Unspecified,
            bluefinTint = Color.Unspecified,
            greenHighlight = Color.Unspecified,
            mustard = Color.Unspecified,
            malbec = Color.Unspecified,
            malbecHighlight = Color.Unspecified,
            mustardHighlight = Color.Unspecified,
            oldGreen = Color.Unspecified,
            olive = Color.Unspecified,
            oliveHighlight = Color.Unspecified,
            ribEye = Color.Unspecified,
            ribEyeHighlight = Color.Unspecified,
            warmWhite = Color.Unspecified,
            systemBlack = Color.Unspecified,
            blue = Color.Unspecified,
            blueHighlight = Color.Unspecified,
            green = Color.Unspecified,
            orange = Color.Unspecified,
            orangeHighlight = Color.Unspecified,
            red = Color.Unspecified,
            persimmon = Color.Unspecified
        )
    }

val ExtendedColors =
    ExtendedColorTokens(
        // CM Extended Colors
        berry = Color(0xFFA11053),
        berryHighlight = Color(0xFFFF7A91),
        berryTint = Color(0xFFFFEFF2),
        bluefin = Color(0xFF026B91),
        bluefinHighlight = Color(0xFF8CD9DE),
        bluefinTint = Color(0xFFEAFCFD),
        greenHighlight = Color(0xFF00CC5C),
        mustard = Color(0xFF9E8400),
        malbec = Color(0xFF77004F),
        malbecHighlight = Color(0xFFE285A2),
        mustardHighlight = Color(0xFFFFFF55),
        oldGreen = Color(0xFF80BB00),
        olive = Color(0xFF436E00),
        oliveHighlight = Color(0xFFC4F17F),
        ribEye = Color(0xFFBB2211),
        ribEyeHighlight = Color(0xFFFF9C75),
        warmWhite = Color(0xFFFAF7EE),
        systemBlack = Color(0xFF303030),
        // JV Extended Colors
        blue = Color(0xFF0082BA),
        blueHighlight = Color(0xFF00B5E2),
        green = Color(0xFF97D700),
        orange = Color(0xFFFF6720),
        orangeHighlight = Color(0xFFFF9E1B),
        red = Color(0xFFAB2328),
        persimmon = Color(0xFFC04E02)
    )
