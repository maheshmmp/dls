package com.heb.centralmarket.uicart.screens

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.CoreCaptionTextView
import com.heb.centralmarket.uicart.component.bottomButtonContainer.UICBottomButtonsContainer
import com.heb.centralmarket.uicart.component.buttons.CoreButtonSize
import com.heb.centralmarket.uicart.component.buttons.UICPrimaryButton
import com.heb.centralmarket.uicart.component.buttons.UICSecondaryButton
import com.heb.centralmarket.uicart.component.buttons.UICTertiaryButton
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography


@Composable
fun BottomButtonContainerScreen(
    navController: NavController,
    drawerState: DrawerState,
) {
    var isTransparentEnabled by remember { mutableStateOf(false) }
    // Scaffold provides structure with a top app bar and screen body.
    Scaffold(
        topBar = {
            // App bar with title and navigation (back) icon
            AppBar(
                title = R.string.bottom_button_container,
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
                    ChangerSwitchLayout(
                        displayText = stringResource(R.string.bottom_button_container_transparent),
                        onCheckedChange = { enabled ->
                            isTransparentEnabled = enabled
                        }
                    )
                    ThemeSwitcherAction()
                    DarkModeSwitcherAction()
                }
            )
        },
    ) {
        // Core Background wrapper that will include App theme styling
        CoreBackground {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(it)
                        .padding(horizontal = UICSpacing.spacing.spacing050),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = stringResource(R.string.bottom_button_container_primary),
                    modifier = Modifier
                        .padding(
                            top = UICSpacing.spacing.spacing100
                        )
                        .align(Alignment.Start),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )
                Spacer(modifier = Modifier.height(UICSpacing.spacing.spacing100))
                UICBottomButtonsContainer(
                    contentOrientation = Orientation.Horizontal,
                    primaryButton = {
                        UICPrimaryButton(
                            onClick = {},
                            buttonText = stringResource(com.heb.centralmarket.uicart.components.R.string.bottom_button_container_primary_button).uppercase(),
                            buttonSize = CoreButtonSize.MEDIUM,
                            modifier = Modifier.fillMaxWidth(),
                            isButtonFullWidth = true,
                        )
                    },
                    modifier = Modifier,
                    isComponentFilled = !isTransparentEnabled
                )
                Text(
                    text = stringResource(R.string.bottom_button_container_primary_secondary),
                    modifier = Modifier
                        .padding(
                            top = UICSpacing.spacing.spacing100
                        )
                        .align(Alignment.Start),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )
                Spacer(modifier = Modifier.height(UICSpacing.spacing.spacing100))
                UICBottomButtonsContainer(
                    contentOrientation = Orientation.Horizontal,
                    primaryButton = {
                        UICPrimaryButton(
                            onClick = {},
                            buttonText = stringResource(com.heb.centralmarket.uicart.components.R.string.bottom_button_container_primary_button).uppercase(),
                            buttonSize = CoreButtonSize.MEDIUM,
                            modifier = Modifier.fillMaxWidth(),
                            isButtonFullWidth = true,
                        )
                    },
                    secondaryButton = {
                        UICSecondaryButton(
                            onClick = {},
                            buttonText = stringResource(com.heb.centralmarket.uicart.components.R.string.bottom_button_container_secondary_button).uppercase(),
                            buttonSize = CoreButtonSize.MEDIUM,
                            modifier = Modifier.fillMaxWidth(),
                            isButtonFullWidth = true,
                        )
                    },
                    modifier = Modifier,
                    isComponentFilled = !isTransparentEnabled
                )
                Text(
                    text = stringResource(R.string.bottom_button_container_primary_tertiary_vertical),
                    modifier = Modifier
                        .padding(
                            top = UICSpacing.spacing.spacing100
                        )
                        .align(Alignment.Start),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )
                Spacer(modifier = Modifier.height(UICSpacing.spacing.spacing100))
                UICBottomButtonsContainer(
                    contentOrientation = Orientation.Vertical,
                    primaryButton = {
                        UICPrimaryButton(
                            onClick = {},
                            buttonText = stringResource(com.heb.centralmarket.uicart.components.R.string.bottom_button_container_primary_button).uppercase(),
                            buttonSize = CoreButtonSize.MEDIUM,
                            modifier = Modifier.fillMaxWidth(),
                            isButtonFullWidth = true,
                        )
                    },
                    secondaryButton = {
                        UICTertiaryButton(
                            onClick = {},
                            buttonText = stringResource(com.heb.centralmarket.uicart.components.R.string.bottom_button_container_secondary_button1).uppercase(),
                            buttonSize = CoreButtonSize.MEDIUM,
                            modifier = Modifier.fillMaxWidth(),
                            isButtonFullWidth = true,
                        )
                    },
                    modifier = Modifier,
                    isComponentFilled = !isTransparentEnabled
                )
                Text(
                    text = stringResource(R.string.bottom_button_container_all),
                    modifier = Modifier
                        .padding(
                            top = UICSpacing.spacing.spacing100
                        )
                        .align(Alignment.Start),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )
                Spacer(modifier = Modifier.height(UICSpacing.spacing.spacing100))
                UICBottomButtonsContainer(
                    contentOrientation = Orientation.Vertical,
                    primaryButton = {
                        UICPrimaryButton(
                            onClick = {},
                            buttonText = stringResource(com.heb.centralmarket.uicart.components.R.string.bottom_button_container_primary_button).uppercase(),
                            buttonSize = CoreButtonSize.MEDIUM,
                            modifier = Modifier.fillMaxWidth(),
                            isButtonFullWidth = true,
                        )
                    },
                    secondaryButton = {
                        UICTertiaryButton(
                            onClick = {},
                            buttonText = stringResource(com.heb.centralmarket.uicart.components.R.string.bottom_button_container_secondary_button1).uppercase(),
                            buttonSize = CoreButtonSize.MEDIUM,
                            modifier = Modifier.fillMaxWidth(),
                            isButtonFullWidth = true,
                        )
                    },
                    captionText = {
                        CoreCaptionTextView(
                            text = stringResource(com.heb.centralmarket.uicart.components.R.string.bottom_button_container_description),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                    },
                    modifier = Modifier,
                    isComponentFilled = !isTransparentEnabled
                )
                Text(
                    text = stringResource(R.string.bottom_button_container_primary_caption),
                    modifier = Modifier
                        .padding(
                            top = UICSpacing.spacing.spacing100
                        )
                        .align(Alignment.Start),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )
                Spacer(modifier = Modifier.height(UICSpacing.spacing.spacing100))
                UICBottomButtonsContainer(
                    contentOrientation = Orientation.Vertical,
                    primaryButton = {
                        UICPrimaryButton(
                            onClick = {},
                            buttonText = stringResource(com.heb.centralmarket.uicart.components.R.string.bottom_button_container_primary_button).uppercase(),
                            buttonSize = CoreButtonSize.MEDIUM,
                            modifier = Modifier.fillMaxWidth(),
                            isButtonFullWidth = true,
                        )
                    },
                    captionText = {
                        CoreCaptionTextView(
                            text = stringResource(com.heb.centralmarket.uicart.components.R.string.bottom_button_container_description),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                    },
                    modifier = Modifier,
                    isComponentFilled = !isTransparentEnabled
                )
            }
        }
    }
}
