package com.heb.centralmarket.uicart.component.listitems

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.component.BodyVariant
import com.heb.centralmarket.uicart.component.CoreBodyTextView
import com.heb.centralmarket.uicart.component.HeadingVariant
import com.heb.centralmarket.uicart.component.buttons.IconButtonVariant
import com.heb.centralmarket.uicart.component.buttons.UICIconButton
import com.heb.centralmarket.uicart.component.buttons.UICIconButtonSize
import com.heb.centralmarket.uicart.component.buttons.UICLinkButton
import com.heb.centralmarket.uicart.component.buttons.UICLinkButtonStyle
import com.heb.centralmarket.uicart.component.buttons.UICRadioButton
import com.heb.centralmarket.uicart.component.listitems.UICLocationDetailsConstants.ROW_WEIGHT
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.icons.circleInfoIcon
import com.heb.centralmarket.uicart.icons.infoIcon
import com.heb.centralmarket.uicart.icons.orderAheadCart
import com.heb.centralmarket.uicart.icons.pen
import com.heb.centralmarket.uicart.icons.pin
import com.heb.centralmarket.uicart.icons.trashIcon
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICHeight
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.utils.TestTags

/**

 * A reusable composable list item to display address-related information.
 *
 * @param modifier Modifier to be applied to the component
 * @param isEditable Whether the item shows edit/delete buttons or radio button
 * @param onStoreInfoClicked Callback when store info button is clicked
 * @param onEditClicked Callback when edit button is clicked
 * @param onDeleteClicked Callback when delete button is clicked
 * @param onSelected Callback when radio button selection changes
 * @param isSelected Whether the radio button is selected
 * @param showDivider Whether to show bottom divider line
 * @param uicLocationDetails The location details object containing all location information
 *
 * Functionality:
 * - Shows title, optional description, and optional delivery notes.
 * - In editable mode: displays Edit & Delete icons with callbacks.
 * - In selectable mode: displays a RadioButton with selected state and callback.
 * - Optionally shows a bottom divider.
 *
 * Accessibility:
 * - Merges text fields into a single accessibility node.
 */
@Composable
fun UICLocationDetails(
    modifier: Modifier = Modifier,
    isEditable: Boolean = false,
    onStoreInfoClicked: (() -> Unit?)? = null,
    onEditClicked: (() -> Unit?)? = null,
    onDeleteClicked: (() -> Unit?)? = null,
    onSelected: ((Boolean) -> Unit?)? = null,
    isSelected: Boolean = false,
    uicLocationDetails: UICLocationDetailsObj,
    showDivider: Boolean,
) {
    val columnHeightPx = remember { mutableIntStateOf(0) }
    val density = LocalDensity.current
    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .let {
                    if (onSelected != null) {
                        it.clickable {
                            onSelected.invoke(!isSelected)
                        }
                    } else {
                        it
                    }
                }.testTag(TestTags.StoreSelect.STORE_SELECTION_ROW),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(UICTheme.colorScheme.structural.bgPrimary)
                    .padding(
                        start = UICSpacing.spacing.spacing100,
                        bottom = UICSpacing.spacing.spacing100,
                    ),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier =
                    Modifier
                        .wrapContentHeight()
                        .weight(ROW_WEIGHT)
                        .onGloballyPositioned { coordinates ->
                            columnHeightPx.intValue = coordinates.size.height
                        }.semantics(mergeDescendants = true) {
                            contentDescription =
                                buildString {
                                    append(uicLocationDetails.locationName)
                                    if (uicLocationDetails.locationAddressLineOne != null) {
                                        append(". ")
                                        append(uicLocationDetails.locationAddressLineOne)
                                    }
                                }
                        },
            ) {
                CoreBodyTextView(
                    text = uicLocationDetails.locationName,
                    bodyVariant = BodyVariant.BODY_1,
                    modifier =
                        Modifier
                            .padding(top = UICSpacing.spacing.spacing100)
                            .testTag(TestTags.StoreSelect.STORE_NAME),
                    // Clear individual semantics
                    isBold = true,
                    textAlign = TextAlign.Start,
                )

                Column(
                    modifier =
                        Modifier
                            .padding(top = UICSpacing.spacing.spacing050),
                ) {
                    if (!uicLocationDetails.locationAddressLineOne.isNullOrBlank()) {
                        CoreBodyTextView(
                            text = uicLocationDetails.locationAddressLineOne,
                            bodyVariant = BodyVariant.BODY_1,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.testTag(TestTags.AddressBook.ADDRESS_TEXT)

                        )
                    }
                    if (!uicLocationDetails.locationAddressLineTwo.isNullOrBlank()) {
                        CoreBodyTextView(
                            text = uicLocationDetails.locationAddressLineTwo,
                            bodyVariant = BodyVariant.BODY_1,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.testTag(TestTags.AddressBook.ADDRESS_TEXT)
                        )
                    }
                    if (!uicLocationDetails.locationAddressLineThree.isNullOrBlank()) {
                        CoreBodyTextView(
                            text = uicLocationDetails.locationAddressLineThree,
                            bodyVariant = BodyVariant.BODY_1,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.testTag(TestTags.AddressBook.ADDRESS_DETAILS_TEXT)
                        )
                    }
                }
                if (uicLocationDetails.variant == UICLocationDetailItemVariant.DELIVERY_ADDRESS &&
                    !uicLocationDetails.deliveryNotes.isNullOrEmpty()
                ) {
                    CoreBodyTextView(
                        text = uicLocationDetails.deliveryNotes,
                        bodyVariant = BodyVariant.BODY_2,
                        modifier = Modifier.padding(top = UICSpacing.spacing.spacing050),
                        textAlign = TextAlign.Start,
                        color = UICTheme.colorScheme.txt.secondary,
                    )
                } else if (uicLocationDetails.variant == UICLocationDetailItemVariant.STORE_LOCATION) {
                    FlowRow(
                        modifier = Modifier.padding(top = UICSpacing.spacing.spacing050),
                        verticalArrangement = Arrangement.spacedBy(UICSpacing.spacing.spacing100),
                        horizontalArrangement = Arrangement.spacedBy(UICSpacing.spacing.spacing100),
                    ) {
                        if (uicLocationDetails.locationData != null) {
                            StoreInfotext(
                                iconVector = pin(),
                                text = uicLocationDetails.locationData,
                                iconColor = UICTheme.colorScheme.brandSecondary.icon,
                                bodyVariant = BodyVariant.BODY_2,
                                color = UICTheme.colorScheme.txt.primary,
                                isBold = false,
                            )
                        }
                        if (uicLocationDetails.isOrderAheadAvailable) {
                            StoreInfotext(
                                iconVector = orderAheadCart(),
                                text = "Order Ahead Available",
                                iconColor = UICTheme.colorScheme.brandSecondary.icon,
                                bodyVariant = BodyVariant.BODY_2,
                                color = UICTheme.colorScheme.txt.primary,
                                isBold = false,
                            )
                        }
                    }
                }
            }
            Column(
                modifier =
                    Modifier
                        .wrapContentHeight()
                        .height(with(density) { columnHeightPx.intValue.toDp() }),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End,
            ) {
                if (uicLocationDetails.variant == UICLocationDetailItemVariant.STORE_LOCATION && uicLocationDetails.hasStoreInfo) {
                    UICLinkButton(
                        modifier =
                            Modifier.padding(
                                end = UICSpacing.spacing.spacing025,
                                top = UICSpacing.spacing.spacing025,
                            ),
                        onClick = { onStoreInfoClicked?.invoke() },
                        buttonText = stringResource(R.string.store_info),
                        leadingIcon = {
                            Icon(
                                imageVector = circleInfoIcon(),
                                contentDescription = stringResource(R.string.store_info),
                            )
                        },
                        enabled = true,
                        isButtonFullWidth = false,
                        buttonStyle = UICLinkButtonStyle.REGULAR_BOLD,
                    )
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    if (isEditable) {
                        Row(
                            modifier = Modifier
                                .padding(end = UICSpacing.spacing.spacing050),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            UICIconButton(
                                onClick = { onEditClicked?.invoke() },
                                buttonSize = UICIconButtonSize.SMALL,
                                iconVector = pen(),
                                variant = IconButtonVariant.LIGHT,
                                modifier = modifier
                                    .padding(end = UICSpacing.spacing.spacing050)
                                    .testTag(TestTags.AddressBook.ADDRESS_EDIT_BUTTON),
                            )

                            VerticalDivider(
                                thickness = UICHeight.height.divider,
                                modifier =
                                    Modifier
                                        .height(height = UICSpacing.spacing.spacing150),
                                color = UICTheme.colorScheme.structural.outlineLight,
                            )

                            UICIconButton(
                                onClick = { onDeleteClicked?.invoke() },
                                buttonSize = UICIconButtonSize.SMALL,
                                iconVector = trashIcon(),
                                variant = IconButtonVariant.NEUTRAL,
                                modifier =
                                    modifier
                                        .padding(start = UICSpacing.spacing.spacing050)
                                        .testTag(TestTags.AddressBook.ADDRESS_TRASH_BUTTON),
                            )
                        }
                    } else {
                        UICRadioButton(
                            selected = isSelected,
                            onClick = { onSelected?.invoke(!isSelected) },
                            contentDescription = "",
                            modifier =
                                if (uicLocationDetails.variant == UICLocationDetailItemVariant.STORE_LOCATION) {
                                    Modifier
                                        .padding(end = UICSpacing.spacing.spacing050)
                                        .testTag(TestTags.StoreSelect.RADIO_BUTTON)
                                } else {
                                    Modifier
                                        .padding(end = UICSpacing.spacing.spacing050)
                                        .testTag(TestTags.StoreSelect.RADIO_BUTTON)
                                },
                        )
                    }
                }
            }
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

@Composable
fun StoreInfotext(
    modifier: Modifier = Modifier,
    text: String,
    iconVector: ImageVector = infoIcon(),
    iconColor: Color = UICTheme.colorScheme.brand.interactive.core,
    bodyVariant: Int = HeadingVariant.HEADING_2,
    maxLines: Int = Int.MAX_VALUE,
    color: Color = UICTheme.colorScheme.txt.primary,
    isBold: Boolean = true,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign? = TextAlign.Start,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement =
            Arrangement.spacedBy(
                UICSpacing.spacing.spacing025,
            ),
    ) {
        Icon(
            modifier = Modifier.size(UICSpacing.spacing.spacing075),
            imageVector = iconVector,
            contentDescription = "contentDescription",
            tint = iconColor,
        )

        CoreBodyTextView(
            text = text,
            bodyVariant = bodyVariant,
            isBold = isBold,
            color = color,
            modifier = modifier,
            maxLines = maxLines,
            overflow = overflow,
            textAlign = textAlign,
        )
    }
}

@Composable
@Preview(
    name = "AddressListItem Preview",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
)
fun UICLocationDetailsPreview() {
    UICAppTheme {
        UICLocationDetails(
            isEditable = true,
            onEditClicked = {},
            onDeleteClicked = {},
            onSelected = {},
            uicLocationDetails =
                UICLocationDetailsObj(
                    locationName = "Delivery Notifications Get notified when your order is out for delivery",
                    locationAddressLineOne =
                        "Get notified when your order is out for delivery test for multi line",
                    locationAddressLineTwo = "Address Line two",
                    locationAddressLineThree = "Address Line three",
                    deliveryNotes = "optional",
                    locationData = null,
                    isOrderAheadAvailable = false,
                    hasStoreInfo = true,
                    variant = UICLocationDetailItemVariant.STORE_LOCATION,
                ),
            isSelected = true,
            showDivider = true,
        )
    }
}

@Composable
@Preview(
    name = "AddressListItem Preview",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
)
fun UICLocationDetailsNotEditablePreview() {
    UICAppTheme {
        UICLocationDetails(
            isEditable = false,
            onEditClicked = {},
            onDeleteClicked = {},
            onSelected = {},
            uicLocationDetails =
                UICLocationDetailsObj(
                    locationName = "Delivery Notifications ",
                    locationAddressLineOne =
                        "Get notified when your order is out for " +
                            "delivery test for multi line to check arrangement with icons are correct",
                    locationAddressLineTwo = "Address Line two",
                    locationAddressLineThree = "Address Line three",
                    deliveryNotes = "Optional",
                    locationData = "2.72 mi",
                    isOrderAheadAvailable = true,
                    hasStoreInfo = true,
                    variant = UICLocationDetailItemVariant.STORE_LOCATION,
                ),
            showDivider = true,
        )
    }
}

@Composable
@Preview(
    name = "AddressListItem Preview",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
)
fun UICLocationDetailsIsSelectedPreview() {
    UICAppTheme {
        UICLocationDetails(
            isEditable = false,
            onEditClicked = {},
            onDeleteClicked = {},
            onSelected = {},
            isSelected = true,
            uicLocationDetails =
                UICLocationDetailsObj(
                    locationName = "Delivery Notifications ",
                    locationAddressLineOne =
                        "Get notified when your order is out for delivery",
                    locationAddressLineTwo = "Address Line two",
                    locationAddressLineThree = "Address Line three",
                    deliveryNotes = "Optional delivery not wrapping test  if content is long enough on next line",
                    locationData = null,
                    isOrderAheadAvailable = false,
                    hasStoreInfo = false,
                    variant = UICLocationDetailItemVariant.DELIVERY_ADDRESS,
                ),
            showDivider = true,
        )
    }
}

/**
 * Displays a static list of shimmer placeholder rows that visually mimic [UICLocationDetails].
 * Useful during loading states while address list items are being fetched.
 *
 * @param itemRows Number of placeholder items to display.
 * @param showDivider Whether to show a divider line below each item.
 */
@Composable
fun AddressListItemShimmer(
    itemRows: Int,
    modifier: Modifier = Modifier,
    showDivider: Boolean = true,
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .background(color = UICTheme.colorScheme.structural.bgPrimary),
    ) {
        repeat(times = itemRows) { index ->
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
            ) {
                Column(
                    modifier =
                        Modifier
                            .padding(all = UICSpacing.spacing.spacing100),
                    verticalArrangement = Arrangement.spacedBy(space = UICSpacing.spacing.spacing050),
                ) {
                    // Title Placeholder
                    ShimmerBox(
                        modifier =
                            Modifier
                                .fillMaxWidth(fraction = 0.2f)
                                .height(height = UICHeight.height.shimmerRowItemHeight),
                    )
                    ShimmerBox(
                        modifier =
                            Modifier
                                .fillMaxWidth(fraction = 0.5f)
                                .height(height = UICHeight.height.shimmerRowItemHeight),
                    )
                    ShimmerBox(
                        modifier =
                            Modifier
                                .fillMaxWidth(fraction = 0.3f)
                                .height(height = UICHeight.height.shimmerRowItemHeight),
                    )
                }

                if (showDivider) {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = UICTheme.colorScheme.structural.outlineLight,
                        modifier =
                            Modifier
                                .align(Alignment.BottomCenter)
                                .testTag(tag = "AddressListItemShimmer/Divider_$index"),
                    )
                }
            }
        }
    }
}

@Composable
@Preview(
    name = "AddressListItemShimmer Preview",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
)
fun AddressListItemShimmerPreview() {
    UICAppTheme {
        AddressListItemShimmer(
            itemRows = 2,
            showDivider = true,
        )
    }
}

enum class UICLocationDetailItemVariant {
    STORE_LOCATION,
    DELIVERY_ADDRESS,
}

private object UICLocationDetailsConstants {
    const val TAG_SECTION_DIVIDER = "UICLocationDetails/SectionDivider"
    const val ROW_WEIGHT = 1f
}

data class UICLocationDetailsObj(
    val locationName: String,
    val locationAddressLineOne: String?,
    val locationAddressLineTwo: String?,
    val locationAddressLineThree: String?,
    val deliveryNotes: String?,
    val locationData: String?,
    val isOrderAheadAvailable: Boolean,
    val hasStoreInfo: Boolean,
    val variant: UICLocationDetailItemVariant = UICLocationDetailItemVariant.DELIVERY_ADDRESS,
)
