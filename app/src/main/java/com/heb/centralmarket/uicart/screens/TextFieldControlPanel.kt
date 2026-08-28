/*
 * *
 *  * Created by Mahesh Mathew Paul on 12/3/25, 1:01 PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  * Last modified 12/3/25, 12:10 PM
 *
 */

package com.heb.centralmarket.uicart.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

@Composable
fun TextFieldControlPanel(
    isEnabled: Boolean,
    onEnabledChange: (Boolean) -> Unit,
    textSize: Float,
    onTextSizeChange: (Float) -> Unit,
    state: String,
    onStateChange: (String) -> Unit,
    isMultiline: Boolean,
    onMultilineChange: (Boolean) -> Unit,
    isCharCountNeeded: Boolean,
    onCharCountChange: (Boolean) -> Unit,
    isTextAreaIncreased: Boolean,
    onTextAreaChanged: (Boolean) -> Unit,
    charLimit: Int,
    onCharLimitChange: (Int) -> Unit,
) {
    val context = LocalContext.current
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(
            text = stringResource(R.string.control_panel),
            style =
                UICTypography.typography.heading.h3
                    .copy(color = UICTheme.colorScheme.txt.primary),
        )

        // Toggle enable
//         Row(verticalAlignment = Alignment.CenterVertically) {
//             Text("Enabled: ")
//             Switch(checked = isEnabled, onCheckedChange = onEnabledChange)
//         }
        // Text size slider
//         Column {
//             Text("Text Size scale: ${textSize.toInt()} X default sp")
//             Slider(
//                 value = textSize,
//                 onValueChange = onTextSizeChange,
//                 valueRange = 1f..6f
//             )
//         }

        // State radio buttons
        Column {
            Text(
                text = stringResource(R.string.state_for_dynamic_helper_text_and_icons),
                style =
                    UICTypography.typography.body.body1.regular
                        .copy(color = UICTheme.colorScheme.txt.primary),
            )
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = state == stringResource(R.string.normal), onClick = {
                        onStateChange(
                            context.getString(
                                R.string.normal,
                            ),
                        )
                    })
                    Text(
                        stringResource(R.string.normal),
                        style =
                            UICTypography.typography.body.body1.regular
                                .copy(color = UICTheme.colorScheme.txt.primary),
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = state == stringResource(R.string.error), onClick = {
                        onStateChange(
                            context.getString(
                                R.string.error,
                            ),
                        )
                    })
                    Text(
                        stringResource(R.string.error),
                        style =
                            UICTypography.typography.body.body1.regular
                                .copy(color = UICTheme.colorScheme.txt.primary),
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = state == stringResource(R.string.warning), onClick = {
                        onStateChange(
                            context.getString(
                                R.string.warning,
                            ),
                        )
                    })
                    Text(
                        stringResource(R.string.warning),
                        style =
                            UICTypography.typography.body.body1.regular
                                .copy(color = UICTheme.colorScheme.txt.primary),
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = state == stringResource(R.string.helper), onClick = {
                        onStateChange(
                            context.getString(
                                R.string.helper,
                            ),
                        )
                    })
                    Text(
                        stringResource(R.string.no_helper),
                        style =
                            UICTypography.typography.body.body1.regular
                                .copy(color = UICTheme.colorScheme.txt.primary),
                    )
                }
            }
        }

        // Multiline toggle
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    stringResource(R.string.multiline),
                    style =
                        UICTypography.typography.body.body1.regular
                            .copy(color = UICTheme.colorScheme.txt.primary),
                )
                Switch(checked = isMultiline, onCheckedChange = onMultilineChange)
            }

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    stringResource(R.string.char_limit),
                    style =
                        UICTypography.typography.body.body1.regular
                            .copy(color = UICTheme.colorScheme.txt.primary),
                )
                Switch(checked = isCharCountNeeded, onCheckedChange = onCharCountChange)
            }

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    stringResource(R.string.text_area),
                    style =
                        UICTypography.typography.body.body1.regular
                            .copy(color = UICTheme.colorScheme.txt.primary),
                )
                Switch(checked = isTextAreaIncreased, onCheckedChange = onTextAreaChanged)
            }
        }

        // Character limit slider
        Column {
            Text(
                stringResource(R.string.character_limit, charLimit),
                style =
                    UICTypography.typography.body.body1.regular
                        .copy(color = UICTheme.colorScheme.txt.primary),
            )
            Slider(
                value = charLimit.toFloat(),
                onValueChange = { onCharLimitChange(it.toInt()) },
                valueRange = 10f..200f,
            )
        }
    }
}
