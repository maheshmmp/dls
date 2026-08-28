package com.heb.centralmarket.uicart.themesystem

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.themesystem.background.BackgroundTheme
import com.heb.centralmarket.uicart.themesystem.background.LocalBackgroundTheme
import com.heb.centralmarket.uicart.themesystem.background.LocalTintTheme
import com.heb.centralmarket.uicart.themesystem.background.TintTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

enum class UICSystemThemePreference {
    System,
    Dark,
    Light,
}

fun UICSystemThemePreference.resolve(systemDark: Boolean): Boolean =
    when (this) {
        UICSystemThemePreference.System -> systemDark
        UICSystemThemePreference.Dark -> true
        UICSystemThemePreference.Light -> false
    }

fun UICSystemThemePreference.next(): UICSystemThemePreference =
    when (this) {
        UICSystemThemePreference.System -> UICSystemThemePreference.Dark
        UICSystemThemePreference.Dark -> UICSystemThemePreference.Light
        UICSystemThemePreference.Light -> UICSystemThemePreference.System
    }

object UICartThemeSystem {
    private val _themeUpdates = MutableStateFlow(UICThemeSystem.CentralMarket)
    val themeUpdates: StateFlow<UICThemeSystem> = _themeUpdates

    private val _darkModeUpdates = MutableStateFlow(UICSystemThemePreference.System)
    val darkModeUpdates: StateFlow<UICSystemThemePreference> = _darkModeUpdates

    fun setThemeSystem(theme: UICThemeSystem) {
        _themeUpdates.value = theme
    }

    fun getThemeSystem(): UICThemeSystem = _themeUpdates.value

    fun setDarkMode(darkModePreference: UICSystemThemePreference) {
        _darkModeUpdates.value = darkModePreference
    }

    fun getDarkMode(): UICSystemThemePreference = _darkModeUpdates.value

    fun initialize(
        themeSystem: UICThemeSystem,
        darkMode: UICSystemThemePreference = UICSystemThemePreference.System,
    ) {
        _themeUpdates.value = themeSystem
        _darkModeUpdates.value = darkMode
        when (themeSystem) {
            UICThemeSystem.CentralMarket -> {
                // Debug-specific initialization
            }
            UICThemeSystem.JoeVs -> {
                // Release-specific initialization
            }
            UICThemeSystem.MiTienda -> {
                //Release-specific initialization
            }
        }
    }
}

@Composable
fun UICAppTheme(
    darkModePreference: UICSystemThemePreference? = null,
    content: @Composable () -> Unit,
) {
    val systemDark = isSystemInDarkTheme()

    val currentTheme by UICartThemeSystem.themeUpdates.collectAsState()
    val themeDarkMode by UICartThemeSystem.darkModeUpdates.collectAsState()
    val effectiveDarkModePreference = darkModePreference ?: themeDarkMode
    val colorScheme = getColorScheme(currentTheme, effectiveDarkModePreference.resolve(systemDark))

    val defaultBackgroundTheme =
        BackgroundTheme(
            color = colorScheme.brand.buttonPrimary.onCore,
            tonalElevation = 2.dp,
        )

    val tintTheme = TintTheme(colorScheme.brand.primary.bg)

    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalExtendedColorScheme provides ExtendedColors,
        LocalBackgroundTheme provides defaultBackgroundTheme,
        LocalTintTheme provides tintTheme,
        LocalTypography provides typography,
        LocalShape provides shape,
        LocalSpacing provides spacing,
        LocalHeight provides height,
        LocalCoreBorderRadius provides coreBorderRadius,
        LocalSizes provides sizes
    ) {
        Surface(
            color = colorScheme.structural.bgPrimary,
            modifier = Modifier.fillMaxSize(),
            content = content,
        )
    }
}

private fun getColorScheme(
    theme: UICThemeSystem,
    isDark: Boolean,
) = when (theme) {
    UICThemeSystem.CentralMarket -> if (isDark) CentralMarketDark else CentralMarket
    UICThemeSystem.JoeVs -> if (isDark) JoeVsDark else JoeVs
    UICThemeSystem.MiTienda -> if (isDark) MiTiendaDark else MiTienda
}

@VisibleForTesting
val CentralMarket =
    ColorTokens(
        txt =
            ColorTokens.Txt(
                primary = Color(0xFF303030),
                primaryInverse = Color(0xFFFFFFFF),
                secondary = Color(0xFF767676),
                secondaryInverse = Color(0xFFECECEC),
            ),
        brand =
            ColorTokens.Brand(
                primary =
                    ColorTokens.Brand.Primary(
                        core = Color(0xFF00693C),
                        onCore = Color(0xFFFFFFFF),
                        hover = Color(0xFF004D29),
                        focus = Color(0xFF5EBA8E),
                        bg = Color(0xFFE3F7EB),
                        disabled = Color(0x6600693C),
                    ),
                buttonPrimary =
                    ColorTokens.Brand.ButtonPrimary(
                        core = Color(0xFF00693C),
                        hover = Color(0xFF004D29),
                        focus = Color(0xFF5EBA8E),
                        onCore = Color(0xFFFFFFFF),
                        bg = Color(0xFFE3F7EB),
                        disabled = Color(0x6600693C),
                    ),
                buttonPrimaryTonal =
                    ColorTokens.Brand.ButtonPrimaryTonal(
                        core = Color(0xFFE3F7EB),
                        hover = Color(0x1400693C),
                        press = Color(0x1A00693C),
                        focus = Color(0xFF004D29),
                        onCore = Color(0xFF00693C),
                        border = Color(0xFF00693C),
                    ),
                interactive =
                    ColorTokens.Brand.Interactive(
                        core = Color(0xFF00693C),
                        onCore = Color(0xFFFFFFFF),
                        press = Color(0xFF004D29),
                        focus = Color(0xFF5EBA8E),
                        bg = Color(0xFFE3F7EB),
                        disabled = Color(0x6600693C),
                        hover = Color(0xFF004D29),
                    ),
                interactiveNeutral =
                    ColorTokens.Brand.InteractiveNeutral(
                        onCore = Color(0xFF303030),
                        press = Color(0x1A303030),
                        hover = Color(0x14303030)
                    ),
                incrementer =
                    ColorTokens.Brand.Incrementer(
                        core = Color(0xFF00693C),
                        onCore = Color(0xFF00693C),
                        coreDisabled = Color(0x66FFFFFF),
                        onCoreDisabled = Color(0x6600693C),
                    ),
                coupon =
                    ColorTokens.Brand.Coupon(
                        bg = Color(0xFFFFFEF5),
                        onBg = Color(0xFF303030),
                        core = Color(0xFFFDE988),
                        onCore = Color(0xFF303030),
                    ),
            ),
        brandSecondary =
            ColorTokens.BrandSecondary(
                core = Color(0xFFC04E02),
                onCore = Color(0xFFFFFFFF),
                hover = Color(0xFFA64000),
                focus = Color(0xFFF2975A),
                coreBg = Color(0xFFFFF1E5),
                coreDisabled = Color(0x66C04E02),
                icon = Color(0xFFC04E02),
            ),
        interactiveGray =
            ColorTokens.InteractiveNeutral(
                core = Color(0xFFD9D9D9),
                onCore = Color(0xFF303030),
            ),
        positive =
            ColorTokens.Positive(
                core = Color(0xFF1C8100),
                onCore = Color(0xFFFFFFFF),
                bg = Color(0xFFE9FCE3),
            ),
        negative =
            ColorTokens.Negative(
                core = Color(0xFFBD1417),
                onCore = Color(0xFFFFFFFF),
                bg = Color(0xFFFFF2F3),
            ),
        warning =
            ColorTokens.Warning(
                core = Color(0xFFD09820),
                onCore = Color(0xFFFFFFFF),
                bg = Color(0xFFFFF8EB),
            ),
        info =
            ColorTokens.Info(
                core = Color(0xFF026B91),
                onCore = Color(0xFF303030),
                bg = Color(0xFFD0F0FB),
                bgLight = Color(0xFFF4FCFF),
                onBgLight = Color(0xFF303030),
            ),
        structural =
            ColorTokens.Structural(
                bgPrimary = Color(0xFFFFFFFF),
                bgSecondary = Color(0xFFF7F7F7),
                elevated = Color(0xFFFFFFFF),
                outlineLight = Color(0xFFECECEC),
                outlineDark = Color(0xFF767676),
                systemThemeColor = Color(0xFFFFFFFF)
            ),
        neutral =
            ColorTokens.Neutral(
                white = Color(0xFFFFFFFF),
                gray4 = Color(0xFFF7F7F7),
                gray3 = Color(0xFFECECEC),
                gray2 = Color(0xFFB8B8B8),
                gray1 = Color(0xFF767676),
                black = Color(0xFF303030),
                disabled = Color(0xB3303030),
                transparent = Color.Transparent
            ),
        overlay =
            ColorTokens.Overlay(
                dark40 = Color(0x66000000),
            ),
        shadow =
            ColorTokens.Shadow(
                default = Color(0x33000000),
                light = Color(0x33000000),
                dark = Color(0x33000000)
            ),
        state =
            ColorTokens.State(
                interactiveNeutral =
                    ColorTokens.State.InteractiveNeutral(
                        press = Color(0x1A303030),
                        hover = Color(0x14303030)
                    ),
                buttonPrimaryTonal =
                    ColorTokens.State.ButtonPrimaryTonal(
                        press = Color(0x1A00693C),
                        hover = Color(0x1400693C)
                    )
            ),
        splash = ColorTokens.Splash(
            bg = Color(0xFF00693C)
        )
    )

@VisibleForTesting
val CentralMarketDark =
    ColorTokens(
        txt =
            ColorTokens.Txt(
                primary = Color(0xFFFFFFFF),
                primaryInverse = Color(0xFF303030),
                secondary = Color(0xFFD9D9D9),
                secondaryInverse = Color(0xFF575757),
            ),
        brand =
            ColorTokens.Brand(
                primary =
                    ColorTokens.Brand.Primary(
                        core = Color(0xFF5EBA8E),
                        onCore = Color(0xFF303030),
                        hover = Color(0xFF0F8A55),
                        focus = Color(0xFF033F21),
                        bg = Color(0xFF002914),
                        disabled = Color(0x665EBA8E),
                    ),
                buttonPrimary =
                    ColorTokens.Brand.ButtonPrimary(
                        core = Color(0xFF5EBA8E),
                        onCore = Color(0xFF303030),
                        hover = Color(0xFF0F8A55),
                        focus = Color(0xFF033F21),
                        bg = Color(0xFF002914),
                        disabled = Color(0x665EBA8E),
                    ),
                interactive =
                    ColorTokens.Brand.Interactive(
                        core = Color(0xFF5EBA8E),
                        onCore = Color(0xFF303030),
                        press = Color(0xFF0F8A55),
                        focus = Color(0xFF033F21),
                        bg = Color(0xFF002914),
                        disabled = Color(0x665EBA8E),
                        hover = Color(0xFF0F8A55),
                    ),
                incrementer =
                    ColorTokens.Brand.Incrementer(
                        core = Color(0xFF00693C),
                        onCore = Color(0xFFFFFFFF),
                        coreDisabled = Color(0x6600693C),
                        onCoreDisabled = Color(0x66FFFFFF),
                    ),
                buttonPrimaryTonal =
                    ColorTokens.Brand.ButtonPrimaryTonal(
                        core = Color(0xFF002914),
                        hover = Color(0x145EBA8E),
                        press = Color(0x1A5EBA8E),
                        focus = Color(0xFFA9D6BF),
                        onCore = Color(0xFF5EBA8E),
                        border = Color(0xFF5EBA8E),
                    ),
                interactiveNeutral =
                    ColorTokens.Brand.InteractiveNeutral(
                        onCore = Color(0xFFF7F7F7),
                        press = Color(0x1AFFFFFF),
                        hover = Color(0x14FFFFFF)
                    ),
                coupon =
                    ColorTokens.Brand.Coupon(
                        bg = Color(0xFF121212),
                        onBg = Color(0xFFFDE988),
                        core = Color(0xFFFEDB00),
                        onCore = Color(0xFF303030),
                    ),
            ),
        brandSecondary =
            ColorTokens.BrandSecondary(
                core = Color(0xFFF2975A),
                onCore = Color(0xFF303030),
                hover = Color(0xFFDF7020),
                focus = Color(0xFF993800),
                coreBg = Color(0xFF702900),
                coreDisabled = Color(0x66F2975A),
                icon = Color(0xFFF2975A),
            ),
        interactiveGray =
            ColorTokens.InteractiveNeutral(
                core = Color(0xFF424242),
                onCore = Color(0xFFF7F7F7),
            ),
        positive =
            ColorTokens.Positive(
                core = Color(0xFF99E085),
                onCore = Color(0xFFFFFFFF),
                bg = Color(0xFF186902),
            ),
        negative =
            ColorTokens.Negative(
                core = Color(0xFFE75C5E),
                onCore = Color(0xFFFFFFFF),
                bg = Color(0xFFA61214),
            ),
        warning =
            ColorTokens.Warning(
                core = Color(0xFFFDCF72),
                onCore = Color(0xFFFFFFFF),
                bg = Color(0xFF9C6E00),
            ),
        info =
            ColorTokens.Info(
                core = Color(0xFF0B9FD5),
                onCore = Color(0xFFFFFFFF),
                bg = Color(0xFF025979),
                bgLight = Color(0xFF121212),
                onBgLight = Color(0xFF0B9FD5),
            ),
        structural =
            ColorTokens.Structural(
                bgPrimary = Color(0xFF121212),
                bgSecondary = Color(0xFF1F1F1F),
                elevated = Color(0xFF1F1F1F),
                outlineLight = Color(0xFF424242),
                outlineDark = Color(0xFFD9D9D9),
                systemThemeColor = Color(0xFF000000)
            ),
        neutral =
            ColorTokens.Neutral(
                white = Color(0xFFFFFFFF),
                gray4 = Color(0xFFF7F7F7),
                gray3 = Color(0xFFECECEC),
                gray2 = Color(0xFFB8B8B8),
                gray1 = Color(0xFF767676),
                black = Color(0xFF303030),
                disabled = Color(0xB3303030),
                transparent = Color.Transparent
            ),
        overlay =
            ColorTokens.Overlay(
                dark40 = Color(0x66000000),
            ),
        shadow =
            ColorTokens.Shadow(
                default = Color(0x33000000),
                light = Color(0x33000000),
                dark = Color(0x33000000)
            ),
        state =
            ColorTokens.State(
                interactiveNeutral =
                    ColorTokens.State.InteractiveNeutral(
                        press = Color(0x1AFFFFFF),
                        hover = Color(0x14FFFFFF)
                    ),
                buttonPrimaryTonal =
                    ColorTokens.State.ButtonPrimaryTonal(
                        press = Color(0x1A5EBA8E),
                        hover = Color(0x145EBA8E)
                    )
            ),
        splash = ColorTokens.Splash(
            bg = Color(0xFF00693C)
        )
    )

@VisibleForTesting
val JoeVs =
    ColorTokens(
        txt =
            ColorTokens.Txt(
                primary = Color(0xFF303030),
                primaryInverse = Color(0xFFFFFFFF),
                secondary = Color(0xFF767676),
                secondaryInverse = Color(0xFFECECEC),
            ),
        brand =
            ColorTokens.Brand(
                primary =
                    ColorTokens.Brand.Primary(
                        core = Color(0xFFDA291C),
                        onCore = Color(0xFFFFFFFF),
                        hover = Color(0xFFC61205),
                        focus = Color(0xFFFF6C61),
                        bg = Color(0xFFFFECEB),
                        disabled = Color(0x66DA291C),
                    ),
                buttonPrimary =
                    ColorTokens.Brand.ButtonPrimary(
                        core = Color(0xFFFEDB00),
                        hover = Color(0xFFCCB000),
                        focus = Color(0xFFFBE76A),
                        onCore = Color(0xFF303030),
                        bg = Color(0xFFFFFADB),
                        disabled = Color(0x66FEDB00),
                    ),
                interactive =
                    ColorTokens.Brand.Interactive(
                        core = Color(0xFF1C8100),
                        onCore = Color(0xFFFFFFFF),
                        press = Color(0xFF186902),
                        focus = Color(0xFF99E085),
                        bg = Color(0xFFE9FCE3),
                        disabled = Color(0x661C8100),
                        hover = Color(0xFF186902),
                    ),
                incrementer =
                    ColorTokens.Brand.Incrementer(
                        core = Color(0xFFFFFFFF),
                        onCore = Color(0xFF303030),
                        coreDisabled = Color(0x66FFFFFF),
                        onCoreDisabled = Color(0x66303030),
                    ),
                buttonPrimaryTonal =
                    ColorTokens.Brand.ButtonPrimaryTonal(
                        core = Color(0xFFFFF8CC),
                        hover = Color(0x14303030),
                        press = Color(0x1A303030),
                        focus = Color(0xFFCCB000),
                        onCore = Color(0xFF303030),
                        border = Color(0xFF998400),
                    ),
                interactiveNeutral =
                    ColorTokens.Brand.InteractiveNeutral(
                        onCore = Color(0xFF303030),
                        press = Color(0x1A303030),
                        hover = Color(0x14303030)
                    ),
                coupon =
                    ColorTokens.Brand.Coupon(
                        bg = Color(0xFFFFFADB),
                        onBg = Color(0xFF303030),
                        core = Color(0xFFFEDB00),
                        onCore = Color(0xFF303030),
                    ),
            ),
        brandSecondary =
            ColorTokens.BrandSecondary(
                core = Color(0xFF1C8100),
                onCore = Color(0xFFFFFFFF),
                hover = Color(0xFF186902),
                focus = Color(0xFF99E085),
                coreBg = Color(0xFFE9FCE3),
                coreDisabled = Color(0x661C8100),
                icon = Color(0xFFDA291C),
            ),
        interactiveGray =
            ColorTokens.InteractiveNeutral(
                core = Color(0xFFD9D9D9),
                onCore = Color(0xFF303030),
            ),
        positive =
            ColorTokens.Positive(
                core = Color(0xFF1C8100),
                onCore = Color(0xFFFFFFFF),
                bg = Color(0xFFE9FCE3),
            ),
        negative =
            ColorTokens.Negative(
                core = Color(0xFFBD1417),
                onCore = Color(0xFFFFFFFF),
                bg = Color(0xFFFFF2F3),
            ),
        warning =
            ColorTokens.Warning(
                core = Color(0xFFE07306),
                onCore = Color(0xFFFFFFFF),
                bg = Color(0xFFFFF8EB),
            ),
        info =
            ColorTokens.Info(
                core = Color(0xFF006AA3),
                onCore = Color(0xFF303030),
                bg = Color(0xFFDCF3FE),
                bgLight = Color(0xFFF2FBFF),
                onBgLight = Color(0xFF303030),
            ),
        structural =
            ColorTokens.Structural(
                bgPrimary = Color(0xFFFFFFFF),
                bgSecondary = Color(0xFFF7F7F7),
                elevated = Color(0xFFFFFFFF),
                outlineLight = Color(0xFFECECEC),
                outlineDark = Color(0xFF767676),
                systemThemeColor = Color(0xFFFFFFFF)
            ),
        neutral =
            ColorTokens.Neutral(
                white = Color(0xFFFFFFFF),
                gray4 = Color(0xFFF7F7F7),
                gray3 = Color(0xFFECECEC),
                gray2 = Color(0xFFB8B8B8),
                gray1 = Color(0xFF767676),
                black = Color(0xFF303030),
                disabled = Color(0xB3303030),
                transparent = Color.Transparent
            ),
        overlay =
            ColorTokens.Overlay(
                dark40 = Color(0x66000000),
            ),
        shadow =
            ColorTokens.Shadow(
                default = Color(0x33000000),
                light = Color(0x33000000),
                dark = Color(0x33000000)
            ),
        state =
            ColorTokens.State(
                interactiveNeutral =
                    ColorTokens.State.InteractiveNeutral(
                        press = Color(0x1A303030),
                        hover = Color(0x14303030)
                    ),
                buttonPrimaryTonal =
                    ColorTokens.State.ButtonPrimaryTonal(
                        press = Color(0x1A303030),
                        hover = Color(0x14303030),
                    )
            ),
        splash = ColorTokens.Splash(
            bg = Color(0xFF1C8100)
        )
    )

@VisibleForTesting
val JoeVsDark =
    ColorTokens(
        txt =
            ColorTokens.Txt(
                primary = Color(0xFFFFFFFF),
                primaryInverse = Color(0xFF303030),
                secondary = Color(0xFFD9D9D9),
                secondaryInverse = Color(0xFF575757),
            ),
        brand =
            ColorTokens.Brand(
                primary =
                    ColorTokens.Brand.Primary(
                        core = Color(0xFFFF6C61),
                        onCore = Color(0xFF303030),
                        hover = Color(0xFFF24439),
                        focus = Color(0xFF9F0B00),
                        bg = Color(0xFF400400),
                        disabled = Color(0x66FF6C61),
                    ),
                buttonPrimary =
                    ColorTokens.Brand.ButtonPrimary(
                        core = Color(0xFFFEDB00),
                        onCore = Color(0xFF303030),
                        hover = Color(0xFFCCB000),
                        focus = Color(0xFFFBE76A),
                        bg = Color(0xFFFFFADB),
                        disabled = Color(0x66FEDB00),
                    ),
                interactive =
                    ColorTokens.Brand.Interactive(
                        core = Color(0xFF99E085),
                        onCore = Color(0xFF303030),
                        press = Color(0xFF40A824),
                        focus = Color(0xFF135601),
                        bg = Color(0xFF102D01),
                        disabled = Color(0x6699E085),
                        hover = Color(0xFF40A824),
                    ),
                incrementer =
                    ColorTokens.Brand.Incrementer(
                        core = Color(0xFFFEDB00),
                        onCore = Color(0xFF303030),
                        coreDisabled = Color(0x66FEDB00),
                        onCoreDisabled = Color(0x66303030),
                    ),
                buttonPrimaryTonal =
                    ColorTokens.Brand.ButtonPrimaryTonal(
                        core = Color(0xFF332C00),
                        hover = Color(0x14FEDB00),
                        press = Color(0x1AFEDB00),
                        focus = Color(0xFFFFF199),
                        onCore = Color(0xFFFEDB00),
                        border = Color(0xFF998400),
                    ),
                interactiveNeutral =
                    ColorTokens.Brand.InteractiveNeutral(
                        onCore = Color(0xFFF7F7F7),
                        press = Color(0x1AFFFFFF),
                        hover = Color(0x14FFFFFF)
                    ),
                coupon =
                    ColorTokens.Brand.Coupon(
                        bg = Color(0xFF121212),
                        onBg = Color(0xFFFEDB00),
                        core = Color(0xFFFEDB00),
                        onCore = Color(0xFF303030),
                    ),
            ),
        brandSecondary =
            ColorTokens.BrandSecondary(
                core = Color(0xFF99E085),
                onCore = Color(0xFF303030),
                hover = Color(0xFF40A824),
                focus = Color(0xFF135601),
                coreBg = Color(0xFF102D01),
                coreDisabled = Color(0x6699E085),
                icon = Color(0xFFFF6C61),
            ),
        interactiveGray =
            ColorTokens.InteractiveNeutral(
                core = Color(0xFF424242),
                onCore = Color(0xFFF7F7F7),
            ),
        positive =
            ColorTokens.Positive(
                core = Color(0xFF99E085),
                onCore = Color(0xFFFFFFFF),
                bg = Color(0xFF186902),
            ),
        negative =
            ColorTokens.Negative(
                core = Color(0xFFE75C5E),
                onCore = Color(0xFFFFFFFF),
                bg = Color(0xFFA61214),
            ),
        warning =
            ColorTokens.Warning(
                core = Color(0xFFFFB95A),
                onCore = Color(0xFFFFFFFF),
                bg = Color(0xFFB25E09),
            ),
        info =
            ColorTokens.Info(
                core = Color(0xFF0FA9F0),
                onCore = Color(0xFFFFFFFF),
                bg = Color(0xFF005685),
                bgLight = Color(0xFF121212),
                onBgLight = Color(0xFF0FA9F0),
            ),
        structural =
            ColorTokens.Structural(
                bgPrimary = Color(0xFF121212),
                bgSecondary = Color(0xFF1F1F1F),
                elevated = Color(0xFF1F1F1F),
                outlineLight = Color(0xFF424242),
                outlineDark = Color(0xFFD9D9D9),
                systemThemeColor = Color(0xFF000000)
            ),
        neutral =
            ColorTokens.Neutral(
                white = Color(0xFFFFFFFF),
                gray4 = Color(0xFFF7F7F7),
                gray3 = Color(0xFFECECEC),
                gray2 = Color(0xFFB8B8B8),
                gray1 = Color(0xFF767676),
                black = Color(0xFF303030),
                disabled = Color(0xB3303030),
                transparent = Color.Transparent
            ),
        overlay =
            ColorTokens.Overlay(
                dark40 = Color(0x66000000),
            ),
        shadow =
            ColorTokens.Shadow(
                default = Color(0x33000000),
                light = Color(0x33000000),
                dark = Color(0x33000000)
            ),
        state =
            ColorTokens.State(
                interactiveNeutral =
                    ColorTokens.State.InteractiveNeutral(
                        press = Color(0x1AFFFFFF),
                        hover = Color(0x14FFFFFF)
                    ),
                buttonPrimaryTonal =
                    ColorTokens.State.ButtonPrimaryTonal(
                        press = Color(0x1AFEDB00),
                        hover = Color(0x14FEDB00),
                    )
            ),
        splash = ColorTokens.Splash(
            bg = Color(0xFF1C8100)
        )
    )
@VisibleForTesting
val MiTienda =
    ColorTokens(
        txt =
            ColorTokens.Txt(
                primary = Color(0xFF303030),
                primaryInverse = Color(0xFFFFFFFF),
                secondary = Color(0xFF767676),
                secondaryInverse = Color(0xFFECECEC),
            ),
        brand =
            ColorTokens.Brand(
                primary =
                    ColorTokens.Brand.Primary(
                        core = Color(0xFFB92454),
                        onCore = Color(0xFFFFFFFF),
                        hover = Color(0xFFA2204A),
                        focus = Color(0xFFE76E95),
                        bg = Color(0xFFFCDFE8),
                        disabled = Color(0x66B92454),
                    ),
                buttonPrimary =
                    ColorTokens.Brand.ButtonPrimary(
                        core = Color(0xFFFFCE24),
                        hover = Color(0xFFE1B221),
                        focus = Color(0xFFFFE17A),
                        onCore = Color(0xFF303030),
                        bg = Color(0xFFFFF7DB),
                        disabled = Color(0x66FFCE24),
                    ),
                interactive =
                    ColorTokens.Brand.Interactive(
                        core = Color(0xFF1C8100),
                        onCore = Color(0xFFFFFFFF),
                        press = Color(0xFF186902),
                        focus = Color(0xFF99E085),
                        bg = Color(0xFFE9FCE3),
                        disabled = Color(0x661C8100),
                        hover = Color(0xFF186902),
                    ),
                incrementer =
                    ColorTokens.Brand.Incrementer(
                        core = Color(0xFFFFFFFF),
                        onCore = Color(0xFF303030),
                        coreDisabled = Color(0x66FFFFFF),
                        onCoreDisabled = Color(0x66303030),
                    ),
                buttonPrimaryTonal =
                    ColorTokens.Brand.ButtonPrimaryTonal(
                        core = Color(0xFFFFF2BD),
                        hover = Color(0x14303030),
                        press = Color(0x1A303030),
                        focus = Color(0xFFE1B221),
                        onCore = Color(0xFF303030),
                        border = Color(0xFFBB8D10),
                    ),
                interactiveNeutral =
                    ColorTokens.Brand.InteractiveNeutral(
                        onCore = Color(0xFF303030),
                        press = Color(0x1A303030),
                        hover = Color(0x14303030)
                    ),
                coupon =
                    ColorTokens.Brand.Coupon(
                        bg = Color(0xFFFFF7DB),
                        onBg = Color(0xFF303030),
                        core = Color(0xFFFFCE24),
                        onCore = Color(0xFF303030),
                    ),
            ),
        brandSecondary =
            ColorTokens.BrandSecondary(
                core = Color(0xFFB92454),
                onCore = Color(0xFFFFFFFF),
                hover = Color(0xFF522D14),
                focus = Color(0xFFB2764D),
                coreBg = Color(0xFFECD5C6),
                coreDisabled = Color(0x66653819),
                icon = Color(0xFFB92454),
            ),
        interactiveGray =
            ColorTokens.InteractiveNeutral(
                core = Color(0xFFD9D9D9),
                onCore = Color(0xFF303030),
            ),
        positive =
            ColorTokens.Positive(
                core = Color(0xFF1C8100),
                onCore = Color(0xFF303030),
                bg = Color(0xFFE9FCE3),
            ),
        negative =
            ColorTokens.Negative(
                core = Color(0xFFBD1417),
                onCore = Color(0xFF303030),
                bg = Color(0xFFFFF2F3),
            ),
        warning =
            ColorTokens.Warning(
                core = Color(0xFFE07306),
                onCore = Color(0xFF303030),
                bg = Color(0xFFFFF8EB),
            ),
        info =
            ColorTokens.Info(
                core = Color(0xFF006AA3),
                onCore = Color(0xFF303030),
                bg = Color(0xFFDCF3FE),
                bgLight = Color(0xFFF2FBFF),
                onBgLight = Color(0xFF303030),
            ),
        structural =
            ColorTokens.Structural(
                bgPrimary = Color(0xFFFFFFFF),
                bgSecondary = Color(0xFFF7F7F7),
                elevated = Color(0xFFFFFFFF),
                outlineLight = Color(0xFFECECEC),
                outlineDark = Color(0xFF767676),
                systemThemeColor = Color(0xFFFFFFFF)
            ),
        neutral =
            ColorTokens.Neutral(
                white = Color(0xFFFFFFFF),
                gray4 = Color(0xFFF7F7F7),
                gray3 = Color(0xFFECECEC),
                gray2 = Color(0xFFB8B8B8),
                gray1 = Color(0xFF767676),
                black = Color(0xFF303030),
                disabled = Color(0xB3303030),
                transparent = Color.Transparent
            ),
        overlay =
            ColorTokens.Overlay(
                dark40 = Color(0x66000000),
            ),
        shadow =
            ColorTokens.Shadow(
                default = Color(0x33000000),
                light = Color(0x33000000),
                dark = Color(0x33000000)
            ),
        state =
            ColorTokens.State(
                interactiveNeutral =
                    ColorTokens.State.InteractiveNeutral(
                        press = Color(0x1A303030),
                        hover = Color(0x14303030)
                    ),
                buttonPrimaryTonal =
                    ColorTokens.State.ButtonPrimaryTonal(
                        press = Color(0x1A303030),
                        hover = Color(0x14303030)
                    )
            ),
        splash = ColorTokens.Splash(
            bg = Color(0xFFEEDEAD)
        )
    )

@VisibleForTesting
val MiTiendaDark =
    ColorTokens(
        txt =
            ColorTokens.Txt(
                primary = Color(0xFFFFFFFF),
                primaryInverse = Color(0xFF303030),
                secondary = Color(0xFFD9D9D9),
                secondaryInverse = Color(0xFF575757),
            ),
        brand =
            ColorTokens.Brand(
                primary =
                    ColorTokens.Brand.Primary(
                        core = Color(0xFFE97499),
                        onCore = Color(0xFF303030),
                        hover = Color(0xFFD93F71),
                        focus = Color(0xFF8F1E43),
                        bg = Color(0xFF521428),
                        disabled = Color(0x66E76E95),
                    ),
                buttonPrimary =
                    ColorTokens.Brand.ButtonPrimary(
                        core = Color(0xFFFFCE24),
                        onCore = Color(0xFF303030),
                        hover = Color(0xFFE1B221),
                        focus = Color(0xFFFFE17A),
                        bg = Color(0xFFFFF7DB),
                        disabled = Color(0x66FFCE24),
                    ),
                interactive =
                    ColorTokens.Brand.Interactive(
                        core = Color(0xFF99E085),
                        onCore = Color(0xFF303030),
                        press = Color(0xFF40A824),
                        focus = Color(0xFF135601),
                        bg = Color(0xFF102D01),
                        disabled = Color(0x6699E085),
                        hover = Color(0xFF40A824),
                    ),
                incrementer =
                    ColorTokens.Brand.Incrementer(
                        core = Color(0xFFFFCE24),
                        onCore = Color(0xFF303030),
                        coreDisabled = Color(0x66FFCE24),
                        onCoreDisabled = Color(0x66303030),
                    ),
                buttonPrimaryTonal =
                    ColorTokens.Brand.ButtonPrimaryTonal(
                        core = Color(0xFF542F13),
                        hover = Color(0x14FFCE24),
                        press = Color(0x1AFFCE24),
                        focus = Color(0xFFFFEA94),
                        onCore = Color(0xFFFFCE24),
                        border = Color(0xFFBB8D10),
                    ),
                interactiveNeutral =
                    ColorTokens.Brand.InteractiveNeutral(
                        onCore = Color(0xFFF7F7F7),
                        press = Color(0x1AFFFFFF),
                        hover = Color(0x14FFFFFF)
                    ),
                coupon =
                    ColorTokens.Brand.Coupon(
                        bg = Color(0xFF121212),
                        onBg = Color(0xFFFFCE24),
                        core = Color(0xFFFFCE24),
                        onCore = Color(0xFF303030),
                    ),
            ),
        brandSecondary =
            ColorTokens.BrandSecondary(
                core = Color(0xFFE97499),
                onCore = Color(0xFF303030),
                hover = Color(0xFF995D33),
                focus = Color(0xFF462611),
                coreBg = Color(0xFF2D180B),
                coreDisabled = Color(0x66B2764D),
                icon = Color(0xFFE97499),
            ),
        interactiveGray =
            ColorTokens.InteractiveNeutral(
                core = Color(0xFF424242),
                onCore = Color(0xFFF7F7F7),
            ),
        positive =
            ColorTokens.Positive(
                core = Color(0xFF99E085),
                onCore = Color(0xFFFFFFFF),
                bg = Color(0xFF186902),
            ),
        negative =
            ColorTokens.Negative(
                core = Color(0xFFE75C5E),
                onCore = Color(0xFFFFFFFF),
                bg = Color(0xFFA61214),
            ),
        warning =
            ColorTokens.Warning(
                core = Color(0xFFFFB95A),
                onCore = Color(0xFFFFFFFF),
                bg = Color(0xFFB25E09),
            ),
        info =
            ColorTokens.Info(
                core = Color(0xFF0FA9F0),
                onCore = Color(0xFFFFFFFF),
                bg = Color(0xFF005685),
                bgLight = Color(0xFF121212),
                onBgLight = Color(0xFF0FA9F0),
            ),
        structural =
            ColorTokens.Structural(
                bgPrimary = Color(0xFF121212),
                bgSecondary = Color(0xFF1F1F1F),
                elevated = Color(0xFF1F1F1F),
                outlineLight = Color(0xFF424242),
                outlineDark = Color(0xFFD9D9D9),
                systemThemeColor = Color(0xFF000000)
            ),
        neutral =
            ColorTokens.Neutral(
                white = Color(0xFFFFFFFF),
                gray4 = Color(0xFFF7F7F7),
                gray3 = Color(0xFFECECEC),
                gray2 = Color(0xFFB8B8B8),
                gray1 = Color(0xFF767676),
                black = Color(0xFF303030),
                disabled = Color(0xB3303030),
                transparent = Color.Transparent
            ),
        overlay =
            ColorTokens.Overlay(
                dark40 = Color(0x66000000),
            ),
        shadow =
            ColorTokens.Shadow(
                default = Color(0x33000000),
                light = Color(0x33000000),
                dark = Color(0x33000000)
            ),
        state =
            ColorTokens.State(
                interactiveNeutral =
                    ColorTokens.State.InteractiveNeutral(
                        press = Color(0x1AFFFFFF),
                        hover = Color(0x14FFFFFF)
                    ),
                buttonPrimaryTonal =
                    ColorTokens.State.ButtonPrimaryTonal(
                        press = Color(0x1AFFCE24),
                        hover = Color(0x14FFCE24)
                    )
            ),
        splash = ColorTokens.Splash(
            bg = Color(0xFFEEDEAD)
        )
    )

object UICTheme {
    val colorScheme: ColorTokens
        @Composable
        get() = LocalColorScheme.current
}

object UICExtendedTheme {
    val colorScheme: ExtendedColorTokens
        @Composable
        get() = LocalExtendedColorScheme.current
}

object UICTypography {
    val typography: Typography
        @Composable
        get() = LocalTypography.current
}

object UICShape {
    val shapes: Shape
        @Composable
        get() = LocalShape.current
}

object UICSpacing {
    val spacing: Spacing
        @Composable
        get() = LocalSpacing.current
}

object UICHeight {
    val height: Height
        @Composable
        get() = LocalHeight.current
}

object UICCornerRadius {
    val borderRadius: CoreBorderRadius
        @Composable
        get() = LocalCoreBorderRadius.current
}

object UICSize {
    val size: Sizes
        @Composable
        get() = LocalSizes.current
}

enum class UICThemeSystem {
    CentralMarket,
    JoeVs,
    MiTienda
}
