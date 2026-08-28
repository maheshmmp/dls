/*
 * *
 *  * Created by Mahesh Mathew Paul on 11/4/25, 12:28 AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  * Last modified 11/4/25, 12:28 AM
 *
 */

package com.heb.centralmarket.uicart.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import kotlinx.coroutines.delay

@Composable
fun rememberAutoFocusRequester(): FocusRequester {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        delay(200)
        focusRequester.requestFocus()
        keyboardController?.show()
    }
    return focusRequester
}
