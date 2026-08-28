package com.heb.centralmarket.uicart.component.buttons

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.component.CoreCaptionTextView
import com.heb.centralmarket.uicart.component.buttons.UICIconButtonConstants.DISABLED_ALPHA
import com.heb.centralmarket.uicart.component.buttons.UICIconButtonConstants.PRESSED_CONTENT_ALPHA
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.icons.accountAvatar
import com.heb.centralmarket.uicart.themesystem.UICCornerRadius
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.utils.rememberDebouncedClick

/**
 * A customizable icon button component with an optional badge.
 *
 * @param onClick The callback invoked when the button is clicked.
 * @param modifier The modifier to be applied to the button.
 * @param enabled Determines if the button is enabled or disabled.
 * @param badgeCount Optional text displayed in the badge at the top-right corner.
 * @param buttonSize The size of the button, defaulting to [UICIconButtonSize.MEDIUM].
 * @param showBadge Determines if the badge should be displayed. Default is `true`.
 * @param iconVector A vector passed to render icon inside the button.
 * @param contentDescription Description used for accessibility.
 */
@Composable
fun UICIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    badgeCount: String? = "",
    variant: IconButtonVariant = IconButtonVariant.NEUTRAL,
    buttonSize: UICIconButtonSize = UICIconButtonSize.MEDIUM,
    showBadge: Boolean = true,
    iconVector: ImageVector? = null,
    contentDescription: String? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()

    LaunchedEffect(isHovered) {
        Log.d("HoverTest", "Hovered: $isFocused")
    }
    val currentState =
        when {
            !enabled -> ButtonState.DISABLED
            isPressed -> ButtonState.PRESSED
            isHovered -> ButtonState.HOVER
            isFocused -> ButtonState.FOCUSED
            else -> ButtonState.ENABLED
        }
    val style = IconButtonStyleProvider.getStyle(variant, currentState)

    val debouncedOnClick = rememberDebouncedClick(onClick, 500L)

    DisableRippleEffect {
        FilledIconButton(
            onClick = debouncedOnClick ?: {},
            enabled = enabled,
            interactionSource = interactionSource,
            modifier =
                modifier
                    .size(size = buttonSize.buttonSize + UICIconButtonConstants.BUTTON_BORDER_WIDTH)
                    .hoverable(interactionSource = interactionSource)
                    .focusable(enabled = true, interactionSource = interactionSource),
            shape =
                RoundedCornerShape(UICCornerRadius.borderRadius.medium),
            colors =
                IconButtonDefaults.iconButtonColors(
                    containerColor = style.background,
                    contentColor = style.iconTint,
                ),
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .border(
                            width = UICIconButtonConstants.BUTTON_BORDER_WIDTH,
                            color = style.borderColor,
                            shape = RoundedCornerShape(UICCornerRadius.borderRadius.medium),
                        )
                        .alpha(alpha = style.alpha),
                contentAlignment = Alignment.Center,
            ) {
                iconVector?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = contentDescription,
                        tint = style.iconTint,
                        modifier = Modifier.size(buttonSize.iconSize),
                    )
                }

                if (showBadge && !badgeCount.isNullOrEmpty()) {
                    Box(
                        modifier =
                            Modifier
                                .align(Alignment.TopEnd)
                                .heightIn(UICIconButtonConstants.BADGE_SIZE)
                                .widthIn(min = UICIconButtonConstants.BADGE_SIZE + UICIconButtonConstants.BUTTON_BORDER_WIDTH)
                                .clip(CircleShape)
                                .background(style.badgeColor)
                                .border(
                                    UICIconButtonConstants.BADGE_BORDER_WIDTH,
                                    UICTheme.colorScheme.neutral.white,
                                    CircleShape,
                                ),
                        contentAlignment = Alignment.Center,
                    ) {
                        CompositionLocalProvider(
                            LocalDensity provides
                                Density(
                                    LocalDensity.current.density,
                                    fontScale = minOf(a = LocalDensity.current.fontScale, b = 1f),
                                ),
                        ) {
                            CoreCaptionTextView(
                                text = badgeCount,
                                isBold = true,
                                color = style.badgeTextColor,
                                textAlign = TextAlign.Center,
                                modifier =
                                    Modifier
                                        .padding(
                                            start = UICIconButtonConstants.BUTTON_BORDER_WIDTH,
                                            end = UICIconButtonConstants.BUTTON_BORDER_WIDTH,
                                        ),
                            )
                        }
                    }
                }
            }
        }
    }
}

enum class IconButtonVariant(
    val color: @Composable () -> Color,
    @param:StringRes val labelResId: Int,
) {
    HEADER_LIGHT({ UICTheme.colorScheme.neutral.transparent }, labelResId = R.string.light_header),
    HEADER_DARK({ UICTheme.colorScheme.brand.primary.core }, labelResId = R.string.dark_header),
    NEUTRAL({ UICTheme.colorScheme.brand.primary.onCore }, labelResId = R.string.neutral),
    LIGHT({ UICTheme.colorScheme.neutral.transparent }, labelResId = R.string.light),
}

enum class ButtonState { ENABLED, DISABLED, PRESSED, HOVER, FOCUSED }

enum class UICIconButtonSize(
    val buttonSize: Dp,
    val iconSize: Dp,
) {
    SMALL(buttonSize = 32.dp, iconSize = 16.dp),
    MEDIUM(buttonSize = 36.dp, iconSize = 20.dp),
    LARGE(buttonSize = 40.dp, iconSize = 24.dp),
    EXTRA_LARGE(buttonSize = 48.dp, iconSize = 32.dp),
}

private data class IconButtonStyle(
    val background: Color,
    val borderColor: Color,
    val iconTint: Color,
    val badgeColor: Color,
    val badgeTextColor: Color,
    val alpha: Float = 1f,
)

private object IconButtonStyleProvider {
    @Composable
    fun getStyle(
        variant: IconButtonVariant,
        state: ButtonState,
    ): IconButtonStyle {
        val scheme = UICTheme.colorScheme
        return when (variant) {
            IconButtonVariant.HEADER_LIGHT ->
                when (state) {
                    ButtonState.ENABLED ->
                        IconButtonStyle(
                            background = scheme.neutral.transparent,
                            borderColor = scheme.neutral.transparent,
                            iconTint = scheme.brand.primary.core,
                            badgeColor = scheme.brand.coupon.core,
                            badgeTextColor = scheme.brand.coupon.onCore,
                        )

                    ButtonState.DISABLED ->
                        IconButtonStyle(
                            background = scheme.neutral.gray1,
                            borderColor = scheme.neutral.white,
                            iconTint =
                                scheme.brand.primary.core
                                    .copy(alpha = DISABLED_ALPHA),
                            badgeColor = scheme.brand.coupon.core,
                            badgeTextColor = scheme.brand.coupon.onCore,
                            alpha = 0.5f,
                        )

                    ButtonState.PRESSED ->
                        IconButtonStyle(
                            background = scheme.brand.primary.bg,
                            borderColor = scheme.brand.primary.focus,
                            iconTint = scheme.brand.primary.core,
                            badgeColor = scheme.brand.coupon.core,
                            badgeTextColor = scheme.brand.coupon.onCore,
                        )

                    ButtonState.HOVER ->
                        IconButtonStyle(
                            background = scheme.brand.primary.bg,
                            borderColor = scheme.neutral.transparent,
                            iconTint = scheme.brand.primary.core,
                            badgeColor = scheme.brand.coupon.core,
                            badgeTextColor = scheme.brand.coupon.onCore,
                        )

                    ButtonState.FOCUSED ->
                        IconButtonStyle(
                            background = scheme.neutral.transparent,
                            borderColor = scheme.brand.primary.focus,
                            iconTint = scheme.brand.primary.core,
                            badgeColor = scheme.brand.coupon.core,
                            badgeTextColor = scheme.brand.coupon.onCore,
                        )
                }

            IconButtonVariant.HEADER_DARK ->
                when (state) {
                    ButtonState.ENABLED ->
                        IconButtonStyle(
                            background = scheme.neutral.transparent,
                            borderColor = scheme.neutral.transparent,
                            iconTint = scheme.txt.primaryInverse,
                            badgeColor = scheme.brand.coupon.core,
                            badgeTextColor = scheme.brand.coupon.onCore,
                        )

                    ButtonState.DISABLED ->
                        IconButtonStyle(
                            background = scheme.neutral.transparent,
                            borderColor = scheme.neutral.transparent,
                            iconTint =
                                scheme.txt.primaryInverse
                                    .copy(alpha = DISABLED_ALPHA),
                            badgeColor = scheme.brand.coupon.core,
                            badgeTextColor = scheme.brand.coupon.onCore,
                            alpha = 0.5f,
                        )

                    ButtonState.PRESSED ->
                        IconButtonStyle(
                            background = scheme.txt.primary.copy(alpha = PRESSED_CONTENT_ALPHA),
                            borderColor = scheme.structural.outlineLight,
                            iconTint = scheme.txt.primaryInverse,
                            badgeColor = scheme.brand.coupon.core,
                            badgeTextColor = scheme.brand.coupon.onCore,
                        )

                    ButtonState.HOVER ->
                        IconButtonStyle(
                            background = scheme.txt.primary.copy(alpha = PRESSED_CONTENT_ALPHA),
                            borderColor = scheme.brand.primary.focus,
                            iconTint = scheme.txt.primaryInverse,
                            badgeColor = scheme.brand.coupon.core,
                            badgeTextColor = scheme.brand.coupon.onCore,
                        )

                    ButtonState.FOCUSED ->
                        IconButtonStyle(
                            background = scheme.neutral.transparent,
                            borderColor = scheme.structural.outlineLight,
                            iconTint = scheme.txt.primaryInverse,
                            badgeColor = scheme.brand.coupon.core,
                            badgeTextColor = scheme.brand.coupon.onCore,
                        )
                }

            IconButtonVariant.NEUTRAL ->
                when (state) {
                    ButtonState.ENABLED ->
                        IconButtonStyle(
                            background = scheme.neutral.transparent,
                            borderColor = scheme.neutral.transparent,
                            iconTint = scheme.txt.secondary,
                            badgeColor = scheme.brand.coupon.core,
                            badgeTextColor = scheme.brand.coupon.onCore,
                        )

                    ButtonState.DISABLED ->
                        IconButtonStyle(
                            background = scheme.neutral.transparent,
                            borderColor = scheme.neutral.transparent,
                            iconTint =
                                scheme.txt.secondary
                                    .copy(alpha = DISABLED_ALPHA),
                            badgeColor = scheme.brand.coupon.core,
                            badgeTextColor = scheme.brand.coupon.onCore,
                            alpha = 0.5f,
                        )

                    ButtonState.PRESSED ->
                        IconButtonStyle(
                            background = scheme.structural.bgSecondary,
                            borderColor = scheme.structural.outlineLight,
                            iconTint = scheme.txt.secondary,
                            badgeColor = scheme.brand.coupon.core,
                            badgeTextColor = scheme.brand.coupon.onCore,
                        )

                    ButtonState.HOVER ->
                        IconButtonStyle(
                            background = scheme.structural.bgSecondary,
                            borderColor = scheme.neutral.transparent,
                            iconTint = scheme.txt.secondary,
                            badgeColor = scheme.brand.coupon.core,
                            badgeTextColor = scheme.brand.coupon.onCore,
                        )

                    ButtonState.FOCUSED ->
                        IconButtonStyle(
                            background = scheme.neutral.transparent,
                            borderColor = scheme.structural.outlineLight,
                            iconTint = scheme.txt.secondary,
                            badgeColor = scheme.brand.coupon.core,
                            badgeTextColor = scheme.brand.coupon.onCore,
                        )
                }

            IconButtonVariant.LIGHT ->
                when (state) {
                    ButtonState.ENABLED ->
                        IconButtonStyle(
                            background = scheme.neutral.transparent,
                            borderColor = scheme.neutral.transparent,
                            iconTint = scheme.brand.interactive.core,
                            badgeColor = scheme.brand.coupon.core,
                            badgeTextColor = scheme.brand.coupon.onCore,
                        )

                    ButtonState.DISABLED ->
                        IconButtonStyle(
                            background = scheme.neutral.transparent,
                            borderColor = scheme.neutral.transparent,
                            iconTint =
                                scheme.brand.interactive.core
                                    .copy(alpha = DISABLED_ALPHA),
                            badgeColor = scheme.brand.coupon.core,
                            badgeTextColor = scheme.brand.coupon.onCore,
                            alpha = 0.5f,
                        )

                    ButtonState.PRESSED ->
                        IconButtonStyle(
                            background = scheme.brand.interactive.bg,
                            borderColor = scheme.brand.interactive.focus,
                            iconTint = scheme.brand.interactive.core,
                            badgeColor = scheme.brand.coupon.core,
                            badgeTextColor = scheme.brand.coupon.onCore,
                        )

                    ButtonState.HOVER ->
                        IconButtonStyle(
                            background = scheme.brand.interactive.bg,
                            borderColor = scheme.neutral.transparent,
                            iconTint = scheme.brand.interactive.core,
                            badgeColor = scheme.brand.coupon.core,
                            badgeTextColor = scheme.brand.coupon.onCore,
                        )

                    ButtonState.FOCUSED ->
                        IconButtonStyle(
                            background = scheme.neutral.transparent,
                            borderColor = scheme.brand.interactive.focus,
                            iconTint = scheme.brand.interactive.core,
                            badgeColor = scheme.brand.coupon.core,
                            badgeTextColor = scheme.brand.coupon.onCore,
                        )
                }
        }
    }
}

private object UICIconButtonConstants {
    val BUTTON_BORDER_WIDTH = 2.dp
    val BADGE_BORDER_WIDTH = 1.dp
    val BADGE_SIZE = 15.dp
    const val DISABLED_ALPHA = 0.4f
    const val PRESSED_CONTENT_ALPHA = 0.3f
}

@Preview(name = "Small Icon Button", group = "Icon Buttons")
@Composable
fun PreviewSmallUICIconButton() {
    UICIconButton(
        onClick = { /* Handle click */ },
        badgeCount = "2",
        variant = IconButtonVariant.HEADER_LIGHT,
        buttonSize = UICIconButtonSize.SMALL,
        iconVector = accountAvatar(),
    )
}

@Preview(name = "medium Icon Button", group = "Icon Buttons")
@Composable
fun PreviewMediumUICIconButton() {
    UICIconButton(
        onClick = { /* Handle click */ },
        badgeCount = "2",
        buttonSize = UICIconButtonSize.MEDIUM,
        variant = IconButtonVariant.HEADER_DARK,
        iconVector = accountAvatar(),
    )
}

@Preview(name = "medium Icon Button", group = "Icon Buttons")
@Composable
fun PreviewLargeUICIconButton() {
    UICIconButton(
        onClick = { /* Handle click */ },
        badgeCount = "2",
        buttonSize = UICIconButtonSize.LARGE,
        variant = IconButtonVariant.NEUTRAL,
        iconVector = accountAvatar(),
    )
}

@Preview(name = "medium Icon Button", group = "Icon Buttons")
@Composable
fun PreviewXLUICIconButton() {
    UICIconButton(
        onClick = { /* Handle click */ },
        badgeCount = "9",
        showBadge = false,
        enabled = true,
        modifier = Modifier.background(Color.Gray),
        contentDescription = "asas",
        buttonSize = UICIconButtonSize.EXTRA_LARGE,
        variant = IconButtonVariant.LIGHT,
        iconVector = accountAvatar(),
    )
}
