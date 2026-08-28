package com.heb.centralmarket.uicart.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.buttons.UICLinkButtonStyle
import com.heb.centralmarket.uicart.component.buttons.UICLinkButton
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.icons.downArrow
import com.heb.centralmarket.uicart.icons.plusIcon
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

@Composable
fun LinkButtonScreen(
    navController: NavHostController,
    drawerState: DrawerState,
) {
    var isFillWidthEnabled by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            AppBar(
                title = R.string.link_button,
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
                }
            )
        },
    ) {
        CoreBackground {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(it)
                        .padding(horizontal = UICSpacing.spacing.spacing050),
                verticalArrangement = Arrangement.spacedBy(UICSpacing.spacing.spacing050),
                horizontalAlignment = Alignment.Start,
            ) {
                LinkButtons(isFillWidthEnabled)
            }
        }
    }
}

@Composable
fun LinkButtons(isFillMaxWidth: Boolean) {
    val buttonStyles = listOf(
        UICLinkButtonStyle.REGULAR_BOLD_ALL_CAPS,
        UICLinkButtonStyle.REGULAR_BOLD,
        UICLinkButtonStyle.REGULAR,
        UICLinkButtonStyle.COMPACT_BOLD_ALL_CAPS,
        UICLinkButtonStyle.COMPACT_BOLD,
        UICLinkButtonStyle.COMPACT
    )

    Column(verticalArrangement = Arrangement.spacedBy(UICSpacing.spacing.spacing025)) {
        buttonStyles.forEach { style ->
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(vertical = 20.dp)
            ) {

                Text(
                    text = variantTitle(style),
                    style = UICTypography.typography.caption.regular,
                    color = UICTheme.colorScheme.txt.primary,
                    modifier = Modifier.padding(top = 5.dp)
                )


                UICLinkButton(
                    onClick = { /* Handle click */ },
                    buttonText = stringResource(R.string.enabled),
                    leadingIcon = { Icon(imageVector = plusIcon(), contentDescription = "Add") },
                    trailingIcon = { Icon(imageVector = downArrow(), contentDescription = "Down") },
                    buttonStyle = style,
                    isButtonFullWidth = isFillMaxWidth,
                    enabled = true
                )
                UICLinkButton(
                    onClick = { /* Disabled, so won't trigger */ },
                    buttonText = stringResource(R.string.disabled),
                    leadingIcon = { Icon(imageVector = plusIcon(), contentDescription = "Add") },
                    trailingIcon = { Icon(imageVector = downArrow(), contentDescription = "Down") },
                    buttonStyle = style,
                    isButtonFullWidth = isFillMaxWidth,
                    enabled = false
                )
            }
        }
    }
}

@Composable
fun variantTitle(variant: UICLinkButtonStyle): String {
    return when (variant) {
        UICLinkButtonStyle.REGULAR_BOLD_ALL_CAPS -> "Compact = false, Bold = true, All Caps = true"
        UICLinkButtonStyle.REGULAR_BOLD -> "Compact = false, Bold = true, All Caps = false"
        UICLinkButtonStyle.REGULAR -> "Compact = false, Bold = false, All Caps = false"
        UICLinkButtonStyle.COMPACT_BOLD_ALL_CAPS -> "Compact = true, Bold = true, All Caps = true"
        UICLinkButtonStyle.COMPACT_BOLD -> "Compact = true, Bold = true, All Caps = false"
        UICLinkButtonStyle.COMPACT -> "Compact = true, Bold = false, All Caps = false"
    }
}
