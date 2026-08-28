/*
 * Created by Mahesh Mathew Paul on 12/12/24, 12:21 pm
 * mahesh.paul@ust.com
 * Last modified 12/12/24, 12:20 pm
 * Copyright (c) 2024.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.themesystem.UICCornerRadius
import com.heb.centralmarket.uicart.themesystem.UICSpacing
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

@Composable
fun StyleModifierScreen(drawerState: DrawerState) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            AppBar(
                title = R.string.style_modifiers,
                drawerState = drawerState,
            )
        },
    ) {
        CoreBackground {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(it)
                        .padding(UICSpacing.spacing.spacing100)
                        .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Style Modifiers
                Text(
                    text = stringResource(R.string.style_modifiers_margin),
                    style = UICTypography.typography.title.t2,
                    modifier = Modifier.padding(top = UICSpacing.spacing.spacing025),
                    color = UICTheme.colorScheme.txt.primary,
                )

                MarginModifierItem(
                    tokenName = "spacing_025",
                    value =
                        UICSpacing.spacing.spacing025.value
                            .toInt(),
                    color = Color(0x661400FE),
                )
                HorizontalDivider(modifier = Modifier.fillMaxWidth())

                MarginModifierItem(
                    tokenName = "spacing_050",
                    value =
                        UICSpacing.spacing.spacing050.value
                            .toInt(),
                    color = Color(0x6624FE00),
                )
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
                MarginModifierItem(
                    tokenName = "spacing_075",
                    value =
                        UICSpacing.spacing.spacing075.value
                            .toInt(),
                    color = Color(0x66FB38FF),
                )
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
                MarginModifierItem(
                    tokenName = "spacing_100",
                    value =
                        UICSpacing.spacing.spacing100.value
                            .toInt(),
                    color = Color(0x6600F0FF),
                )
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
                MarginModifierItem(
                    tokenName = "spacing_150",
                    value =
                        UICSpacing.spacing.spacing150.value
                            .toInt(),
                    color = Color(0x66FFB800),
                )
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
                MarginModifierItem(
                    tokenName = "spacing_200",
                    value =
                        UICSpacing.spacing.spacing200.value
                            .toInt(),
                    color = Color(0x66FF5C00),
                )

                // Padding Modifiers
                Text(
                    text = stringResource(R.string.style_modifiers_padding),
                    style = UICTypography.typography.title.t2,
                    modifier = Modifier.padding(top = UICSpacing.spacing.spacing150),
                    color = UICTheme.colorScheme.txt.primary,
                )

                PaddingModifierItem(
                    tokenName = "spacing_025",
                    value =
                        UICSpacing.spacing.spacing025.value
                            .toInt(),
                    color = Color(0x661400FE),
                )
                HorizontalDivider(modifier = Modifier.fillMaxWidth())

                PaddingModifierItem(
                    tokenName = "spacing_050",
                    value =
                        UICSpacing.spacing.spacing050.value
                            .toInt(),
                    color = Color(0x6624FE00),
                )
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
                PaddingModifierItem(
                    tokenName = "spacing_075",
                    value =
                        UICSpacing.spacing.spacing075.value
                            .toInt(),
                    color = Color(0x66FB38FF),
                )
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
                PaddingModifierItem(
                    tokenName = "spacing_100",
                    value =
                        UICSpacing.spacing.spacing100.value
                            .toInt(),
                    color = Color(0x6600F0FF),
                )
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
                PaddingModifierItem(
                    tokenName = "spacing_150",
                    value =
                        UICSpacing.spacing.spacing150.value
                            .toInt(),
                    color = Color(0x66FFB800),
                )
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
                PaddingModifierItem(
                    tokenName = "spacing_200",
                    value =
                        UICSpacing.spacing.spacing200.value
                            .toInt(),
                    color = Color(0x66FF5C00),
                )

                // Border Radius
                Text(
                    text = stringResource(R.string.style_modifiers_border),
                    style = UICTypography.typography.title.t2,
                    modifier = Modifier.padding(top = UICSpacing.spacing.spacing150),
                    color = UICTheme.colorScheme.txt.primary,
                )
                BorderRadiusItem(
                    tokenName = "sm",
                    value =
                        UICCornerRadius.borderRadius.small.value
                            .toInt(),
                    color = Color(0x660000FF),
                )
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
                BorderRadiusItem(
                    tokenName = "md",
                    value =
                        UICCornerRadius.borderRadius.medium.value
                            .toInt(),
                    color = Color(0x66FB38FF),
                )
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
                BorderRadiusItem(
                    tokenName = "lg",
                    value =
                        UICCornerRadius.borderRadius.large.value
                            .toInt(),
                    color = Color(0x66FF0000),
                )
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
                BorderRadiusItem(
                    tokenName = "full",
                    value =
                        UICCornerRadius.borderRadius.full.value
                            .toInt(),
                    color = Color(0x66FFB800),
                )
            }
        }
    }
}

@Composable
fun MarginModifierItem(
    tokenName: String,
    value: Int,
    color: Color,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(UICSpacing.spacing.spacing075),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
    ) {
        Text(
            text = tokenName,
            style = UICTypography.typography.subtitle.subTitle.regular,
            color = UICTheme.colorScheme.txt.primary,
        )
        Text(
            text = "${value}px",
            style = UICTypography.typography.subtitle.subTitle.regular,
            color = UICTheme.colorScheme.txt.primary,
        )
        Box {
            Spacer(
                modifier =
                    Modifier
                        .size(value.dp)
                        .background(color = color),
            )
        }
    }
}

@Composable
fun PaddingModifierItem(
    tokenName: String,
    value: Int,
    color: Color,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(UICSpacing.spacing.spacing050),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
    ) {
        Text(
            text = tokenName,
            style = UICTypography.typography.subtitle.subTitle.regular,
            color = UICTheme.colorScheme.txt.primary,
        )
        Text(
            text = "${value}px",
            style = UICTypography.typography.subtitle.subTitle.regular,
            color = UICTheme.colorScheme.txt.primary,
        )
        Box(
            modifier =
                Modifier
                    .size(70.dp)
                    .border(
                        width = 1.dp,
                        color = color,
                        shape = RoundedCornerShape(UICCornerRadius.borderRadius.small),
                    ).padding(value.dp),
            contentAlignment = Alignment.Center,
        ) {
            Spacer(
                modifier =
                    Modifier
                        .background(color = color)
                        .fillMaxSize(),
            )
        }
    }
}

@Composable
fun BorderRadiusItem(
    tokenName: String,
    value: Int,
    color: Color,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(UICSpacing.spacing.spacing050),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
    ) {
        Text(
            text = tokenName,
            style = UICTypography.typography.subtitle.subTitle.regular,
            color = UICTheme.colorScheme.txt.primary,
        )
        Text(
            text = "${value}px",
            style = UICTypography.typography.subtitle.subTitle.regular,
            color = UICTheme.colorScheme.txt.primary,
        )
        Box(
            modifier =
                Modifier
                    .size(60.dp)
                    .background(
                        color = color,
                        shape = RoundedCornerShape(value.dp),
                    ),
        ) {
            Spacer(
                modifier =
                    Modifier
                        .clip(RoundedCornerShape(value.dp)),
            )
        }
    }
}
