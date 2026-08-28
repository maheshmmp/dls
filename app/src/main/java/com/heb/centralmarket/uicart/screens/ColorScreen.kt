/*
 * Created by Mahesh Mathew Paul on 12/12/24, 12:20 pm
 * mahesh.paul@ust.com
 * Last modified 12/12/24, 10:51 am
 * Copyright (c) 2024.
 * All rights reserved.
 */

package com.heb.centralmarket.uicart.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.heb.centralmarket.uicart.R
import com.heb.centralmarket.uicart.component.AppBar
import com.heb.centralmarket.uicart.component.CoreBackground
import com.heb.centralmarket.uicart.previews.AllScreenPreview
import com.heb.centralmarket.uicart.themesystem.UICAppTheme
import com.heb.centralmarket.uicart.themesystem.UICExtendedTheme
import com.heb.centralmarket.uicart.themesystem.UICTheme
import com.heb.centralmarket.uicart.themesystem.UICTypography

@Composable
fun ColorScreen(drawerState: DrawerState) {
    Scaffold(
        topBar = {
            AppBar(
                title = R.string.color_title,
                drawerState = drawerState,
            )
        },
    ) {
        CoreBackground {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                DisplayColorGrid()
            }
        }
    }
}

@Composable
fun DisplayColorGrid() {
    // Access colors from the MaterialTheme color scheme
    val colorScheme = UICTheme.colorScheme
    val extendedColorScheme = UICExtendedTheme.colorScheme
    val sections =
        listOf(
            stringResource(R.string.text_colors) to
                listOf(
                    colorScheme.txt.primary,
                    colorScheme.txt.primaryInverse,
                    colorScheme.txt.secondary,
                    colorScheme.txt.secondaryInverse,
                ),
            stringResource(R.string.primary_colors) to
                listOf(
                    colorScheme.brand.primary.core,
                    colorScheme.brand.primary.onCore,
                    colorScheme.brand.primary.hover,
                    colorScheme.brand.primary.focus,
                    colorScheme.brand.primary.bg,
                    colorScheme.brand.primary.disabled,
                ),
            stringResource(R.string.primary_button_colors) to
                listOf(
                    colorScheme.brand.buttonPrimary.core,
                    colorScheme.brand.buttonPrimary.hover,
                    colorScheme.brand.buttonPrimary.focus,
                    colorScheme.brand.buttonPrimary.onCore,
                    colorScheme.brand.buttonPrimary.bg,
                    colorScheme.brand.buttonPrimary.disabled,
                ),
            stringResource(R.string.primary_button_tonal_colors) to
                    listOf(
                        colorScheme.brand.buttonPrimaryTonal.core,
                        colorScheme.brand.buttonPrimaryTonal.hover,
                        colorScheme.brand.buttonPrimaryTonal.onCore,
                        colorScheme.brand.buttonPrimaryTonal.border,
                    ),
            stringResource(R.string.interactive_colors) to
                listOf(
                    colorScheme.brand.interactive.core,
                    colorScheme.brand.interactive.onCore,
                    colorScheme.brand.interactive.press,
                    colorScheme.brand.interactive.focus,
                    colorScheme.brand.interactive.bg,
                    colorScheme.brand.interactive.disabled,
                ),
            stringResource(R.string.incrementer_colors) to
                listOf(
                    colorScheme.brand.incrementer.core,
                    colorScheme.brand.incrementer.onCore,
                    colorScheme.brand.incrementer.coreDisabled,
                    colorScheme.brand.incrementer.onCoreDisabled,
                ),
            stringResource(R.string.coupon_colors) to
                listOf(
                    colorScheme.brand.coupon.core,
                    colorScheme.brand.coupon.onCore,
                ),
            stringResource(R.string.secondary_colors) to
                listOf(
                    colorScheme.brandSecondary.core,
                    colorScheme.brandSecondary.onCore,
                    colorScheme.brandSecondary.hover,
                    colorScheme.brandSecondary.focus,
                    colorScheme.brandSecondary.coreBg,
                    colorScheme.brandSecondary.coreDisabled,
                    colorScheme.brandSecondary.icon,
                ),
            stringResource(R.string.interactive_neutral_colors) to
                listOf(
                    colorScheme.interactiveGray.core,
                    colorScheme.interactiveGray.onCore,
                ),
            stringResource(R.string.positive_colors) to
                listOf(
                    colorScheme.positive.core,
                    colorScheme.positive.onCore,
                    colorScheme.positive.bg,
                ),
            stringResource(R.string.negative_colors) to
                listOf(
                    colorScheme.negative.core,
                    colorScheme.negative.onCore,
                    colorScheme.negative.bg,
                ),
            stringResource(R.string.warning_colors) to
                listOf(
                    colorScheme.warning.core,
                    colorScheme.warning.onCore,
                    colorScheme.warning.bg,
                ),
            stringResource(R.string.info_colors) to
                listOf(
                    colorScheme.info.core,
                    colorScheme.info.onCore,
                    colorScheme.info.bg,
                ),
            stringResource(R.string.structural_colors) to
                listOf(
                    colorScheme.structural.bgPrimary,
                    colorScheme.structural.bgSecondary,
                    colorScheme.structural.outlineLight,
                    colorScheme.structural.outlineDark,
                ),
            stringResource(R.string.neutral_colors) to
                listOf(
                    colorScheme.neutral.white,
                    colorScheme.neutral.gray4,
                    colorScheme.neutral.gray3,
                    colorScheme.neutral.gray2,
                    colorScheme.neutral.gray1,
                    colorScheme.neutral.black,
                    colorScheme.neutral.disabled,
                ),
            stringResource(R.string.overlay_colors) to
                listOf(
                    colorScheme.overlay.dark40,
                ),
            stringResource(R.string.extended_cm_colors) to
                listOf(
                    extendedColorScheme.berry,
                    extendedColorScheme.berryHighlight,
                    extendedColorScheme.berryTint,
                    extendedColorScheme.bluefin,
                    extendedColorScheme.berryHighlight,
                    extendedColorScheme.bluefinTint,
                    extendedColorScheme.greenHighlight,
                    extendedColorScheme.mustard,
                    extendedColorScheme.malbec,
                    extendedColorScheme.malbecHighlight,
                    extendedColorScheme.mustardHighlight,
                    extendedColorScheme.oldGreen,
                    extendedColorScheme.olive,
                    extendedColorScheme.oliveHighlight,
                    extendedColorScheme.ribEye,
                    extendedColorScheme.ribEyeHighlight,
                    extendedColorScheme.warmWhite,
                    extendedColorScheme.systemBlack,
                ),
            stringResource(R.string.extended_jv_colors) to
                listOf(
                    extendedColorScheme.blue,
                    extendedColorScheme.blueHighlight,
                    extendedColorScheme.green,
                    extendedColorScheme.orange,
                    extendedColorScheme.orangeHighlight,
                    extendedColorScheme.red,
                ),
        )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp),
    ) {
        sections.forEach { (sectionName, colors) ->
            item {
                SectionHeader(sectionName)
                LazyRow(
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                ) {
                    items(colors.size) { index ->
                        ColorBox(colors[index])
                    }
                }
            }
        }
    }
}

@Composable
fun SectionHeader(sectionName: String) {
    Text(
        text = sectionName,
        style = UICTypography.typography.heading.h6,
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        color = UICTheme.colorScheme.txt.primary,
    )
}

@Composable
fun ColorBox(color: Color) {
    Box(
        modifier =
            Modifier
                .width(86.dp)
                .height(86.dp)
                .aspectRatio(1f)
                .background(color = color)
                .padding(0.dp),
        contentAlignment = Alignment.BottomCenter,
    ) {
        val colorHex =
            "#" + Integer.toHexString(color.toArgb()).uppercase().padStart(8, '0')
        Text(
            text = colorHex,
            modifier = Modifier.padding(4.dp),
            color = UICTheme.colorScheme.txt.primary,
        )
    }
}

@AllScreenPreview
@Composable
fun ColorScreenPreview() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    UICAppTheme {
        ColorScreen(drawerState)
    }
}
