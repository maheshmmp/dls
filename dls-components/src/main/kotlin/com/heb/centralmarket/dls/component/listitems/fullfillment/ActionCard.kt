/*
 *
 *  Created by Mahesh Paul on 2/2/26, 1:50 PM
 *  Copyright (c) 2026 . All rights reserved.
 *  Last modified 2/2/26, 1:31 PM
 *
 */

package com.heb.centralmarket.uicart.component.listitems.fullfillment

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import com.heb.centralmarket.uicart.component.BodyVariant
import com.heb.centralmarket.uicart.component.CoreBodyTextView
import com.heb.centralmarket.uicart.component.listitems.UICLocationCardDefaults
import com.heb.centralmarket.uicart.icons.CoreIcon
import com.heb.centralmarket.uicart.themesystem.UICShape
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.utils.TestTags
import com.heb.centralmarket.uicart.utils.rememberDebouncedClick

/**
 * A customizable action card component with an icon, title, optional description, and click behavior.
 *
 * @param title The title text displayed on the card.
 * @param description Optional description text displayed below the title.
 * @param leftIcon The icon displayed on the left side of the card.
 * @param leftIconContentDescription Content description for the left icon for accessibility.
 * @param leftIconTint Optional tint color for the left icon. Defaults to no tint.
 * @param rightIcon The icon displayed on the right side of the card.
 * @param rightIconContentDescription Content description for the right icon for accessibility.
 * @param rightIconTint Optional tint color for the right icon. Defaults to no tint.
 * @param modifier Modifier to be applied to the card.
 * @param onActionCardClick Optional callback invoked when the card is clicked.
 * @param debounceDelayMillis The delay in milliseconds before the click is processed.
 * @param isWarning Whether the card is a warning card.
 */
@Composable
fun ActionCard(
    modifier: Modifier = Modifier,
    title: String? = null,
    description: String? = null,
    leftIcon: ImageVector,
    leftIconContentDescription: String,
    leftIconTint: Color? = null,
    rightIcon: ImageVector,
    rightIconContentDescription: String,
    rightIconTint: Color? = null,
    onActionCardClick: (() -> Unit)? = null,
    debounceDelayMillis: Long = 500L,
    isWarning: Boolean? = false
) {
    val interactions = remember { MutableInteractionSource() }
    val isPressed by interactions.collectIsPressedAsState()

    val debouncedOnClick: (() -> Unit)? =
        rememberDebouncedClick(onActionCardClick, debounceDelayMillis)

    Card(
        shape = UICShape.shapes.mediumRoundCornerShape,
        border = BorderStroke(
            UICLocationCardDefaults.OutlinedButtonBorderWidth,
            color = UICTheme.colorScheme.structural.outlineLight
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (isPressed) {
                UICTheme.colorScheme.structural.bgSecondary
            } else {
                UICTheme.colorScheme.structural.bgPrimary
            }
        ),
        modifier = modifier
            .fillMaxWidth()
            .indication(interactionSource = interactions, null)
            .clickable(
                interactionSource = interactions,
                indication = null,
                onClick = { debouncedOnClick?.invoke() })
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(all = UICSpacing.spacing.spacing100)
        ) {
            CoreIcon(
                imageVector = leftIcon,
                contentDescription = leftIconContentDescription,
                tint = leftIconTint ?: Color.Unspecified,
                size = UICSpacing.spacing.spacing125
            )

            Spacer(modifier = Modifier.width(width = UICSpacing.spacing.spacing075))

            Column(modifier = Modifier.weight(1f)) {
                CoreBodyTextView(
                    text = title ?: "",
                    bodyVariant = BodyVariant.BODY_1,
                    color = UICTheme.colorScheme.brand.interactive.core,
                    isBold = true,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag(TestTags.Fulfillment.SELECT_TIMESLOT_TEXT)
                )

                if (description != null) {
                    CoreBodyTextView(
                        text = description,
                        bodyVariant = BodyVariant.BODY_2,
                        color = if (isWarning == true) UICTheme.colorScheme.negative.core else UICTheme.colorScheme.txt.primary,
                        isBold = false,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = UICSpacing.spacing.spacing025)
                            .testTag(TestTags.Fulfillment.SELECT_TIMESLOT_DETAILS)
                    )
                }
            }

            Spacer(modifier = Modifier.width(width = UICSpacing.spacing.spacing075))

            CoreIcon(
                imageVector = rightIcon,
                contentDescription = rightIconContentDescription,
                tint = rightIconTint ?: Color.Unspecified,
            )
        }
    }
}