/*
 * Created by Mahesh Mathew Paul on 13/02/25, 2:52 pm
 * mahesh.paul@ust.com
 * Last modified 13/02/25, 2:51 pm
 * Copyright (c) 2025.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.component

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.heb.centralmarket.uicart.component.buttons.CoreButtonSize
import com.heb.centralmarket.uicart.component.buttons.UICPrimaryButton
import com.heb.centralmarket.uicart.icons.trashIcon
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PrimaryButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private var buttonText: String = "ICON BUTTON"
    private var wasClicked = false
    private var isEnabled = true
    private var enablePressed = false
    private var buttonSize = CoreButtonSize.MEDIUM
    private var leadingIcon: (@Composable () -> Unit)? = null

    @Before
    fun setUp() {
        wasClicked = false
        composeTestRule.setContent {
            UICAppTheme {
                UICPrimaryButton(
                    onClick = { wasClicked = true },
                    buttonText = buttonText,
                    enabled = isEnabled,
                    buttonSize = buttonSize,
                    leadingIcon = leadingIcon,
                )
            }
        }
        testConfig(
            "ICON BUTTON",
            icon = {
                Icon(
                    imageVector = trashIcon(),
                    contentDescription = "Leading Icon",
                )
            },
        )
    }

    private fun testConfig(
        text: String,
        enabled: Boolean = true,
        pressed: Boolean = false,
        size: CoreButtonSize = CoreButtonSize.MEDIUM,
        icon: (@Composable () -> Unit)? = null,
    ) {
        buttonText = text
        isEnabled = enabled
        enablePressed = pressed
        buttonSize = size
        leadingIcon = icon
    }

    @Test
    fun primaryButton_whenEnabled_displaysCorrectStylingAndText() {
        composeTestRule
            .onNode(hasText(buttonText))
            .assertExists()
            .assertIsEnabled()
            .assertHasClickAction()
            .assertHeightIsAtLeast(CoreButtonSize.MEDIUM.height.dp)
    }
}
