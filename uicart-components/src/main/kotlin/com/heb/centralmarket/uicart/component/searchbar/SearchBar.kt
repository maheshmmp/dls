/*
 * *
 *  * Created by 160857 on 9/11/25, 6:30 PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  * Last modified 9/11/25, 6:19 PM
 *
 */

package com.heb.centralmarket.uicart.component.searchbar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.heb.centralmarket.uicart.component.BodyVariant
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.CoreBodyTextView
import com.heb.centralmarket.uicart.component.buttons.CoreIconButton
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.icons.closeVector
import com.heb.centralmarket.uicart.icons.search
import com.heb.centralmarket.uicart.themesystem.UICShape
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography
import com.heb.centralmarket.uicart.utils.TestTags

private const val ANIM_FADE_IN_DURATION = 200
private const val ANIM_FADE_OUT_DURATION = 150
private const val SEARCH_BAR_ANIM_LABEL = "RightIconAnimation"

/**
 * A reusable search bar component with:
 * - Search icon on the left
 * - Editable text field with placeholder
 * - Close or custom right icon with fade animation
 *
 * @param modifier Modifier for layout customization
 * @param query Current text value of the search bar
 * @param onQueryChange Callback triggered on text change
 * @param placeholder Placeholder text (shown when empty and not focused)
 * @param rightIcon Optional custom right-side icon (e.g., barcode scanner)
 */
@Composable
fun UICSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchCleared: () -> Unit,
    placeholder: String = stringResource(id = R.string.searchbar_placeholder),
    rightIcon: (@Composable (() -> Unit))? = null
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(value = false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = UICTheme.colorScheme.structural.bgSecondary,
                shape = UICShape.shapes.smallRoundCornerShape
            )
            .padding(horizontal = UICSpacing.spacing.spacing075),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = search(),
            contentDescription = stringResource(id = R.string.search_icon_content_description),
            tint = UICTheme.colorScheme.brand.primary.core,
            modifier = modifier.testTag(TestTags.AddressBook.SEARCH_ICON)
        )

        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            singleLine = true,
            textStyle = UICTypography.typography.body.body1.regular.copy(
                color = UICTheme.colorScheme.txt.primary
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    focusManager.clearFocus()
                    onQueryChange(query)
                }
            ),
            cursorBrush = SolidColor(value = UICTheme.colorScheme.brand.primary.core),
            modifier = Modifier
                .weight(weight = 1f)
                .padding(vertical = UICSpacing.spacing.spacing025)
                .focusRequester(focusRequester)
                .onFocusChanged { state -> isFocused = state.isFocused }
                .testTag(TestTags.AddressBook.SEARCH_BAR)
        ) { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = UICSpacing.spacing.spacing050)
            ) {
                if (query.isEmpty() && !isFocused) {
                    CoreBodyTextView(
                        text = placeholder,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        bodyVariant = BodyVariant.BODY_1,
                        color = UICTheme.colorScheme.txt.secondary
                    )
                }
                innerTextField()
            }
        }

        var showClose by remember { mutableStateOf(value = query.isNotEmpty()) }

        LaunchedEffect(key1 = query) {
            showClose = query.isNotEmpty()
        }

        if (rightIcon == null) {
            AnimatedVisibility(
                visible = showClose,
                enter = fadeIn(animationSpec = tween(durationMillis = ANIM_FADE_IN_DURATION)),
                exit = fadeOut(animationSpec = tween(durationMillis = ANIM_FADE_OUT_DURATION))
            ) {
                CoreIconButton(
                    iconVector = closeVector(),
                    contentDescription = stringResource(id = R.string.close_icon_content_description),
                    onClick = {
                        onQueryChange("")
                        onSearchCleared
                        focusManager.clearFocus()
                    }
                )
            }
        } else {
            AnimatedContent(
                targetState = if (showClose) RightIconType.Close else RightIconType.Custom,
                transitionSpec = {
                    fadeIn(animationSpec = tween(durationMillis = ANIM_FADE_IN_DURATION))
                        .togetherWith(exit = fadeOut(animationSpec = tween(durationMillis = ANIM_FADE_OUT_DURATION)))
                },
                label = SEARCH_BAR_ANIM_LABEL
            ) { target ->
                when (target) {
                    RightIconType.Close -> {
                        CoreIconButton(
                            iconVector = closeVector(),
                            contentDescription = stringResource(id = R.string.close_icon_content_description),
                            onClick = {
                                showClose = false
                                onQueryChange("")
                                focusManager.clearFocus()
                            },
                        )
                    }
                    RightIconType.Custom -> {
                        rightIcon()
                    }
                }
            }
        }

    }
}

enum class RightIconType {
    Close,
    Custom
}

@Preview(
    name = "SearchBar – Close (Light)",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun SearchBarPreview_Close_Light() {
    var query by remember { mutableStateOf("Wine") }

    CoreBackground {
        UICSearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearchCleared = {}
        )
    }
}

@Preview(
    name = "SearchBar – Close (Dark)",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SearchBarPreview_Close_Dark() {
    var query by remember { mutableStateOf("Wine") }

    CoreBackground {
        UICSearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearchCleared = {}
        )
    }
}

@Preview(
    name = "SearchBar – Barcode (Light)",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun SearchBarPreview_Barcode_Light() {
    var query by remember { mutableStateOf("Bread") }

    CoreBackground {
        UICSearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearchCleared = {}
        )
    }
}

@Preview(
    name = "SearchBar – Barcode (Dark)",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SearchBarPreview_Barcode_Dark() {
    var query by remember { mutableStateOf("Bread") }

    CoreBackground {
        UICSearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearchCleared = {}
            )
    }
}