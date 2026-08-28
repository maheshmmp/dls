/*
 * Created by Mahesh Mathew Paul on 20/02/25, 2:11 pm
 * mahesh.paul@ust.com
 * Last modified 20/02/25, 2:11 pm
 * Copyright (c) 2025.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.heb.centralmarket.uicart.component.listitems.PreferenceRowItem
import org.junit.Rule
import org.junit.Test

class NotificationsUITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun preferenceRowItem_displaysCorrectContentAndHandlesToggle() {
        val testTitle = "Test Notification"
        val testDescription = "This is a test notification description."
        var isSubscribed = false

        composeTestRule.setContent {
            PreferenceRowItem(
                isSubscribed = isSubscribed,
                onSubscriptionChanged = { isSubscribed = it },
                notificationTitle = testTitle,
                notificationDescription = testDescription,
                showDivider = true,
            )
        }

        // Verify if the title and description are displayed
        composeTestRule.onNodeWithText(testTitle).assertIsDisplayed()
        composeTestRule.onNodeWithText(testDescription).assertIsDisplayed()

        // Verify if the toggle button is displayed
        val toggleButton = composeTestRule.onNodeWithContentDescription(testTitle)
        toggleButton.assertIsDisplayed()

        // Click the toggle button and verify state change
        toggleButton.performClick()
        composeTestRule.runOnIdle {
            assert(isSubscribed) // Ensure the toggle state changed
        }

        // Verify if the divider is displayed
        composeTestRule.onNodeWithTag("SectionDivider").assertIsDisplayed()
    }
}
