package com.heb.centralmarket.uicart.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.buttons.IconButtonVariant
import com.heb.centralmarket.uicart.component.buttons.UICIconButton
import com.heb.centralmarket.uicart.component.buttons.UICIconButtonSize
import com.heb.centralmarket.uicart.icons.accountAvatar
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography
import kotlin.math.roundToInt

@Composable
fun UICIconButtonScreen(
    navController: NavHostController,
    drawerState: DrawerState,
) {
    var selectedBg by remember { mutableStateOf(IconButtonVariant.HEADER_LIGHT) }
    var selectedSize by remember { mutableStateOf(UICIconButtonSize.MEDIUM) }
    var showBadge by remember { mutableStateOf(true) }
    var isEnabled by remember { mutableStateOf(true) }
    var badgeCount by remember { mutableIntStateOf(9) }

    Scaffold(
        topBar = {
            AppBar(
                title = R.string.icon_buttons,
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
        CoreBackground(modifier = Modifier.background(color = Color.Transparent)) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(innerPadding)
                        .padding(horizontal = UICSpacing.spacing.spacing050),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(UICSpacing.spacing.spacing100))

                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .background(color = selectedBg.color.invoke()),
                    contentAlignment = Alignment.Center,
                ) {
                    UICIconButton(
                        onClick = { /* Handle click */ },
                        enabled = isEnabled,
                        badgeCount = badgeCount.toString(),
                        buttonSize = selectedSize,
                        variant = selectedBg,
                        showBadge = showBadge,
                        iconVector = accountAvatar(),
                    )
                }
                Spacer(modifier = Modifier.height(UICSpacing.spacing.spacing100))

                Text(
                    text = "Select Background Color:",
                    style = UICTypography.typography.heading.h4,
                    color = UICTheme.colorScheme.txt.primary,
                )

                BackgroundColorSelector(
                    selected = selectedBg,
                    onSelected = { selectedBg = it },
                )
                IconSizeSlider(value = selectedSize, onValueChange = { selectedSize = it })
                SwitchRow(
                    label = "Show Badge",
                    checked = showBadge,
                    onCheckedChange = { showBadge = it },
                )

                SwitchRow(
                    label = "Button Enabled",
                    checked = isEnabled,
                    onCheckedChange = { isEnabled = it },
                )

                BadgeCountLimit(badgeCount, onCharLimitChange = { badgeCount = it })
            }
        }
    }
}

@Composable
fun BackgroundColorSelector(
    selected: IconButtonVariant,
    onSelected: (IconButtonVariant) -> Unit,
) {
    Column {
        IconButtonVariant.entries.forEach { option ->
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clickable { onSelected(option) },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = selected == option,
                    onClick = { onSelected(option) },
                    colors =
                        RadioButtonDefaults.colors(
                            selectedColor = UICTheme.colorScheme.brand.primary.core,
                            unselectedColor = UICTheme.colorScheme.structural.outlineLight,
                        ),
                )
                Text(
                    text = stringResource(option.labelResId),
                    color = UICTheme.colorScheme.txt.primary,
                    modifier = Modifier.padding(start = 4.dp),
                )
            }
        }
    }
}

@Composable
fun BadgeCountLimit(
    charLimit: Int,
    onCharLimitChange: (Int) -> Unit,
) {
    Column(modifier = Modifier.padding(UICSpacing.spacing.spacing100)) {
        Text(
            stringResource(R.string.character_limit, charLimit),
            style =
                UICTypography.typography.body.body1.regular
                    .copy(color = UICTheme.colorScheme.txt.primary),
        )
        Slider(
            value = charLimit.toFloat(),
            onValueChange = { onCharLimitChange(it.toInt()) },
            valueRange = 1f..199f,
        )
    }
}

@Composable
fun IconSizeSlider(
    value: UICIconButtonSize,
    onValueChange: (UICIconButtonSize) -> Unit,
    modifier: Modifier = Modifier,
) {
    val sizes = UICIconButtonSize.entries.toTypedArray()
    val steps = sizes.size - 1
    val currentIndex = sizes.indexOf(value).coerceIn(0, steps)
    val haptics = LocalHapticFeedback.current

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Slider(
            value = currentIndex.toFloat(),
            onValueChange = { new ->
                val index = new.roundToInt().coerceIn(0, steps)
                val newSize = sizes[index]
                if (newSize != value) {
                    onValueChange(newSize)
                    haptics.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                }
            },
            valueRange = 0f..steps.toFloat(),
            steps = steps - 1,
            modifier = Modifier.fillMaxWidth(0.8f),
            colors =
                SliderDefaults.colors(
                    thumbColor = UICTheme.colorScheme.brand.primary.onCore,
                    activeTrackColor = UICTheme.colorScheme.brand.coupon.onCore,
                    inactiveTrackColor = UICTheme.colorScheme.structural.bgPrimary,
                ),
        )
        Row(
            modifier =
                Modifier
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text("S", color = UICTheme.colorScheme.txt.primary)
            Text("M", color = UICTheme.colorScheme.txt.primary)
            Text("L", color = UICTheme.colorScheme.txt.primary)
            Text("XL", color = UICTheme.colorScheme.txt.primary)
        }
    }
}

@Composable
fun SwitchRow(
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

@Preview(name = "medium Icon Button", group = "Icon Buttons")
@Composable
fun PreviewUICIconButtonScreen() {
    UICIconButtonScreen(
        NavHostController(LocalContext.current),
        rememberDrawerState(initialValue = DrawerValue.Closed),
    )
}
