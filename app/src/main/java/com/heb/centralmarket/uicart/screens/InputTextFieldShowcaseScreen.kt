/*
 * *
 *  * Created by Mahesh Mathew Paul on 12/3/25, 1:01 PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  * Last modified 12/3/25, 12:07 PM
 *
 */

package com.heb.centralmarket.uicart.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.UICInputTextField
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.themesystem.UICTheme

@Composable
fun InputTextFieldShowcaseScreen(
    navController: NavController,
    drawerState: DrawerState,
) {
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    // 👇 centralize state here
    var isEnabled by remember { mutableStateOf(true) }
    var textSize by remember { mutableFloatStateOf(1f) }
    var state by remember { mutableStateOf("Normal") }
    var isMultiline by remember { mutableStateOf(false) }
    var isTextAreaIncreased by remember { mutableStateOf(false) }
    var isCharCountNeeded by remember { mutableStateOf(false) }
    var charLimit by remember { mutableIntStateOf(550) }

    Scaffold(
        topBar = {
            AppBar(
                title = R.string.input_text_field,
                drawerState = drawerState,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = chevronLeft(),
                            contentDescription = stringResource(id = R.string.back),
                            tint = UICTheme.colorScheme.txt.primary,
                        )
                    }
                },
                actionIcon = {
                    ThemeSwitcherAction()
                    DarkModeSwitcherAction()
                },
            )
        },
    ) { innerPadding ->
        CoreBackground(modifier = Modifier.padding(innerPadding)) {
            androidx.compose.foundation.layout.Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                UICInputTextField(
                    modifier = Modifier.padding(16.dp),
                    value = text,
                    onValueChange = { text = it },
                    label = stringResource(R.string.label),
                    enabled = isEnabled,
                    singleLine = !isMultiline,
                    maxLines = if (isMultiline) 4 else 1,
                    isMultilineTextAreaNeeded = isTextAreaIncreased,
                    maxChar = if (isCharCountNeeded) charLimit else null,
                    isError = state == stringResource(R.string.error),
                    errorText = if (state == stringResource(R.string.error)) stringResource(R.string.error_message) else null,
                    helperText =
                        when (state) {
                            stringResource(R.string.warning) -> stringResource(R.string.this_is_a_warning)
                            stringResource(R.string.helper) -> null
                            else -> stringResource(R.string.helper)
                        },
                    scaleFactor = textSize,
                    trailingIcon = {
                        when (state) {
                            "Error" ->
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Error",
                                    tint = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.size(18.dp),
                                )

                            "Warning" ->
                                Icon(
                                    imageVector = Icons.Default.Warning,
                                    contentDescription = "Warning",
                                    tint = MaterialTheme.colorScheme.tertiary,
                                    modifier = Modifier.size(18.dp),
                                )
                        }
                    },
                    keyboardOptions =
                        KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text, // Type of keyboard (text, number, email, password, etc.)
                            imeAction = ImeAction.Done, // Action button on the keyboard (Done, Next, Search, etc.)
                        ),
                    keyboardActions =
                        KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                            },
                            onSearch = {
                                focusManager.clearFocus()
                            },
                        ),
                )
                HorizontalDivider(modifier = Modifier.height(10.dp), color = Color.Blue)
                TextFieldControlPanel(
                    isEnabled = isEnabled,
                    onEnabledChange = { isEnabled = it },
                    textSize = textSize,
                    onTextSizeChange = { textSize = it },
                    state = state,
                    onStateChange = { state = it },
                    isMultiline = isMultiline,
                    onMultilineChange = { isMultiline = it },
                    isCharCountNeeded = isCharCountNeeded,
                    onCharCountChange = { isCharCountNeeded = it },
                    charLimit = charLimit,
                    onCharLimitChange = { charLimit = it },
                    isTextAreaIncreased = isTextAreaIncreased,
                    onTextAreaChanged = { isTextAreaIncreased = it },
                )
            }
        }
    }
}
