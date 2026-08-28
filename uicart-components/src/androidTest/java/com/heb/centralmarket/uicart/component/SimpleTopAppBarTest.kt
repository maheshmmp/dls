/*
 * Created by Mahesh Mathew Paul on 14/02/25, 12:27 pm
 * mahesh.paul@ust.com
 * Last modified 06/02/25, 4:06 pm
 * Copyright (c) 2025.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.component

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.heb.centralmarket.uicart.component.buttons.CoreIconButton
import com.heb.centralmarket.uicart.component.buttons.IconButtonSize
import com.heb.centralmarket.uicart.components.R
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SimpleTopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private var navigationClicked = false
    private var actionClicked = false
    private var currentTheme = AppBarTheme.PRIMARY

    private fun setTestContent(
        theme: AppBarTheme = AppBarTheme.PRIMARY,
        onNavigationClick: () -> Unit = { navigationClicked = true },
        onActionClick: () -> Unit = { actionClicked = true },
    ) {
        currentTheme = theme
        composeTestRule.setContent {
            UICAppTheme {
                SimpleTopAppBar(
                    headerText = "headerText",
                    navigationIcon = {
                        CoreIconButton(
                            onClick = { onNavigationClick() },
                            buttonSize = IconButtonSize.SMALL,
                            theme = AppBarTheme.PRIMARY,
                            showBadge = true,
                            iconVector = chevronLeft(),
                            contentDescription = stringResource(R.string.preview_navigation_icon),
                        )
                    },
                    theme = AppBarTheme.PRIMARY,
                )
            }
        }
    }

    @Before
    fun setup() {
        navigationClicked = false
        actionClicked = false
        setTestContent(theme = currentTheme)
    }

    @Test
    fun testPrimaryThemeAppBarDisplaysCorrectColors() {
        // Assert
        composeTestRule
            .onNodeWithTag("cm_toolbar")
            .assertExists()
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText("OK", ignoreCase = true)
            .assertExists()
            .assertIsDisplayed()
            .assertTextContains("OK")
    }

    @Test
    fun testNavigationIconClickCallback() {
        // Act
        composeTestRule
            .onNodeWithContentDescription("Navigate back")
            .performClick()

        // Assert
        assert(navigationClicked) { "Navigation click callback was not triggered" }
    }

    @Test
    fun testActionIconClickCallback() {
        // Act
        composeTestRule
            .onNodeWithContentDescription("More options")
            .performClick()

        // Assert
        assert(actionClicked) { "Action click callback was not triggered" }
    }

//    @Test
//    fun testSecondaryThemeAppBarDisplaysCorrectly() {
//        // Arrange
//        currentTheme = AppBarTheme.SECONDARY
//        setTestContent(theme = currentTheme)
//
//        // Assert
//        composeTestRule
//            .onNodeWithTag("cm_toolbar")
//            .assertExists()
//            .assertIsDisplayed()
//
//        composeTestRule
//            .onNodeWithContentDescription("Navigate back")
//            .assertExists()
//            .assertIsDisplayed()
//
//        composeTestRule
//            .onNodeWithContentDescription("More options")
//            .assertExists()
//            .assertIsDisplayed()
//    }
}
