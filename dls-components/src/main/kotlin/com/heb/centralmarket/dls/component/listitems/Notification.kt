/*
 * Created by Mahesh Mathew Paul on 12/03/25, 6:57 pm
 * mahesh.paul@ust.com
 * Last modified 12/03/25, 1:55 pm
 * Copyright (c) 2025.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.component.listitems

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.heb.centralmarket.uicart.component.BodyVariant
import com.heb.centralmarket.uicart.component.CoreBodyTextView
import com.heb.centralmarket.uicart.component.buttons.UICToggleButton
import com.heb.centralmarket.uicart.shimmer
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICShape
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme

/**
 * A composable function that displays a notification row with a title, description,
 * and a toggle button to subscribe/unsubscribe from the notification.
 *
 * @param isSubscribed A Boolean indicating whether the user is subscribed to the notification.
 * @param onSubscriptionChanged A lambda function that is triggered when the toggle button state changes.
 * @param notificationTitle The title of the notification.
 * @param notificationDescription A brief description of the notification.
 * @param modifier A [Modifier] for customizing the layout appearance of the composable.
 * @param showDivider This flag is used to decide whether the divider needs to be shown or hidden.
 */
@Composable
fun PreferenceRowItem(
    modifier: Modifier = Modifier,
    isSubscribed: Boolean,
    onSubscriptionChanged: (Boolean) -> Unit,
    notificationTitle: String,
    notificationDescription: String? = null,
    showDivider: Boolean,
) {
    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .wrapContentHeight(),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(UICTheme.colorScheme.structural.bgPrimary)
                    .padding(
                        horizontal = UICSpacing.spacing.spacing100,
                        vertical = UICSpacing.spacing.spacing100,
                    ),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier =
                    Modifier
                        .wrapContentHeight()
                        .weight(1f)
                        .padding(end = UICSpacing.spacing.spacing100)
                        .semantics(mergeDescendants = true) {
                            // Merge all text content into a single accessibility node
                            contentDescription = buildString {
                                append(notificationTitle)
                                if (notificationDescription != null) {
                                    append(". ")
                                    append(notificationDescription)
                                }
                            }
                        },
            ) {
                CoreBodyTextView(
                    text = notificationTitle,
                    bodyVariant = BodyVariant.BODY_1,
                    modifier =
                        if (notificationDescription == null) {
                            Modifier
                                .padding(top = UICSpacing.spacing.spacing050)
                                .clearAndSetSemantics { } // Clear individual semantics
                        } else {
                            Modifier
                                .padding(top = UICSpacing.spacing.spacing000)
                                .clearAndSetSemantics { } // Clear individual semantics
                        },
                    isBold = true,
                    textAlign = TextAlign.Start,
                )
                if (notificationDescription != null) {
                    CoreBodyTextView(
                        text = notificationDescription,
                        bodyVariant = BodyVariant.BODY_2,
                        modifier = Modifier.padding(top = UICSpacing.spacing.spacing050),
                        textAlign = TextAlign.Start,
                    )
                }
            }

            UICToggleButton(
                toggleCheck = isSubscribed,
                toggleDescription = notificationTitle,
                onToggleChange = onSubscriptionChanged,
                modifier =
                    Modifier
                        .wrapContentHeight()
                        .padding(vertical = UICSpacing.spacing.spacing000)
                        .align(Alignment.Top),
            )
        }

        if (showDivider) {
            SectionDivider(
                modifier =
                    Modifier
                        .testTag("SectionDivider")
                        .clearAndSetSemantics { } // Clear individual semantics
                        .align(Alignment.BottomStart),
                dividerColor = UICTheme.colorScheme.structural.outlineLight,
            )
        }
    }
}

/**
 * Displays a static list of shimmer placeholder rows that visually mimic [PreferenceRowItem].
 * Useful during loading states while notification preferences are being fetched.
 *
 * Each row consists of a placeholder for:
 * - Title (always shown)
 * - Optional description text
 * - Toggle button
 * - Optional bottom divider
 *
 * @param paddingValues The padding to apply around the shimmer list content.
 * @param itemRows Number of placeholder items to display.
 * @param showDescription Whether to show a placeholder for the description text in each item.
 * @param showDivider Whether to show a divider line below each item.
 */
@Composable
fun PreferenceRowItemShimmer(
    paddingValues: PaddingValues,
    itemRows: Int,
    showDescription: Boolean = true,
    showDivider: Boolean = true
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(UICTheme.colorScheme.structural.bgSecondary)
            .padding(paddingValues)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(UICTheme.colorScheme.structural.bgPrimary)
                .padding(
                    horizontal = UICSpacing.spacing.spacing100,
                    vertical = UICSpacing.spacing.spacing100
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ShimmerBox(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(UICSpacing.spacing.spacing150)
            )
            ShimmerBox(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(UICSpacing.spacing.spacing150)
            )
        }
        repeat(itemRows) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(UICTheme.colorScheme.structural.bgPrimary)
                        .padding(
                            horizontal = UICSpacing.spacing.spacing100,
                            vertical = UICSpacing.spacing.spacing100
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .weight(1f)
                            .padding(end = UICSpacing.spacing.spacing100)
                    ) {
                        // Title Placeholder
                        ShimmerBox(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .height(UICSpacing.spacing.spacing150)
                        )

                        if (showDescription) {
                            ShimmerBox(
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .height(UICSpacing.spacing.spacing100)
                                    .padding(top = UICSpacing.spacing.spacing050)
                            )
                            ShimmerBox(
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .height(UICSpacing.spacing.spacing100)
                                    .padding(top = UICSpacing.spacing.spacing050)
                            )
                            ShimmerBox(
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .height(UICSpacing.spacing.spacing100)
                                    .padding(top = UICSpacing.spacing.spacing050)
                            )
                        }
                    }

                    // Toggle Placeholder
                    ShimmerBox(
                        modifier = Modifier
                            .size(
                                width = UICSpacing.spacing.spacing300,
                                height = UICSpacing.spacing.spacing200
                            )
                            .align(Alignment.Top)
                    )
                }
            }
        }
    }
}

@Composable
fun ShimmerBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(
                color = UICTheme.colorScheme.structural.outlineLight,
                shape = UICShape.shapes.smallRoundCornerShape
            )
            .shimmer()
    )
}


@Preview(showBackground = true)
@Composable
fun PreferenceRowItemPreview() {
    UICAppTheme {
        Surface {
            var isSubscribed by remember { mutableStateOf(false) }
            PreferenceRowItem(
                isSubscribed = isSubscribed,
                onSubscriptionChanged = { isSubscribed = it },
                notificationTitle = "Receive Festival Emails",
                notificationDescription = "Need some delicious inspiration? Don't miss anything that looks cool",
                showDivider = true,
            )
        }
    }
}
