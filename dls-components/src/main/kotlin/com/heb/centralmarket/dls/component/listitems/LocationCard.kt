/*
 *
 *  Created by Mahesh Paul on 2/12/26, 1:05 PM
 *  Copyright (c) 2026 . All rights reserved.
 *  Last modified 2/12/26, 1:00 PM
 *
 */

package com.heb.centralmarket.uicart.component.listitems

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.component.BodyVariant
import com.heb.centralmarket.uicart.component.CoreBodyTextView
import com.heb.centralmarket.uicart.component.buttons.CoreButtonSize
import com.heb.centralmarket.uicart.component.buttons.UICSecondaryButton
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.icons.CoreIcon
import com.heb.centralmarket.uicart.icons.pin
import com.heb.centralmarket.uicart.themesystem.UICShape
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.utils.TestTags

/**
 * Displays a card with store location details, including location name, address lines, and an action button.
 *
 * @param locationName The name of the store.
 * @param addressLine1 The first line of the address (optional).
 * @param addressLine2 The second line of the address (optional).
 * @param addressLine3 The third line of the address (optional).
 * @param actionButtonText The text to display on the action button.
 * @param modifier Modifier to be applied to the card.
 * @param onActionButtonClick Callback invoked when the action button is clicked.
 * @param debounceDelayMillis The delay in milliseconds to debounce the action button click.
 */
@Composable
fun LocationCard(
    modifier: Modifier = Modifier,
    locationName: String,
    addressLine1: String? = null,
    addressLine2: String? = null,
    addressLine3: String? = null,
    actionButtonText: String,
    onActionButtonClick: () -> Unit,
    debounceDelayMillis: Long = 500L
) {
    Card(
        shape = UICShape.shapes.mediumRoundCornerShape,
        border = BorderStroke(
            UICLocationCardDefaults.OutlinedButtonBorderWidth,
            color = UICTheme.colorScheme.structural.outlineLight
        ),
        colors = CardDefaults.cardColors(
            containerColor = UICTheme.colorScheme.structural.bgPrimary
        ),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(all = UICSpacing.spacing.spacing100)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CoreIcon(
                    imageVector = pin(),
                    contentDescription = stringResource(id = R.string.location_icon_content_description),
                    tint = UICTheme.colorScheme.txt.primary,
                    size = UICLocationCardDefaults.LocationIconSize
                )

                Spacer(modifier = Modifier.width(width = UICSpacing.spacing.spacing050))

                CoreBodyTextView(
                    text = locationName,
                    bodyVariant = BodyVariant.BODY_1,
                    isBold = true,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.testTag(TestTags.Fulfillment.LOCATION_NAME)
                )
            }
            Spacer(modifier = Modifier.height(height = UICSpacing.spacing.spacing025))

            Column(modifier = Modifier.padding(start = UICLocationCardDefaults.AddressLeftMarginWidth)) {
                if (!addressLine1.isNullOrBlank()) {
                    CoreBodyTextView(
                        text = addressLine1,
                        bodyVariant = BodyVariant.BODY_1,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.testTag(TestTags.Fulfillment.LOCATION_ADDRESS)
                    )
                }
                if (!addressLine2.isNullOrBlank()) {
                    CoreBodyTextView(
                        text = addressLine2,
                        bodyVariant = BodyVariant.BODY_1,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.testTag(TestTags.Fulfillment.LOCATION_DETAILS)
                    )
                }
                if (!addressLine3.isNullOrBlank()) {
                    CoreBodyTextView(
                        text = addressLine3,
                        bodyVariant = BodyVariant.BODY_1,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.testTag(TestTags.Fulfillment.LOCATION_DETAILS)
                    )
                }
            }

            Spacer(modifier = Modifier.height(height = UICSpacing.spacing.spacing100))

            UICSecondaryButton(
                onClick = onActionButtonClick,
                buttonText = actionButtonText,
                buttonSize = CoreButtonSize.SMALL,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(TestTags.Fulfillment.CHANGE_BUTTON),
                isButtonFullWidth = true,
            )
        }
    }
}

object UICLocationCardDefaults {
    val OutlinedButtonBorderWidth = 1.dp
    val LocationIconSize = 20.dp
    val AddressLeftMarginWidth = 28.dp
}