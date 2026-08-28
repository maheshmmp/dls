package com.heb.centralmarket.uicart.component.bottomSheet

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.themesystem.UICExtendedTheme
import com.heb.centralmarket.uicart.themesystem.UICHeight
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.utils.TestTags

/**
 * ## AnimatedBottomSheetHeader
 *
 * A composable header for the `UICBottomModalSheet` that animates its appearance and layout
 * based on the user's scroll interaction.
 *
 * ### Functional Overview
 * - Displays a drag handle, an optional title, and a close (`X`) icon.
 * - Animates between expanded and collapsed states based on scroll offset.
 * - Dynamically transitions background and icon colors using `animateColorAsState`.
 * - Adjusts height between **120.dp (expanded)** and **60.dp (collapsed)**.
 * - Returns the measured height in pixels, which is useful for offsetting scrollable content below it.
 *
 * ### Visual Behavior
 * - When scrolled upward (`collapseFraction` → 1):
 *   - Header height shrinks.
 *   - Background animates to `primary.core`.
 *   - Title and close icon align horizontally (like an AppBar).
 * - When expanded (`collapseFraction` → 0):
 *   - Header height increases.
 *   - Background is transparent.
 *   - Title (if present) appears below the close icon.
 *
 * ### Parameters
 * @param title Optional title text displayed below or beside the close icon.
 *               When null, the header background remains transparent.
 * @param scrollOffset The current scroll position (in pixels) of the sheet’s content.
 *                     Used to determine the collapse animation state.
 * @param onClose Callback invoked when the close (`X`) icon is pressed.
 * @param modifier Modifier for additional layout customization.
 *
 * ### Returns
 * Returns the current header height **in pixels** as an `Int`.
 * This value is useful when you need to offset the body content below the header dynamically.
 *
 */
@SuppressLint("ComposableNaming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatedBottomSheetHeader(
    title: String?,
    scrollOffset: Float,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
): Int {
    val collapsedThreshold = 120f
    val collapseFraction = (scrollOffset / collapsedThreshold).coerceIn(0f, 1f)
    val interactions = remember { MutableInteractionSource() }

    val bgColor by animateColorAsState(
        targetValue =
            if (!title.isNullOrBlank()) {
                if (collapseFraction > 0.5f) {
                    Color.Transparent
//                    UICTheme.colorScheme.brand.primary.core
                } else {
                    Color.Transparent
                }
            } else {
                Color.Transparent
            },
    )

    // Icon tint color animation
    val iconTint by animateColorAsState(
        targetValue =
            if (bgColor == Color.Transparent) {
                UICTheme.colorScheme.structural.bgPrimary
            } else {
                UICTheme.colorScheme.txt.primaryInverse
            },
    )

    val collapsedHeight by animateDpAsState(
        if (collapseFraction > 0.8f) {
            UICHeight.height.large
        } else {
            UICModalBottomSheetDefaults.LARGE_ACTION_BAR
        },
    )
    val heightPx = with(LocalDensity.current) { collapsedHeight.value.toInt() }

    Surface(
        color = if (title.isNullOrBlank()) Color.Transparent else bgColor,
        tonalElevation =
            if (!title.isNullOrBlank() && collapseFraction > 0.5f) {
                UICSpacing.spacing.spacing025
            } else {
                UICSpacing.spacing.spacing000
            },
        shape =
            RoundedCornerShape(
                topStart = UICSpacing.spacing.spacing100,
                topEnd = UICSpacing.spacing.spacing100,
            ),
        modifier =
            modifier
                .fillMaxWidth()
                .height(collapsedHeight)
                .zIndex(1f), // keeps header above scroll content
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = UICSpacing.spacing.spacing075,
                        vertical = UICSpacing.spacing.spacing050,
                    ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {
            Box(
                modifier =
                    width(UICHeight.height.large)
                        .height(UICSpacing.spacing.spacing025)
                        .background(
                            color =
                                if (bgColor == Color.Transparent) {
                                    UICTheme.colorScheme.txt.secondary
                                } else {
                                    UICTheme.colorScheme.brand.buttonPrimary.onCore
                                },
                            shape = RoundedCornerShape(UICSpacing.spacing.spacing025),
                        ),
            )

            Spacer(modifier = height(UICSpacing.spacing.spacing050))

            if (collapseFraction < 0.8f) {
                Column(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(UICSpacing.spacing.spacing025),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        modifier =
                            height(UICSpacing.spacing.spacing150)
                                .width(UICSpacing.spacing.spacing150)
                                .background(
                                    color = UICExtendedTheme.colorScheme.systemBlack.copy(alpha = .7f),
                                    shape = RoundedCornerShape(UICSpacing.spacing.spacing200),
                                ).align(alignment = Alignment.Start)
                                .indication(interactionSource = interactions, null)
                                .clickable(
                                    interactionSource = interactions,
                                    indication = null,
                                    onClick = onClose,
                                ),
                    ) {
                        Icon(
                            modifier =
                                size(UICSpacing.spacing.spacing100)
                                    .align(alignment = Alignment.Center)
                                    .testTag(TestTags.DiscardModal.X_BUTTON),
                            painter = painterResource(R.drawable.uic_ic_xclear),
                            contentDescription = "Close",
                            tint = Color.White,
                        )
                    }

                   /* if (!title.isNullOrBlank()) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = title,
                                style =
                                    UICTypography.typography.title.t2
                                        .copy(color = Color.Blue),
                            )
                        }
                    }*/
                }
            } else {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(UICHeight.height.actionBarHeight),
                ) {
                    Box(
                        modifier =
                            height(UICSpacing.spacing.spacing150)
                                .width(UICSpacing.spacing.spacing150)
                                .background(
                                    color = UICExtendedTheme.colorScheme.systemBlack.copy(alpha = .7f),
                                    shape = RoundedCornerShape(UICSpacing.spacing.spacing200),
                                ).align(alignment = Alignment.CenterStart)
                                .indication(interactionSource = interactions, null)
                                .clickable(
                                    interactionSource = interactions,
                                    indication = null,
                                    onClick = onClose,
                                ),
                    ) {
                        Icon(
                            modifier =
                                size(UICSpacing.spacing.spacing100)
                                    .align(alignment = Alignment.Center),
                            painter = painterResource(R.drawable.uic_ic_xclear),
                            contentDescription = "Close",
                            tint = Color.White,
                        )
                    }
                }

                /*if (!title.isNullOrBlank()) {
                    Text(
                        text = title,
                        style =
                            UICTypography.typography.title.t2
                                .copy(color = iconTint),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.align(Alignment.Center),
                    )
                }*/
            }
        }
    }

    return heightPx
}

private object UICModalBottomSheetDefaults {
    val LARGE_ACTION_BAR = 60.dp
}
