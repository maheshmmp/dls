package com.heb.centralmarket.uicart.component
/**
 * Author: Ritu Varma G
 * Date Created: 02-08-2025
 * Last Modified: 02-08-2025
 */

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICHeight
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.utils.TestTags

/**
 * ## EmptyCart Composable
 *
 * A reusable UI component that represents an **empty state cart view**.
 * This component can dynamically display an image (from a **URL** or **local drawable resource**),
 * a title (heading text), and an optional body text (description).
 *
 * ### Features:
 * - Accepts **dynamic image sources**: remote URL or local drawable resource.
 * - Supports **placeholder image** while loading network images.
 * - Flexible layout with customizable **title heading level** and **body text variant**.
 * - Aligns content vertically centered in the available space.
 *
 * ### Parameters:
 * @param modifier Modifier to be applied to the root layout.
 * @param imageModel Image source, which can be either a **String URL** or a **DrawableRes ID (Int)**.
 * @param contentDesc Content description for accessibility (null if not needed).
 * @param titleText Title text to be displayed as heading (will be uppercased).
 * @param bodyText Optional body text (description message below title).
 * @param bodyContentMaxLine Maximum number of lines for the body text (default is 2).
 *
 */
@Composable
fun EmptyListPlaceHolder(
    modifier: Modifier? = Modifier,
    imageModel: Any,
    contentDesc: String? = null,
    titleText: String,
    bodyText: String? = null,
    bodyContentMaxLine: Int = 2
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = imageModel,
            contentDescription = contentDesc,
            modifier =
                modifier
                    ?.wrapContentSize()
                    ?.heightIn(max = UICHeight.height.imageHolderHeight) ?: Modifier.wrapContentSize(),
            contentScale = ContentScale.Fit,
            placeholder = if (imageModel is String) painterResource(R.drawable.uic_ic_settings_gear) else null,
        )
        Column(
            modifier =
                Modifier
                    .padding(
                        horizontal = UICSpacing.spacing.spacing100,
                        vertical = UICSpacing.spacing.spacing100
                    ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CoreHeadingTextView(
                text = titleText.uppercase(),
                headingLevel = HeadingVariant.HEADING_3,
                color = UICTheme.colorScheme.txt.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.testTag(TestTags.OrderAhead.EMPTY_CART_TEXT)
            )
            if (bodyText?.isNotEmpty() == true) {

                Spacer(modifier = Modifier.height(UICSpacing.spacing.spacing050))

                CoreBodyTextView(
                    text = bodyText,
                    bodyVariant = BodyVariant.BODY_1,
                    maxLines = bodyContentMaxLine,
                    color = UICTheme.colorScheme.txt.primary,
                    textAlign = TextAlign.Center,
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmptyCartWithImageAndContent() {
    UICAppTheme {
        CoreBackground(modifier = Modifier.size(200.dp, 100.dp)) {
            EmptyListPlaceHolder(
                imageModel = R.drawable.uic_ic_shop,
                contentDesc = stringResource(R.string.section_description),
                titleText = stringResource(R.string.empty_cart_your_cart_title),
                bodyText = stringResource(R.string.empty_cart_your_cart_body),
                bodyContentMaxLine = 2,
                modifier = Modifier
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewEmptyCartWithImageAndTitle() {
    UICAppTheme {
        CoreBackground(modifier = Modifier.size(200.dp, 100.dp)) {
            EmptyListPlaceHolder(
                imageModel = R.drawable.uic_ic_shop,
                contentDesc = stringResource(R.string.section_description),
                titleText = stringResource(R.string.empty_cart_your_cart_title),
                modifier = Modifier
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewEmptyCartWithoutImageAndWithContent() {
    UICAppTheme {
        CoreBackground(modifier = Modifier.size(200.dp, 100.dp)) {
            EmptyListPlaceHolder(
                imageModel = R.drawable.uic_ic_shop,
                contentDesc = stringResource(R.string.section_description),
                titleText = stringResource(R.string.empty_cart_your_cart_title),
                bodyText = stringResource(R.string.empty_cart_your_cart_body),
                bodyContentMaxLine = 2,
                modifier = Modifier.size(100.dp)
            )
        }
    }
}