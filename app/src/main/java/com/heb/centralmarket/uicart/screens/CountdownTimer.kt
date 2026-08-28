/*
 * Created by Mahesh Mathew Paul on 12/03/25, 11:19 am
 * mahesh.paul@ust.com
 * Last modified 12/03/25, 11:19 am
 * Copyright (c) 2025.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.navigation.NavController
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.component.ppg.CountdownTimer
import com.heb.centralmarket.uicart.icons.chevronLeft
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography
import com.heb.centralmarket.uicart.utils.parseHexColor
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassportGermany(
    navController: NavController,
    drawerState: DrawerState,
) {
    Scaffold(
        topBar = {
            AppBar(
                title = R.string.countdown_timer,
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
            )
        },
    ) {
        var red by remember { mutableFloatStateOf(255f) }
        var green by remember { mutableFloatStateOf(255f) }
        var blue by remember { mutableFloatStateOf(255f) }
        var hexText by remember { mutableStateOf("#FFFFFF") }

        var bgRed by remember { mutableFloatStateOf(0f) }
        var bgGreen by remember { mutableFloatStateOf(0f) }
        var bgBlue by remember { mutableFloatStateOf(0f) }
        var bgHexText by remember { mutableStateOf("#000000") }

        val textColor = Color(red / 255f, green / 255f, blue / 255f)
        val backgroundColor = Color(bgRed / 255f, bgGreen / 255f, bgBlue / 255f)

        var selectedDate = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(10)

        var headingText by remember { mutableStateOf("Passport Germany 2025!") }
        var descriptionText by remember { mutableStateOf("Online & In-store from September XXth - XXth") }

        var useImage by remember { mutableStateOf(false) }

        CoreBackground {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(it)
                        .padding(
                            horizontal = UICSpacing.spacing.spacing050,
                            vertical = UICSpacing.spacing.spacing050,
                        ),
                verticalArrangement = Arrangement.spacedBy(UICSpacing.spacing.spacing025),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.countdown_timer),
                    modifier =
                        Modifier
                            .padding(top = UICSpacing.spacing.spacing025)
                            .align(Alignment.Start),
                    style = UICTypography.typography.caption.bold,
                    color = UICTheme.colorScheme.txt.primary,
                )

                CountdownTimer(
                    backgroundImageUrl = if (useImage) {
                        "https://picsum.photos/200/300"
                    } else {
                        ""
                    },
                    textColor = textColor,
                    backgroundColor = backgroundColor,
                    targetMillis = selectedDate,
                    heading = headingText,
                    description = descriptionText,
                    onClick = {
                    }
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(UICSpacing.spacing.spacing050)
                ) {
                    Checkbox(
                        checked = useImage,
                        onCheckedChange = { useImage = it }
                    )
                    Text(text = stringResource(R.string.use_background_image))
                }

                Spacer(modifier = Modifier.height(UICSpacing.spacing.spacing050))

                // Title input
                Text(stringResource(R.string.title), fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = headingText,
                    onValueChange = { headingText = it },
                    label = { Text(stringResource(R.string.enter_title)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.7f)
                )

                Spacer(modifier = Modifier.height(UICSpacing.spacing.spacing050))

                // Description input
                Text(stringResource(R.string.description), fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = descriptionText,
                    onValueChange = { descriptionText = it },
                    label = { Text(stringResource(R.string.enter_description)) },
                    singleLine = false,
                    modifier = Modifier.fillMaxWidth(0.7f)
                )

                Spacer(modifier = Modifier.height(UICSpacing.spacing.spacing050))
                Text(stringResource(R.string.text_color), fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = hexText,
                    onValueChange = {
                        hexText = it
                        it.parseHexColor()?.let { color ->
                            red = color.red * 255f
                            green = color.green * 255f
                            blue = color.blue * 255f
                        }
                    },
                    label = { Text(stringResource(R.string.hex_code)) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(capitalization = KeyboardCapitalization.Characters),
                    modifier = Modifier.fillMaxWidth(0.7f)
                )

                Spacer(modifier = Modifier.height(UICSpacing.spacing.spacing050))
                Text(stringResource(R.string.background_color), fontWeight = FontWeight.Bold)
                OutlinedTextField(
                    value = bgHexText,
                    onValueChange = {
                        bgHexText = it
                        it.parseHexColor()?.let { color ->
                            bgRed = color.red * 255f
                            bgGreen = color.green * 255f
                            bgBlue = color.blue * 255f
                        }
                    },
                    label = { Text(stringResource(R.string.hex_code)) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(capitalization = KeyboardCapitalization.Characters),
                    modifier = Modifier.fillMaxWidth(0.7f)
                )
            }
        }
    }
}
