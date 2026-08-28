package com.heb.centralmarket.uicart.screens

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBarTheme
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.UICHeaderFulfilment
import com.heb.centralmarket.uicart.component.buttons.CoreIconButton
import com.heb.centralmarket.uicart.component.buttons.IconButtonSize
import com.heb.centralmarket.uicart.icons.backArrow
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICSystemThemePreference
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICThemeSystem
import com.heb.centralmarket.uicart.themesystem.UICTypography
import com.heb.centralmarket.uicart.themesystem.UICartThemeSystem
import com.heb.centralmarket.uicart.themesystem.next
import com.heb.centralmarket.uicart.themesystem.resolve

/**
 * Composable screen that displays an AppBar with a toggleable segmented control and theme switchers.
 *
 * @param navController The navigation controller for handling navigation events.
 */
@Composable
fun HeaderFulfilmentToggleScreen(
    navController: NavController
) {
    val view = LocalView.current
    val greenColor = UICTheme.colorScheme.brand.primary.core
    var isEnabled by remember { mutableStateOf(true) }

    val selectedTab1 = remember { mutableIntStateOf(0) }
    // Configurable tab count
    var tabCount by remember { mutableIntStateOf(3) }
    val tabs = when (tabCount) {
        2 -> listOf("In-store", "Pickup")
        else -> listOf("In-store", "Pickup", "Delivery")
    }

    // Set status bar color using the modern approach
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            window?.statusBarColor = greenColor.toArgb()
            window?.let {
                WindowCompat.getInsetsController(it, view).isAppearanceLightStatusBars = false
            }
        }
    }

    @Composable
    fun ContentView(paddingValues: PaddingValues) {
        CoreBackground {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues = paddingValues),
                verticalArrangement = Arrangement.spacedBy(space = UICSpacing.spacing.spacing025),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Your other content goes here
                Text(
                    "Selected Tab : " + tabs[selectedTab1.intValue],
                    modifier = Modifier.padding(top = 36.dp),
                    color = UICTheme.colorScheme.txt.primary,
                    style = UICTypography.typography.body.body1.bold,
                )

                Spacer(modifier = Modifier.height(32.dp))
                Text("Select Number of tabs")
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    horizontalArrangement = Arrangement.Center,

                    ) {
                    TabRow(selectedTabIndex = if (tabCount == 2) 0 else 1) {
                        Tab(
                            selected = tabCount == 2,
                            onClick = {
                                selectedTab1.intValue =
                                    0 // Reset selected tab when changing tab count
                                tabCount = 2
                            },
                            text = { Text("2 Tabs") }
                        )
                        Tab(
                            selected = tabCount == 3,
                            onClick = {
                                selectedTab1.intValue =
                                    0 // Reset selected tab when changing tab count
                                tabCount = 3
                            },
                            text = { Text("3 Tabs") }
                        )
                    }
                }

                SwitchRows(
                    label = "Enable Toggle",
                    checked = isEnabled,
                    onCheckedChange = { isEnabled = it },
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text("Select theme")
                Row {
                    ThemesSwitcherAction()
                    DarkModeSwitcherActions()
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        androidx.compose.material3.Scaffold(
            topBar = {
                UICHeaderFulfilment(
                    headerText = stringResource(R.string.ways_to_shop),
                    items = tabs,
                    selectedIndex = selectedTab1.intValue,
                    onItemSelected = { selectedTab1.intValue = it },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = isEnabled,
                    navigationIcon = {
                        CoreIconButton(
                            onClick = { navController.popBackStack() },
                            buttonSize = IconButtonSize.SMALL,
                            theme = AppBarTheme.PRIMARY,
                            showBadge = true,
                            iconVector = backArrow(),
                            contentDescription = stringResource(id = com.heb.centralmarket.uicart.components.R.string.preview_navigation_icon),
                        )
                    }
                )
            }
        ) { paddingValues ->
            ContentView(paddingValues)
        }
    }
}

/**
 * Composable for switching between different theme systems.
 *
 * Cycles through CentralMarket, JoeVs, and MiTienda themes on each click.
 */
@Composable
fun ThemesSwitcherAction() {
    val themeOptions = listOf(
        UICThemeSystem.CentralMarket,
        UICThemeSystem.JoeVs,
        UICThemeSystem.MiTienda,
    )
    val currentTheme by UICartThemeSystem.themeUpdates.collectAsState()

    IconButton(
        modifier = Modifier.padding(top = 24.dp),
        onClick = {
            val currentIndex = themeOptions.indexOf(currentTheme)
            val nextIndex = (currentIndex + 1) % themeOptions.size
            UICartThemeSystem.setThemeSystem(themeOptions[nextIndex])
        }
    ) {
        Icon(
            painter = painterResource(
                id = when (currentTheme) {
                    UICThemeSystem.CentralMarket -> R.drawable.ic_cm_appbar
                    UICThemeSystem.JoeVs -> R.drawable.ic_jv_appbar
                    UICThemeSystem.MiTienda -> R.drawable.ic_mi_tienda_appbar
                }
            ),
            modifier = Modifier.padding(all = UICSpacing.spacing.spacing050),
            contentDescription = "Switch Theme",
            tint = UICTheme.colorScheme.txt.primary,
        )
    }
}

/**
 * Composable for toggling dark mode on and off.
 */
@Composable
fun DarkModeSwitcherActions() {
    val currentDarkMode by UICartThemeSystem.darkModeUpdates.collectAsState()
    val systemDark = isSystemInDarkTheme()
    val iconRes =
        when (currentDarkMode) {
            UICSystemThemePreference.Dark -> R.drawable.ic_light_mode
            UICSystemThemePreference.Light,
            UICSystemThemePreference.System,
                -> R.drawable.ic_dark_mode
        }

    IconButton(
        modifier = Modifier.padding(top = 24.dp),
        onClick = {
            UICartThemeSystem.setDarkMode(currentDarkMode.next())
        }) {
        Icon(
            painter =
                painterResource(
                    id = iconRes,
                ),
            contentDescription = "Cycle dark mode: ${currentDarkMode.name} (${
                if (currentDarkMode.resolve(
                        systemDark
                    )
                ) "dark" else "light"
            })",
            tint = UICTheme.colorScheme.txt.primary,
        )
    }
}

/**
 * Composable row with a label and a switch, for toggling a boolean value.
 *
 * @param label The label to display next to the switch.
 * @param checked The current checked state of the switch.
 * @param onCheckedChange Callback when the checked state changes.
 */
@Composable
fun SwitchRows(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            color = UICTheme.colorScheme.txt.primary,
            style = UICTypography.typography.body.body1.regular,
        )

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors =
                SwitchDefaults.colors(
                    checkedThumbColor = UICTheme.colorScheme.brand.primary.core,
                    checkedTrackColor = UICTheme.colorScheme.brand.primary.bg,
                    uncheckedThumbColor = UICTheme.colorScheme.structural.outlineLight,
                    uncheckedTrackColor = UICTheme.colorScheme.structural.bgSecondary,
                ),
        )
    }
}