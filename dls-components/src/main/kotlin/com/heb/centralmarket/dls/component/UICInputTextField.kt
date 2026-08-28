package com.heb.centralmarket.uicart.component

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isUnspecified
import androidx.compose.ui.unit.sp
import com.heb.centralmarket.uicart.themesystem.UICHeight
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography
import com.heb.centralmarket.uicart.utils.TestTags

/**
 * A custom, reusable text input field for the UIC design system.
 *
 * This composable provides a highly customizable `BasicTextField` wrapper that supports
 * floating labels, helper/error text, trailing icons, character counters, scaling,
 * and dynamic ellipsizing. It is intended to be the base text input component across the app.
 *
 * ### Features:
 * - **Floating label**: Label animates between inline and top-left when focused or filled.
 * - **Helper/Error text**: Shows optional supporting text or error messages below the field.
 * - **Trailing icon**: Optional composable icon (e.g., visibility toggle for passwords).
 * - **Character counter**: Displays live character count when `maxChar` is provided.
 * - **Ellipsizing**: Dynamically truncates text with ellipsis when not focused.
 * - **Scaling**: Font size, line height, and label styles scale with `scaleFactor`.
 * - **Keyboard control**: Supports `KeyboardOptions` and `KeyboardActions`
 *   for input type (email, password, number, etc.) and IME actions.
 * - **Accessibility**: Exposes content description and error semantics for screen readers.
 *
 * @param value Current text value of the input field.
 * @param onValueChange Lambda triggered when the input text changes.
 * @param modifier Modifier to be applied to the text field container.
 * @param label Floating label text displayed above/inside the field.
 * @param enabled Whether the field is editable or disabled.
 * @param isError Indicates whether the field is in an error state.
 * @param helperText Optional supporting/help text shown below the field.
 * @param errorText Optional error message shown below the field when `isError` is true.
 * @param placeholder Optional placeholder text displayed when the field is empty and unfocused.
 * @param trailingIcon Optional composable icon displayed at the end of the input row.
 * @param maxChar Maximum allowed characters (shows counter if provided).
 * @param singleLine Whether the field is constrained to a single line.
 * @param maxLines Maximum number of visible lines for multiline text.
 * @param scaleFactor Scale factor applied to font size, label size, and line height.
 * @param overflow Defines how text that exceeds max lines is handled (default = Ellipsis).
 * @param keyboardOptions Keyboard configuration (e.g., email, password, number, etc.).
 * @param keyboardActions IME action callbacks (e.g., Done hides keyboard, Next moves focus).
 * @param visualTransformation Transforms input visually (e.g., password dots).
 * @param isMultilineTextAreaNeeded If true, the field behaves like a fixed-height text area
 *   (always reserves space up to `maxLines`). If false and multiline, the field wraps content
 *   dynamically, expanding only as needed up to `maxLines`.
 */
@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun UICInputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    enabled: Boolean = true,
    isError: Boolean = false,
    helperText: String? = null,
    errorText: String? = null,
    placeholder: String? = null,
    trailingIcon: (@Composable (() -> Unit))? = null,
    maxChar: Int? = null,
    showCharIndicator: Boolean = false,
    singleLine: Boolean = true,
    isMultilineTextAreaNeeded: Boolean = false,
    maxLines: Int = 1,
    scaleFactor: Float = 0f,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    var isFocused by remember { mutableStateOf(false) }
    var fieldWidth by remember { mutableIntStateOf(0) }
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val helperTextFontSize = UICTypography.typography.caption.regular.fontSize
    val scaledHelperFontSize = (helperTextFontSize.value * scaleFactor).sp
    val baseFontSize = UICTypography.typography.body.body1.regular.fontSize
    var textState by remember { mutableStateOf(TextFieldValue(value)) }
    var wasFocused by remember { mutableStateOf(false) }

    LaunchedEffect(value) {
        if (!isFocused || textState.text != value) {
            textState = textState.copy(text = value)
        }
    }

    val scaledSize =
        if (baseFontSize.isUnspecified) {
            (UICInputTextFieldDefaults.DefaultFontSizeSp.value * scaleFactor).sp
        } else {
            (baseFontSize.value * scaleFactor).sp
        }
    val indicatorColor =
        when {
            isError -> UICTheme.colorScheme.negative.core
            isFocused -> UICTheme.colorScheme.brand.interactive.core
            else -> UICTheme.colorScheme.structural.outlineLight
        }
    val indicatorThickness =
        when {
            !enabled -> UICInputTextFieldDefaults.ThinIndicator
            isError || isFocused -> UICInputTextFieldDefaults.ThickIndicator
            else -> UICInputTextFieldDefaults.ThinIndicator
        }
    val shouldFloat = isFocused || value.isNotEmpty()
    val targetLabelStyle =
        if (shouldFloat) {
            UICTypography.typography.body.body2.bold
        } else {
            UICTypography.typography.body.body1.regular
        }
    val scaledLabelStyle =
        targetLabelStyle.copy(
            fontSize = (targetLabelStyle.fontSize.value * scaleFactor).sp,
        )
    UICHeight.height.circularLoading
    val labelHeight = with(LocalDensity.current) { scaledLabelStyle.fontSize.toDp() }

    val revisedScaleFactor = if (scaleFactor == 1f) 1f else (-scaleFactor)
    val labelOffsetY by animateDpAsState(
        if (shouldFloat) (-1 * revisedScaleFactor).dp else UICInputTextFieldDefaults.LabelCollapsedOffsetY,
        label = "labelOffset",
    )
    val lineHeight = (UICInputTextFieldDefaults.DefaultLineHeightSp.value * scaleFactor).sp
    val lineHeightDp = with(LocalDensity.current) { lineHeight.toDp() }
    val density = LocalDensity.current
    val cutoffChars =
        remember(fieldWidth, scaledSize, singleLine, density) {
            if (fieldWidth == 0) return@remember Int.MAX_VALUE
            val charWidthPx =
                with(density) { scaledSize.toPx() } * UICInputTextFieldDefaults.CHAR_WIDTH_FACTOR
            val charsPerLine = (fieldWidth / charWidthPx).toInt()

            if (singleLine) {
                charsPerLine
            } else {
                charsPerLine * 4
            }
        }
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .semantics {
                    contentDescription = "$label input field"
                    if (isError && !errorText.isNullOrEmpty()) {
                        this.error(errorText)
                    }
                },
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            // Floating label
            Text(
                text = label,
                maxLines = 2,
                style =
                    scaledLabelStyle.copy(
                        lineHeight = (35 * (scaleFactor * UICInputTextFieldDefaults.LINE_HEIGHT_SCALE_COLLAPSED)).sp,
                        color =
                            when {
                                isError -> UICTheme.colorScheme.negative.core
                                !enabled -> UICTheme.colorScheme.txt.secondary
                                isFocused -> UICTheme.colorScheme.brand.interactive.core
                                else -> UICTheme.colorScheme.txt.primary
                            },
                    ),
                modifier = Modifier.offset(y = labelOffsetY),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // TextField itself
                BasicTextField(
                    value = textState,
                    onValueChange = { newValue ->
                        // Apply maxChar and special phone rules before updating state
                        val incomingText = newValue.text

                        val isPhone = keyboardOptions.keyboardType == androidx.compose.ui.text.input.KeyboardType.Phone

                        val processedText =
                            if (isPhone) {
                                // Allow only digits; block '0' or '1' in the first two positions.
                                // Build a filtered string respecting existing characters.
                                buildString {
                                    var keptCount = 0
                                    incomingText.forEach { ch ->
                                        if (!ch.isDigit()) {
                                            // skip non-digit
                                        } else {
                                            val isFirstTwo = keptCount < 1
                                            val isZeroOrOne = ch == '0' || ch == '1'
                                            if (isFirstTwo && isZeroOrOne) {
                                                // skip 0/1 in first two positions
                                            } else {
                                                append(ch)
                                                keptCount++
                                            }
                                        }
                                    }
                                }
                            } else {
                                incomingText
                            }

                        val finalText = if (maxChar == null) processedText else processedText.take(maxChar)

                        // If text didn't change after filtering, still update selection to keep UX smooth
                        if (finalText != textState.text || newValue.selection != textState.selection) {
                            // Place cursor at the new selection end but clamp to finalText length
                            val selEnd = finalText.length.coerceAtMost(newValue.selection.end)
                            val sel =
                                androidx.compose.ui.text
                                    .TextRange(selEnd)
                            textState = TextFieldValue(finalText, sel)
                            onValueChange(finalText)
                        }
                    },
                    modifier =
                        Modifier
                            .weight(1f)
                            .padding(
                                top = labelHeight + (8 * scaleFactor).dp,
                                bottom = UICInputTextFieldDefaults.PaddingTop,
                            )
                            .onFocusChanged { isFocused = it.isFocused }
                            .bringIntoViewRequester(bringIntoViewRequester)
                            .onGloballyPositioned { coordinates ->
                                fieldWidth = coordinates.size.width
                            }
                            .testTag(TestTags.AddressBook.inputField(label))
                            .then(
                                when {
                                    singleLine -> Modifier.height(lineHeightDp)

                                    isMultilineTextAreaNeeded -> Modifier.height(lineHeightDp * maxLines)

                                    else ->
                                        Modifier.heightIn(
                                            min = lineHeightDp,
                                            max = lineHeightDp * maxLines,
                                        )
                                },
                            ),
                    singleLine = singleLine,
                    textStyle =
                        UICTypography.typography.body.body1.regular.copy(
                            lineHeight = (35 * (scaleFactor * UICInputTextFieldDefaults.LINE_HEIGHT_SCALE_EXPANDED)).sp,
                            color = UICTheme.colorScheme.txt.primary,
                            fontSize = scaledSize,
                        ),
                    enabled = enabled,
                    keyboardOptions = keyboardOptions,
                    keyboardActions = keyboardActions,
                    visualTransformation = visualTransformation,
                    decorationBox = { innerTextField ->
                        val collapsedText =
                            if (!isFocused) {
                                val baseText = textState.text
                                if (baseText.length > cutoffChars) {
                                    baseText.take(cutoffChars) + "…"
                                } else {
                                    baseText
                                }
                            } else {
                                textState.text
                            }

                        if (!isFocused) {
                            Text(
                                text = collapsedText,
                                maxLines = if (isMultilineTextAreaNeeded) 4 else 1,
                                overflow = TextOverflow.Ellipsis,
                                style =
                                    UICTypography.typography.body.body1.regular.copy(
                                        color = UICTheme.colorScheme.txt.primary,
                                        fontSize = scaledSize,
                                    ),
                            )
                        } else {
                            innerTextField()
                        }
                    },
                )
                trailingIcon?.invoke()
            }
        }
        HorizontalDivider(
            thickness = indicatorThickness,
            color = indicatorColor,
        )
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = UICInputTextFieldDefaults.PaddingTop),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            when {
                isError && !errorText.isNullOrEmpty() ->
                    Text(
                        errorText,
                        style =
                            UICTypography.typography.caption.regular.copy(
                                lineHeight =
                                    (
                                        UICInputTextFieldDefaults.HELPER_TEXT_HEIGHT *
                                            (scaleFactor * UICInputTextFieldDefaults.HELPER_TEXT_SCALING_VALUE)
                                    ).sp,
                                color = UICTheme.colorScheme.negative.core,
                                fontSize = scaledHelperFontSize,
                            ),
                        modifier = Modifier.weight(1f, fill = false),
                    )

                !helperText.isNullOrEmpty() && !isError ->
                    Text(
                        helperText,
                        style =
                            UICTypography.typography.caption.regular.copy(
                                lineHeight =
                                    (
                                        UICInputTextFieldDefaults.HELPER_TEXT_HEIGHT *
                                            (scaleFactor * UICInputTextFieldDefaults.HELPER_TEXT_SCALING_VALUE)
                                    ).sp,
                                color = UICTheme.colorScheme.txt.secondary,
                                fontSize = scaledHelperFontSize,
                            ),
                        modifier = Modifier.weight(1f, fill = false),
                    )

                else -> {
                    Spacer(modifier = Modifier.weight(1f, fill = false))
                }
            }

            if (maxChar != null) {
                if (showCharIndicator) {
                    Text(
                        style =
                            UICTypography.typography.caption.regular.copy(
                                color = UICTheme.colorScheme.txt.secondary,
                                fontSize = scaledHelperFontSize,
                            ),
                        text = "${value.length}/$maxChar",
                    )
                }
            }
        }
    }

    // Place cursor at end when field is focused for the first time after being unfocused
    LaunchedEffect(isFocused) {
        if (isFocused && !wasFocused) {
            textState =
                textState.copy(
                    selection =
                        androidx.compose.ui.text
                            .TextRange(textState.text.length),
                )
        }
        wasFocused = isFocused
    }
}

private object UICInputTextFieldDefaults {
    // Typography fallbacks
    val DefaultFontSizeSp = 16.sp
    val DefaultLineHeightSp = 20.sp
    val LabelCollapsedOffsetY = 24.dp

    // Scaling multipliers
    const val CHAR_WIDTH_FACTOR = 0.55f
    const val LINE_HEIGHT_SCALE_COLLAPSED = 0.35f
    const val LINE_HEIGHT_SCALE_EXPANDED = 0.50f

    // Indicator thickness
    val ThinIndicator = 1.dp
    val ThickIndicator = 2.dp

    const val HELPER_TEXT_HEIGHT = 35
    const val HELPER_TEXT_SCALING_VALUE = 0.35

    val PaddingTop = 6.dp
}
